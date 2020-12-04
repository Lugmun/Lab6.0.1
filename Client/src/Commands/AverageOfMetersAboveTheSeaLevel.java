package Commands;

import CityPackage.CityHashtable;

import java.util.Set;

public class AverageOfMetersAboveTheSeaLevel implements Command {


    private String msg;

    @Override
    public void execute(CityHashtable hashtable) {

        double value = 0;

        if (!hashtable.isEmpty()) {
            Set<Long> keys = hashtable.keySet();
            double sumValue = 0; int i = 0;

            for(long key : keys) {
                if (hashtable.get(key).getMetersAboveSeaLevel()!=null) {
                    value = hashtable.get(key).getMetersAboveSeaLevel();
                    sumValue = sumValue + value;
                    i = i+1;
                }

            }
            value = sumValue/i;

        } else {
            msg="Коллекция пуста";
        }
        msg="Среднее значение: "+value;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
