
public abstract class MDPPolicy<T extends MDPState> {

	public abstract String getAction(T currentState);
	//public abstract double getProb(T currentState, String action);
	
}
