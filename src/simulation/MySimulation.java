package simulation;

import OSPABA.*;
import OSPStat.Stat;
import agents.*;

public class MySimulation extends Simulation {
    
    private boolean isStopedArrivalT1Generator;
    private boolean isStopedArrivalT2Generator;
    private boolean isStopedArrivalRentalGenerator;
    private Stat _statWaitingTimeForAll;
    private Stat _statWaitingTimeForRentCar;
    private Stat _statWaitingTimeForReturnCar;
    private Stat _lengthMeanT1Stat;
    private Stat _lengthMeanT2Stat;
    private Stat _lengthMeanRentalWaitForMinibusStat;
    private Stat _lengthMeanRentalRentReturnStat;
    private Stat _departuresCustomers;
    private Stat _arrivalCustomersT1;
    private Stat _arrivalCustomersT2;
    private Stat _arrivalCustomersRental;
    private Stat _statWaitingTimeT1;
    private Stat _statWaitingTimeT2;
    private Stat _statWaitingForOperatingCustomer;
    private Stat _statOccupacyOperators;

    public MySimulation() {
        init();
    }

    @Override
    public void prepareSimulation() {
        super.prepareSimulation();
        // Create global statistcis
        isStopedArrivalT1Generator = false;
        isStopedArrivalT2Generator = false;
        isStopedArrivalRentalGenerator = false;
        _statWaitingTimeForAll = new Stat();
        _statWaitingTimeForRentCar = new Stat();
        _statWaitingTimeForReturnCar = new Stat();
        _lengthMeanT1Stat = new Stat();
        _lengthMeanT2Stat = new Stat();
        _lengthMeanRentalWaitForMinibusStat = new Stat();
        _lengthMeanRentalRentReturnStat = new Stat();
        _departuresCustomers = new Stat();
        _arrivalCustomersT1 = new Stat();
        _arrivalCustomersT2 = new Stat();
        _arrivalCustomersRental = new Stat();
        _statWaitingTimeT1 = new Stat();
        _statWaitingTimeT2 = new Stat();
        _statWaitingForOperatingCustomer = new Stat();
        _statOccupacyOperators = new Stat();
        
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Reset entities, queues, local statistics, etc...
        agentModel().startSimulation();
    }

    @Override
    public void replicationFinished() {
        // Collect local statistics into global, update UI, etc...
        super.replicationFinished();
        _statWaitingTimeForAll.addSample(agentEnvironment().getStatWaitingTimeForAllCustomers().mean());
        _statWaitingTimeForRentCar.addSample(agentEnvironment().getStatWaitingTimeRentCarCustomers().mean());
        _statWaitingTimeForReturnCar.addSample(agentEnvironment().getStatWaitingTimeReturnCarCustomers().mean());
        _lengthMeanT1Stat.addSample(agentT1().lengthQueueWStatInteger().mean());
        _lengthMeanT2Stat.addSample(agentT2().lengthQueueWStatInteger().mean());
        _lengthMeanRentalWaitForMinibusStat.addSample(agentRental().lengthLoadQueueWStatInteger().mean());
        _lengthMeanRentalRentReturnStat.addSample(agentRental().lengthUnloadQueueWStat().mean());
        _departuresCustomers.addSample(agentEnvironment().getDeparturesCustomersCount());
        _arrivalCustomersT1.addSample(agentT1().getArrivalCustomersCount());
        _arrivalCustomersT2.addSample(agentT2().getArrivalCustomersCount());
        _arrivalCustomersRental.addSample(agentRental().getArrivalCustomersCount());
        _statWaitingTimeT1.addSample(agentEnvironment().getStatWaitingTimeT1().mean());
        _statWaitingTimeT2.addSample(agentEnvironment().getStatWaitingTimeT2().mean());
        _statWaitingForOperatingCustomer.addSample(agentEnvironment().getStatWaitingForOperatingCustomer().mean());
        _statOccupacyOperators.addSample(agentRental().getOccupancyWorkingTime());
        //System.out.println("R" + currentReplication() + " celkový priemer: " + convertTimeToString(_statWaitingTimeForBorrowCar.mean()) + " priemer danej replikácie(" + convertTimeToString(agentEnvironment().getStatWaitingTime().mean()) + ")");
    }

    @Override
    public void simulationFinished() {
        // Dysplay simulation results
        super.simulationFinished();
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        setAgentModel(new AgentModel(Id.agentModel, this, null) {
        });
        setAgentAirport(new AgentAirport(Id.agentAirport, this, agentModel()));
        setAgentEnvironment(new AgentEnvironment(Id.agentEnvironment, this, agentModel()));
        setAgentBoardingCustomers(new AgentBoardingCustomers(Id.agentBoardingCustomers, this, agentAirport()));
        setAgentT1(new AgentT1(Id.agentT1, this, agentAirport()));
        setAgentT2(new AgentT2(Id.agentT2, this, agentAirport()));
        setAgentT3(new AgentT3(Id.agentT3, this, agentAirport()));
        setAgentRental(new AgentRental(Id.agentRental, this, agentAirport()));
    }

    private AgentModel _agentModel;

    public AgentModel agentModel() {
        return _agentModel;
    }

