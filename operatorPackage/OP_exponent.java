package operatorPackage;

public class OP_exponent extends OperatorMaster
{
	private static final int priority=3;
	
	
	public Double calc(Double par1, Double par2)
	{
		return Math.pow(par1, par2);
	}
	

	public int getPriority()
	{
		return priority;
	}
}
