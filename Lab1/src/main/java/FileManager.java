import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class FileManager {
    private boolean flag_o;
    private boolean flag_p;
    private boolean flag_a;
    private boolean flag_s;
    private boolean flag_f;

    private HashMap<String, String> destination_files = new HashMap<String, String>()
    {{
        put("int", "int.txt");
        put("string", "string.txt");
        put("float", "float.txt");
    }};
    private ArrayList<String> source_files;

    private String prefix;
    private String path;

    FileManager(String[] data) {
        source_files = new ArrayList<String>();
    }

    public void print() {
        for (String file : source_files) {
            System.out.print(file + " ");
        }
    }

    public void readFile(String file_name) {
        try (FileReader fr = new FileReader(file_name);
             Scanner scan = new Scanner(fr)) {

            // На заметку - try-with-resources автоматически вызывает close() для FileWriter!

            Writer intWriter = new FileWriter(destination_files.get("int"), true);
            Writer fltWriter = new FileWriter(destination_files.get("float"), true);
            Writer strWriter = new FileWriter(destination_files.get("string"), true);

            String line;

            while (scan.hasNextLine()) {
                line = scan.nextLine();
                switch (typeParse(line)) {
                    case ("int"):
                        intWriter.write(line + "\n");
                        break;
                    case ("float"):
                        fltWriter.write(line + "\n");
                        break;
                    case ("string"):
                        strWriter.write(line + "\n");
                        break;
                }
            }

            intWriter.flush();
            fltWriter.flush();
            strWriter.flush();

        } catch (FileNotFoundException e) {
            System.out.println("Error! " + file_name + " doesn't exist!");
        } catch (IOException e) {
            System.out.println("Error occurred while writing file: " + file_name);
        }

    }

    public String typeParse(String line) {
        boolean isInt = false;
        boolean isFloat = false;
        String result = "null";

        // Check if float
        try {
            if (line.matches("[0-9]*\\.[0-9]+")) result = "float";
            else if (line.matches("[+-]?[0-9]+")) result = "int";
            else result = "string";
        } catch(NumberFormatException e) {
            System.out.println("Error! Invalid type");
        }
        return result;
    }

    public void readContents() {
        for (String name : source_files) {
            readFile(name);
        }
    }

    public void parseArgs(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            if ("-o".equals(args[i])) {
                //Check for repeating
                if (flag_o) throw new IllegalArgumentException("Error! Flag '-o' was occurred several times.");
                else flag_o = true;

                //Check for out of range
                if (i + 1 >= args.length) {
                    throw new IllegalArgumentException("Error! Expected path, but got mess.");
                }

                path = args[i + 1];

                //Check format
                if (!path.startsWith("/")) {
                    throw new IllegalArgumentException("Error! Incorrect path.");
                }

                File directory = new File(path);
//                if (!directory.isAbsolute()) {
//                    directory = directory.getAbsoluteFile();
//                }

                //Check if dir does exist
                if (directory.isDirectory()) {
                    throw new IllegalArgumentException("Error! Directory doesn't exist.");
                }
                //TODO: добавить ко всем source_files пусть

                i++;
            }
            else if ("-p".equals(args[i])) {
                //Check for repeating
                if (flag_p) throw new IllegalArgumentException("Error! Flag '-p' was occurred several times.");
                else flag_p = true;

                //Check for out of range
                if (i + 1 >= args.length) {
                    throw new IllegalArgumentException("Error! Expected path, but got mess.");
                }

                prefix = args[i + 1];

                if (!prefix.matches("^[^./\\\\:*?\"<>|]+$")) {
                    throw new IllegalArgumentException("Error! Invalid prefix.");
                }
                //TODO: добавить ко всем source_files пусть

                i++;
            }
            else {
                source_files.add(args[i]);
            }
        }
    }
}
