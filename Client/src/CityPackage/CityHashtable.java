package CityPackage;

import java.time.ZonedDateTime;
import java.util.Hashtable;

public class CityHashtable extends Hashtable<Long, City> {
    public ZonedDateTime authDateTime;

    public void setAuthDateTime(ZonedDateTime authDateTime) {
        this.authDateTime = authDateTime;
    }

    public ZonedDateTime getAuthDateTime(){
        return this.authDateTime;
    }
}

