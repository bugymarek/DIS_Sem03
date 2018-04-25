/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators;

import java.util.Random;

/**
 *
 * @author Bugy
 */
public class UniformRangeDistribution implements IRandom {

    private final Random Rnd;
    private final double UpperLimit;
    private final double LowerLimit;

    public UniformRangeDistribution(double UpperLimit, double LowerLimit, Random rnd) {
        this.UpperLimit = UpperLimit;
        this.LowerLimit = LowerLimit;
        this.Rnd = rnd;
    }

    @Override
    public double next() {
        return LowerLimit + (Rnd.nextDouble() * (UpperLimit - LowerLimit));
    }
}
