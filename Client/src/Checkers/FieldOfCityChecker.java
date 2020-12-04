package Checkers;

import CityPackage.*;

import java.util.Scanner;

public class FieldOfCityChecker implements Checker {


    /*
    public City newCheckerOfFields(String[] str) {
        try{FileReader fileReader = new FileReader(String.valueOf(scanner1));
        }
        Scanner scanner2 = new Scanner(fileReader);
    }

     */


    @Override
    public City checkEverything(String[] nextRecord, int counter) {
        Scanner scanner = new Scanner(System.in);

        //String[] nextRecord = str.split(";", 10);

        String name = null;
        double x = 0;
        Integer y = null;
        Long area = null;
        long population = 0;
        Double metersAboveTheSea = null;
        Climate climate = null;
        Government government = null;
        StandardOfLiving standardOfLiving = null;
        Human governor = null;



        boolean bool = false;

        City newCity = new City(null, null, null, 0, null, null, null, null, null);

        if (nextRecord.length == 10) {

            if (!nextRecord[0].equals("")) {
                newCity.setName(nextRecord[0]);
            } else {
                while (nextRecord[0].equals("")) {
                    System.out.println("Поле <<Name>> в " + counter + " строке не задано. Пожалуйста, введите его значение");
                    nextRecord[0] = scanner.nextLine();
                }
                newCity.setName(nextRecord[0]);
            }

            while (x == 0) {
                try {
                    if (!nextRecord[1].equals("")) {
                        x = Double.parseDouble(nextRecord[1]);
                    } else {x = 0; break;}
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException. В " + counter + " строке несоответсвие. Пожалуйста, введите значение типа double");
                    nextRecord[1] = scanner.nextLine();
                }
            }

            while (y == null) {
                try {
                    y = Integer.valueOf(nextRecord[2]);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException. В " + counter + " строке несоответсвие. Пожалуйста, введите значение поля типа int");
                    nextRecord[2] = scanner.nextLine();
                }
            }
            Coordinates coordinates = new Coordinates(x, y);
            newCity.setCoordinates(coordinates);
            while ((area == null) || (Long.parseLong(nextRecord[3]) <= 0)) {
                try {
                    area = Long.valueOf(nextRecord[3]);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException. В " + counter + " строке несоответсвие. Пожалуйста, введите значение типа long, большее 0");
                    nextRecord[3] = scanner.nextLine();
                    continue;
                }
                newCity.setArea(area);
            }


            while (population <= 0) {
                try {
                    population = Long.parseLong(nextRecord[4]);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException. В " + counter + " строке несоответствие. Пожалуйста, введите значение типа long, большее 0");
                    nextRecord[4] = scanner.nextLine();
                    continue;
                }
                newCity.setPopulation(population);
            }

            while (metersAboveTheSea == null) {
                try {
                    if (!nextRecord[5].equals("")) {
                        metersAboveTheSea = Double.valueOf(nextRecord[5]);
                    } else {newCity.setMetersAboveSeaLevel(null); break;}
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException. В " + counter + " строке несоответствие. Пожалуйста, введите значение типа double");
                    nextRecord[5] = scanner.nextLine();
                    continue;
                }
                newCity.setMetersAboveSeaLevel(metersAboveTheSea);
            }
            /*
            if ((Climate.POLAR_ICECAP.toString().equals(nextRecord[6])) || (Climate.STEPPE.toString().equals(nextRecord[6])) ||
                    (Climate.SUBARCTIC.toString().equals(nextRecord[6])) || (Climate.TROPICAL_SAVANNA.toString().equals(nextRecord[6]))) {
                newCity.setClimate(nextRecord[6]);
            } else {
                System.out.println("Поле Climate в " + counter + " городе будет null");
            }

             */

            while (!bool) {

                switch (nextRecord[6]) {
                    case "POLAR_ICECAP":
                    case "STEPPE":
                    case "SUBARCTIC":
                    case "TROPICAL_SAVANNA":
                    case "null":
                        newCity.setClimate(nextRecord[6]);
                        bool = true;
                        break;
                    default:
                        System.out.println("Такого значения поля <<climate>> в " + counter + " строке нет, введите один из следующих вариантов: \n" +
                                           Climate.POLAR_ICECAP + "\n" +
                                           Climate.STEPPE + "\n" +
                                           Climate.SUBARCTIC + "\n" +
                                           Climate.TROPICAL_SAVANNA + "\n" +
                                           "null \n");
                        nextRecord[6] = scanner.nextLine();
                        bool = false;
                }
            }

            /*
            if ((Government.ANARCHY.toString().equals(nextRecord[7])) || (Government.CORPORATOCRACY.toString().equals(nextRecord[7])) ||
                    (Government.ETHNOCRACY.toString().equals(nextRecord[7])) || (Government.NOOCRACY.toString().equals(nextRecord[7])) ||
                    (Government.PLUTOCRACY.toString().equals(nextRecord[7]))) {
                newCity.setGovernment(nextRecord[7]);
            } else {
                System.out.println("Поле <<Government>> будет null");
            }

             */

            bool = false;

            while (!bool) {

                switch (nextRecord[7]) {
                    case "ANARCHY":
                    case "CORPORATOCRACY":
                    case "ETHNOCRACY":
                    case "NOOCRACY":
                    case "PLUTOCRACY":
                    case "null":
                        newCity.setGovernment(nextRecord[7]);
                        bool = true;
                        break;
                    default:
                        System.out.println("Такого значения поля <<government>> в " + counter + " строке нет, введите один из следующих вариантов: \n" +
                                           Government.ANARCHY + "\n" +
                                           Government.CORPORATOCRACY + "\n" +
                                           Government.ETHNOCRACY + "\n" +
                                           Government.NOOCRACY + "\n" +
                                           Government.PLUTOCRACY + "\n" +
                                           "null \n");
                        nextRecord[7] = scanner.nextLine();
                        bool = false;
                }
            }

            /*
            if ((StandardOfLiving.HIGH.toString().equals(nextRecord[8])) || (StandardOfLiving.MEDIUM.toString().equals(nextRecord[8])) ||
                    (StandardOfLiving.NIGHTMARE.toString().equals(nextRecord[8])) || (StandardOfLiving.ULTRA_HIGH.toString().equals(nextRecord[8])) ||
                    (StandardOfLiving.ULTRA_LOW.toString().equals(nextRecord[8]))) {
                newCity.setStandartOfLiving(nextRecord[8]);
            } else {
                System.out.println("Поле StandardOfLiving в " + counter + " городе будет  null");
            }

             */

            bool = false;

            while (!bool) {
                switch (nextRecord[8]) {
                    case "ULTRA_HIGH":
                    case "HIGH":
                    case "MEDIUM":
                    case "NIGHTMARE":
                    case "ULTRA_LOW":
                    case "null":
                        newCity.setStandartOfLiving(nextRecord[8]);
                        bool = true;
                        break;
                    default:
                        System.out.println("Такого значения поля <<standardOfLiving>> в " + counter + " строке нет, введите один из следующих вариантов: \n" +
                                           StandardOfLiving.ULTRA_HIGH + "\n" +
                                           StandardOfLiving.HIGH + "\n" +
                                           StandardOfLiving.MEDIUM + "\n" +
                                           StandardOfLiving.NIGHTMARE + "\n" +
                                           StandardOfLiving.ULTRA_LOW + "\n" +
                                           "null \n");
                        nextRecord[8] = scanner.nextLine();
                        bool = false;
                }
            }
            bool = false;

            if (!nextRecord[9].equals("")) {
                newCity.setGovernor(new Human(nextRecord[9]));
            } else {
                System.out.println("Поле <<governor>> в " + counter + " строке будет null");
                newCity.setGovernor(null);
            }

            //hashtable.put(id, new City(id, name, coordinates, creationDate, area, population, metersAboveTheSea, climate, government, standardOfLiving, governor));
            //hashtable.setAuthDateTime(LocalDateTime.now());

                    /*
                    Set<Long> keys = newCity.keySet();
                    for(Long key: keys){

                    }*/

        } else {
            System.out.println("Не достаточно полей в данном файле" + "\n" +
                               "Пожалуйста, исправьте свой файл" + "\n" +
                               "Необходимое количество полей: 10");
            System.exit(0);
        }
        return newCity;
    }
}



