package LeDucSu.example;

import java.util.regex.Pattern;

public class AccountService {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Đăng ký tài khoản
     * @param username tên đăng nhập (không được rỗng)
     * @param password mật khẩu (phải > 6 ký tự)
     * @param email email (phải hợp lệ)
     * @return true nếu đăng ký thành công, false nếu thất bại
     */
    public boolean registerAccount(String username, String password, String email) {
        // Kiểm tra username không rỗng
        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        // Kiểm tra password > 6 ký tự
        if (password == null || password.length() <= 6) {
            return false;
        }

        // Kiểm tra email hợp lệ
        if (!isValidEmail(email)) {
            return false;
        }

        return true;
    }

    /**
     * Kiểm tra email có hợp lệ không
     */
    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}