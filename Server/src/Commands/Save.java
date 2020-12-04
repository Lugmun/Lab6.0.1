package Commands;

import CityPackage.CityHashtable;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

public class Save implements Command {

    private String msg;

    @Override
    public void execute(CityHashtable hashtable) {

        try(FileOutputStream fos = new FileOutputStream("notes3.csv");
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            Set<Long> keys = hashtable.keySet();
            for(Long key: keys){
                //cities.get(key).setId();
                byte[] buffer = hashtable.get(key).toString().getBytes();
                bos.write(buffer, 0, buffer.length);
                //System.out.println();
            }
            //bos.flush();
            //bos.close();
            msg="Коллекция была записана в файл";

        }
        catch(IOException ex){
            msg=ex.getMessage();
        }


    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
