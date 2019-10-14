/*TO-DO:
Розробити метод, який знаходить таксі з найменшим прибутком.

1)
* відношення спадкування, агрегування та залежності класів
* розробка екземплярів класу-агрегата з різними параметрами (subclasses?)
* виклик методів для екземплярів класу, що агрегуються + (TP, Taxi)
* розробка класів-нащадків +
* виклик перевизначених методів для екземплярів класів-нащадків і т.д.

2)
* вкладений клас (TechnicalPassport)
* локальний клас (Вартість??)

3) основні прийоми обробки масивів:
* відшукання заданого елемента
* відшукання найбільшого (найменшого) значення
* сортування масиву
* копіювання масиву
* */

/*Taxi Park Functions
* Brief Report:
* * profit
* * number of cars
* * list of cars
*/

/*Вимоги:
Спадкування
Перевизначення
Поліморфізм
Агрегування
Відношення*/

package Lab1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaxiPark {
    private int numberOfCars = 0;
    private ArrayList<Taxi> cars;
    private Tariffs tariff;


    TaxiPark(double fare) {
        cars = new ArrayList<Taxi>();
        tariff = new Tariffs(fare);
    }


    public void addTaxi(int type) {
        numberOfCars++;
        switch (type) {
            case (0):
                cars.add(new StandardTaxi());
                break;
            case (1):
                cars.add(new ComfortTaxi());
                break;
            case (2):
                cars.add(new GreenTaxi());
                break;
        }
    }

    public void addTaxi(int type, String brand, String regNumber, String color,
                        int productionYear, int registrationYear) {
        numberOfCars++;
        switch (type) {
            case (0):
                cars.add(new StandardTaxi(brand, regNumber, color, productionYear, registrationYear));
                break;
            case (1):
                cars.add(new ComfortTaxi(brand, regNumber, color, productionYear, registrationYear));
                break;
            case (2):
                cars.add(new GreenTaxi(brand, regNumber, color, productionYear, registrationYear));
                break;
        }
    }

    public void addTaxi(int type, String driver, String brand, String regNumber, String color,
                        int productionYear, int registrationYear) {
        numberOfCars++;
        switch (type) {
            case (0):
                cars.add(new StandardTaxi(driver, brand, regNumber, color, productionYear, registrationYear));
                break;
            case (1):
                cars.add(new ComfortTaxi(driver, brand, regNumber, color, productionYear, registrationYear));
                break;
            case (2):
                cars.add(new GreenTaxi(driver, brand, regNumber, color, productionYear, registrationYear));
                break;
        }
    }

    public void removeTaxi(int index) {
        cars.remove(index);
    }


    public void addRide(int index, double distance, boolean night) {
        if (index >= numberOfCars) {
            index = numberOfCars - 1;
        }
        Taxi taxi = cars.get(index);
        if (night) {
            taxi.addNightRide(tariff, distance);
        }
        else {
            taxi.addRide(tariff, distance);
        }
        cars.remove(index);
        cars.add(index, taxi);
    }


    public double getTotalProfit() {
        double carProfit = 0;
        for (Taxi car : cars) {
            carProfit += car.getProfit();
        }
        return carProfit;
    }

    public double getTotalDistance() {
        double carProfit = 0;
        for (Taxi car : cars) {
            carProfit += car.getTotalDistance();
        }
        return carProfit;
    }

    public int getTaxiIndex(String regNumber) {
        int index = 0;
        for (Taxi car : cars) {
            if (car.getRegNumber().equals(regNumber)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int getMinProfitTaxi() {
        double minProfit = cars.get(0).getProfit();
        int carIndex = 0;

        int i = 0;
        for (Taxi car : cars) {
            double profit = car.getProfit();
            if (profit < minProfit) {
                minProfit = profit;
                carIndex = i;
            }
            i++;
        }
        return carIndex;
    }

    public int getMaxProfitTaxi() {
        double maxProfit = cars.get(0).getProfit();
        int carIndex = 0;

        int i = 0;
        for (Taxi car : cars) {
            double profit = car.getProfit();
            if (profit > maxProfit) {
                maxProfit = profit;
                carIndex = i;
            }
            i++;
        }
        return carIndex;
    }


    public ArrayList<Taxi> getStandardTaxis() {
        ArrayList<Taxi> taxis = new ArrayList<>();
        for (Taxi taxi : cars) {
            if (taxi instanceof StandardTaxi) {
                taxis.add(taxi);
            }
        }
        return taxis;
    }

    public ArrayList<Taxi> getComfortTaxis() {
        ArrayList<Taxi> taxis = new ArrayList<>();
        for (Taxi taxi : cars) {
            if (taxi instanceof ComfortTaxi) {
                taxis.add(taxi);
            }
        }
        return taxis;
    }

    public ArrayList<Taxi> getGreenTaxis() {
        ArrayList<Taxi> taxis = new ArrayList<>();
        for (Taxi taxi : cars) {
            if (taxi instanceof GreenTaxi) {
                taxis.add(taxi);
            }
        }
        return taxis;
    }


    public void printCarInfo(int index) {
        cars.get(index).info();
    }

    public void printParkInfo() {
        int count = 0;
        for (Taxi taxi : cars) {
            System.out.printf("TAXI %d\n", ++count);
            taxi.info();
            System.out.println();
        }
    }

    public void printParkBrief() {
        int count = 0;
        for (Taxi taxi : cars) {
            System.out.printf("TAXI %d", ++count);
            taxi.taxiInfo();
            System.out.println();
        }
    }

    public void printParkTariffs() {
        tariff.info();
    }



    public static void main(String[] args) {
        TaxiPark taxiPark = new TaxiPark(4);

        // adding taxis for testing
        taxiPark.addTaxi(1, "John Smith","Nissan", "АА9087КН",
                "red", 2015, 2015);
        taxiPark.addTaxi(0, "Smith John", "Citroen", "АВ4523ВА",
                "blue", 2009, 2014);
        taxiPark.addTaxi(2, "Kora Nem", "АІ7834АК",
                "white", 2011, 2016);
        taxiPark.addTaxi(2, "Nem Kora","BMW", "АА1001АА",
                "gray", 2018, 2019);
        taxiPark.addTaxi(0, "Anonymous Driver", "Daewoo", "АК9080ВА",
                "green", 2016, 2016);

        // getting info about all available standard taxi
        System.out.println("***All Standard Taxis***");
        for (Taxi t : taxiPark.getStandardTaxis()) {
            t.info();
            System.out.println();
        }

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
        taxiPark.addRide(3, 27.3, false);

        System.out.println("\n\n***All taxi park cars***");
        taxiPark.printParkInfo();

        System.out.printf("Total Taxi Park Profit: %.2f\n", taxiPark.getTotalProfit());
        System.out.printf("Total Taxi Park Distance: %.2f\n\n", taxiPark.getTotalDistance());

        taxiPark.removeTaxi(2);

        System.out.printf("Car index with registration number %s: %d\n\n",
                "АВ4523ВА", taxiPark.getTaxiIndex("АВ4523ВА"));

        int minTaxiIndex = taxiPark.getMinProfitTaxi();
        int maxTaxiIndex = taxiPark.getMaxProfitTaxi();
        System.out.printf("Taxi with minimum profit:\n\tindex: %d\n\tprofit: %.2f\n",
                minTaxiIndex, taxiPark.cars.get(minTaxiIndex).getProfit());
        System.out.printf("Taxi with maximum profit:\n\tindex: %d\n\tprofit: %.2f\n",
                maxTaxiIndex, taxiPark.cars.get(maxTaxiIndex).getProfit());

    }

}
