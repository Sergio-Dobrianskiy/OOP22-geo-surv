package it.unibo.geosurv.model.player;

public class Levels {
	
	private final static int STARTING_LEVEL = 1;
	private final static int MAX_LEVEL = 16;
	private final static int EXP_FIRST_LEVEL = 30;
	private final static float LEVEL_MULTIPLIER = 1.1f;
	private int currentLevel;
	private int currentExp;
	private int expToLevel;
	
	public Levels() {
		this.currentLevel = Levels.STARTING_LEVEL;
		this.currentExp = 0;
		this.expToLevel = Levels.EXP_FIRST_LEVEL;
	}
	
	public void expUp (final int exp) {
		this.currentExp += exp;
		if (this.currentExp > this.expToLevel && this.currentLevel < Levels.MAX_LEVEL) {
			this.levelUp();
		}
	}
	
	public void levelUp () {
		if (this.currentLevel < Levels.MAX_LEVEL) {
			this.currentLevel += 1;
			this.currentExp = 0;
			this.expToLevel *= LEVEL_MULTIPLIER;
		}
	}

	public int getCurrentLevel() {
		return this.currentLevel;
	}
	
	public int getCurrentExp() {
		return this.currentExp;
	}
	
	public int getExpToLevel() {
		return this.expToLevel;
	}

}
