package ServerPackage;

import CityPackage.City;
import CityPackage.CityHashtable;
import other.RequestManager;
import other.Respond;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class Server {

    CityHashtable hashtable;
    private Scanner scanner = new Scanner(System.in);
    private int port = 0;

    public Server(CityHashtable hashtable) {
        System.out.println("Введите номер порта, чтобы запустить сервер");
        int port = 0;

        while (port == 0) {
            try {
                int a = Integer.parseInt(scanner.nextLine().trim());
                if (a < 1025 || a > 65535) {
                    System.out.println("Был введён неправильный порт. Введите значение типа int из промежутка (1024;65535)");
                } else {
                    port = a;
                    this.port = port;
                    System.out.println("Текущий порт: " + port);
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат ввода");
            }
        }
        this.hashtable = hashtable;
    }

    public void establishConnection() {
        try (ServerSocket server = new ServerSocket(port)) {

            System.out.println("Сервер запущен");

            Selector selector = Selector.open();
            ServerSocketChannel serverChannel = server.getChannel();
            serverChannel.configureBlocking(false);
            SelectionKey serverKey = serverChannel.register(selector, SelectionKey.OP_CONNECT);

            while (true)
                try {
                    if (selector.selectNow() == 0) continue;
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                    while (iterator.hasNext()) {
                        SelectionKey currentKey = iterator.next();

                        if (currentKey.isConnectable()) {
                            ServerSocketChannel ssChannel = (ServerSocketChannel) currentKey.channel();
                            SocketChannel newClient = ssChannel.accept();
                            newClient.register(selector, SelectionKey.OP_WRITE);

                        } else if (currentKey.isReadable()) {

                            SocketChannel sChannel = (SocketChannel) currentKey.channel();
                            Socket newClientSocket = sChannel.socket();

                            RequestManager request = new RequestManager(newClientSocket);
                            Respond respond = new Respond(request.processing(hashtable));

                            Server.send(newClientSocket, respond);

                            //ObjectOutputStream output =
                            //        new ObjectOutputStream(
                            //                newClientSocket.getOutputStream());
                            //
                            //output.writeObject(respond);

                            //Command<> command = (Command) o;
                            //deserializer.deserialize(o);
                        }
                    }


                    //CharBuffer buffer = CharBuffer.allocate(256);
                    //int bytes = reader.read(buffer);




                } catch (IOException e) {
                    e.printStackTrace();
                }
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }


    //public void writeRespond(Respond respond) {
    //}

    public static void send(Socket clientSocket, Respond respond){

        try {
            System.out.println("Отсылаю ответ клиенту");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutputStream.writeObject(respond);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
