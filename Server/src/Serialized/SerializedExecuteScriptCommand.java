package Serialized;

import CityPackage.City;
import other.Command;

public class SerializedExecuteScriptCommand {
    private Command<String,Object> command;

    public SerializedExecuteScriptCommand(Command<String,Object> command) {
        this.command = command;
    }

    public Command<String,Object> getCommand() {
        return command;
    }
}
