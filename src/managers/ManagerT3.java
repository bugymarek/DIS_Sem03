package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import entity.Customer;

//meta! id="23"
public class ManagerT3 extends Manager {

    public ManagerT3(int id, Simulation mySim, Agent myAgent) {
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

    //meta! sender="AgentAirport", id="45", type="Request"
    public void processArrivalMinibus(MessageForm message) {
        if (((MyMessage) message).getMinibus().isEmpty()) {
            message.setCode(Mc.minibusReadyForMove);
            response(message);
        } else {
            //System.out.println("Prisiel minibus: " + myMessage(message).getMinibus().getID() + " pasazierov: " + myMessage(message).getMinibus().getSize());
            myMessage(message).getMinibus().setPosition("Som na T3");
            Customer customer = myMessage(message).getMinibus().getCustomerFromBus();
            myMessage(message).setCustomer(customer);
            message.setCode(Mc.unloadCustomer);
            message.setAddressee(mySim().findAgent(Id.agentBoardingCustomers));
            request(message);
        }
    }

    //meta! sender="AgentBoardingCustomers", id="85", type="Response"
    public void processUnloadCustomerDone(MessageForm message) {
        myAgent().incrementCustomersCount(myMessage(message).getCustomer().getPassengersCount());
        MessageForm copyMessage = new MyMessage(myMessage(message));

        myMessage(copyMessage).setCode(Mc.departureCustomer);
        copyMessage.setAddressee(mySim().findAgent(Id.agentAirport));
        myMessage(copyMessage).getCustomer().setAllWaitingTime(mySim().currentTime() - myMessage(message).getCustomer().getArrivalTimeToSystem());
        notice(copyMessage);
        
        myAgent().incrementDepartureCustomersCount(myMessage(message).getCustomer().getPassengersCount());
        
        processArrivalMinibus(message);
        
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
            case Mc.unloadCustomerDone:
                processUnloadCustomerDone(message);
                break;

            case Mc.serveArrivalMinibus:
                processArrivalMinibus(message);
                break;

            default:
                processDefault(message);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentT3 myAgent() {
        return (AgentT3) super.myAgent();
    }

    private MyMessage myMessage(MessageForm message) {
        return (MyMessage) message;
    }

}
