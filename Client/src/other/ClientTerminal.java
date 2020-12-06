package other;

import Checkers.FieldOfCityChecker;
import Commands.*;

import CityPackage.City;
import CityPackage.CityHashtable;
import Serialized.*;

import java.util.Hashtable;
import java.util.Scanner;

public class ClientTerminal {

    //private CityHashtable hashtable;
    SerializedCommand serializedCommand;
    //SerializedSimplyCommand simple;
    //SerializedArgumentCommand difficult;
    //SerializedArgumentObjectCommand moreDifficult;
    //SerializedExecuteScriptCommand executeScriptCommand;
    private City cityToPut;
    String[] mstr;
    private Command command = new Command();
    private Scanner scanner = new Scanner(System.in);
    private String newLine = "";
    private Info info = new Info();
    private Show show = new Show();
    private Insert insert = new Insert();
    private UpdateId update_id = new UpdateId();
    private RemoveKey remove_key = new RemoveKey();
    private Clear clear = new Clear();
    private Save save = new Save();
    //private ExecuteScript executeScript = new ExecuteScript(this);
    private ReplaceIfGreater replace_if_greater = new ReplaceIfGreater();
    private ReplaceIfLower replace_if_lower = new ReplaceIfLower();
    private RemoveLowerKey remove_lower_key = new RemoveLowerKey();
    private AverageOfMetersAboveTheSeaLevel average_of_meters_above_the_sea_level = new AverageOfMetersAboveTheSeaLevel();
    private PrintFieldAscendingGovernment print_field_ascending_government = new PrintFieldAscendingGovernment();
    private PrintFieldDescendingGovernor print_field_descending_governor = new PrintFieldDescendingGovernor();


    public ClientTerminal(){
        //this.hashtable = hashtable;
    }



    /**

     *осуществляет запуск интерактивного режима

     */

    public SerializedCommand start(){
        System.out.println("Напечатайте 'help' для просмотра списка команд");
        newLine = scanner.nextLine().trim();
        String[] arr = newLine.split(" ", 2);
        if (arr.length == 1){
            return commandManager(arr[0], null);
        }else{
            return commandManager(arr[0],arr[1]);
        }
    }


    //public SerializedCommand startWithParams(String arr1, String arr2){
    //    if (arr2 == null){
    //        return  commandManager(arr1, null);
    //    }else{
    //        return commandManager(arr1,arr2);
    //    }
    //
    //}

