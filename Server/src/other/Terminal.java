package other;

import CityPackage.City;
import Commands.*;
import CityPackage.CityHashtable;
import ServerPackage.Server;

import java.io.IOException;
import java.util.Scanner;

public class Terminal {

    private Server server;
    private RequestManager request;
    private Respond respond = new Respond();
    private CityHashtable hashtable;
    private Scanner scanner = new Scanner(System.in);
    private String asRespond;
    private Info info = new Info();
    private Show show = new Show();
    private Insert insert = new Insert();
    private UpdateId update_id = new UpdateId();
    private RemoveKey remove_key = new RemoveKey();
    private Clear clear = new Clear();
    private Save save = new Save();
    private ExecuteScript executeScript = new ExecuteScript(this);
    private ReplaceIfGreater replace_if_greater = new ReplaceIfGreater();
    private ReplaceIfLower replace_if_lower = new ReplaceIfLower();
    private RemoveLowerKey remove_lower_key = new RemoveLowerKey();
    private AverageOfMetersAboveTheSeaLevel average_of_meters_above_the_sea_level = new AverageOfMetersAboveTheSeaLevel();
    private PrintFieldAscendingGovernment print_field_ascending_government = new PrintFieldAscendingGovernment();
    private PrintFieldDescendingGovernor print_field_descending_governor = new PrintFieldDescendingGovernor();


    public Terminal(CityHashtable hashtable, RequestManager request){
        this.hashtable = hashtable;
        this.request = request;
        System.out.println("Терминал успешно создан");
    }
    /**

     *осуществляет запуск интерактивного режима

     */

    //public void start(){
    //    System.out.println("Можете начать работать. Напечатайте 'help' для просмотра списка команд");
    //    while (true){
    //        newLine = scanner.nextLine().trim();
    //        String[] arr = newLine.split(" ", 2);
    //        if (arr.length == 1){
    //            commandManager(arr[0], null);
    //        }else{
    //            commandManager(arr[0],arr[1]);
    //        }
    //    }
    //}

    public String start() throws IOException {
        return startWithCommand(this.request.decrypt(this.request.getObject()));
    }

    private String startWithCommand(Command command){
        System.out.println("Сервер в процессе исполнения команды: " + command.getCommandName());
        //if (arr2 == null){
            switch (command.getCommandName()){
                case "exit":
                    System.out.println("До свидания");
                    System.exit(0);
                    break;

                case "help":
                    asRespond = "help : вывести справку по доступным командам\n" +                                                                          //complete
                                "info : вывести в стандартный поток вывода информацию о коллекции\n" +                                                      //complete
                                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +                            //complete
                                "insert null {element} : добавить новый элемент c заданным ключом\n" +                                                      //complete
                                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +                               //complete
                                "remove_key null : удалить элемент из коллекции по его ключу\n" +                                                           //complete
                                "clear : очистить коллекцию\n" +                                                                                            //complete
                                "save : сохранить коллекцию в файл\n" +                                                                                     //complete
                                "execute_script file_name : считать и исполнить скрипт из указанного файла\n" +                                             //
                                "exit : завершить программу (без сохранения в файл)\n" +                                                                    //complete
                                "replace_if_greater null {element} : заменить значение по ключу, если новое значение больше старого\n" +                    //Complete
                                "replace_if_lower null {element} : заменить значение по ключу, если новое значение меньше старого\n" +                      //Complete
                                "remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный\n" +                          //complete
                                "average_of_meters_above_sea_level : вывести среднее значение поля metersAboveSeaLevel для всех элементов коллекции\n" +    //complete
                                "print_field_ascending_government : вывести значения поля government всех элементов в порядке возрастания\n" +              //complete
                                "print_field_descending_governor : вывести значения поля governor всех элементов в порядке убывания\n";                     //complete
                    break;

                case "info":
                    info.execute(hashtable);
                    asRespond = info.getMessage();
                    //server.writeRespond(respond);
                    break;

                case "show":
                    show.execute(hashtable);
                    asRespond = show.getMessage();
                    //server.writeRespond(respond);
                    break;

                case "clear":
                    clear.execute(hashtable);
                    asRespond = clear.getMessage();
                    //server.writeRespond(respond);
                    break;
                case "save":
                    save.execute(hashtable);
                    asRespond = save.getMessage();
                    //server.writeRespond(respond);
                    break;

                case "average_of_meters_above_sea_level":
                    average_of_meters_above_the_sea_level.execute(hashtable);
                    asRespond = average_of_meters_above_the_sea_level.getMessage();
                    break;

                case "print_field_ascending_government":
                    print_field_ascending_government.execute(hashtable);
                    asRespond = print_field_ascending_government.getMessage();
                    break;

                case "print_field_descending_governor":
                    print_field_descending_governor.execute(hashtable);
                    asRespond = print_field_descending_governor.getMessage();
                    break;

                case "insert":
                    insert.execute(hashtable, (City) command.getAdditional(), (long) command.getArgument());
                    asRespond = insert.getMessage();
                    break;


                case  "update":
                    update_id.execute(hashtable, (City) command.getAdditional(), (long) command.getArgument());
                    asRespond = update_id.getMessage();
                    break;

                case "remove_key":
                    remove_key.execute(hashtable, (long) command.getArgument());
                    asRespond = remove_key.getMessage();
                    break;


                case "replace_if_greater":
                    replace_if_greater.execute(hashtable, (City) command.getAdditional(), (long) command.getArgument());
                    asRespond = replace_if_greater.getMessage();
                    break;

                case "replace_if_lower":
                    replace_if_lower.execute(hashtable, (City) command.getAdditional(), (long) command.getArgument());
                    asRespond = replace_if_lower.getMessage();
                    break;


                case "remove_lower_key":
                    remove_lower_key.execute(hashtable, (long) command.getArgument());
                    asRespond = remove_lower_key.getMessage();
                    break;


                //case "execute_script":
                //    executeScript.execute(hashtable,arr2.trim());
                //    break;

                default:
                    asRespond = "Неизвестная команда, напечатайте 'help' для просмотра списка команд";
                    break;
            }
        return asRespond;
    }
}


