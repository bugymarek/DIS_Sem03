package simulation;

import OSPABA.*;

public class Mc extends IdList
{
	//meta! userInfo="Generated code: do not modify", tag="begin"
	public static final int arrivalCustomer = 1001;
	public static final int departureCustomer = 1003;
	public static final int loadCustomer = 1022;
        public static final int loadCustomerDone = 1021;
	public static final int init = 1004;
	public static final int unloadCustomer = 1023;
        public static final int unloadCustomerDone = 1024;
	public static final int serveCustomer = 1027;
        public static final int serveArrivalMinibus = 1020;
	//meta! tag="end"

	// 1..1000 range reserved for user
        public static final int newCustomer = 1040;
        public static final int arrivalCustomerT1 = 1041;
        public static final int arrivalCustomerT2 = 1042;
        public static final int arrivalCustomerRental = 1043;
        public static final int movingMinibusDone = 1044;
        public static final int minibusReadyForMove = 1045;
}
