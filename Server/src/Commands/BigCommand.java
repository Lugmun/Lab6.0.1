package Commands;

import CityPackage.City;
import CityPackage.CityHashtable;

public interface BigCommand {
    public void execute(CityHashtable hashtable, long id);
    public String getMessage();
}
