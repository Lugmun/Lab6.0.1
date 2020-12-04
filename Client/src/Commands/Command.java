package Commands;

import CityPackage.CityHashtable;

public interface Command {
    public void execute(CityHashtable hashtable);
    public String getMessage();
}
