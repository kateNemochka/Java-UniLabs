package Lab3;

/*
1.	Розробити клас, в якому агрегуються об’єкти класу, розробленого на лабораторній роботі 2,
    у вигляді колекції інтерфейсного типу Collection. В цьому класі розробити методи, які надають можливість (30 балів):
    •	відшукувати об’єкт, який відповідає заданим вимогам;
    •	створювати колекцію, яка містить тільки унікальні елементи;
    •	порівнювати об’єкти за різними критеріями якості;
    •	виконувати операцію фільтрації об’єктів за заданою ознакою,
    •	виконувати визначення середнього значення кількісної ознаки об’єкта.
*/
/*
2.	Розробити метод для сортування об’єктів за вибраним критерієм якості, використовуючи різні способи:
*/
/*  1) з анонімним класом,
            interface HelloWorld {
                public void greet();
                public void greetSomeone(String someone);
            }
            public void sayHello() {
                class EnglishGreeting implements HelloWorld {
                    String name = "world";
                    public void greet() {
                        greetSomeone("world");
                    };
                    public void greetSomeone(String someone) {
                        name = someone;
                        System.out.println("Hello " + name);
                    }
                }*/
/*  2) з лямбда-виразом,
            myShapesCollection.stream()
                .filter( e -> e.getColor() == Color.RED)
                .forEach(e -> System.out.println(e.getName()));*/
/*  3) з посиланням на методи.
            String joined = elements.stream()
                    .map( Object::toString )
                    .collect(Collectors.joining(", "));
            int total = employees.stream()
                    .collect(Collectors.summingInt( Employee::getSalary)));
    Зробити висновки про переваги кожного способу  (20 балів).
*/

import Lab1.*;

import java.util.*;
import java.util.stream.Collectors;

/*
Альтернативна реалізація TaxiPark з використанням Collections
*/

public class CollectionTaxiPark extends TaxiPark {

    public CollectionTaxiPark(double fare) {
        super(fare);
    }

    @Override
    public double getTotalProfit() {
        return getCars().stream()
                .mapToDouble(Taxi::getProfit)
                .sum();
    }
    @Override
    public double getTotalDistance() {
        return getCars().stream()
                .collect(Collectors.summingDouble(Taxi::getTotalDistance));
    }

    // * пошук середнього *
    public double getAverageProfit() {
        return getCars().stream()
                .mapToDouble(Taxi::getProfit)
                .average().getAsDouble();
    }

    // * пошук об’єкта (1го чи декількох?), який відповідає заданим вимогам *
    // пошук з номерним знаком
    public Taxi getTaxiWithRegNumber(String regNumber) {
        return getCars().stream()
                .filter(taxi -> taxi.getRegNumber().equals(regNumber))
                .findFirst().get();
    }

    // * фільтрація об'єктів за заданою ознакою
    // таксі з або без водія
    public ArrayList<Taxi> getTaxisWithoutDriver() {
        return (ArrayList<Taxi>) getCars().stream()
                .filter(taxi -> taxi.getDriver() == null)
                .collect(Collectors.toList());
    }
    public ArrayList<Taxi> getTaxisWithDriver() {
        return (ArrayList<Taxi>) getCars().stream()
                .filter(taxi -> taxi.getDriver() != null)
                .collect(Collectors.toList());
    }

    public ArrayList<Taxi> filterByProfitMoreThan(double profit) {
        return (ArrayList<Taxi>) getCars().stream()
                .filter(taxi -> taxi.getProfit() > profit)
                .collect(Collectors.toList());
    }

    // * створювати колекцію, яка містить тільки унікальні елементи *
    public Set<String> getAvailableTaxiTypes() {
        return getCars().stream()
                .map(Taxi::getTaxiType)
                .collect(Collectors.toSet());
    }

    // * порівняння *
    // порівняти за прибутком
    // порівняти за ціною
    public int compareTaxis(Taxi taxi1, Taxi taxi2, int price, int profit) {
        Comparator<Taxi> compareByProfit = new Comparator<Taxi>() {
            @Override
            public int compare(Taxi t1, Taxi t2) {
                return Double.compare(t1.getProfit(), t2.getProfit());
            }
        };
        Comparator<Taxi> compareByPrice = new Comparator<Taxi>() {
            @Override
            public int compare(Taxi t1, Taxi t2) {
                return Double.compare(t1.getFare(getTariff()), t2.getFare(getTariff()));
            }
        };
        if (price == 1 && profit == 0) {
            return compareByPrice.compare(taxi1, taxi2);
        }
        else if (price == 0 && profit == 1) {
            return compareByProfit.compare(taxi1, taxi2);
        }
        else {
            int res1 = compareByPrice.compare(taxi1, taxi2);
            if (res1 == 0) {
                return compareByProfit.compare(taxi1, taxi2);
            }
            else {
                return res1;
            }
        }
    }

