package Lab1;

public class GreenTaxi extends Taxi{
    public GreenTaxi() {
        super();
    }

    public GreenTaxi(String brand, String regNumber, String color,
                     int productionYear, int registrationYear) {
        super(brand, regNumber, color, productionYear, registrationYear);
    }

    public GreenTaxi(String driver, String brand, String regNumber, String color,
                     int productionYear, int registrationYear) {
        super(driver, brand, regNumber, color, productionYear, registrationYear);
    }

    @Override
    public void addRide(Tariffs tariff, double distance) {
        double fare = tariff.calculateGreen(distance, 0);
        addDistance(distance);
        addProfit(fare);
    }

    @Override
    public void addNightRide(Tariffs tariff, double distance) {
        double fare = tariff.calculateGreen(distance, 1);
        addDistance(distance);
        addProfit(fare);
    }

    @Override
    public String getTaxiType() {
        return "Green";
    }
}
