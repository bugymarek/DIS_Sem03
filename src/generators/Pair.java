package generators;

public class Pair {
    private double interval;
    private double lambda;
    private int index;

    public Pair(double interval, double lambda) {
        this.interval = interval;
        this.lambda = lambda;
    }

    public double getInterval() {
        return interval;
    }

    public double getLambda() {
        return lambda;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
