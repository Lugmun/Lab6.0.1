package CityPackage;

public enum StandardOfLiving {
    ULTRA_HIGH("ULTRA_HIGH"),
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    ULTRA_LOW("ULTRA_LOW"),
    NIGHTMARE("NIGHTMARE");

    private String string;

    // constructor to set the string
    StandardOfLiving(String name){
        string = name;
    }

    // the toString just returns the given name
    @Override
    public String toString() {
        return string;
    }

}
