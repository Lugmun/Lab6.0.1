import ServerPackage.Server;
import other.FileConverter;

public class Main {
    public static void main(String[] args) {

        FileConverter fc = new FileConverter();

        try {
            if (!args[0].trim().isEmpty()) {
                fc.fileReaderLineByLine(args[0].trim());
                //Terminal t = new Terminal(fc.hashtable);
                //t.start();
                Server s = new Server(fc.hashtable);
                s.establishConnection();
            } else {
                System.out.println("Путь не найден");
            }
        } catch (Exception e) {
            if (e instanceof ArrayIndexOutOfBoundsException) {
                System.out.println("Не был передан файл в качестве: аргумент командной строки");
            } else {
                e.printStackTrace();
            }
        }
    }
}
