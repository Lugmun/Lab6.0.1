package Serialized;

import other.Command;

import java.io.Serializable;

public class SerializedExecuteScriptCommand implements Serializable, SerializedCommand {
    private Command<String,Object> command;

    public SerializedExecuteScriptCommand(Command<String,Object> command) {
        this.command = command;
    }

    public Command<String,Object> getCommand() {
        return command;
    }
}
