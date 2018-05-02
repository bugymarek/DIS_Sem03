package continualAssistants;

import Generators.IntervalGenerator;
import OSPABA.*;
import simulation.*;
import agents.*;
import entity.Customer;

//meta! id="18"
public class PlanArrivalCustomerRental extends Scheduler {

    private int _idCustomer;
    //private static ExponentialRNG _exp = new ExponentialRNG(2d); // second
    private IntervalGenerator _intervalGenerator;

    public PlanArrivalCustomerRental(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
        _intervalGenerator = new IntervalGenerator(3, mySim);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
        _idCustomer = 0;
    }

    //meta! sender="AgentEnvironment", id="19", type="Start"
    public void processStart(MessageForm message) {
        message.setCode(Mc.newCustomer);
        hold(_intervalGenerator.sample(), message);
    }

    private void processNewCustomer(MessageForm message) {
        MyMessage msg = new MyMessage((MyMessage) message);
        hold(_intervalGenerator.sample(), msg);

        _idCustomer++;
        ((MyMessage) message).setCustomer(new Customer(_idCustomer, Config.RentalName, mySim()));
        assistantFinished(message);
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

}
