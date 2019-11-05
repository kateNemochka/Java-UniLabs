/* TO-DO:
- кілька конструкторів класу
- визначення методу з одним іменем, але різним списком аргументів
- інкапсуляція даних
- статичне поле
- статичний метод
- внутрішній клас

- створити масив об’єктів класу та виконати тести методів
*/
package Lab1;


import java.util.Arrays;

public class Car implements Comparable<Car> {

    private int carId;
    private static int nextId = 1;
    private TechnicalPassport techPassport;
    private int speed;


    public Car() {
        carId = nextId++;
        speed = 0;
        techPassport = new TechnicalPassport();
    }

    public Car(String brand, String regNumber, String color,
               int productionYear, int registrationYear) {
        carId = nextId++;
        speed = 0;
        techPassport = new TechnicalPassport(brand, regNumber, color, productionYear, registrationYear);
    }


    private class TechnicalPassport {
        private String brand;
        private String regNumber;
        private String color;
        private int productionYear;
        private int registrationYear;


        TechnicalPassport() {
            brand = null;
            regNumber = null;
            color = null;
            productionYear = 0;
            registrationYear = 0;
        }

        TechnicalPassport(String brand, String regNumber, String color,
                          int productionYear, int registrationYear) {
            this.brand = brand;
            this.regNumber = regNumber;
            this.color = color;
            this.productionYear = productionYear;
            this.registrationYear = registrationYear;
        }

        public void setProductionData(String brand, String color, int productionYear) {
            this.brand = brand;
            this.color = color;
            this.productionYear = productionYear;
        }

        public void setRegistrationData(String regNumber, int registrationYear) {
            this.regNumber = regNumber;
            this.registrationYear = registrationYear;
        }

        public String getRegNumber() {
            return regNumber;
        }

        public int getProductionYear() {
            return productionYear;
        }

        public void info() {
            System.out.printf("Brand: %s\nColor: %s\nProduction Year: %d\n" +
                            "Registration Number: %s\nRegistration Year: %d\n",
                    brand, color, productionYear, regNumber, registrationYear);
        }

    }


    public int compareTo(Car car) {
        // Значення null вважаються рівними "ЯЯ9999ЯЯ", щоб розмістити їх в кінці масиву при сортуванні
        if (car.getRegNumber() == null) {
            return this.getRegNumber().compareTo("ЯЯ9999ЯЯ");
        }
        else if (this.getRegNumber() == null) {
            return "ЯЯ9999ЯЯ".compareTo(car.getRegNumber());
        }
        else
            return this.getRegNumber().compareTo(car.getRegNumber());
    }

    public int getCarId() {
        return carId;
    }

    public int getSpeed() {
        return speed;
    }


    public String getColor() {
        return techPassport.color;
    }

    public String getBrand() {
        return techPassport.brand;
    }

    public int getProductionYear() {
        return techPassport.productionYear;
    }

    public int getRegistrationYear() {
        return techPassport.registrationYear;
    }


    public final String getRegNumber() {
        return techPassport.getRegNumber();
    }

    public void setCarInfo(String brand, String regNumber, String color,
                           int productionYear, int registrationYear) {
        techPassport.setProductionData(brand, color, productionYear);
        techPassport.setRegistrationData(regNumber, registrationYear);
    }

    public final void setCarRegInfo(String regNumber, int registrationYear) {
        techPassport.setRegistrationData(regNumber, registrationYear);
    }

    public final void setCarProdInfo(String brand, String color, int productionYear) {
        techPassport.setProductionData(brand, color, productionYear);
    }


    public static int distance(int speed, int time) {
        return speed * time;
    }

    public final void accelerate() {
        speed += 5;
    }

    public final void accelerate(int delta) {
        speed += delta;
    }

    public void info() {
        System.out.printf("Car ID: %d\n", carId);
        techPassport.info();
    }



    public static void main(String[] args) {
        // array with Car objects
        int numberOfCars = 3;
        Car[] cars = new Car[numberOfCars];
        cars[0] = new Car();
        cars[1] = new Car("Nissan", "ВІ7651ЕН", "black",
                    2009, 2015);
        cars[2] = new Car("BMW", "АА7667АА","green",
                    2019, 2019 );

        Arrays.sort(cars);

        // accelerating cars
        for (Car car : cars) {
            car.accelerate();
        }
        for (int i = 0; i <= 8; ++i) {
            cars[i%2].accelerate(9 * (i % 5));
        }

        // displaying info about every car
        for (Car car : cars) {
            car.info();
        }

        // displaying the distance car will overcome in 2 hours
        for (Car car : cars) {
            System.out.printf("Car ID: %d; speed: %d km/h\n",
                    car.getCarId(), car.getSpeed());
            System.out.printf("\tDistance in 2 hours: %d km\n", distance(car.getSpeed(), 2));
        }

        // adding information about empty car entity
        int noneIndex = -1;
        for (int i = 0; i < numberOfCars; ++i) {
            if (cars[i].getRegNumber() == null) {
                noneIndex = i;
                break;
            }
        }
        System.out.println(noneIndex);
        cars[noneIndex].setCarInfo("Tesla", "АІ2547АМ", "white",
                            2015, 2017);

        Arrays.sort(cars);

        for (Car car : cars) {
            car.info();
        }
    }

}
