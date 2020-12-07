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


            //System.out.println("Можете начать работать");

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
                    //System.out.println("Клиент запущен");


                    SocketChannel socketChannel = SocketChannel.open();
                    //socketChannel.configureBlocking(false);
                    boolean isConnected = socketChannel.connect(a);
                    if (isConnected) {
                        System.out.println("connected");
                        ClientTerminal terminal = new ClientTerminal();

                        ByteBuffer forWrite = ByteBuffer.allocate(1024*1024);
                        forWrite = ByteBuffer.wrap(Serializer.serialize(terminal.start()));
                        forWrite.flip();
                        //socketChannel.write(f);
                        socketChannel.socket().getOutputStream().write(forWrite.array());
                        socketChannel.socket().getOutputStream().flush();
                        //socketChannel.socket().getOutputStream().close();



                        //f.clear();
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

                    //ByteBuffer f = ByteBuffer.wrap(Serializer.serialize(terminal.start()));
                    //socketChannel.write(f);
//
                    //f = ByteBuffer.allocate(1024*1024);
                    //socketChannel.read(f);
                    //ByteArrayInputStream bais = new ByteArrayInputStream(f.array());
                    //ObjectInputStream ois = new ObjectInputStream(bais);
                    //Respond respond = (Respond) ois.readObject();
                    //System.out.println(respond.getMessage());
                    //ois.close(); bais.close();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
