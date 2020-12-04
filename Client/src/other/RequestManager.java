package other;

import CityPackage.City;
import CityPackage.CityHashtable;
import Serialized.SerializedArgumentCommand;
import Serialized.SerializedArgumentObjectCommand;
import Serialized.SerializedExecuteScriptCommand;
import Serialized.SerializedSimplyCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

public class RequestManager implements Serializable {

    Object o = null;
    public Socket clientSocket;
    Command command;
    //Command<Integer,Object> bigCommand1;
    //Command<Integer,City> bigCommand2;


    public RequestManager(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    //public void toCommand(Object T) {
    //}

    //формирование объекта из stream'а
    public Object getObject(Socket clientSocket) {
        try(ObjectInputStream input =
                    new ObjectInputStream(
                            clientSocket.getInputStream()))
        {

            o = input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }

    //десериализация
    public Command decrypt(Object o) throws IOException {
        if (o instanceof SerializedSimplyCommand) {
            SerializedSimplyCommand simplyCommand = (SerializedSimplyCommand) o;
            Command<Object,Object> command = (Command<Object,Object>) simplyCommand.getCommand();
            //return command;
            //String arg = "";
            //command.execute(clientSocket);
        }

        if (o instanceof SerializedArgumentCommand) {
            SerializedArgumentCommand argumentCommand = (SerializedArgumentCommand) o;
            Command<Long,Object> command = (Command<Long,Object>) argumentCommand.getCommand();
            //return bigCommand1;
            //Integer arg = bigCommand1.getArgument();
            //bigCommand.execute(arg, clientSocket);
        }

        if (o instanceof SerializedArgumentObjectCommand) {
            SerializedArgumentObjectCommand argumentObjectCommand = (SerializedArgumentObjectCommand) o;
            Command<Long,City> command = (Command<Long,City>) argumentObjectCommand.getCommand();
            //return bigCommand2;
            //City city = argumentObjectCommand.getObject();
            //bigCommand.execute(arg, clientSocket);
        }

        if (o instanceof SerializedExecuteScriptCommand) {
            SerializedExecuteScriptCommand executeScriptCommand = (SerializedExecuteScriptCommand) o;
            Command<String,Object> command = executeScriptCommand.getCommand();
            //command.execute(combinedCommand, socket);
        }
        return command;
    }

    //обработка запроса
    public String processing(CityHashtable hashtable) throws IOException {
        Terminal t = new Terminal(hashtable);
        return t.start();
    }
}
