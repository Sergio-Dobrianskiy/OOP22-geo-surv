package it.unibo.geosurv.model.player;

public class PlayerLevels {

	private final static int STARTING_LEVEL = 1;
	private final static int MAX_LEVEL = 12; // 4 weapons * 3 levels each
	private final static int EXP_FIRST_LEVEL = 2;
	private final static float LEVEL_MULTIPLIER = 1.1f;
	private final MainPlayer player;
	private int currentLevel;
	private int currentExp;
	private int expToLevel;

	public PlayerLevels(final MainPlayer player) {
		this.currentLevel = PlayerLevels.STARTING_LEVEL;
		this.currentExp = 0;
		this.expToLevel = PlayerLevels.EXP_FIRST_LEVEL;
		this.player = player;
	}

	public void expUp(final int exp) {
		this.currentExp += exp;
		if (this.currentExp > this.expToLevel && this.currentLevel < PlayerLevels.MAX_LEVEL) {
			this.levelUp();
		}
	}

	public void levelUp() {
		if (this.currentLevel < PlayerLevels.MAX_LEVEL) {
			this.currentLevel += 1;
			this.currentExp = 0;
			this.expToLevel *= LEVEL_MULTIPLIER;
			this.player.levelUpWeapon();
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
