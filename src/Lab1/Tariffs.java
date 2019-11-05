package Lab1;

import java.lang.reflect.Type;

public class Tariffs {
    private double basicFare;
    private double night;
    private double comfort;
    private double green;

    public Tariffs() {
        basicFare = 0.0;
        night = 0.0;
        comfort = 0.0;
        green = 0.0;
    }

    public Tariffs(double basicFare) {
        this.basicFare = basicFare;
        this.night = basicFare / 4;
        this.comfort = basicFare / 2;
        this.green = basicFare / 4;
    }

    public Tariffs(double basicFare, double night, double comfort, double green) {
        this.basicFare = basicFare;
        this.night = night;
        this.comfort = comfort;
        this.green = green;
    }


    public double getBasicFare() {
        return basicFare;
    }
    public void setBasicFare(double fare) {
        basicFare = fare;
    }

    public double getNight() {
        return night;
    }
    public void setNight(double night) {
        this.night = night;
    }

    public double getComfort() {
        return comfort;
    }
    public void setComfort(double comfort) {
        this.comfort = comfort;
    }

    public double getGreen() {
        return green;
    }
    public void setGreen(double green) {
        this.green = green;
    }

    public double getFare(Class<?> taxiType) {
        if (taxiType == StandardTaxi.class) {
            return getBasicFare();
        }
        else if (taxiType == ComfortTaxi.class) {
            return getComfort() + getBasicFare();
        }
        else if (taxiType == GreenTaxi.class) {
            return getGreen() + getBasicFare();
        }
        return 0.0;
    }


    public double calculateStandart(double distance, int n) {
        double fare = basicFare + n * night;
        return fare * distance;
    }

    public double calculateComfort(double distance, int n) {
        return calculateStandart(distance, n) + comfort * distance;
    }

    public double calculateGreen(double distance, int n) {
        return calculateStandart(distance, n) + green * distance;
    }


    public void info() {
        System.out.printf("\nFare per kilometer\n\tBasic: %.2f\n\tComfort: %.2f\n" +
                          "\tGreen: %.2f\n\tNight Extra Pay: %.2f\n\n",
                            basicFare, basicFare+comfort, basicFare+green, night);
    }


    public static void main(String[] args) {
        Tariffs t1 = new Tariffs(5.0);
        Tariffs t2 = new Tariffs(4.0, 1.0, 3.0, 2.0);

        t1.info();
        t2.info();

        double distance = 5.5;
        System.out.println("Calculations check");
        System.out.printf("T1 Basic: %.2f\n",       t1.calculateStandart(distance,0));
        System.out.printf("T2 Basic: %.2f\n",       t2.calculateStandart(distance,0));
        System.out.printf("T1 Basic Night: %.2f\n", t1.calculateStandart(distance,1));
        System.out.printf("T1 Comfort: %.2f\n",     t1.calculateComfort(distance,0));
        System.out.printf("T1 Green: %.2f\n",       t1.calculateGreen(distance,0));
        System.out.printf("T1 Green Nigth: %.2f\n", t1.calculateGreen(distance,1));

        t1.setGreen(4.0);

        t1.info();

        System.out.println(t1.getFare(GreenTaxi.class));
    }
}
