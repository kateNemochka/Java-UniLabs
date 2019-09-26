/* TO-DO:
- кілька конструкторів класу
- визначення методу з одним іменем, але різним списком аргументів
- інкапсуляція даних
- статичне поле
- статичний метод

- створити масив об’єктів класу та виконати тести методів
*/
package Lab1;


public class Car implements Comparable<Car> {

    private int carId;
    private static int nextId = 1;
    private String brand;
    private int year;
    private int speed;


    public Car() {
        carId = nextId++;
        speed = 0;
        this.brand = "Brand Unknown";
        this.year = -1;
    }

    public Car(String brand, int year) {
        carId = nextId++;
        speed = 0;
        this.brand = brand;
        this.year = year;
    }


    public int compareTo(Car car) {
        if (this.carId == car.carId) {
            return 0;
        }
        else if (this.speed > car.carId) {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        System.out.printf("Car ID: %d\nBrand: %s\nYear: %d\nCurrent speed: %d km/h\n\n",
                carId, brand, year, speed);
    }


    public static void main(String[] args) {
        // array with Car objects
        Car[] cars = new Car[4];
        cars[0] = new Car();
        cars[1] = new Car("Nissan", 2008);
        cars[2] = new Car("BMW", 2019);
        cars[3] = new Car("Suzuki", 2017);

        // accelerating cars
        for (Car car : cars) {
            car.accelerate();
        }
        for (int i = 0; i <= 8; ++i) {
            cars[i%4].accelerate(9 * (i % 5));
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

        // adding car info
        System.out.println("\nAdding car 1 missing information");
        cars[0].setBrand("Tesla");
        cars[0].setYear(2015);
        cars[0].info();

    }

}
