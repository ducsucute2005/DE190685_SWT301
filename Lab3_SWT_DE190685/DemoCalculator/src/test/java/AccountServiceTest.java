import LeDucSu.example.AccountService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    private AccountService accountService;
    private static PrintWriter resultWriter;
    private static int testCaseNumber = 0;
    private static int passedCount = 0;
    private static int failedCount = 0;

    @BeforeAll
    static void setupClass() throws IOException {
        resultWriter = new PrintWriter(new FileWriter("UnitTest-Result.txt"));
        resultWriter.println("=" .repeat(80));
        resultWriter.println("UNIT TEST REPORT - ACCOUNT REGISTRATION");
        resultWriter.println("Function: registerAccount(username, password, email)");
        resultWriter.println("Time: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        resultWriter.println("=" .repeat(80));
        resultWriter.println();

        // Header của bảng
        resultWriter.printf("%-10s | %-12s | %-12s | %-25s | %-8s | %-8s | %-6s%n",
                "UTCID", "Username", "Password", "Email", "Expected", "Actual", "Status");
        resultWriter.println("-".repeat(80));
    }

    @BeforeEach
    void setup() {
        accountService = new AccountService();
    }

    @AfterAll
    static void tearDownClass() {
        resultWriter.println("-".repeat(80));
        resultWriter.println();
        resultWriter.println("TEST SUMMARY:");
        resultWriter.printf("  Total Test Cases: %d%n", testCaseNumber);
        resultWriter.printf("  Passed: %d (%.2f%%)%n", passedCount,
                (passedCount * 100.0 / testCaseNumber));
        resultWriter.printf("  Failed: %d (%.2f%%)%n", failedCount,
                (failedCount * 100.0 / testCaseNumber));
        resultWriter.println();
        resultWriter.println("=" .repeat(80));
        resultWriter.close();
    }

    /**
     * CHỈ 1 TEST DUY NHẤT - Test registerAccount với dữ liệu từ CSV
     */
    @ParameterizedTest(name = "Test Case {index}: username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("Test Register Account from CSV")
    void testRegisterAccount(String username, String password, String email, boolean expected) {
        testCaseNumber++;

        // Thực thi test
        boolean actual = accountService.registerAccount(username, password, email);

        // Kiểm tra kết quả
        boolean isPassed = (expected == actual);
        String status = isPassed ? "PASS" : "FAIL";

        if (isPassed) {
            passedCount++;
        } else {
            failedCount++;
        }

        // Ghi kết quả vào file
        String usernameDisplay = (username == null || username.isEmpty()) ? "(empty)" : username;
        resultWriter.printf("UTCID%02d    | %-12s | %-12s | %-25s | %-8s | %-8s | %-6s%n",
                testCaseNumber,
                usernameDisplay,
                password,
                email,
                expected,
                actual,
                status);
        resultWriter.flush();

        // Assert
        assertEquals(expected, actual,
                String.format("Test failed for: username=%s, password=%s, email=%s",
                        username, password, email));
    }
}