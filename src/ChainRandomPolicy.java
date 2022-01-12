
public class ChainRandomPolicy extends MDPPolicy<ChainState> {

	@Override
	public String getAction(ChainState state) {
		if (Math.random() < 0.5) {
			return "LEFT";
		} else {
			return "RIGHT";
		}
	}

}
