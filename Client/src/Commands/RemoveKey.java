package Commands;

import CityPackage.CityHashtable;

public class RemoveKey implements BigCommand {
    private String msg;

    @Override
    public void execute(CityHashtable hashtable, long id) {
        try {
            int prevSize = hashtable.size();

            if (!hashtable.isEmpty()) {

                if (hashtable.containsKey(id)) {
                    hashtable.remove(id);
                } else msg="Города c таким id нет";

            } else msg="Коллекция пуста";

            if (prevSize - hashtable.size() > 0) msg="Город был успешно удалён";

        }catch (NumberFormatException e){
            msg="ID должен быть типа long от 0 до "+Long.MAX_VALUE;
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
