import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class AppTests {

    @Test
    void test_type_parsing() {
        FileManager fm = new FileManager();

        // Int parse
        Assertions.assertEquals("int", fm.typeParse("123"));
        Assertions.assertNotEquals("int", fm.typeParse("1.0"));
        Assertions.assertNotEquals("int", fm.typeParse("12.3"));
        Assertions.assertNotEquals("int", fm.typeParse("123a"));

        // Float parse
        Assertions.assertEquals("float", fm.typeParse("12.3"));
        Assertions.assertEquals("float", fm.typeParse("12.57"));
        Assertions.assertEquals("float", fm.typeParse("1.0"));
        Assertions.assertNotEquals("float", fm.typeParse("123.9a"));
        Assertions.assertNotEquals("float", fm.typeParse("1.o"));
        Assertions.assertNotEquals("float", fm.typeParse("1..0"));

        // String parse
        Assertions.assertEquals("string", fm.typeParse("some_string"));
        Assertions.assertEquals("string", fm.typeParse(""));
        Assertions.assertEquals("string", fm.typeParse(" "));
        Assertions.assertEquals("string", fm.typeParse("a1"));
        Assertions.assertEquals("string", fm.typeParse("../?77"));
        Assertions.assertEquals("string", fm.typeParse("1..15"));
        Assertions.assertNotEquals("string", fm.typeParse("1.15"));
    }

    @Test
    void test_file_reading() {
//        FileManager fm = new FileManager();
//        String[] args = new String[] {"a.txt", "b.txt", "c.tx"};
//        fm.readContents();
    }

    @Test
    void test_statistics() {
        FileManager fm = new FileManager();

        try {
            File temp1 = File.createTempFile("test1", ".txt");
            File temp2 = File.createTempFile("test2", ".txt");

            Writer writer1 = new FileWriter(temp1.getName());
            Writer writer2 = new FileWriter(temp2.getName());

            writer1.write("52\n" +
                    "venom\n" +
                    "14.88\n");
            writer2.write("123\n" +
                    "abo123\n");

            writer1.flush();
            writer2.flush();

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            PrintStream og = System.out;

            {
                String[] args = new String[] {temp1.getName(), temp2.getName()};
                System.setOut(new PrintStream(output));

                fm.readContents();
                fm.printStatistics();

                String result = "\\ \\ \\ FULL statistics \\ \\ \\ \n" +
                        "\n" +
                        "\\ Integers \\\n" +
                        "Count: 1\n" +
                        "Min: 123\n" +
                        "Max: 123\n" +
                        "Sum: 123\n" +
                        "Mean: 123.0\n" +
                        "\n" +
                        "\\ String \\\n" +
                        "Count: 1\n" +
                        "Min length: 6\n" +
                        "Max length: 6";

                Assertions.assertEquals(output.toString(), result);
            }
        } catch (IOException e) {
            System.out.println("Error! Cannot create temporary file.");
        }

    }
}
