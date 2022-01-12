import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MDPQTablePolicy<T extends MDPState> extends MDPPolicy<T> {
	
	private Map<T, List<Pair>> Q;
	
	public MDPQTablePolicy() {
		Q = new HashMap<>();
	}
	
	public MDPQTablePolicy(Map<T, List<Pair>> Q) {
		this.Q = Q;
	}
	
	public void setQ(T state, String action, double value) {
		if (!Q.containsKey(state)) {
			Q.put(state, new ArrayList<>());
		}
		List<MDPQTablePolicy<T>.Pair> pairs = Q.get(state);
		if (!pairs.contains(new Pair(action, 0))) {
			pairs.add(new Pair(action, value));
		} else {
			pairs.set(pairs.indexOf(new Pair(action, 0)), new Pair(action, value));
		}
	}
	
	public double getQ(T state, String action) {
		return Q.get(state).get(Q.get(state).indexOf(new Pair(action, 0))).value;
	}
	
	public double getQ(T state) {
		List<MDPQTablePolicy<T>.Pair> pairs = Q.get(state);
		int bestIndex = 0;
		for (int i = 0; i < pairs.size(); i++) {
			if (pairs.get(i).value > pairs.get(bestIndex).value) {
				bestIndex = i;
			}
		}
		return pairs.get(bestIndex).value;
	}

	@Override
	public String getAction(T currentState) {
		List<MDPQTablePolicy<T>.Pair> pairs = Q.get(currentState);
		int bestIndex = 0;
		for (int i = 0; i < pairs.size(); i++) {
			if (pairs.get(i).value > pairs.get(bestIndex).value) {
				bestIndex = i;
			}
		}
		return pairs.get(bestIndex).action;
	}
		
	private class Pair {
		String action;
		double value;
		
		public Pair(String action, double value) {
			this.action = action;
			this.value = value;
		}
		
		public boolean equals(Object o) {
			if (o instanceof MDPQTablePolicy.Pair) {
				MDPQTablePolicy<T>.Pair p = (MDPQTablePolicy<T>.Pair) o;
				return p.action.equals(action);
			}
			return false;
		}
		
		public int hashCode() {
			return action.hashCode();
		}
	}
	
}
