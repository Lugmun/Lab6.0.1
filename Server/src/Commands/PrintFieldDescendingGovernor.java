package Commands;

import CityPackage.City;
import CityPackage.CityHashtable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrintFieldDescendingGovernor implements Command {

    private String msg;

    @Override
    public void execute(CityHashtable hashtable) {
        if (!hashtable.isEmpty()) {
            //Set<Long> keys = hashtable.keySet();
            //double previousKey = 0; double presentKey = 0;
            City[] array = new City[0];
            List<City> list = Arrays.asList(hashtable.values().toArray(array));
            Collections.sort(list);
            Collections.reverse(list);
            for (City c : list) {
                msg=c.getGovernor().toString();
            }
        } else msg="Коллекция пуста";
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
