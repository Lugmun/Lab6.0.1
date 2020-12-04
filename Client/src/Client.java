import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    private Scanner scanner = new Scanner(System.in);
    private byte[] buffer = new byte[64000];
    private int port;
    private String add;
    private String exit = "exit";

    public Client(){
        System.out.println("Введите адресс сервера");
        String add = "";

        try {
            while (add.equals("")) {
                add = scanner.nextLine();
                System.out.println("Адрес сервера: " + add);
            }

            System.out.println("Введите порт");
            int port = 0;

            while (port == 0) {
                try {
                    int a = Integer.parseInt(scanner.nextLine().trim());
                    if (a < 1025 || a > 65535) {
                        System.out.println("Был введён неправильный порт");
                    } else {
                        this.port = a;
                        System.out.println("Порт: " + port);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Введённое значение не числовое");
                }
            }


            System.out.println("Можете начать работать");

        }catch (NoSuchElementException e){
            System.out.println("End of input");
            System.exit(0);
        }
    }

    public void establishConnection() {
            try (Socket socket = new Socket(add, port)) {
                while (true) {
                    System.out.println("Клиент запущен");


                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
