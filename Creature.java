package dragon;

//
//Author: Jordan J. Greiner
//Date: 3/4/2021
//Updated 3/8/2021
//
//Description: A simple turn based fight between a knight and a dragon. Creature class. The
//hero and the dragon inherit from this class. It could potentially be used in the future to 
//make other kinds of creatures.
//

public abstract class Creature {
	
	protected String name;
	protected int health;
	protected int trueDefense;
	protected int defense;
	protected boolean isPoisoned;
	protected boolean isDead;
	
	public Creature(String name, int health, int defense) {
		this.name = name;
		this.health = health;
		this.trueDefense = defense;
		this.defense = defense;
		this.isPoisoned = false;
		this.isDead = false;
	}
	
	public abstract void defend();
	public abstract void attack(Creature c, String weapon);
	
	protected void takeDamage(int damage) {
		if (damage > this.defense) {
			int pain = damage - this.defense;
			this.health -= pain;
			System.out.println("-" + this.name + " takes " + pain + " damage.");
		} else {
			System.out.println("-" + this.name + " takes no damage.");
		}
		
		if (this.health <= 0) {
			this.isDead = true;
			System.out.println("-" + this.name + " has been vanquished!");
		}
	}
	
	protected void getPoisoned() {
		this.isPoisoned = true;
		System.out.println("-" + this.name + " is poisoned.");
	}
	
	public boolean poisonStatus(){
		return this.isPoisoned;
	}
	
	public void takePoisonDamage() {
		//could have made this neater with the existing damage method
		this.health--;
		System.out.println("-" + this.name + " takes " + 1 + " poison damage.");
	}
	
	public boolean getIsDead() {
		return this.isDead;
	}
	
	public abstract void useHolyWater();
	
	public String getName() {
		return this.name;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getDefense() {
		return this.defense;
	}
	
	public void resetDefense() {
		this.defense = this.trueDefense;
	}
	
	public String toString() {
		return "Name: " + this.name + "\n" +
			   "Health: " + this.health + "\n" +
			   "Type: " + this.getClass().getName();
	}
	
	public String endOfTurnUpdate() {
		return "end of turn update"; //code this
	}
	
}
