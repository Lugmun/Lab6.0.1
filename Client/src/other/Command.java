package other;

import java.io.Serializable;

public class Command<T, P> implements Serializable {
    static final long serialVersionUID = -407699740872077328L;
    private String commandName;
    private T argument;
    private P additional;

    public Command(String name, T argument){
        this.commandName = name;
        this.argument = argument;
    }

    public Command(){
        this.commandName = null;
        this.argument = null;
        this.additional = null;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public T getArgument() {
        return argument;
    }

    public void setArgument(T argument) {
        this.argument = argument;
    }

    public P getAdditional() {
        return additional;
    }

    public void setAdditional(P additional) {
        this.additional = (P) additional;
    }

    public void setEverything(String commandName, T argument, P additional){
        setCommandName(commandName);
        setArgument(argument);
        setAdditional(additional);
    }
}