    private SerializedCommand commandManager(String arr1, String arr2){
        if (arr2 == null){
            switch (arr1){
                //case "exit":
                //    command.setEverything(arr1,null, null);
                //    serializedCommand = new SerializedSimplyCommand(command);
                //    break;
                case "exit":
                    //command.setEverything(arr1,null, null);
                    //serializedCommand = new SerializedSimplyCommand(command);
                    System.out.println("До свидания");
                    System.exit(0);
                    break;

                case "help":
                    command.setCommandName(arr1);
                    serializedCommand = new SerializedSimplyCommand(command);

                    //System.out.println("help : вывести справку по доступным командам");                                                                          //complete
                    //System.out.println("info : вывести в стандартный поток вывода информацию о коллекции");                                                      //complete
                    //System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");                            //complete
                    //System.out.println("insert null {element} : добавить новый элемент c заданным ключом");                                                      //complete
                    //System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");                               //complete
                    //System.out.println("remove_key null : удалить элемент из коллекции по его ключу");                                                           //complete
                    //System.out.println("clear : очистить коллекцию");                                                                                            //complete
                    //System.out.println("save : сохранить коллекцию в файл");                                                                                     //complete
                    //System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла");                                             //
                    //System.out.println("exit : завершить программу (без сохранения в файл)");                                                                    //complete
                    //System.out.println("replace_if_greater null {element} : заменить значение по ключу, если новое значение больше старого");                    //Complete
                    //System.out.println("replace_if_lower null {element} : заменить значение по ключу, если новое значение меньше старого");                      //Complete
                    //System.out.println("remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный");                          //complete
                    //System.out.println("average_of_meters_above_sea_level : вывести среднее значение поля metersAboveSeaLevel для всех элементов коллекции");    //complete
                    //System.out.println("print_field_ascending_government : вывести значения поля government всех элементов в порядке возрастания");              //
                    //System.out.println("print_field_descending_governor : вывести значения поля governor всех элементов в порядке убывания");                    //
                    break;

                case "info":
                    command.setEverything(arr1, null, null);
                    serializedCommand = new SerializedSimplyCommand(command);
                    break;

                case "show":
                    command.setEverything(arr1, null, null);
                    serializedCommand = new SerializedSimplyCommand(command);
                    break;

                case "clear":
                    command.setEverything(arr1, null, null);
                    serializedCommand = new SerializedSimplyCommand(command);
                    break;
                /*case "save":
                    save.execute(hashtable);
                    break;
                    */

                case "insert":

                case "update":

                case "remove_key":

                case "replace_if_lower":

                case "replace_if_greater":

                    System.out.println("Null второй агрумент");
                    break;

                case "average_of_meters_above_sea_level":
                    command.setEverything(arr1, null, null);
                    serializedCommand = new SerializedSimplyCommand(command);
                    break;

                case "print_field_ascending_government":
                    command.setEverything(arr1, null, null);
                    serializedCommand = new SerializedSimplyCommand(command);
                    break;

                case "print_field_descending_governor":
                    command.setEverything(arr1, null, null);
                    serializedCommand = new SerializedSimplyCommand(command);
                    break;

                case "execute_script":
                    System.out.println("Null значение имени файла. Попробуйте другой аргумент");
                    break;

                default:
                    System.out.println("Неизвестная команда, напечатайте 'help' для просмотра списка команд");
                    break;
            }

        } else{

            switch (arr1){

                case "insert":
                    mstr = arr2.split(" ", 2);
                    if (mstr.length == 2) {
                        try {
                            mstr[1] = mstr[1].replaceAll("[{}]", "");
                            String[] nextRecord = mstr[1].split(";", 10);
                            long neededId = Long.parseLong(mstr[0]);
                            FieldOfCityChecker fieldOfCityChecker = new FieldOfCityChecker();
                            City cityToPut = fieldOfCityChecker.checkEverything(nextRecord, -1);
                            command.setEverything(arr1, neededId, cityToPut);
                            serializedCommand = new SerializedArgumentObjectCommand(command);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;


                case  "update":
                    mstr = arr2.split(" ", 2);

                    if (mstr.length == 2) {
                        try {
                            mstr[1] = mstr[1].replaceAll("[{}]", "");
                            String[] nextRecord = mstr[1].split(";", 10);
                            long neededId = Long.parseLong(mstr[0]);

                            FieldOfCityChecker fieldOfCityChecker = new FieldOfCityChecker();
                            City cityToPut = fieldOfCityChecker.checkEverything(nextRecord, -1);
                            command.setEverything(arr1, neededId, cityToPut);
                            serializedCommand = new SerializedArgumentObjectCommand(command);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "remove_key":
                    try {
                        long idToRemove = Long.parseLong(arr2);
                        command.setEverything(arr1, idToRemove, null);
                        serializedCommand = new SerializedArgumentCommand(command);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Введенное значение не числового формата");
                    }
                    break;


                case "replace_if_greater":
                    try {
                        String[] mstr = arr2.split(" ", 2);
                        mstr[1] = mstr[1].replaceAll("[{}]", "");
                        String[] nextRecord = mstr[1].split(";", 10);
                        long presentId = Long.parseLong(mstr[0]);
                        FieldOfCityChecker fieldOfCityChecker = new FieldOfCityChecker();
                        City cityToPut = fieldOfCityChecker.checkEverything(nextRecord, -1);
                        command.setEverything(arr1, presentId, cityToPut);
                        serializedCommand = new SerializedArgumentObjectCommand(command);
                    } catch (NumberFormatException e) {
                        System.out.println("Введенное значение не числового формата");
                    }
                    break;

                case "replace_if_lower":
                    try {
                        String[] mstr = arr2.split(" ", 2);
                        mstr[1] = mstr[1].replaceAll("[{}]", "");
                        String[] nextRecord = mstr[1].split(";", 10);
                        long presentId = Long.parseLong(mstr[0]);
                        FieldOfCityChecker fieldOfCityChecker = new FieldOfCityChecker();
                        City cityToPut = fieldOfCityChecker.checkEverything(nextRecord, -1);
                        command.setEverything(arr1, presentId, cityToPut);
                        serializedCommand = new SerializedArgumentObjectCommand(command);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;


                case "remove_lower_key":
                    try {
                        long idToRemove = Long.parseLong(arr2);
                        command.setEverything(arr1, idToRemove, null);
                        serializedCommand = new SerializedArgumentCommand(command);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                    //case "execute_script":
//
                    //executeScript.execute(hashtable,arr2.trim());
                    //executeScriptCommand = SerializedExecuteScriptCommand(command);
//
                    //break;

                default:
                    System.out.println("Неизвестная команда, напечатайте 'help' для просмотра списка команд");
                    break;

            }
        }
        return serializedCommand;
    }
}
