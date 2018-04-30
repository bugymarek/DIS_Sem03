package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;
import OSPRNG.TriangularRNG;
import generators.UniformRangeDistribution;
import java.util.Random;
//meta! id="97"

public class ProcessServeCustomer extends Process {

    private static TriangularRNG _inFirstTriang = new TriangularRNG(1.6, 1.95, 3.0);
    private static TriangularRNG _inSecondTriang = new TriangularRNG(3.1, 4.65, 5.1);
    private static TriangularRNG _outFirstTriang = new TriangularRNG(1.0, 1.55, 2.1);
    private static TriangularRNG _outSecondTriang = new TriangularRNG(2.9, 4.3, 4.8);
    private Random _rate = new Random();

    public ProcessServeCustomer(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

    //meta! sender="AgentService", id="98", type="Start"
    public void processStart(MessageForm message) {
        //System.out.println("AgentRental zacal som obsluhovat zakaznika: " + ((MyMessage) message).getCustomer().getTerminalAndID() + " pocet free pracovnikov: " + myAgent().getFreeOperatorsCount());
        message.setCode(Mc.serveCustomerDone);
        hold(next(message), message);
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        switch (message.code()) {
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.start:
                processStart(message);
                break;

            case Mc.serveCustomerDone:
                assistantFinished(message);

            default:
                processDefault(message);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentRental myAgent() {
        return (AgentRental) super.myAgent();
    }

    private double next(MessageForm message) {
        double valueRate = _rate.nextDouble();
        double valueGenerated;
        if (((MyMessage) message).getCustomer().getTerminal().equals("Rental")) {
            if (valueRate <= 0.13394683) {
                valueGenerated = _outSecondTriang.sample();
                //System.out.println("Out second: " + valueGenerated);
                return convertToSecunds(valueGenerated);
            }
            valueGenerated = _outFirstTriang.sample();
            //System.out.println("Out first: " + valueGenerated);
            return convertToSecunds(valueGenerated);
        } else if (valueRate <= 0.234375) {
            valueGenerated = _inSecondTriang.sample();
            //System.out.println("in second: " + valueTest);
            return convertToSecunds(valueGenerated);
        }
        valueGenerated = _inFirstTriang.sample();
        //System.out.println("in first: " + valueTest);
        return convertToSecunds(valueGenerated);
    }

    private double convertToSecunds(double value) {
        return value * 60.0;
    }
}
