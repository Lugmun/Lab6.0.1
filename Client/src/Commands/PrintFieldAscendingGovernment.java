package Commands;

import CityPackage.City;
import CityPackage.CityHashtable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrintFieldAscendingGovernment implements Command {
    private String msg;

    @Override
    public void execute(CityHashtable hashtable) {
        //double value = 0;


        if (!hashtable.isEmpty()) {
            //Set<Long> keys = hashtable.keySet();
            //double previousKey = 0; double presentKey = 0;
            City[] array = new City[0];
            List<City> list = Arrays.asList(hashtable.values().toArray(array));
            Collections.sort(list);
            for (City c : list) {
                msg=c.getGovernment().toString();
            }
        } else msg="Коллекция пуста";
    }


    @Override
    public String getMessage() {
        return this.msg;
    }
}
