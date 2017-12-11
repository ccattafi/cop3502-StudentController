package ufl.cs1.controllers;

import game.controllers.DefenderController;
import game.models.Defender;
import game.models.Game;
import game.models.Node;

import java.util.List;

public final class StudentController implements DefenderController
{
	public void init(Game game) { }

	public void shutdown(Game game) { }

	public int[] update(Game game,long timeDue)
	{
		int[] actions = new int[Game.NUM_DEFENDER];
		List<Defender> enemies = game.getDefenders();
		
		//Chooses a random LEGAL action if required. Could be much simpler by simply returning
		//any random number of all of the ghosts
		for(int i = 0; i < actions.length; i++)
		{
			Defender defender = enemies.get(i);
			List<Integer> possibleDirs = defender.getPossibleDirs();
			if (possibleDirs.size() != 0)
				actions[i]=possibleDirs.get(Game.rng.nextInt(possibleDirs.size()));
			else
				actions[i] = -1;
		}
		return actions;
	}

	public int getPowerPillProtectorPinky(Game game, int defendNum) { //protects around Power pills, if none exists then attacks Pac

		if(game.getDefender(defendNum).isVulnerable()) { //vulnerable mode
			return game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(),false);
		}

		List <Node> numPowerPills = game.getPowerPillList();
		int directionToMove = 0;

		if (numPowerPills.size() > 0) { // Power pill exists behavior
			if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(numPowerPills, true)) > 15) { //Pill is closest
				directionToMove = game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(numPowerPills, true), true);
				return directionToMove;
			}
			else { //Pacman is closest to pill
				if(game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(numPowerPills, true)) > 10) {
					return game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(numPowerPills, true), true);
				}
				else {
					directionToMove = 0;
					directionToMove = game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(), true);
					return directionToMove;
				}
			}

		}
		else { //No Pills behavior, seeks attacker
			directionToMove = 0;
			directionToMove = game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(), true);
			return directionToMove;
		}
	}
}