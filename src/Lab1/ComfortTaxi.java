package Lab1;

public class ComfortTaxi extends Taxi{
    public ComfortTaxi() {
        super();
    }

    public ComfortTaxi(String brand, String regNumber, String color,
                int productionYear, int registrationYear) {
        super(brand, regNumber, color, productionYear, registrationYear);
    }

    public ComfortTaxi(String driver, String brand, String regNumber, String color,
                int productionYear, int registrationYear) {
        super(driver, brand, regNumber, color, productionYear, registrationYear);
    }

    @Override
    public void addRide(Tariffs tariff, double distance) {
        double fare = tariff.calculateComfort(distance, 0);
        addDistance(distance);
        addProfit(fare);
    }

    @Override
    public void addNightRide(Tariffs tariff, double distance) {
        double fare = tariff.calculateComfort(distance, 1);
        addDistance(distance);
        addProfit(fare);
    }

    @Override
    public String getTaxiType() {
        return "Comfort taxi";
    }



    public static void main(String[] args) {
        Tariffs tariff = new Tariffs(5.0);

        ComfortTaxi t = new ComfortTaxi("John Smith", "Nissan", "ВН0987КА",
                "blue", 2008, 2016);
        t.info();
        t.addRide(tariff, 10.0);
        System.out.println();
        t.taxiInfo();
    }
}
