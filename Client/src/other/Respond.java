package other;

import java.io.Serializable;

public class Respond implements Serializable {
    public String message;
    public RequestManager request;

    public Respond(String message) {
        this.message = message;
    }

    public Respond(RequestManager request){
        this.request = request;
    }


    public Respond(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

