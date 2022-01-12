import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PolicyIteration {
	
	private MDP mdp;
	private List<MDPState> states;
	private List<String> actions;
	private Map<MDPState, Double> valueFunction;
	
	private double delta; //For halting condition

	public PolicyIteration(MDP mdp, List<MDPState> states, List<String> actions) {
		this.mdp = mdp;
		this.states = states;
		this.actions = actions;
		
		valueFunction = new HashMap<>();
		for (MDPState state : states) {
			valueFunction.put(state, Double.valueOf(0));
		}
	}
	
	public void iterate() {
		for (int i = 0; i < 100; i++) {
			evalStep();
		}
		updateStep();
	}
	
	private void evalStep() {
		for (MDPState state : states) {
			
		}
	}
	
	private void updateStep() {
		
	}
	
}