    // * сортування *
    /*
    * 1 - ціна проїзду
    * 2 - прибуток
    * */
    public void lambdaSort() {
        ArrayList<Taxi> taxis = getCars();
        taxis.sort(
                (Taxi t1, Taxi t2) -> {
                    if (t1.getFare(getTariff()) == t2.getFare(getTariff())) {
                        if (t1.getProfit() == t2.getProfit()) {
                            return 0;
                        }
                        else {
                            return (t1.getProfit() < t2.getProfit()) ? -1 : 1;
                        }
                    }
                    else {
                        return (t1.getFare(getTariff()) < t2.getFare(getTariff())) ? -1 : 1;
                    }
                });
        setCars(taxis);
    }
    public void linkSort() {
        ArrayList<Taxi> taxis = getCars();
        taxis.sort(Comparator.comparing(Taxi::getProfit));
        setCars(taxis);
    }

    public void anonymousSort() {
        ArrayList<Taxi> taxis = getCars();
        taxis.sort(new Comparator<Taxi>() {
            @Override
            public int compare(Taxi t1, Taxi t2) {
                if (t1.getFare(getTariff()) == t2.getFare(getTariff())) {
                    if (t1.getProfit() == t2.getProfit()) {
                        return 0;
                    }
                    else {
                        return (t1.getProfit() < t2.getProfit()) ? -1 : 1;
                    }
                }
                else {
                    return (t1.getFare(getTariff()) < t2.getFare(getTariff())) ? -1 : 1;
                }
            }
        });
        setCars(taxis);
    }


    public void printParkInfo() {
        getCars().stream().forEach(taxi -> taxi.info());
    }
    public void printParkBrief() {
        getCars().forEach(Taxi::taxiInfo);
    }


    public static void main(String[] args) {
        CollectionTaxiPark taxiPark = new CollectionTaxiPark(4);
        taxiPark.addTaxi(0, "John Smith","Nissan", "АА9087КН",
                "red", 2015, 2015);
        taxiPark.addTaxi(0, "Citroen", "АВ4523ВА",
                "blue", 2009, 2014);
        taxiPark.addTaxi(2, "Ford", "АІ7834АК",
                "white", 2011, 2016);
        taxiPark.addTaxi(2,"BMW", "АА1001АА",
                "gray", 2018, 2019);
        taxiPark.addTaxi(0, "Anonymous Driver", "Daewoo", "АК9080ВА",
                "blue", 2016, 2016);

        System.out.println("Taxis with driver:");
        taxiPark.getTaxisWithDriver().stream()
                .forEach(taxi -> taxi.taxiInfo());
        System.out.println("\nTaxis without driver: ");
        taxiPark.getTaxisWithoutDriver().stream()
                .forEach(taxi -> taxi.taxiInfo());

        // adding new rides
        taxiPark.addRide(5, 4.0, true);
        taxiPark.addRide(2, 6.5, false);
        taxiPark.addRide(0, 15.0, false);
        taxiPark.addRide(3, 12.0, true);
        taxiPark.addRide(1, 26.0, false);

        taxiPark.addRide(1, 8.0, true);
        taxiPark.addRide(2, 14.4, false);
        taxiPark.addRide(0, 4.0, true);
        taxiPark.addRide(3, 7.8, false);

        System.out.println("\nAvailable Taxi Types:");
        System.out.println(taxiPark.getAvailableTaxiTypes());
        System.out.println("\nTaxi with АА1001АА registration number");
        taxiPark.getTaxiWithRegNumber("АА1001АА").info();

        System.out.print("\nAverage Profit: ");
        System.out.println(taxiPark.getAverageProfit());

        System.out.println("\nComparing taxi 1 and taxi 2 by price");
        System.out.println(taxiPark.compareTaxis(taxiPark.getCar(1), taxiPark.getCar(2), 1, 0));
        System.out.println(taxiPark.getCar(1).getFare(taxiPark.getTariff()));
        System.out.println(taxiPark.getCar(2).getFare(taxiPark.getTariff()));

        System.out.println("\nFiltering taxis by profit: profit more than 110");
        System.out.println(taxiPark.filterByProfitMoreThan(110.0));

        taxiPark.lambdaSort();
        System.out.println("\nTaxi park after sorting cars");
        taxiPark.printParkBrief();
    }

}



