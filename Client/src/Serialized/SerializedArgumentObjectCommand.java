package Serialized;

//import ClientPackage.Command;

import CityPackage.City;
import other.Command;

import java.io.Serializable;

;

public class SerializedArgumentObjectCommand implements Serializable {

    private Long id;
    private Command<Long,City> command;
    private City city;

    public SerializedArgumentObjectCommand(Command<Long,City> command) {
        this.command = command;
        this.id = command.getArgument();
        this.city = command.getAdditional();
    }

    public Command<Long,City> getCommand() {
        return command;
    }

    public City getObject() {
        return city;
    }

    public Long getArg() {
        return id;
    }
}
