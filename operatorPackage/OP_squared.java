package operatorPackage;

public class OP_squared extends OperatorMaster
{
	private static final int priority=3;
	
	public Double calc(Double par1, Double par2)
	{
		return par1*par1;
	}
	
	public int getPriority()
	{
		return priority;
	}
}

