package dragon;

//
//Author: Jordan J. Greiner
//Date: 3/4/2021
//Updated 3/8/2021
//
//Description: A simple turn based fight between a knight and a dragon. It requires a lot
//of work and was thrown together in a hurry but I wanted to make it to show I understand 
//classes and inheritance, etc. It was originally inspired by St. George and the Dragon but I ended
//up using Arthur (for king arthur) and Vermithrax (the dragon from Dragonslayer, the movie).
//

// Potential TO DO list:
//would be cool to add a portion where the user names their character
//make creature type not be dragon.TYPE (when we look up stats)
//a toString for dragon
//change usePotion for when we create a hero w more than 100 health
//make arrows pierce a flying dragon
//code an "end of turn" message that will say stuff like 'the hero isn't looking too good' based on health

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Hero hero = new Hero("Arthur");
		Dragon dragon = new Dragon("Vermithrax");
		System.out.println("======================");
		System.out.println("Hero Name: " + hero.getName());
		System.out.println("Drgaon Name: " + dragon.getName());
		System.out.println("======================");
		
		Scanner scanner = new Scanner(System.in);
		printScenario();

		while(true) {
			printBattleOptions();
			String input = scanner.nextLine();
			System.out.println(); // newline after input
			
			if(verifyInput(input)) {
				if (input.equals("stats")){
					System.out.println(hero + "\n\n");
					continue;
					} // so stats don't take a turn to see
				executeCommand(input, hero, dragon); // hero attacks
				if (dragon.getFlying()) {dragon.resetDefense();} // resets defense bonus from flying
				if (dragon.getIsDead()) {break;} // check if dragon died
				randomDragonAction(hero, dragon); // dragon attacks
				if (hero.getIsDead()) {break;}  // check if human died
				if (hero.poisonStatus()) {hero.takePoisonDamage();}  // check for poison and deal damage
				if (hero.getIsDead()) {break;}  // check if human died
				hero.resetDefense(); // resets defense bonus from using shield
				hero.endOfTurnUpdate();
				dragon.endOfTurnUpdate();
			} else {
				continue;
			}
			System.out.println(); //newline at the end of the turn
		}
		
		if(hero.getIsDead()){
			System.out.println("========================");
			System.out.println("GAME OVER - YOU DIED...\nBetter luck next time!");
			System.out.println("========================");
		} else if (dragon.getIsDead()){
			System.out.println("==============================");
			System.out.println(hero.getName() + " had vanquished " + dragon.getName() + 
								"!\nPeace had returned to the kingdom!\nGood Job!");
			System.out.println("==============================");
		}
		
		scanner.close();
	}
	
	public static void printScenario() {
		System.out.println("Welcome to the battle of the hero and the dragon.\n" +
							"You were called to vanquish an evil dragon who is\n" +
							"destroying the kingdom! Get ready, Hero!\n\n" +
							"As you approach the mouth of the vile beast's cave, he\n" +
							"suddenly rushes out and comes upon you. As the hills shake,\n" +
							"you are left with a choice...\n");
	}
	
	public static void printBattleOptions() {
		System.out.println("What shall you do?\n" +
							"Attack by bow? - input 'bow'\n" +
							"Attack by blade? - input 'blade'\n" +
							"Attack by lance? - input 'lance'\n" +
							"Defend with the shield? - input 'shield'\n" +
							"Use a health potion? - input 'health'\n" +
							"Use holy water? - input 'holy'\n" +
							"See your stats? - input 'stats'\n" +
							"Input: ");
	}
	
	public static boolean verifyInput(String input) {
		List<String> validResponses = new ArrayList<String>();
		validResponses.add("bow");
		validResponses.add("blade");
		validResponses.add("lance");
		validResponses.add("shield");
		validResponses.add("health");
		validResponses.add("holy");
		validResponses.add("stats");
		
		if (!validResponses.contains(input)) {
			System.out.println("Please input a valid option.\n");
			return false;
		} else {
			return true;
		}
	}
	
	public static void executeCommand(String input, Hero hero, Dragon dragon) {
		switch(input) {
		  case "bow":
		    hero.attack(dragon, "A");
		    break;
		  case "blade":
			hero.attack(dragon, "S");
			break;
		  case "lance":
		    hero.attack(dragon, "L");
			break;
		  case "shield":
			hero.defend();
			break;
		  case "health":
			hero.usePotion();
			break;
		  case "holy":
			hero.useHolyWater();
			break;
		  default:
		    System.out.println("-The hero trips on a twig.");
		}
	}
	
	public static void randomDragonAction(Hero hero, Dragon dragon) {
		Random random = new Random(); 
        int selection = random.nextInt(4); 
        
		switch(selection) {
		  case 0:
		    dragon.attack(hero, "F");
		    break;
		  case 1:
			dragon.attack(hero, "C");
			break;
		  case 2:
			dragon.attack(hero, "B");
			break;
		  case 3:
			dragon.defend();
			break;
		  default:
		    System.out.println("-The dragon remembers a song and starts humming.");
		}
	}
}
