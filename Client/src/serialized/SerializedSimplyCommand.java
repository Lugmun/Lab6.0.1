package serialized;

//import ClientPackage.Command;

import other.Command;

import java.io.Serializable;

public class SerializedSimplyCommand implements Serializable, SerializedCommand {
    private Command<Object,Object> command;
    static final long  serialVersionUID = 8076267999480335974L;

    public SerializedSimplyCommand(Command<Object,Object> command) {
        this.command = command;
    }

    public Command<Object,Object> getCommand() {
        return command;
    }

}
