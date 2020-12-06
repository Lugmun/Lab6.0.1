package ServerPackage;

import CityPackage.City;
import CityPackage.CityHashtable;
import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import other.RequestManager;
import other.Respond;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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

    public void start() {
        try //(ServerSocket server = new ServerSocket(port))
        {

            this.selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(address);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);


            System.out.println("Сервер запущен: " + serverSocketChannel.getLocalAddress().toString());

            //serverChannel.configureBlocking(false);
            //SelectionKey serverKey = serverChannel.register(selector, SelectionKey.OP_CONNECT);

            while (true)
                try {
                    if (this.selector.selectNow() == 0) {
                        continue;
                    }
                    System.out.println("not empty");
                    Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
                    while (keys.hasNext()) {
                        System.out.println("Канал найден");
                        SelectionKey currentKey = (SelectionKey) keys.next();
                        keys.remove();
                        if (!currentKey.isValid()) continue;
                        if (currentKey.isAcceptable()) accept(currentKey);

                        else if (currentKey.isReadable()) read(currentKey);

                    }


                    //CharBuffer buffer = CharBuffer.allocate(256);
                    //int bytes = reader.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
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


    public void read(SelectionKey key) {
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            Socket clientSocket = socketChannel.socket();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
            int numRead = socketChannel.read(byteBuffer);
            if (numRead == -1) {
                this.session.remove(socketChannel);
                System.out.println("Система:Пользователь отключился: " + socketChannel.socket().getRemoteSocketAddress() + "\n");
                socketChannel.close();
                key.cancel();
                return;
            }

            byteBuffer = ByteBuffer.allocate(1024*1024);
            socketChannel.read(byteBuffer);
            ByteArrayInputStream bais =
                    new ByteArrayInputStream(
                            byteBuffer.array());

            ObjectInputStream ois =
                    new ObjectInputStream(bais);

            Object o = ois.readObject();
            ois.close(); bais.close();


            RequestManager request = new RequestManager(clientSocket, o);
            //System.out.println(request.clientSocket.toString());
            //System.out.println(request.getObject(clientSocket).toString());
            //System.exit(0);
            //request.decrypt(request.getObject(clientSocket)).toString();
            //request.processing(hashtable);
            Respond respond = new Respond(request.processing(hashtable));
            Server.send(clientSocket, respond);
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
