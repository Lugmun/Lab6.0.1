package Commands;

import CityPackage.CityHashtable;

public class Clear implements Command {

    private String msg;

    @Override
    public void execute(CityHashtable hashtable) {
        if(!hashtable.isEmpty()){
            hashtable.clear();
            msg="Коллекция была очищена";
        } else {msg="Коллекция пуста";}
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
