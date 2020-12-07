import other.ClientTerminal;
import other.Respond;
import other.Serializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    private Scanner scanner = new Scanner(System.in);
    private byte[] buffer = new byte[64000];
    private int port;
    private String add = "";
    //private String exit = "exit";

    public Client(){
        System.out.println("Введите адресс сервера");

        try {
            while (this.add.equals("")) {
                this.add = scanner.nextLine();
                System.out.println("Адрес сервера: " + this.add);
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
                        System.out.println("Порт: " + this.port);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Введённое значение не числовое");
                }
            }

        }catch (NoSuchElementException e){
            System.out.println("Конец ввода");
            System.exit(0);
        }
    }

    public void start() {
        SocketAddress a =
                new InetSocketAddress(this.add, this.port);
            try {
                while (true) {
                    SocketChannel socketChannel = SocketChannel.open();
                    boolean isConnected = socketChannel.connect(a);
                    if (isConnected) {
                        System.out.println("connected");
                        ClientTerminal terminal = new ClientTerminal();

                        ByteBuffer forWrite = ByteBuffer.allocate(1024*1024);
                        forWrite = ByteBuffer.wrap(Serializer.serialize(terminal.start()));
                        forWrite.flip();

                        socketChannel.socket().getOutputStream().write(forWrite.array());
                        socketChannel.socket().getOutputStream().flush();

                        System.out.println(forWrite.clear());

                        ByteBuffer forRead = ByteBuffer.allocate(1024*3);

                        System.out.println(socketChannel.read(forRead));
                        forRead.flip();
                        Object resp = Serializer.deserialize(forRead.array());
                        Respond respond = (Respond) resp;
                        System.out.println("принял ответ, вывожу");
                        System.out.println(respond.getMessage());
                        socketChannel.close();
                    }
                    else {
                        System.out.println("not connected");
                        System.exit(0);
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
