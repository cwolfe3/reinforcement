
public class ChainState extends MDPState {
	
	private int i;
	
	public ChainState(int i) {
		this.i = i;
	}

	@Override
	public double perform(String action) {
		double result = 0;
		
		int disp = action.equals("LEFT") ? -1 : 1;
		disp *= Math.random() < 0.8 ? 1 : -1;
		
		if (disp == -1 && i == 0) {
			disp = 1;
		}
		if (disp == 1 && i == 4) {
			disp = -1;
		}
		
		if (i > 0 && i <= 4 && disp < 0) {
			if (i == 3) {
				result = 1;
			}
		} else if (i >= 0 && i < 4 && disp > 0) {
			if (i == 1) {
				result = 1;
			}
		}
		
		i += disp;
		return result;
	}
	
	public boolean equals(Object o) {
		if (o instanceof ChainState) {
			ChainState s = (ChainState) o;
			return i == s.i;
		}
		return false;
	}
	
	public int hashCode() {
		return Integer.valueOf(i).hashCode();
	}
	
	public int getPosition() {
		return i;
	}
	
	public ChainState copy() {
		return new ChainState(i);
	}
	
}
