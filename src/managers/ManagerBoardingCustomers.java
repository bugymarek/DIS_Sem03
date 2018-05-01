package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="52"
public class ManagerBoardingCustomers extends Manager {

    public ManagerBoardingCustomers(int id, Simulation mySim, Agent myAgent) {
        super(id, mySim, myAgent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication

        if (petriNet() != null) {
            petriNet().clear();
        }
    }

    //meta! sender="AgentT1", id="83", type="Request"
    public void processLoadCustomer(MessageForm message) {
        ((MyMessage) message).getMinibus().setPosition("Nastupovanie");
        message.setAddressee(myAgent().findAssistant(Id.processLoadCustomer));
        startContinualAssistant(message);
    }
    
     public void processUnLoadCustomer(MessageForm message) {
        ((MyMessage) message).getMinibus().setPosition("Vystupovanie");
        message.setAddressee(myAgent().findAssistant(Id.processUnloadCustomer));
        startContinualAssistant(message);
    }

    //meta! sender="ProcessUnloadCustomer", id="87", type="Finish"
    public void processFinishProcessUnloadCustomer(MessageForm message) {
        message.setCode(Mc.unloadCustomerDone);
        response(message);
    }

    //meta! sender="ProcessLoadCustomer", id="81", type="Finish"
    public void processFinishProcessLoadCustomer(MessageForm message) {
        message.setCode(Mc.loadCustomerDone);
        ((MyMessage) message).getMinibus().addCustomerToBus(((MyMessage) message).getCustomer());
        response(message);
    }

    //meta! sender="AgentT3", id="85", type="Request"
    public void processUnloadCustomer(MessageForm message) {
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        switch (message.code()) {
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    public void init() {
    }

    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.finish:
                switch (message.sender().id()) {
                    case Id.processUnloadCustomer:
                        processFinishProcessUnloadCustomer(message);
                        break;

                    case Id.processLoadCustomer:
                        processFinishProcessLoadCustomer(message);
                        break;
                }
                break;

            case Mc.unloadCustomer:
                processUnLoadCustomer(message);
                break;

            case Mc.loadCustomer:
                processLoadCustomer(message);
                break;

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

}
