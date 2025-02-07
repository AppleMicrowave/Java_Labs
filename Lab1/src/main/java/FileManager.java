import java.io.*;
import java.util.*;

public class FileManager {
    private boolean flag_o;
    private boolean flag_p;
    private boolean flag_a;
    private boolean flag_s;
    private boolean flag_f;

    private boolean wasInt = false;
    private boolean wasFlt = false;
    private boolean wasStr = false;

    // На заметку - если при создании объекта Writer нет файла по указанному пути,
    // он сам создаёт этот файл.
    private Writer intWriter = null;
    private Writer fltWriter = null;
    private Writer strWriter = null;

    private final HashMap<String, String> destination_files = new HashMap<>();
    private final ArrayList<String> source_files = new ArrayList<>();

    private String prefix = "";
    private String path = "";

    public void print() {
        for (String file : source_files) {
            System.out.print(file + " ");
        }
    }

    public void readFile(String file_name) {
        try (FileReader fr = new FileReader(file_name);
             Scanner scan = new Scanner(fr)) {
            // На заметку - try-with-resources автоматически вызывает close() для FileWriter!

            String line;

            while (scan.hasNextLine()) {
                line = scan.nextLine();
                switch (typeParse(line)) {
                    case ("int"):
                        if (!wasInt) {
                            intWriter = new FileWriter(destination_files.get("int"), flag_a);
                            wasInt = true;
                        }
                        intWriter.write(line + "\n");
                        break;
                    case ("float"):
                        if (!wasFlt) {
                            fltWriter = new FileWriter(destination_files.get("float"), flag_a);
                            wasFlt = true;
                        }
                        fltWriter.write(line + "\n");
                        break;
                    case ("string"):
                        if (!wasStr) {
                            strWriter = new FileWriter(destination_files.get("string"), flag_a);
                            wasStr = true;
                        }
                        strWriter.write(line + "\n");
                        break;
                }
            }

            if (wasInt) intWriter.flush();
            if (wasFlt) fltWriter.flush();
            if (wasStr) strWriter.flush();

        } catch (FileNotFoundException e) {
            System.out.println("Error! " + file_name + " doesn't exist!");
        } catch (IOException e) {
            System.out.println("Error occurred while writing file: " + file_name);
        }

    }

    public String typeParse(String line) {
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
            switch (args[i]) {
                case "-o" -> {
                    //Check for repeating
                    if (flag_o) throw new IllegalArgumentException("Error! Flag '-o' was occurred several times.");
                    else flag_o = true;

                    //Check for out of range
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException("Error! Expected path, but got mess.");
                    }

                    path = args[i + 1];

                    // Check format
                    if (!path.startsWith("/")) {
                        throw new IllegalArgumentException("Error! Incorrect path.");
                    }

                    File directory = new File(path);
                    if (!directory.isAbsolute()) {
                        directory = directory.getAbsoluteFile();
                    }

                    //Check if dir does exist
                    if (!directory.exists() || !directory.isDirectory()) {
                        throw new IllegalArgumentException("Error! Directory doesn't exist.");
                    }

                    i++;
                }
                case "-p" -> {
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

                    i++;
                }
                case "-a" -> flag_a = true;
                case null, default -> source_files.add(args[i]);
            }
        }
        destination_files.put("int", path + prefix + "int.txt");
        destination_files.put("float", path + prefix + "float.txt");
        destination_files.put("string", path + prefix + "string.txt");
    }
}
