package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;
import OSPRNG.UniformContinuousRNG;
import generators.UniformRangeDistribution;
import java.util.Random;

//meta! id="86"
public class ProcessUnloadCustomer extends Process {

    private static UniformContinuousRNG _uniform = new UniformContinuousRNG(Config.GetOutOfBusLowerLimit, Config.GetOutOfBusUpperLimit); // second

    public ProcessUnloadCustomer(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

    //meta! sender="AgentBoardingCustomers", id="87", type="Start"
    public void processStart(MessageForm message) {
        double wait = getWaitingTimeForGroup(message);
        message.setCode(Mc.unloadCustomerDone);
        hold(wait, message);
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

            case Mc.unloadCustomerDone:
                assistantFinished(message);

            default:
                processDefault(message);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentBoardingCustomers myAgent() {
        return (AgentBoardingCustomers) super.myAgent();
    }

    private double getWaitingTimeForGroup(MessageForm message) {
        int count = ((MyMessage) message).getCustomer().getPassengersCount();
        double wait = 0;
        for (int i = 0; i < count; i++) {
            wait += _uniform.sample();
        }
        return wait;
    }

}
