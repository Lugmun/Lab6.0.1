package CityPackage;

public enum Government {
    ANARCHY("ANARCHY"),
    CORPORATOCRACY("CORPORATOCRACY"),
    NOOCRACY("NOOCRACY"),
    PLUTOCRACY("PLUTOCRACY"),
    ETHNOCRACY("ETHNOCRACY");

    private String string;

    // constructor to set the string
    Government(String name){
        string = name;
    }

    // the toString just returns the given name
    @Override
    public String toString() {
        return string;
    }

}
