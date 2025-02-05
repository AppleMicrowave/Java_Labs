import java.io.*;
import java.util.*;

public class FileManager {
    private boolean flag_o;
    private boolean flag_p;
    private boolean flag_a;
    private boolean flag_s;
    private boolean flag_f;

    private ArrayList<String> files = new ArrayList<>();

    FileManager(String[] data) {
        Collections.addAll(files, data);
    }

    public void readFile(String file_name) {
        try (FileReader fr = new FileReader(file_name);
             Scanner scan = new Scanner(fr)) {

            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("Error! " + file_name + " doesn't exist!");
        } catch (IOException e) {
            System.out.println("Error occurred while reading file: " + file_name);
        }
    }

    public void readContents() {
        for (String name : files) {
            readFile(name);
        }
    }

    public static void main(String[] args) {
        FileManager fm = new FileManager(new String[]{"file1.txt", "file2.txt"});
        fm.readContents();
    }
}
