import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QLearner {
	
	private MDP mdp;
	
	private Map<MDPState, List<Pair>> Q;
	
	private MDPState lastState;

	public QLearner(MDP mdp) {
		this.mdp = mdp;
		lastState = mdp.getState();
		Q = new HashMap<>();
	}
	
	public void iterate() {
		MDPState lastState = mdp.getState();
		
		double reward = mdp.iterate();
		
		MDPState currentState = mdp.getState();

		setQ(lastState, mdp.getLastAction(), reward + mdp.getDiscountFactor() * getQ(currentState));
	}
	
	public MDPPolicy policy() {
		MDPExplicitPolicy policy = new MDPExplicitPolicy();
		for (MDPState state : Q.keySet()) {
			List<QLearner.Pair> pairs = Q.get(state);
			int bestIndex = 0;
			for (int i = 0; i < pairs.size(); i++) {
				if (pairs.get(i).value > pairs.get(bestIndex).value) {
					bestIndex = i;
				}
			}
			policy.setAction(state, pairs.get(bestIndex).action);
		}
		return policy;
	}
	
	public void setQ(MDPState state, String action, double value) {
		if (!Q.containsKey(state)) {
			Q.put(state, new ArrayList<>());
		}
		List<QLearner.Pair> pairs = Q.get(state);
		if (!pairs.contains(new Pair(action, 0))) {
			pairs.add(new Pair(action, value));
		} else {
			pairs.set(pairs.indexOf(new Pair(action, 0)), new Pair(action, value));
		}
	}
	
	public double getQ(MDPState state, String action) {
		return Q.get(state).get(Q.get(state).indexOf(new Pair(action, 0))).value;
	}
	
	public double getQ(MDPState state) {
		List<QLearner.Pair> pairs = Q.get(state);
		int bestIndex = 0;
		for (int i = 0; i < pairs.size(); i++) {
			if (pairs.get(i).value > pairs.get(bestIndex).value) {
				bestIndex = i;
			}
		}
		return pairs.get(bestIndex).value;
	}
	
	private class Pair {
		String action;
		double value;
		
		public Pair(String action, double value) {
			this.action = action;
			this.value = value;
		}
		
		public boolean equals(Object o) {
			if (o instanceof QLearner.Pair) {
				QLearner.Pair p = (QLearner.Pair) o;
				return p.action.equals(action);
			}
			return false;
		}
		
		public int hashCode() {
			return action.hashCode();
		}
	}
	
}
