package operatorPackage;

public abstract class OperatorMaster
{
	private int itsPriority;
	
	public int getPriority()
	{
		return itsPriority;
	}
	
	public abstract Double calc(Double par1, Double par2);
}
