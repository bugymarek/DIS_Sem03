package continualAssistants;

import Generators.IntervalGenerator;
import OSPABA.*;
import OSPRNG.ExponentialRNG;
import simulation.*;
import agents.*;
import entity.Customer;
import generators.Pair;
import generators.Pairs;
import java.util.ArrayList;
import java.util.List;

//meta! id="12"
public class PlanArrivalCustomerT1 extends Scheduler {
    private int _idCustomer;
    //private static ExponentialRNG _exp = new ExponentialRNG(Config.averageArrivalT1); // second
    private IntervalGenerator _intervalGenerator;

    public PlanArrivalCustomerT1(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
        List<Pair> pairs = new ArrayList<>();
        Pair pair;

        for (int i = 0; i < Pairs.T1Pairs.length; i++) {
            pair = new Pair(Pairs.T1Pairs[i][0]*60.0,Pairs.T1Pairs[i][1]);
            pair.setIndex(i);
            pairs.add(pair);
        }
        _intervalGenerator = new IntervalGenerator(pairs, mySim);
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

    @Override
    public AgentEnvironment myAgent() {
        return (AgentEnvironment) super.myAgent();
    }

    private void processNewCustomer(MessageForm message) {
        MyMessage msg = new MyMessage((MyMessage) message);
        hold(_intervalGenerator.sample(), msg);
        
        _idCustomer++;
        ((MyMessage) message).setCustomer(new Customer(_idCustomer, "T1" ,mySim()));
        assistantFinished(message);
    }

}
