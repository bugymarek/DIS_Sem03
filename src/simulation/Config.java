/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

/**
 *
 * @author Bugy
 */
public class Config {
    //  all constants are represented as second units

    public static double SimulationTime = 30.0 * 24.0 * 60.0 * 60.0;

    public static double LengthRentalToT1 = ((60.0 * 60.0) / 35.0) * 6.4;
    public static double LengthT1ToT2 = ((60.0 * 60.0) / 35.0) * 0.5;
    public static double LengthT2ToRental = ((60.0 * 60.0) / 35.0) * 2.5;

    public static double averageArrivalT1 = ((60.0 * 60.0) / 43.0);
    public static double averageArrivalT2 = ((60.0 * 60.0) / 19.0);

    public static double OperatingLowerLimit = 2.0 * 60.0;
    public static double OperatingUpperLimit = 10.0 * 60.0;

    public static double BoardingLowerLimit = 10.0;
    public static double BoardingUpperLimit = 14.0;

    public static double GetOutOfBusLowerLimit = 4.0;
    public static double GetOutOfBusUpperLimit = 12.0;

    public static int countMinibuses;
    public static int countOperators;
    

    public static int pocetReplikacii;
    public static boolean novySposobPrace;
    public static int pocetPracovnikovZelenina;
    public static int pocetPracovnikovMasoSyr;
    public static int pocetPracovnikovPokladne;
}
