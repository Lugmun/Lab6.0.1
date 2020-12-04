package Commands;


import CityPackage.CityHashtable;


public class Info implements Command {

    private String msg;

    @Override
    public void execute(CityHashtable hashtable) {
        if(hashtable.isEmpty()){
            msg="Коллекция пуста";
        }else {
            msg=    "Количество городов в хранилище: " + hashtable.size() + "\n" +
                    "Дата инициализации: " + hashtable.getAuthDateTime() + "\n" +
                    "Тип коллекции: " + hashtable.getClass().getSimpleName();
        }
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
