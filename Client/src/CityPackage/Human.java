package CityPackage;

public class Human {
    private String name; //Поле не может быть null, Строка не может быть пустой

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
}
