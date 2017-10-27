package operatorPackage;

public class OP_subtraction extends OperatorMaster
{
	private static final int priority=1;
	
	public Double calc(Double par1, Double par2)
	{
		return par1-par2;
	}
	
	public int getPriority()
	{
		return priority;
	}
}
