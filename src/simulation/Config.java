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

    //public static double SimulationTime = 30.0 * 24.0 * 60.0 * 60.0;
    public static double SimulationTime = 270.0*60.0;
    public static int ReplicationsCount;

//    public static double LengthRentalToT1 = ((60.0 * 60.0) / 35.0) * 6.4;
//    public static double LengthT1ToT2 = ((60.0 * 60.0) / 35.0) * 0.5;
//    public static double LengthT2ToRental = ((60.0 * 60.0) / 35.0) * 2.5;
    
    public static double LengthRentalToT3 = ((60.0 * 60.0) / 35.0) * 2.9;
    public static double LengthRentalToT1 = ((60.0 * 60.0) / 35.0) * 2.5;
    public static double LengthT3ToT1 = ((60.0 * 60.0) / 35.0) * 0.9;
    public static double LengthT1ToT2 = ((60.0 * 60.0) / 35.0) * 0.5;
    public static double LengthT2ToRental = ((60.0 * 60.0) / 35.0) * 3.4;

    //public static double averageArrivalT1 = ((60.0 * 60.0) / 43.0);
    //public static double averageArrivalT2 = ((60.0 * 60.0) / 19.0);
    //public static double averageArrivalRental = ((60.0 * 60.0) / 25.0);

    //public static double OperatingLowerLimit = 2.0 * 60.0;
    //public static double OperatingUpperLimit = 10.0 * 60.0;

//    public static double BoardingLowerLimit = 10.0;
//    public static double BoardingUpperLimit = 14.0;
//
//    public static double GetOutOfBusLowerLimit = 4.0;
//    public static double GetOutOfBusUpperLimit = 12.0;
    
    public static double BoardingLowerLimit = 10.0;
    public static double BoardingUpperLimit = 14.0;

    public static double GetOutOfBusLowerLimit = 2.0;
    public static double GetOutOfBusUpperLimit = 10.0;

    public static int MinibusesCount;
    public static int OperatorsCount;
    
    public static final double[][] Intervals ={
        {15d*60d,    4,  3,  12},
        {30d*60d,    8,  6,  9},
        {45d*60d,    12, 9,  18},
        {60d*60d,    15, 15, 28},
        {75d*60d,    18, 17, 23},
        {90d*60d,    14, 19, 21},
        {105d*60d,   13, 14, 16},
        {120d*60d,   10, 6,  11},
        {135d*60d,   4,  3,  17},
        {150d*60d,   6,  4,  22},
        {165d*60d,   10, 21, 36},
        {180d*60d,   14, 14, 24},
        {195d*60d,   16, 19, 32},
        {210d*60d,   15, 12, 16},
        {225d*60d,   7,  5,  13},
        {240d*60d,   3,  2,  13},
        {255d*60d,   4,  3,  5},
        {270d*60d,   2,  3,  4}
    };
    
    public static String RentalName = "Rental";
    
   
}
