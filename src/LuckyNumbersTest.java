import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class LuckyNumbersTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void printMainMenuUI(){
        ByteArrayInputStream inputStream = new ByteArrayInputStream("l\n".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);

        LuckyNumbers luckyNumbers = new LuckyNumbers(inputStream, ps);

        String outputText = byteArrayOutputStream.toString();
        ArrayList<Integer> list = new ArrayList<>();
        String key = "\nDeine Tippreihe für Lotto (6 aus 49) lautet:" +
                     "\n" + "[(\\d+)?(,\b)?]";
        // String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();

        assertEquals(outputText, key);
    }




}