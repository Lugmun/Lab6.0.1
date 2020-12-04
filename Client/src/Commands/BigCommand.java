package Commands;

import CityPackage.CityHashtable;

public interface BigCommand {
    public void execute(CityHashtable hashtable, long id);
    public String getMessage();
}
