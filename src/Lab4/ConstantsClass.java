package Lab4;

import Lab1.Tariffs;
import Lab3.MapTaxiPark;

abstract class ConstantsClass {
    public static MapTaxiPark taxiPark = new MapTaxiPark(15);
    public static Tariffs tariff = taxiPark.getTariff();
}
