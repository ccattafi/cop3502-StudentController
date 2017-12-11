package ufl.cs1.controllers;

import game.controllers.DefenderController;
import game.models.Defender;
import game.models.Game;

import java.util.List;

public final class StudentController implements DefenderController {
	public void init(Game game) {

	}

	public void shutdown(Game game) {

	}

	public int[] update(Game game, long timeDue) {
		int[] actions = new int[Game.NUM_DEFENDER];
		List<Defender> enemies = game.getDefenders();

		//Chooses a random LEGAL action if required. Could be much simpler by simply returning
		//any random number of all of the ghosts
		for (int i = 0; i < actions.length; i++) {
			Defender defender = enemies.get(i);
			List<Integer> possibleDirs = defender.getPossibleDirs();
			if (possibleDirs.size() != 0)
				actions[i] = possibleDirs.get(Game.rng.nextInt(possibleDirs.size()));
			else
				actions[i] = -1;
		}
		return actions;
	}

	//protects around forks in path if attacker is not closer than one; otherwise, seeks attacker
	public int getForkProtectorBlinky(Game game, int defendNum) {


		int whichDirection = 0;

		//if Blinky is vulnerable, attempts to flee attacker
		if (game.getDefender(defendNum).isVulnerable()) {
			return game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(), false);
		}


		//Behavior for if at or near a junction
		if (isJunction() == true) {
			if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(isJunction(), true)) > 8) { //Node with many pills is closest
				whichDirection = game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(isJunction(), true), true);
				return whichDirection;
			}
			//Attacker is closer
			else {
				if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(isJunction(), true)) > 4) {
					return game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(isJunction(), true), true);
				} else {
					whichDirection = 0;
					whichDirection = game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(), true);
					return whichDirection;
				}
			}

		} else { //If not near junction, just seeks attacker
			whichDirection = 0;
			whichDirection = game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(), true);
			return whichDirection;
		}
	}
}
























}