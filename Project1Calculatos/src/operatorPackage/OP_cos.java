package operatorPackage;

public class OP_cos extends OperatorMaster
{
	private static final int priority=4;
	
	public Double calc(Double par1, Double par2)
	{
		if(par2>=89.9999999 && par2 <= 90.00000001)
		{
			return 0d;
		}
		return Math.cos(Math.toRadians(par2));
	}
	
	public int getPriority()
	{
		return priority;
	}
}
