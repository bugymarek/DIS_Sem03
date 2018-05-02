package entity;

import OSPABA.Entity;
import OSPABA.Simulation;
import OSPRNG.EmpiricRNG;
import OSPRNG.EmpiricPair;
import OSPRNG.UniformContinuousRNG;
import OSPRNG.UniformDiscreteRNG;

public class Customer extends Entity {

    private double _arrivalTimeToSystem;
    private double _allWaitingTime;
    private int _ID;
    private String _terminal;
    private int _passengersCount;
    private static UniformContinuousRNG _followPassengersProbabilityGeneratro = new UniformContinuousRNG(0d, 1d);

    public Customer(int id, String terminal, Simulation sim) {
        super(sim);
        _arrivalTimeToSystem = sim.currentTime();
        _allWaitingTime = .0;
        _ID = id;
        _terminal = terminal;
        _passengersCount = generatePassengersCount(_followPassengersProbabilityGeneratro.sample());
    }

    public void setArrivalTimeToSystem(double _arrivalTimeToSystem) {
        this._arrivalTimeToSystem = _arrivalTimeToSystem;
    }

    public void setAllWaitingTime(double _allWaitingTime) {
        this._allWaitingTime = _allWaitingTime;
    }

    public double getArrivalTimeToSystem() {
        return _arrivalTimeToSystem;
    }

    public double getAllWaitingTime() {
        return _allWaitingTime;
    }

    public int getID() {
        return _ID;
    }

    public void setID(int id) {
        this._ID = id;
    }

    public void setGeneratedTerminal(String _terminal) {
        this._terminal = _terminal;
    }

    public String getGeneratedTerminal() {
        return _terminal;
    }

    public String getGeneratedTerminalAndID() {
        return this._terminal + "/" + this._ID;
    }

    public int getPassengersCount() {
        return _passengersCount;
    }

    private int generatePassengersCount(double probability) {
        if (probability <= 0.60) {
            return 1;
        } else if (probability <= 0.80) {
            return 2;
        } else if (probability <= 0.95) {
            return 3;
        }
        return 4;
    }
}
