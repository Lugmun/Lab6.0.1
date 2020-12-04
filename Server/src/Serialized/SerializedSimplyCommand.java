package Serialized;

//import ClientPackage.Command;
import other.Command;


import java.io.Serializable;

public class SerializedSimplyCommand implements Serializable {
    private Command<Object,Object> command;

    public SerializedSimplyCommand(Command<Object,Object> command) {
        this.command = command;
    }

    public Command<Object,Object> getCommand() {
        return command;
    }

}
