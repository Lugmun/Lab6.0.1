package ServerPackage;

import CityPackage.CityHashtable;
import other.RequestManager;
import other.Respond;
import other.Serializer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Server {

    private Selector selector;
    SocketAddress address;
    private Set<SocketChannel> session;
    public CityHashtable hashtable;
    private Scanner scanner = new Scanner(System.in);
    private int port = 0;

    public Server(CityHashtable hashtable) {
        System.out.println("Введите номер порта, чтобы запустить сервер");
        while (this.port == 0) {
            try {
                int a = Integer.parseInt(scanner.nextLine().trim());
                if (a < 1025 || a > 65535) {
                    System.out.println("Был введён неправильный порт. Введите значение типа int из промежутка (1024;65535)");
                } else {
                    this.port = a;
                    System.out.println("Текущий порт: " + this.port);
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат ввода");
            }
        }
        this.hashtable = hashtable;
        this.address = new InetSocketAddress(port);
        this.session = new HashSet<SocketChannel>();
    }
    //основной метод реализации клиент-серверного взаимодействия
    public void start() {
        try {
            this.selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(address);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

            System.out.println("Сервер запущен: " + serverSocketChannel.getLocalAddress().toString());

            while (true)
                try {
                    if (this.selector.selectNow() == 0) {
                        continue;
                    }
                    Iterator<SelectionKey> keysIterator = this.selector.selectedKeys().iterator();
                    while (keysIterator.hasNext()) {
                        SelectionKey currentKey = keysIterator.next();
                        if  (currentKey == null)   continue;
                        if (!currentKey.isValid()) continue;
                        if  (currentKey.isAcceptable()) accept(currentKey);
                        if  (currentKey.isReadable())   read(currentKey);
                        keysIterator.remove();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
    //организация доступа каналов с клиента
    private void accept(SelectionKey key) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            channel.register(this.selector, SelectionKey.OP_READ);
            this.session.add(channel);
            System.out.println("Система:Новый пользователь: " + channel.socket().getRemoteSocketAddress());
        }catch (Exception e) {e.printStackTrace();}
    }

    //взаимодействие с потоками данных
    public void read(SelectionKey key) {
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
            int numRead = socketChannel.read(byteBuffer);
            if (numRead == -1) {
                this.session.remove(socketChannel);
                System.out.println("Система:Пользователь отключился: " + socketChannel.socket().getRemoteSocketAddress() + "\n");
                socketChannel.close();
                key.cancel();
                return;
            }
            socketChannel.read(byteBuffer);
            Object o = Serializer.deserialize(byteBuffer.array());
            System.out.println("принял команду, обрабатываю");
            //обработка запроса клиента
            RequestManager request = new RequestManager(o);
            Respond respond = new Respond(request.processing(hashtable));

            //отправка ответа клиенту
            byteBuffer.clear();
            System.out.println("отсылаю ответ клиенту");
            byteBuffer = ByteBuffer.wrap(Serializer.serialize(respond));
            byteBuffer.clear();
            socketChannel.write(byteBuffer);

            socketChannel.close();
        } catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
    }

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
