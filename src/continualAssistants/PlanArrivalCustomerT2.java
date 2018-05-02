package continualAssistants;

import Generators.IntervalGenerator;
import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Customer;

//meta! id="16"
public class PlanArrivalCustomerT2 extends Scheduler {

    private int _idCustomer;
    //private static ExponentialRNG _exp = new ExponentialRNG(Config.averageArrivalT2); // second
    private IntervalGenerator _intervalGenerator;

    public PlanArrivalCustomerT2(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
        
        _intervalGenerator = new IntervalGenerator(2, mySim);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        _idCustomer = 0;
        // Setup component for the next replication
    }

    //meta! sender="AgentEnvironment", id="17", type="Start"
    public void processStart(MessageForm message) {
        message.setCode(Mc.newCustomer);
        hold(_intervalGenerator.sample(), message);
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

    private void processNewCustomer(MessageForm message) {
        MyMessage msg = new MyMessage((MyMessage) message);
        hold(_intervalGenerator.sample(), msg);

        _idCustomer++;
        ((MyMessage) message).setCustomer(new Customer(_idCustomer, "T2", mySim()));
        assistantFinished(message);
    }

    @Override
    public AgentEnvironment myAgent() {
        return (AgentEnvironment) super.myAgent();
    }

}
