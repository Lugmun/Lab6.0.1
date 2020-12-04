package Commands;

import CityPackage.City;
import CityPackage.CityHashtable;

import java.util.Set;

public class ReplaceIfLower implements BiggerCommand {
    private String msg;

    @Override
    public void execute(CityHashtable hashtable, City city, long id) {

        if (!hashtable.isEmpty()){

            try{
                //String[] mstr = str.split(" ", 2);
                //mstr[1] = mstr[1].replaceAll("[{}]", "");
                //String[] nextRecord = mstr[1].split(";", 10);
                //long presentId = Long.parseLong(mstr[0]);
                Set<Long> keys = hashtable.keySet();
                Long[] array = keys.toArray(new Long[0]);
                for(long key : array) {
                    if (id == key) {
                        if (hashtable.get(id).compareTo(hashtable.get(key)) < 0) {
                            //FieldOfCityChecker fieldOfCityChecker = new FieldOfCityChecker();
                            //City cityToPut = fieldOfCityChecker.checkEverything(nextRecord, -1);
                            //hashtable.put(cityToPut.getId(), cityToPut);
                            hashtable.replace(id, city);
                            msg="Город " + id + " был успешно изменён";
                        } else {
                            msg="Значения полей больше имеющихся или они равны";
                        }
                    }
                }
            }catch (Exception e){
                if (e instanceof NumberFormatException) {msg="ID должен быть типа long от 0 до "+Long.MAX_VALUE;}
                e.printStackTrace();
            }
        }else {
            msg="Коллекция пуста";
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
