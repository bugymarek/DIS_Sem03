package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import entity.Customer;

//meta! id="21"
public class ManagerT1 extends Manager {

    public ManagerT1(int id, Simulation mySim, Agent myAgent) {
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

    //meta! sender="AgentAirport", id="39", type="Notice"
    public void processArrivalCustomer(MessageForm message) {
        myAgent().getCustomersQueue().enqueue(message);
        //System.out.println("AgentT1 prichod zakaznika: " + ((MyMessage) message).getCustomer().getTerminalAndID() + " v case: " + ((MyMessage) message).getCustomer().getArrivalTimeToSystem() + "front length: " + myAgent().getCustomersQueue().size());

    }

    //meta! sender="AgentBoardingCustomers", id="83", type="Response"
    public void processLoadCustomerDone(MessageForm message) {
        processArrivalMinibus(message);
    }

    //meta! sender="AgentAirport", id="44", type="Request"
    public void processArrivalMinibus(MessageForm message) {
        if(myAgent().getCustomersQueue().isEmpty() || !((MyMessage)message).getMinibus().isPlaceInBus()){
             myMessage(message).getMinibus().setPosition("Cestujem z T1 do T2");
             message.setCode(Mc.minibusReadyForMove);
             response(message);
        }else {           
            //myMessage(message).getMinibus().setPosition("Som na T1");
            Customer customer = myMessage(myAgent().getCustomersQueue().dequeue()).getCustomer();
            myMessage(message).setCustomer(customer);
            message.setCode(Mc.loadCustomer);
            message.setAddressee(mySim().findAgent(Id.agentBoardingCustomers));
            request(message);
        }
        //System.out.print("Minibus: " + ((MyMessage)message).getMinibus().getID()+ "| Prichod na T1 v cese: " + mySim().currentTime());
        //System.out.println(" Pasažieri: " + " pocet: " + ((MyMessage)message).getMinibus().getSize());
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
            case Mc.serveArrivalMinibus:
                processArrivalMinibus(message);
                break;

            case Mc.arrivalCustomer:
                processArrivalCustomer(message);
                break;

            case Mc.loadCustomerDone:
                processLoadCustomerDone(message);
                break;

            default:
                processDefault(message);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentT1 myAgent() {
        return (AgentT1) super.myAgent();
    }
    
    private MyMessage myMessage(MessageForm message){
        return (MyMessage)message;
    }

}
