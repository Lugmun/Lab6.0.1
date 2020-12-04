package Commands;

import Checkers.FieldOfCityChecker;
import CityPackage.City;
import CityPackage.CityHashtable;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

public class Insert implements BiggerCommand {


    private String msg;

    /*
    @Override
    public void execute(CityHashtable hashtable, City city) {

        if (!hashtable.isEmpty()) {
            String[] mstr = str.split(" ", 2);

            if (mstr.length == 2) {
                try {
                    mstr[1] = mstr[1].replaceAll("[{}]", "");
                    String[] nextRecord = mstr[1].split(";", 10);

                    long neededId = Long.parseLong(mstr[0]);

                    FieldOfCityChecker fieldOfCityChecker = new FieldOfCityChecker();
                    City cityToPut = fieldOfCityChecker.checkEverything(nextRecord, -1);

                    if (!hashtable.containsKey(neededId)) {
                        cityToPut.setId(neededId);
                        hashtable.put(neededId, cityToPut);
                        msg="Город с id " + neededId + " был успешно добавлен";
                    } else msg="Город c таким id существует";
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //System.out.println(str);
            }
        } else {

            String[] mstr = str.split(" ", 2);

            if (mstr.length == 2) {
                try {
                    String[] nextRecord = mstr[1].split(";", 10);

                    long neededId = Long.parseLong(mstr[0]);   //Long.valueOf(mstr[0]);

                    FieldOfCityChecker fieldOfCityChecker = new FieldOfCityChecker();
                    City cityToPut = fieldOfCityChecker.checkEverything(nextRecord, -1);
                    cityToPut.setId(neededId);
                    hashtable.put(cityToPut.getId(), cityToPut);

                    msg="Город с id " + neededId + " был успешно добавлен";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

     */

    @Override
    public void execute(CityHashtable hashtable, City city, long id) {
        if (city == null){
            System.out.println("Original city is null");
            msg = "Город null";
        }else {
            try {
                int prevSize = hashtable.size();

                if (!hashtable.containsKey(id)) {
                    city.setId(id);
                    hashtable.put(city.getId(), city);
                }
                if (prevSize != hashtable.size()) {
                    System.out.println("Element was successfully added to the collection");
                    msg = ("Элемент был успешно добавлен в коллекцию");
                } else {
                    System.out.println("Collection already contains such element");
                    msg = ("Коллекция уже содержит данный элемент");
                }
            } catch (NumberFormatException e) {
                System.out.println("Some number-fields have incorrect values");
                msg = ("Некоторые поля содержат некорректные значения");
            }
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
