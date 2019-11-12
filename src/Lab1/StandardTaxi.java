package Lab1;

public class StandardTaxi extends Taxi {
    public StandardTaxi() {
        super();
    }

    public StandardTaxi(String brand, String regNumber, String color,
                        int productionYear, int registrationYear) {
        super(brand, regNumber, color, productionYear, registrationYear);
    }

    public StandardTaxi(String driver, String brand, String regNumber, String color,
                        int productionYear, int registrationYear) {
        super(driver, brand, regNumber, color, productionYear, registrationYear);
    }

    @Override
    public void addRide(Tariffs tariff, double distance) {
        double fare = tariff.calculateStandart(distance, 0);
        addDistance(distance);
        addProfit(fare);
    }

    @Override
    public void addNightRide(Tariffs tariff, double distance) {
        double fare = tariff.calculateStandart(distance, 1);
        addDistance(distance);
        addProfit(fare);
    }

    @Override
    public String getTaxiType() {
        return "Standard";
    }
}
