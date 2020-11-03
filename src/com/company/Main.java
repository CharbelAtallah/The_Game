package com.company;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //System objects
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        //Game Variables
        String[] enemies = {"Skeleton", "Zombie", "Vampire", "Werewolf"}; //Array of enemies
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        //Player variables
        int health = 100;
        int attackDamage = 50;
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; //Percentage %

        boolean running = true;

        System.out.println("Welcome to the dungeon!");

        Game:
        //labeling the first while loop 'Game'. Because we gonna use 2 while loops, and want to return to the main while loop
        while (running) {
            System.out.println("--------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)]; //Randomise the enemies array. 0 to array max length
            System.out.println("\t# " + enemy + " has appeared! #\n"); //What enemy appeared

            //Another while loop, to keep iterating until the enemy above is dead.
            while (enemyHealth > 0) { //Greater than 0 -> keeps looping
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);

                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack"); //Option 1
                System.out.println("\t2. Drink Health potion"); //Option 2
                System.out.println("\t3. Run!"); //Option 3

                String input = scan.nextLine();

                if (input.equals("1")) { //if user types 1. this block activates the attack mode.
                    // Create 2 variables. Whenever user attacks the enemy, it gonna deal dmg, and receive dmg.
                    int damageDealt = rand.nextInt(attackDamage); // Random number between 0-50. 50 = attackDamage.
                    int damageTaken = rand.nextInt(enemyAttackDamage); //Same, but enemies attack damage.

                    enemyHealth -= damageDealt; //Takes away the damage dealt from the enemies health
                    health -= damageTaken; //Takes away the damage dealt from the users health

                    System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                    System.out.println("\t> You receive " + damageTaken + " in retaliation!");

                    if (health < 1) {
                        System.out.println("\t> You have taken to much damage, you are too weak to go on!");
                        break; //This breaks out of the second while loop to bring us down to the end message, located at the bottom of the first loop
                    }
                } else if (input.equals("2")) { //if user types 2. this block activates the potion mode.
                    if (numHealthPotions > 0) { //First check if there is a number of health potions available
                        health += healthPotionHealAmount; //Add on to the amount the health potion adds for
                        //minus the amount of health potions available after using one
                        numHealthPotions--; //Minus one from the collection
                        System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + " . " //You healed for x amount
                                + "\n\t> You now have " + health + " HP." //You now have x amount of health
                                + "\n\t> You have " + numHealthPotions + " health potions left. \n"); //You have x amount of health potions available
                    } else { //In case it doesn't have any health potions left
                        System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
                    }

                } else if (input.equals("3")) { //if user types 3. this block activates the run mode.
                    System.out.println("\tYou run away from the " + enemy + "!");
                    continue Game; //Ignores everything else below this loop, go back to the start of the first loop, We labeled 'Game'
                } else { //If the user type in wrong stuff, or invalid number
                    System.out.println("\tInvalid command!"); //Feedback
                }
            }
            if (health < 1) {
                System.out.println("You limp out of the dungeon, weak from the battle.");
                break; //The break here would break out of the main while running loop
            }

            System.out.println("--------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println(" # You have " + health + " HP left. # ");
            //If we defeat an enemy, potions could drop. Here we put in the variable.
            if (rand.nextInt(100) < healthPotionDropChance) { //Generate a random number between 0-100.
                // If the random number is less than the percentage chance (50).
                numHealthPotions++;
                System.out.println(" # The " + enemy + " dropped a health potion! # ");
                System.out.println(" # You have " + numHealthPotions + " health potion(s). # ");
            }
            System.out.println("--------------------------------------------");
            //Present 2 option for the player to continue or not.
            System.out.println("What would you like to do?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            String input = scan.nextLine();

            while (!input.equals("1") && !input.equals("2")) { //if input doesn't equals 1 nor 2.
                System.out.println("Invalid command!");
                input = scan.nextLine(); //Grabs the next input and hope the user types in a valid one to jump to the if statements.
            }
            if (input.equals("1")) {
                System.out.println("You continue on your adventure!");
            }
            if (input.equals("2")) {
                System.out.println("You exit the dungeon successfully!");
                break; //breaks out of this loop
            }


        }
        System.out.println("#######################");
        System.out.println("# THANKS FOR PLAYING! #");
        System.out.println("#######################");


    }
}
