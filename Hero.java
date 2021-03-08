package dragon;

//
//Author: Jordan J. Greiner
//Date: 3/4/2021
//Updated 3/8/2021
//
//Description: A simple turn based fight between a knight and a dragon. Hero class.
//

public class Hero extends Creature{
	private int shield;
	private int arrows;
	private int potions;
	private int holyWater;
	private int lanceAttack; // maybe a bit sloppy setting up just this one case
	
	public Hero() {
		this("Default Knight");
	}
	
	public Hero(String name) {
		super(name, 100, 0);
		this.shield = 13;
		this.arrows = 5;
		this.potions = 1;
		this.holyWater = 1;
		this.lanceAttack = 18;
	}
	
	public Hero(String name, int health, int defense, int shield, int arrows, int potions, int holyWater, int lanceAttack) {
		super(name, health, defense);
		this.shield = shield;
		this.arrows = arrows;
		this.potions = potions;
		this.holyWater = holyWater;
		this.lanceAttack = lanceAttack;
	}
	
	public void defend() {
		System.out.println("-You raise the Shield of Courage.");
		this.defense += this.shield;
	}
	
	public void attack(Creature c, String weapon) {
		// S = sword
		// L = lance
		// A = arrow
		
		if (weapon.equals("A")) {
			if (arrows > 0) {
				System.out.println("-" + this.name + " shoots a well placed arrow at " + c.getName());
				c.takeDamage(17);
				this.arrows--;
			} else {
				System.out.println("-You reach into your quiver only to find it empty!");
				c.takeDamage(0);
			}
		} else if (weapon.equals("L")) {
			System.out.println("-" + this.name + " stabs with their trusty lance!");
			c.takeDamage(this.lanceAttack);
		} else if (weapon.equals("S")) {
			System.out.println("-" + this.name + " slashes the Sword of Truth at the terrible wyrm.");
			c.takeDamage(19);
		} else {
			System.out.println("-You panic and stand frozen in fear.");
			c.takeDamage(0);
		}
	}
	
	public void usePotion() {
		if (potions > 0) {
			System.out.println("-Turns out the potion you purchased for a few shiny stones came in handy! You gain 40 health.");
			if (40 + this.health >= 100) {
				this.health = 100;
			} else {
				this.health += 40;
			}
			if (this.isPoisoned) {
				this.isPoisoned = false;
				System.out.println("-" + this.name +" is poisoned no longer!");
			}
			this.potions--;
		} else {
			System.out.println("-You reach in your pocket for a potion but there are none!");
		}
	}
	
	public void useHolyWater() {
		if (holyWater > 0) {
			System.out.println("-'Good thing I went to confession today!' You sprinkle the lance with the Holy Water and it gains attack power!");
			this.lanceAttack += 4;
			this.holyWater--;
		} else {
			System.out.println("-You reach in your pocket for Holy Water but there is none!");
		}
	}
	
	public String toString() {
		return super.toString() + "\nPoison Status: " + this.isPoisoned +
				"\nArrows Remaining: " + this.arrows + "\nRemaining Holy Water: " +
				this.holyWater + "\nRemaining Potions: " + this.potions;
	}
}
