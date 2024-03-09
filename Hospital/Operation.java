package Hospital;

public class Operation extends Expendature {//extended
	private Expendature op1,op2,op3;// it will add with opearatins as a support operations but all adjustment is same as a special process hence I can use it
	
	public Operation(String name,double price) //constructor
	{
		super("Operation", name, price); //passing to parent and here service type is default operation
	}
	public void setLuxury(String luxury)
	{
		super.setLuxury(luxury); //setting luxury and passing to parent
	}
	
	public String getName() //getting name same as parent
	{
		return super.getName();
	}
	public double getAdjustedPrice()  //price based on luxury
	{
		return super.getAdjustedPrice();
	}
	
	public double getSupportPrice(String op)  //price of another support actions
	{
		return switch(op.toUpperCase())
				{
		case "NURSE"->3000;
		case "MEDICAL"->4000;
		case "BLOOD"->4500;
		default ->2000;
				};
	}
	
	public void ExtraOpsRhings(String op1,String op2,String op3)
	{
		this.op1=new Expendature("Operation Support", op1, getSupportPrice(op1));
		this.op2=new Expendature("Operation Support", op2, getSupportPrice(op2));
		this.op3=new Expendature("Operation Support", op3, getSupportPrice(op3));//creating expendature for support action, type is default as support, name is selective and price is as above method getSupportPrice()
	}
	//now using op1 ... op3 I can use getName() getprice() methods
	
	public double getOperationPrice()
	{
		return op1.getAdjustedPrice()+op2.getAdjustedPrice()+op3.getAdjustedPrice()+getAdjustedPrice();
		//total operation cost including basic operation and support
	}
	public void getPrice()
	{
		System.out.println("-".repeat(40));
		Expendature.getPrice(op1.getName(), op1.getAdjustedPrice());
		Expendature.getPrice(op2.getName(), op2.getAdjustedPrice());
		Expendature.getPrice(op3.getName(), op3.getAdjustedPrice());
		Expendature.getPrice(getName(),getAdjustedPrice());
		System.out.println("-".repeat(40));
		Expendature.getPrice(getName()+" Total", getOperationPrice());
		//printing bill of operatrion
		
	}
	//if created a object of of this class then folloing code is required in main method
		/*
		 *Operation ops=new Operation("Heart operation", 40000);//operation name and cost
		ops.setLuxury("prime");//optional
		ops.ExtraOpsRhings("Medical", "blood", "nurse");//adding extra things that used while operaion
		ops.getPrice();//getting total cost with details. some methods are available to get only cost
		 
		 */
	

}
