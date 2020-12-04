package Commands;

import CityPackage.City;
import CityPackage.CityHashtable;

import java.util.Set;

public class UpdateId implements BiggerCommand {
    private String msg;

    //String[] nextRecord;

    @Override
    public void execute(CityHashtable hashtable, City city, long id) {

        int i = 0;

        if (!hashtable.isEmpty()) {
            try {
                //mstr[1] = mstr[1].replaceAll("[{}]", "");
                //String[] nextRecord = mstr[1].split(";", 10);
                //long neededId = Long.parseLong(mstr[0]);     Long.valueOf(mstr[0]);
                Set<Long> keys = hashtable.keySet();
                for(long key : keys){
                    if (id == key) {
                        i = i+1;
                        //System.out.println(hashtable.remove(neededId));
                        //FieldOfCityChecker fieldOfCityChecker = new FieldOfCityChecker();
                        //City cityToPut = fieldOfCityChecker.checkEverything(nextRecord, -1);
                        city.setId(id);
                        hashtable.put(city.getId(), city);
                        msg="Город с id " + id + " был успешно обновлён";
                    }
                }
                if (i==0) {msg="Город не был добавлен";}
            } catch (Exception e) {
                e.printStackTrace();
            }

            //System.out.println(str);
        } else {
            msg=    "Невозможно обновить."+"\n"+
                    "Коллекция пуста";
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
