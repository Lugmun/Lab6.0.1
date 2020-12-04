package CityPackage;

public enum Climate {
    TROPICAL_SAVANNA("TROPICAL_SAVANNA"),
    STEPPE("STEPPE"),
    SUBARCTIC("SUBARCTIC"),
    POLAR_ICECAP("POLAR_ICECAP");

    public String string;

    // constructor to set the string
    Climate(String name){
        string = name;
    }

    // the toString just returns the given name
    @Override
    public String toString() {
        return string;
    }
}
