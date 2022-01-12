
public class MDP {

	private MDPPolicy policy;
	private double totalReward;
	
	private MDPState currentState;
	private String lastAction;
	
	private double discountInit;
	private int time;
	private double discount;
	
	public MDP(double discountInit) {
		this.discountInit = discountInit;
		
		totalReward = 0;
		time = 0;
		discount = discountInit;
	}
	
	public void setPolicy(MDPPolicy policy) {
		this.policy = policy;
	}
	
	public void setState(MDPState newState) {
		currentState = newState;
	}
	
	public MDPState getState() {
		return currentState.copy();
	}
	
	public String getLastAction() {
		return lastAction;
	}
	
	public double getDiscountFactor() {
		return discountInit;
	}
	
	public double discount(double r) {
		return r * Math.pow(discountInit, time);
	}
	
	public double performAction(String action) {
		lastAction = action;
		double newReward = currentState.perform(lastAction);
		totalReward += newReward * discount;
		discount *= discountInit;
		time++;
		return newReward * discount;
	}
	
	public double iterate() {
		lastAction = policy.getAction(currentState);
		double newReward = currentState.perform(lastAction) * discount;
		totalReward += newReward;
		discount *= discountInit;
		time++;
		return newReward;
	}
	
	public void restart(MDPState state, MDPPolicy policy) {
		setState(state);
		setPolicy(policy);
		totalReward = 0;
		time = 0;
		discount = discountInit;
	}
	
	public double getReward() {
		return totalReward;
	}
	
}