    public void setAgentModel(AgentModel agentModel) {
        _agentModel = agentModel;
    }

    private AgentAirport _agentAirport;

    public AgentAirport agentAirport() {
        return _agentAirport;
    }

    public void setAgentAirport(AgentAirport agentAirport) {
        _agentAirport = agentAirport;
    }

    private AgentEnvironment _agentEnvironment;

    public AgentEnvironment agentEnvironment() {
        return _agentEnvironment;
    }

    public void setAgentEnvironment(AgentEnvironment agentEnvironment) {
        _agentEnvironment = agentEnvironment;
    }

    private AgentBoardingCustomers _agentBoardingCustomers;

    public AgentBoardingCustomers agentBoardingCustomers() {
        return _agentBoardingCustomers;
    }

    public void setAgentBoardingCustomers(AgentBoardingCustomers agentBoardingCustomers) {
        _agentBoardingCustomers = agentBoardingCustomers;
    }

    private AgentT1 _agentT1;

    public AgentT1 agentT1() {
        return _agentT1;
    }

    public void setAgentT1(AgentT1 agentT1) {
        _agentT1 = agentT1;
    }

    private AgentT2 _agentT2;

    public AgentT2 agentT2() {
        return _agentT2;
    }

    public void setAgentT2(AgentT2 agentT2) {
        _agentT2 = agentT2;
    }

    private AgentT3 _agentT3;

    public AgentT3 agentT3() {
        return _agentT3;
    }

    public void setAgentT3(AgentT3 agentT3) {
        _agentT3 = agentT3;
    }

    private AgentRental _agentRental;

    public AgentRental agentRental() {
        return _agentRental;
    }

    public void setAgentRental(AgentRental agentRental) {
        _agentRental = agentRental;
    }
    //meta! tag="end"

    private String convertTimeToString(double value) {
        return (String.format("%.3f min", value / 60.0) + " = " + (int) Math.floor((value / 60.0) % 60.0) + " min " + String.format("%.0f", (value % 60.0)) + " sec");
    }

    public boolean isStopedArrivalT1Generator() {
        return isStopedArrivalT1Generator;
    }

    public void setIsStopedArrivalT1Generator(boolean isStopedArrivalGenerator) {
        this.isStopedArrivalT1Generator = isStopedArrivalGenerator;
    }
    
    public boolean isStopedArrivalT2Generator() {
        return isStopedArrivalT2Generator;
    }

    public void setIsStopedArrivalT2Generator(boolean isStopedArrivalGenerator) {
        this.isStopedArrivalT2Generator = isStopedArrivalGenerator;
    }
    
    public boolean isStopedArrivalRentalGenerator() {
        return isStopedArrivalRentalGenerator;
    }

    public void setIsStopedArrivalRentalGenerator(boolean isStopedArrivalGenerator) {
        this.isStopedArrivalRentalGenerator = isStopedArrivalGenerator;
    }

    // global stats geters
    public Stat getStatWaitingTimeForRentCar() {
        return _statWaitingTimeForRentCar;
    }

    public Stat getStatWaitingTimeForReturnCar() {
        return _statWaitingTimeForReturnCar;
    }

    public Stat getLengthMeanT1Stat() {
        return _lengthMeanT1Stat;
    }

    public Stat getLengthMeanT2Stat() {
        return _lengthMeanT2Stat;
    }

    public Stat getLengthMeanRentalWaitForMinibusStat() {
        return _lengthMeanRentalWaitForMinibusStat;
    }

    public Stat getLengthMeanRentalRentReturnStat() {
        return _lengthMeanRentalRentReturnStat;
    }

    public Stat getDeparturesCustomers() {
        return _departuresCustomers;
    }

    public Stat getArrivalCustomersT1() {
        return _arrivalCustomersT1;
    }

    public Stat getArrivalCustomersT2() {
        return _arrivalCustomersT2;
    }

    public Stat getArrivalCustomersRental() {
        return _arrivalCustomersRental;
    }

    public Stat getStatWaitingTimeForAll() {
        return _statWaitingTimeForAll;
    }

    public Stat getStatWaitingTimeT1() {
        return _statWaitingTimeT1;
    }

    public Stat getStatWaitingTimeT2() {
        return _statWaitingTimeT2;
    }

    public Stat getStatWaitingForOperatingCustomer() {
        return _statWaitingForOperatingCustomer;
    }

    public Stat getStatOccupacyOperators() {
        return _statOccupacyOperators;
    }

    public void clearStatistics() {
        agentEnvironment().getStatWaitingTimeForAllCustomers().clear();
        agentEnvironment().getStatWaitingTimeRentCarCustomers().clear();
        agentEnvironment().getStatWaitingTimeReturnCarCustomers().clear();
        agentEnvironment().getStatWaitingTimeT1().clear();      
        agentEnvironment().getStatWaitingTimeT2().clear();
        agentEnvironment().getStatWaitingForOperatingCustomer().clear();
        agentT1().lengthQueueWStatInteger().clear();
        agentT2().lengthQueueWStatInteger().clear();
        agentRental().lengthLoadQueueWStatInteger().clear();
        agentRental().lengthUnloadQueueWStat().clear();
    }
}
