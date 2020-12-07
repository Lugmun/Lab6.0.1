package Commands;

import CityPackage.City;
import CityPackage.CityHashtable;
import other.*;

import java.awt.font.NumericShaper;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class ExecuteScript {

    private ClientTerminal fileClientTerminal;
    private Terminal fileTerminal;
    private String msg = "";
    private RequestManager request;
    private Respond respond;
    //private CityHashtable hashtable;

    public ExecuteScript (ClientTerminal t) {
        this.fileClientTerminal = t;
    }


    public void execute(CityHashtable city, String str) {

        File script = new File(str);

        try (FileReader fr = new FileReader(script)) {

            Scanner scanner = new Scanner(script);
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine().trim();
                String[] nextRecord = nextLine.split(" ", 2);
                if (nextRecord.length == 1) {
                    //fileClientTerminal.start();
                    this.request = new RequestManager(
                            fileClientTerminal.commandManager(nextRecord[0], null));
                    this.respond = new Respond(this.request.processing(city));
                } else {
                    this.request = new RequestManager(
                            fileClientTerminal.commandManager(nextRecord[0], nextRecord[1]));
                    this.respond = new Respond(this.request.processing(city));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return this.msg;
    }
}
