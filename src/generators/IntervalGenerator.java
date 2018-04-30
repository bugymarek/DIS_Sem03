package Generators;

import OSPABA.Simulation;
import OSPRNG.ExponentialRNG;
import generators.Pair;
import simulation.MyMessage;
import simulation.MySimulation;

import java.util.ArrayList;
import java.util.List;

public class IntervalGenerator {

    private List<ExponentialRNG> generators;
    private List<Pair> pairs;
    private Simulation mySim;

    public IntervalGenerator(List<Pair> values, Simulation sim) {
        generators = new ArrayList<>();
        mySim = sim;
        pairs = values;
        int i = 0;
        for (Pair value : values) {
            value.setIndex(i);
            i++;
            generators.add(new ExponentialRNG((60.0*60.0)/value.getLambda()));
        }
    }

    public double sample(){
        Pair pair = null;
        for (Pair p : pairs) {
            if(((p.getInterval()) - (15.0*60.0) )<= mySim.currentTime()){
                pair = p;
            }
        }
        
        double result = generators.get(pair.getIndex()).sample();
        double timeResult = result + mySim.currentTime();
        if (timeResult > pair.getInterval()) {
            int index = pair.getIndex();
            double space = timeResult - pair.getInterval();
            double lowerSpace = result - space;
            double thisLambda = pair.getLambda();
            int size = pairs.size();
            int biggerIndex = index + 1;
            int nextIndex = (index == size - 1) ? index : biggerIndex;
            double nextLambda = pairs.get(nextIndex).getLambda();
            double rate = thisLambda / nextLambda;
            result = ((space) * (rate) + lowerSpace);
        }

        return result;
    }

}
