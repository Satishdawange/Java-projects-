package Hospital;

public class HospitalBill {
	
	private Operation ops;
	private Expendature medical,test;
	//here I bill of operaion, medical and test
	
	public HospitalBill()
	{
		this("Leg",10000,"Medical",500,"Blood test",500);
		//when constructor is empty means above basic operaion, medical and tests will be added as default and this. keyword will pass it to below constructor
	}
	public HospitalBill(String ops,double opPrice ,String medical,double medPrice,String test ,double testPrice)
	//I override construcor for custom input from user
	{
		this.ops=new Operation(ops, opPrice);
		this.medical=new Expendature(medical,"ALl medicals", medPrice);
		this.test=new Expendature(test,"All tests", testPrice);
		//initializing all class and objects for operaion, test and medical
	}
	
	public void ExtraOpsRhings(String op1,String op2,String op3)
	{
		this.ops.ExtraOpsRhings(op1, op2, op3);
		//for operation, this support action is important. need to call it in main method
	}
	
	public void getPrice()//total bill of all activity , opeartion, medical and tests
	{
		test.getPrice();
		System.out.println("-".repeat(40));
		medical.getPrice();
		System.out.println("-".repeat(40));
		ops.getPrice();
		System.out.println("-".repeat(40));
		Expendature.getPrice("TOTAl", test.getAdjustedPrice()+medical.getAdjustedPrice()+ops.getOperationPrice());
	}
	
	
	/*all important method called individually in all classes , are needed to call here for sucessful running of code.
	ex.  for operation we need to set ExtraOpsRhings()
	*/

}
