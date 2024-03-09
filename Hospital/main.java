package Hospital;

public class main {

	public static void main(String[] args) {
		//TODO Auto-generated method stub
		//HospitalBill hb=new HospitalBill();  //default opeartion
		HospitalBill hb=new HospitalBill("Brain", 3000, "Pain killer", 670, "MRI", 7000);
		hb.ExtraOpsRhings("nurse", "blood", "medical");
		hb.getPrice();
	}

}
