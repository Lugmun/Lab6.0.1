package Commands;


import CityPackage.City;
import CityPackage.CityHashtable;

public class Show implements Command {

    private String msg;

    @Override
    public void execute(CityHashtable hashtable) {

        if (hashtable.isEmpty()){
            msg="Коллекция пуста";
        }else{
            for (City c: hashtable.values()){
                msg=c.customToString() + "\n" +
                "--------------------------";
            }
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
