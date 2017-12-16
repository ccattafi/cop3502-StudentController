package ufl.cs1.controllers;

import game.controllers.DefenderController;
import game.models.Defender;
import game.models.Game;
import game.models.Maze;

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

	public int getPowerPillProtectorPinky(Game game, int defendNum) { //protects around Power pills, if none exists then attacks Pac

		if (game.getDefender(defendNum).isVulnerable()) { //vulnerable mode
			return game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(), false);
		}

		List<Node> numPowerPills = game.getPowerPillList();
		int directionToMove = 0;

		if (numPowerPills.size() > 0) { // Power pill exists behavior
			if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(numPowerPills, true)) > 15) { //Pill is closestdirectionToMove = game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(numPowerPills, true), true);
				return directionToMove;
			} else { //Pacman is closest to pill
				if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(numPowerPills, true)) > 10) {
					return game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(numPowerPills, true), true);
				} else {
					directionToMove = 0;
					directionToMove = game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(), true);
					return directionToMove;
				}
			}

		}

	}

	//protects around forks in maze path if attacker is not closer than one; otherwise, seeks attacker
	public int getForkProtectorBlinky(Game game, int defendNum, Maze maze) {


		int whichDirection = 0;

		//if Blinky is vulnerable, attempts to flee attacker
		if (game.getDefender(defendNum).isVulnerable()) {
			return game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(), false);
		}


		//Behavior for if at or near a junction
		if (game.getDefender(defendNum).getLocation() == maze.getJunctionNodes()) {
			if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(maze.getJunctionNodes(), true)) > 8) { //Node with many pills is closest
				whichDirection = game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(maze.getJunctionNodes(), true), true);
				return whichDirection;
			}
			//Attacker is closer
			else {
				if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(maze.getJunctionNodes(), true)) > 4) {
					return game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(maze.getJunctionNodes(), true), true);
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

	public int getPowerPillProtectorInky(Game game, int defendNum) { //protects around Power pills, if none exists then attacks Pac

		if (game.getDefender(defendNum).isVulnerable()) { //vulnerable mode
			return game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(), false);
		}

		List<Node> numPowerPills = game.getPowerPillList();
		int directionToMove = 0;

		if (numPowerPills.size() > 0) { // Power pill exists behavior
			if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(numPowerPills, true)) > 15) { //Pill is closestdirectionToMove = game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(numPowerPills, true), true);
				return directionToMove;
			} else { //Pacman is closest to pill
				if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(numPowerPills, true)) > 10) {
					return game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(numPowerPills, true), true);
				} else {
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

		//protects around forks in maze path if attacker is not closer than one; otherwise, seeks attacker
	public int getForkProtectorClyde(Game game, int defendNum, Maze maze) {


		int whichDirection = 0;

		//if Clyde is vulnerable, attempts to flee attacker
		if (game.getDefender(defendNum).isVulnerable()) {
			return game.getDefender(defendNum).getNextDir(game.getAttacker().getLocation(), false);
		}


		//Behavior for if at or near a junction
		if (game.getDefender(defendNum).getLocation() == maze.getJunctionNodes()) {
			if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(maze.getJunctionNodes(), true)) > 8) { //Node with many pills is closest
				whichDirection = game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(maze.getJunctionNodes(), true), true);
				return whichDirection;
			}
			//Attacker is closer
			else {
				if (game.getAttacker().getLocation().getPathDistance(game.getDefender(defendNum).getTargetNode(maze.getJunctionNodes(), true)) > 4) {
					return game.getDefender(defendNum).getNextDir(game.getDefender(defendNum).getTargetNode(maze.getJunctionNodes(), true), true);
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