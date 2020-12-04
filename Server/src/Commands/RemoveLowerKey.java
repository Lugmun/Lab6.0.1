package Commands;

import CityPackage.CityHashtable;

import java.util.Set;

public class RemoveLowerKey implements BigCommand{
    private String msg;

    @Override
    public void execute(CityHashtable hashtable, long id) {
        if (!hashtable.isEmpty()){
            try{
                int prevSize = hashtable.size();
                Set<Long> keys = hashtable.keySet();
                Long[] array = keys.toArray(new Long[0]);
                for(long key : array) {
                    if (id > key){
                        hashtable.remove(key);
                    }
                }
                msg= prevSize - hashtable.size() + " элементов было удалено";
            }catch (Exception e){
                if (e instanceof NumberFormatException) {msg="ID должен быть типа long от 0 до "+Long.MAX_VALUE;}
                e.printStackTrace();
            }
        } else {
            msg="Коллекция пуста";
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
