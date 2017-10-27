package operatorPackage;

public class OP_sinus extends OperatorMaster
{
	private static final int priority=4;
	
	public Double calc(Double par1, Double par2)
	{
		return Math.sin(Math.toRadians(par2));
	}
	
	public int getPriority()
	{
		return priority;
	}
}
