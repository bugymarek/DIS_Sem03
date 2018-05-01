/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.util.LinkedList;
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
//        parseSimParams();
//        MySimulation sim = new MySimulation();
//		
//		sim.onSimulationWillStart(s ->{
//			System.out.println("Simulating...");
//		});
//                
//                sim.onReplicationWillStart(s ->{
//                    System.out.println("Replikacia: " + sim.currentReplication());
//                });
//
//		sim.simulate(1000, Config.SimulationTime);
        testLinkedList();
    }
        
    public static boolean parseSimParams()
	{
		
		try
		{
			Config.MinibusesCount = Integer.parseInt("7");
                        Config.OperatorsCount = Integer.parseInt("18");
		}
		catch (RuntimeException ex)
		{
                    System.out.println("Pocet minibusov musí byť cele číslo");
			
			return false;
		}
		return true;
	}

    private static void testLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("");
        list.get(3);
        for (Integer integer : list) {
            System.out.println(integer);
        }
     }
    
}
