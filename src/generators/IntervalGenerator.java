package Generators;

import OSPABA.Simulation;
import OSPRNG.ExponentialRNG;
import java.util.ArrayList;
import simulation.Config;

public class IntervalGenerator {

    private Simulation _mySim;
    private ArrayList<ExponentialRNG> _generators;
    private int _indexTermininal;

    public IntervalGenerator(int index, Simulation sim) {
        _indexTermininal = index;
        _mySim = sim;
        generateGenerators();
    }

    public double sample(){
        int intervalIndex = -1;
        double interval = 0;
        double lambda = 0;
        for (int i = 0; i < Config.Intervals.length; i++) {
            if ((Config.Intervals[i][0] - (15d * 60d) ) <= _mySim.currentTime()) {
                interval = Config.Intervals[i][0];
                intervalIndex = i;
                lambda = Config.Intervals[i][_indexTermininal];
            }
        }
        
        return checkGeneratedValue(intervalIndex, interval, lambda);
    }

    private void generateGenerators() {
        _generators = new ArrayList<>();
        for (double[] Interval : Config.Intervals) {
            _generators.add(new ExponentialRNG((60.0 * 60.0) / Interval[_indexTermininal]));
        }
    }
    
    private double checkGeneratedValue(int intervalIndex, double interval,double lambda){
        double generatedValue = _generators.get(intervalIndex).sample();
        double timeResult = generatedValue + _mySim.currentTime();
        if (timeResult > interval) {
            double space = timeResult - interval;
            double lowerSpace = generatedValue - space;
  
            if(intervalIndex != Config.Intervals.length - 1){
                intervalIndex++;
            }
            double nextLambda = Config.Intervals[intervalIndex][_indexTermininal];
            double rate = lambda / nextLambda;
            generatedValue = ((space) * (rate) + lowerSpace);
        }

        return generatedValue;
    }

}
