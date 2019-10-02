/* TO-DO:
- кілька конструкторів класу
- визначення методу з одним іменем, але різним списком аргументів
- інкапсуляція даних
- статичне поле
- статичний метод

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
               int productionYear, int engineVolume, int registrationYear) {
        carId = nextId++;
        speed = 0;
        techPassport = new TechnicalPassport(brand, regNumber, color, productionYear,
                                             engineVolume, registrationYear);
    }


    private class TechnicalPassport {
        private String brand;
        private String regNumber;
        private String color;
        private int productionYear;
        private int engineVolume;
        private int registrationYear;


        TechnicalPassport() {
            brand = "Unknown";
            regNumber = "Unknown";
            color = "Unknown";
            productionYear = 0;
            engineVolume = 0;
            registrationYear = 0;
        }

        TechnicalPassport(String brand, String regNumber, String color,
                          int productionYear, int engineVolume, int registrationYear) {
            this.brand = brand;
            this.regNumber = regNumber;
            this.color = color;
            this.productionYear = productionYear;
            this.engineVolume = engineVolume;
            this.registrationYear = registrationYear;
        }

        public void setProductionData(String brand, String color, int productionYear, int engineVolume) {
            this.brand = brand;
            this.color = color;
            this.productionYear = productionYear;
            this.engineVolume = engineVolume;
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
            System.out.printf("Brand: %s\nColor: %s\nProduction Year: %d\nEngine Volume: %d\n" +
                            "Registration Number: %s\nRegistration Year: %d\n",
                    brand, color, productionYear, engineVolume, regNumber, registrationYear);
        }

    }


    public int compareTo(Car car) {
        if (this.getRegNumber().equals(car.getRegNumber())) {
            return 0;
        }
        else if (this.getRegNumber().compareTo(car.getRegNumber()) < 0) {
            return 1;
        }
        else {
            return -1;
        }
    }

    public int getCarId() {
        return carId;
    }

    public int getSpeed() {
        return speed;
    }

    public String getRegNumber() {
        return techPassport.getRegNumber();
    }


    public static int distance(int speed, int time) {
        return speed * time;
    }

    public void accelerate() {
        speed += 5;
    }

    public void accelerate(int delta) {
        speed += delta;
    }

    public void info() {
        System.out.printf("Car ID: %d\n", carId);
        techPassport.info();
        System.out.printf("Current speed: %d\n\n", speed);
    }


    public static void main(String[] args) {
        // array with Car objects
        Car[] cars = new Car[2];
        //cars[0] = new Car();
        cars[0] = new Car("Nissan", "АІ7681ЕК", "black", 2008, 3, 2013);
        cars[1] = new Car("BMW", "АА0000АА","green", 2019, 4, 2019 );

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

        /*// adding car info
        System.out.println("\nAdding car 1 missing information");
        cars[0].setBrand("Tesla");
        cars[0].setYear(2015);
        cars[0].info();*/

    }

}
