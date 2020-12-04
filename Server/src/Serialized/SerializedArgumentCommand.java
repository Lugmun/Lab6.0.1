package Serialized;

//import ClientPackage.Command;

import Commands.BigCommand;
import other.Command;

import java.io.Serializable;

public class SerializedArgumentCommand implements Serializable {

    private Long id;
    private Command<Long,Object> command;

    public SerializedArgumentCommand(Command<Long,Object> command) {
        this.command = command;
        this.id = command.getArgument();
    }

    public Command<Long,Object> getCommand() {
        return command;
    }

    public Long getArg() {
        return id;
    }
}
