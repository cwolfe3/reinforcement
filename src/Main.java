
public class Main {

	public static void main(String[] args) {
		MDP mdp = new MDP(1);
		mdp.setState(new ChainState(0));
		mdp.setPolicy(new ChainRandomPolicy());
		
		for (int j = 0; j < 5; j++) {
			mdp.restart(new ChainState(j), new ChainRandomPolicy());
			for (int i = 0; i < 100000; i++) {
				mdp.iterate();
			}
			System.out.println(mdp.getReward());
		}
		
		QLearner learner = new QLearner(mdp);
		
		for (int i = 0; i <= 4; i++) {
			learner.setQ(new ChainState(i), "RIGHT", 0);
			learner.setQ(new ChainState(i), "LEFT", 0);
		}
		
		mdp.restart(new ChainState((int)(Math.random() * 5)), new ChainRandomPolicy());
		for (int i = 0; i < 100; i++) {
			learner.iterate();
			//System.out.println(learner.policy());
		}
		
		System.out.println(learner.policy());

		for (int j = 0; j < 5; j++) {
			mdp.restart(new ChainState(j), learner.policy());
			for (int i = 0; i < 100000; i++) {
				mdp.iterate();
			}
			System.out.println(mdp.getReward());
		}
		System.out.println();
		
		MDPExplicitPolicy p = new MDPExplicitPolicy<ChainState>();
		p.setAction(new ChainState(0), "RIGHT");
		p.setAction(new ChainState(1), "RIGHT");
		p.setAction(new ChainState(2), "RIGHT");
		p.setAction(new ChainState(3), "LEFT");
		p.setAction(new ChainState(4), "LEFT");
		
		for (int j = 0; j < 5; j++) {
			mdp.restart(new ChainState(j), p);
			for (int i = 0; i < 100000; i++) {
				mdp.iterate();
			}
			System.out.println(mdp.getReward());
		}


	}
	
}
