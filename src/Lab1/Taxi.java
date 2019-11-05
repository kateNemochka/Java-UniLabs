/*Властивості таксі
вартість проїзду (окремий клас?)
    звичайна
    нічна
    за місто
загальна відстань
відстань по категоріях (для статистики?)
заробіток
тип авто (економ, комфорт)? -
кількість місць в салоні

* Функції
виконати замовлення (addRide)
розрахувати замовлення
інформація про таксі
скидання до дефолту
* */
// +техогляд?
package Lab1;

public class Taxi extends Car {
    private static int nextId = 0;
    private int taxiId;
    private String driver;
    private double profit;
    private double totalDistance;


    public Taxi() {
        super();
        taxiId = ++nextId;
        driver = null;
        profit = 0.0;
        totalDistance = 0.0;
    }

    public Taxi(String brand, String regNumber, String color,
                int productionYear, int registrationYear) {
        super(brand, regNumber, color, productionYear, registrationYear);
        taxiId = ++nextId;
        driver = null;
        profit = 0.0;
        totalDistance = 0.0;
    }

    public Taxi(String driver, String brand, String regNumber, String color,
                int productionYear, int registrationYear) {
        super(brand, regNumber, color, productionYear, registrationYear);
        taxiId = ++nextId;
        this.driver = driver;
        profit = 0.0;
        totalDistance = 0.0;
    }


    public int getID() {
        return taxiId;
    }

    public String getTaxiType() {
        return "Basic taxi";
    }

    public String getDriver() {
        return driver;
    }
    public void setDriver(String driverName) {
        driver = driverName;
    }

    public double getProfit() {
        return profit;
    }
    protected void addProfit(double profit) {
        this.profit += profit;
    }

    public double getTotalDistance() {
        return totalDistance;
    }
    protected void addDistance(double distance) {
        this.totalDistance += distance;
    }

    public double getAverageFare() {
        return profit / totalDistance;
    }

    public double getFare(Tariffs tariff) {
        return tariff.getFare(this.getClass());
    }


    public void addRide(Tariffs tariff, double distance){
        double fare = tariff.calculateStandart(distance, 0);
        profit += fare;
        totalDistance += distance;
    }

    public void addNightRide(Tariffs tariff, double distance) {
        double fare = tariff.calculateStandart(distance, 1);
        profit += fare;
        totalDistance += distance;
    }


    public void reset() {
        profit = 0.0;
        totalDistance = 0.0;
    }

    @Override
    public void info() {
        carInfo();
        taxiInfo();
    }
    public void carInfo() {
        super.info();
    }
    public final void taxiInfo() {
        System.out.printf("--------\nTAXI ID: %d\n========\n", taxiId);
        System.out.println(getTaxiType());
        System.out.printf("Driver: %s\nTotal profit: %.2f\nTotal distance: %.2f\n",
                driver, profit, totalDistance);

    }

    @Override
    public String toString() {
        return "Taxi{" +
                "taxiId=" + taxiId +
                ", driver='" + driver + '\'' +
                ", profit=" + profit +
                ", totalDistance=" + totalDistance +
                '}';
    }


    public static void main(String[] args) {
        Tariffs tariff = new Tariffs(5.0);

        Taxi t1 = new Taxi();

        Taxi t2 = new Taxi("John Smith", "Nissan", "ВН0987КА",
                            "blue", 2008, 2016);

        Taxi t3 = new Taxi("Citroen", "АК5609АН",
                            "black", 2010, 2017);

        t1.info();
        t2.info();
        t3.info();

        t3.setDriver("Kora Nem");
        System.out.println("");
        t3.info();
        System.out.println();

        t2.addRide(tariff, 23);
        t1.addNightRide(tariff, 10);

        System.out.println("Taxi 1");
        t1.taxiInfo();
        System.out.println(t1.getAverageFare());

        System.out.println("Taxi 2");
        t2.taxiInfo();
        System.out.println(t2.getAverageFare());

        System.out.println("Taxi 3");
        t3.taxiInfo();
        System.out.println(t3.getAverageFare());
    }
}
