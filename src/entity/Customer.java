package entity;

import OSPABA.Entity;
import OSPABA.Simulation;
import OSPRNG.EmpiricRNG;
import OSPRNG.EmpiricPair;
import OSPRNG.UniformContinuousRNG;
import OSPRNG.UniformDiscreteRNG;

public class Customer extends Entity
{
	private double _arrivalTimeToSystem;	
	private double _allWaitingTime;
	
	public Customer(Simulation sim)
	{
		super(sim);		
		_arrivalTimeToSystem = sim.currentTime();		
		_allWaitingTime = .0; 
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

	
}

