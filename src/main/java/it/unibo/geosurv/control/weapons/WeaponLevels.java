package it.unibo.geosurv.control.weapons;

import java.util.ArrayList;
import java.util.Random;

public class WeaponLevels {
	
	ArrayList<Weapon> weapons;
	
	public WeaponLevels(final ArrayList<Weapon> weapons) {
		this.weapons = weapons;
	}
	
	public void levelUpWeapon() {
		boolean upgraded = false;
		Random random = new Random();
		while (upgraded == false) {
			int index = random.nextInt(this.weapons.size());
			Weapon tmpWeapon = weapons.get(index);
			upgraded = tmpWeapon.levelUp();
		}
	}
}
