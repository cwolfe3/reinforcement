
public class ChainGeneralPolicy extends MDPPolicy<ChainState> {
	
	private String[] dirs;
	
	public ChainGeneralPolicy() {
		dirs = new String[5];
		for (int i = 0; i < dirs.length; i++) {
			dirs[i] = Math.random() < 0.5 ? "LEFT" : "RIGHT";
		}
	}

	@Override
	public String getAction(ChainState currentState) {
		return dirs[currentState.getPosition()];
	}

}
