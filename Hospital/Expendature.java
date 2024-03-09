package Hospital;

import java.text.Format;



public class Expendature {
	private String service;
	private String name;
	private String luxury="SIMPLE";//default luxury
	private double price;
	
	public Expendature(String service,String name,double price) {
		this.service=service.toUpperCase();
		this.name=name.toUpperCase();
		this.price=price;
	}
	public void setLuxury(String luxury) //setting luxury,optional method
	{
		String lux=luxury.toUpperCase();
		if(lux.equals("DELUXE") || lux.equals("PRIME") || lux.equals("BASIC"))
		{
			this.luxury=lux;
			
		}
		else
		{
			System.out.println("Luxury should is one of Deluxe, Prime or Basic. default is simple\n **Set Again**");//if above word not used,(can use while loop to auto call second time)
		}
	}
	
	public String getName()//setting name by adding service , name and luxury togeteher
	{
		if(luxury.equals("DELUXE") || luxury.equals("PRIME") || luxury.equals("BASIC"))//if luxury selelcted externally then only it will called
		{
			return String.format("(%s) %s %s", luxury,service,name);
		}
		else
			return String.format(" %s %s",service,name);//default name
	}
	public double getAdjustedPrice() //adjustment of price based on luxury
	{
		return price+ switch(luxury)
				{
		case "DELUXE"->2000;
		case "PRIME"->1000;
		case "BASIC"->-1000;
		default->0;
				};
	}
	
	public static void getPrice(String name,double price)
	{
		String bill=String.format("%30s : %8.2f%n", name,price); //format of prinying cost and service name for bill. method is static hence I can call it anywhere using name of class, so printing of bill will follow same format
		System.out.println(bill);
	}
	public void getPrice()
	{
		getPrice(getName(), getAdjustedPrice()); //calling method to print bill
	}
	
	
	//if created a object of of this class then folloing code is required in main method
	/*
	 * Expendature ex=new Expendature("Service name like medical or test or operation", "Actual name like xray for test or heart ooperation",40000); //last is price or cost price)
		ex.setLuxury("Prime");//luxury of service
		ex.getPrice();//getting final cost
	 
	 */

}
