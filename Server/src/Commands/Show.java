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
            String msg = "";
            for (City c : hashtable.values()){
                msg = "\n" + c.customToString() + "\n" +
                "--------------------------";
                this.msg = msg + this.msg;
            }
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
