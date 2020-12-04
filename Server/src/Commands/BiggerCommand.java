package Commands;

import CityPackage.City;
import CityPackage.CityHashtable;
import other.Command;

public interface BiggerCommand {
    public void execute(CityHashtable hashtable, City city, long id);
    public String getMessage();
}
