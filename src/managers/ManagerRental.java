package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import entity.Customer;
import entity.Operator;

//meta! id="24"
public class ManagerRental extends Manager {

    public ManagerRental(int id, Simulation mySim, Agent myAgent) {
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

    //meta! sender="AgentService", id="94", type="Response"
    public void processServeCustomer(MessageForm message) {
    }

    //meta! sender="AgentBoardingCustomers", id="82", type="Response"
    public void processLoadCustomerDone(MessageForm message) {
    }

    //meta! sender="AgentAirport", id="40", type="Notice"
    public void processArrivalCustomer(MessageForm message) {
        processUnloadCustomerDone(message);
    }

    //meta! sender="processserveArrivalMinibus", id="84", type="Response"
    public void processserveArrivalMinibus(MessageForm message) {
        //System.out.print("Minibus: " + ((MyMessage) message).getMinibus().getID() + "| Prichod na Rental v case: " + mySim().currentTime());
        //System.out.println(" Pasažieri: " + " pocet: " + ((MyMessage) message).getMinibus().getSize());
        if (((MyMessage) message).getMinibus().isEmpty()) {
            //myMessage(message).getMinibus().setPosition("Cestujem z Rental do T1");
            message.setCode(Mc.minibusReadyForMove);
            response(message);
        } else {
           // myMessage(message).getMinibus().setPosition("Som na Rental");
            Customer customer = myMessage(message).getMinibus().getCustomerFromBus();
            myMessage(message).setCustomer(customer);
            message.setCode(Mc.unloadCustomer);
            message.setAddressee(mySim().findAgent(Id.agentBoardingCustomers));
            request(message);
        }
    }

    public void processUnloadCustomerDone(MessageForm message) {
        Operator freeOperator = myAgent().getFreeOperator();
        MessageForm copyMessage = new MyMessage(myMessage(message));
        if (freeOperator == null) {
            myAgent().getCustomersUnloadQueue().enqueue(copyMessage);
            //System.out.println("AgentRental prichod do radu zakaznik: " + ((MyMessage) copyMessage).getCustomer().getTerminalAndID() + " front length: " + myAgent().getCustomersUnloadQueue().size());
        }else {
            freeOperator.setOccupied(true);
            myMessage(copyMessage).setOperator(freeOperator);
            copyMessage.setCode(Mc.serveCustomer);
            copyMessage.setAddressee(myAgent().findAssistant(Id.processServeCustomer));
            startContinualAssistant(copyMessage);
        }
        processserveArrivalMinibus(message);
    }

    public void processFinish(MessageForm message) {
        Operator freeOperator = myMessage(message).getOperator();
        freeOperator.setOccupied(false);

        message.setCode(Mc.departureCustomer);
        message.setAddressee(mySim().findAgent(Id.agentAirport));
        myMessage(message).getCustomer().setAllWaitingTime(mySim().currentTime() - myMessage(message).getCustomer().getArrivalTimeToSystem());
        notice(message);
        
        if (!myAgent().getCustomersUnloadQueue().isEmpty()) {
            MessageForm msg = myAgent().getCustomersUnloadQueue().dequeue();
            freeOperator.setOccupied(true);
            myMessage(msg).setOperator(freeOperator);
            msg.setCode(Mc.serveCustomer);
            msg.setAddressee(myAgent().findAssistant(Id.processServeCustomer));
            startContinualAssistant(msg);
        }
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

            case Mc.loadCustomerDone:
                processLoadCustomerDone(message);
                break;

            case Mc.serveArrivalMinibus:
                processserveArrivalMinibus(message);
                break;

            case Mc.arrivalCustomer:
                processArrivalCustomer(message);
                break;

            case Mc.finish:
                processFinish(message);
                break;

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

    private MyMessage myMessage(MessageForm message) {
        return (MyMessage) message;
    }
}
