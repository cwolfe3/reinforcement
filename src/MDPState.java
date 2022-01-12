
public abstract class MDPState implements Cloneable {

	public abstract double perform(String action);
	public abstract MDPState copy();
	
}
