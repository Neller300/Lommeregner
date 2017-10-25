package operatorPackage;

public class OP_invSin extends OperatorMaster
{
	private static final int priority=1;
	
	
	public Double calc(Double par1, Double par2)
	{
		double temp = Math.toDegrees(Math.asin(par2));
		
		if(temp>=29.99997 && temp <= 30.000000000000004)
		{
			return 30d;
		}
		else 
		{
			return temp;
		}
		
		
	}
	

	public int getPriority()
	{
		return priority;
	}
}
