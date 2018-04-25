package continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import simulation.*;
import agents.*;
import entity.Customer;

//meta! id="12"
public class PlanArrivalCustomerT1 extends Scheduler {
    private int _idCustomer;
    private static ExponentialRNG _exp = new ExponentialRNG(Config.averageArrivalT1); // second

    public PlanArrivalCustomerT1(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        _idCustomer = 0;
        // Setup component for the next replication
    }

    //meta! sender="AgentEnvironment", id="13", type="Start"
    public void processStart(MessageForm message) {
        message.setCode(Mc.newCustomer);
        hold(_exp.sample(), message);
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

            case Mc.newCustomer:
                processNewCustomer(message);            	
                break;

            default:
                processDefault(message);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentEnvironment myAgent() {
        return (AgentEnvironment) super.myAgent();
    }

    private void processNewCustomer(MessageForm message) {
        MyMessage msg = new MyMessage((MyMessage) message);
        hold(_exp.sample(), msg);
        
        _idCustomer++;
        ((MyMessage) message).setCustomer(new Customer(_idCustomer, "T1" ,mySim()));
        assistantFinished(message);
    }

}
