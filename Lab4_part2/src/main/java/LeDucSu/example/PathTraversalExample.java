package LeDucSu.example;

import java.io.*;
import java.util.logging.Logger;

public class PathTraversalExample {
    public static void main(String[] args) throws IOException {
        final Logger logger = Logger.getLogger(Main.class.getName());
        String userInput = "../secret.txt";
        File file = new File(userInput);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            logger.info("Reading file: " + file.getPath());
            reader.close();
        }
    }
}
