package Commands;

import CityPackage.CityHashtable;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class ExecuteScript {

    /*private Terminal fileTerminal;
    private String msg;

    public ExecuteScript (Terminal t) {
        this.fileTerminal = t;
    }


    public void execute(CityHashtable city, String str) {

        File script = new File(str);

        try (FileReader fr = new FileReader(script)) {

            Scanner scanner = new Scanner(script);
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine().trim();
                String[] nextRecord = nextLine.split(" ", 2);
                if (nextRecord.length == 1) {
                    fileTerminal.start();
                } else {
                    fileTerminal.start();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getMessage() {
        return this.msg;
    }

     */
}
