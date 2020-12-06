package other;

import Checkers.FieldOfCityChecker;
import CityPackage.*;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.time.ZonedDateTime;


//import org.supercsv.*;

public class FileConverter {

    public CityHashtable hashtable = new CityHashtable();


    public void fileReaderLineByLine(String csvPath){

        File csvFile = new File(csvPath);

        if(!csvFile.exists()){
            System.out.println("File does not exist");
            System.exit(0);
        }
        if(!csvFile.isFile()){
            System.out.println("This is not a file");
            System.exit(0);
        }
        if(!csvFile.canRead()){
            System.out.println("File is unreachable");
            System.exit(0);
        }
        if(!csvPath.endsWith(".csv")){
            System.out.println("This is not a csv-file");
            System.exit(0);
        }

        try(FileReader fr = new FileReader(csvPath)){
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(fr).withCSVParser(parser).build();
            //CSVReader csvReader = new CSVReader(fr);



            int counter = 0;
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext())!= null) {

                counter = counter + 1;

                FieldOfCityChecker fieldOfCityChecker = new FieldOfCityChecker();
                City cityToPut = fieldOfCityChecker.checkEverything(nextRecord, counter);
                cityToPut.setId(City.generateId());
                hashtable.put(cityToPut.getId(), cityToPut);
                hashtable.setAuthDateTime(ZonedDateTime.now());
            }

            } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
