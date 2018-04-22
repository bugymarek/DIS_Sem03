/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import simulation.MySimulation;

/**
 *
 * @author Bugy
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MySimulation sim = new MySimulation();
		
		sim.onSimulationWillStart(s ->{
			System.out.println("Simulating...");
		});

		sim.simulate(3, 90000000d);
    }
    
}
