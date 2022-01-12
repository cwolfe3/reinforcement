import java.util.HashMap;
import java.util.Map;

public class MDPExplicitPolicy<T extends MDPState> extends MDPPolicy<T> {
	
	private Map<T, String> Q;
	
	public MDPExplicitPolicy() {
		Q = new HashMap<>();
	}
	
	public void setAction(T state, String action) {
		Q.put(state, action);
	}

	@Override
	public String getAction(T currentState) {
		return Q.get(currentState);
	}
	
	public String toString() {
		String r = new String();
		for (T state : Q.keySet()) {
			r += state + ", " + Q.get(state) + "\n";
		}
		return r;
	}
	
}
