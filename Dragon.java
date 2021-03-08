package dragon;

//
//Author: Jordan J. Greiner
//Date: 3/4/2021
//Updated 3/8/2021
//
//Description: A simple turn based fight between a knight and a dragon. Dragon class.
//

public class Dragon extends Creature{
	private int flightTimes;
	private boolean isFlying;
	
	public Dragon() {
		this("A Great Wyrm");
	}
	
	public Dragon(String name) {
		super(name, 100, 5);
		this.flightTimes = 2;
		this.isFlying = false;
	}
	
	public Dragon(String name, int health, int defense, int flightTimes) {
		super(name, health, defense);
		this.flightTimes = flightTimes;
		this.isFlying = false;
	}
	
	public void defend() {
		if (flightTimes > 0) {
			this.defense = Integer.MAX_VALUE;
			this.flightTimes--;
			this.isFlying = true;
			System.out.println("-" + this.name + " takes flight!");
		} else {
			System.out.println("-" + this.name + " tried to fly but was too fatigued to get off the ground.");
		}
	}
	
	public void attack(Creature c, String weapon) {
		// F = fire breath
		// C = claw
		// B = vemom bite
		
		if (weapon.equals("F")) {
			System.out.println("-" + this.name + " blows a fierce fire as if from the pits of hell.");
			c.takeDamage(15);
		} else if (weapon.equals("C")) {
			System.out.println("-" + this.name + " swipes with its razor sharp claws.");
			c.takeDamage(16);
		} else if (weapon.equals("B")) {
			System.out.println("-" + this.name + " bites with awful venomous fangs!");
			c.takeDamage(13);
			if (!c.poisonStatus()) {c.getPoisoned();} // only shows message if not previously poisoned
		} else {
			System.out.println("-The beast sneezes and loses a turn.");
			c.takeDamage(0);
		}
	}
	
	public boolean getFlying() {
		return isFlying;
	}
	
	public void useHolyWater() {
		System.out.println("-The holy water burns the dragon and is cries in pain.");
	}
	
	public void resetDefense() {
		super.resetDefense();
		this.isFlying = false;
		System.out.println("-" + this.name + " lands.");
	}
}
