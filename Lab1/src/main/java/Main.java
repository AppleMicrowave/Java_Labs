public class Main {

    public static void main(String[] args) {
        try {
            FileManager manager = new FileManager();
            manager.parseArgs(args);
            manager.readContents();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}