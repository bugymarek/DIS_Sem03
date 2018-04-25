/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import simulation.Config;
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
        parseSimParams();
        MySimulation sim = new MySimulation();
		
		sim.onSimulationWillStart(s ->{
			System.out.println("Simulating...");
		});

		sim.simulate(3, 90000000d);
    }
    
    public static boolean parseSimParams()
	{
		
		try
		{
			Config.countMinibuses = Integer.parseInt("2");
                        Config.countOperators = Integer.parseInt("3");
		}
		catch (RuntimeException ex)
		{
                    System.out.println("Pocet minibusov musí byť cele číslo");
			
			return false;
		}
		return true;
	}
    
}
