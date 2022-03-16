package consoleInterface;

import java.util.InputMismatchException;
import java.util.Scanner;
import businessLogic.Game;
import businessLogic.EasyGame;
import businessLogic.HardGame;
import businessLogic.User;
import data.Data;

public class ConsoleInterface {
    private static final Scanner READ = new Scanner(System.in);

    public ConsoleInterface() {
    }
    
    public boolean launchScreen(Data data){
        boolean invalidChoice = true;
        boolean continueGame = true;
        int choosenOption = 0;
        
        System.out.println("\n********* Who Wants to Be a Millionaire? ********\n");   
        
        do {
            try {                
                System.out.println("* Please choose an approppriate number from below menu *");
                System.out.println("1. Start the game");
                System.out.println("2. View the rules of the game");
                System.out.println("3. Exit the game");
                
                choosenOption = READ.nextInt();

                switch (choosenOption) {
                    case 1:
                        System.out.println("\n** Let's Begin the Game! **\n");
                        invalidChoice = false;
                        break;

                    case 2:
                        continueGame = displayRules(data);
                        invalidChoice = false;
                        break;

                    case 3:
                        invalidChoice = false;
                        continueGame = false;
                        break;

                    default:
                        System.out.println("Entered value is invalid, Please select from 1, 2, 3");
                        invalidChoice = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("There is an Input Mismatch. Please select from 1, 2, 3");
                READ.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " Please select from 1, 2, 3");
                READ.next();
            }              
        } while(invalidChoice);  
        
        return continueGame;
    }
    
    public boolean displayRules(Data data){
        boolean continueGame = false;
        System.out.println(data.getGameRules());
        
        boolean invalidChoice = true;
        do{
            try{
                System.out.println("\n* Please enter 0 to go back to Main Menu *");
                int choosenOption = READ.nextInt();

                if(choosenOption == 0){
                    continueGame = launchScreen(data);
                    invalidChoice = false;
                }else{
                    System.out.println("Entered value is invalid");                    
                } 
            } catch(InputMismatchException ex){
                System.out.println("Input Mismatch");
                READ.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                READ.next();
            }
        }while( invalidChoice );    
        return continueGame;
    } 
    
    public User createUser(){
        User user = null;        
        try{
            System.out.println("** First, let's get to know you! **");        
            System.out.println("* Please Enter your First name:");
            String fName = READ.next();
            System.out.println("* Please Enter your Last name:");
            String lName = READ.next();            
            user = new User(fName, lName);            
        }catch(InputMismatchException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return user;
    }
    
    public void welcomeUser(User user){
        System.out.println("\n** Hey there " + user.getFullName() + "! Welcome to the game, Who Wants to Be a Millionaire! ***\n"
                + "* Now that we know you, let's get you started! *\n");
    }
    
    public Game createGame() {
        boolean invalidChoice = true;
        int choosenOption = 0;   
        
        Game game = null;
        
        do {
            try {
                System.out.println("* Please choose your game number from below menu as per the difficulty level:");
                System.out.println("1. Easy Game");
                System.out.println("2. Hard Game");

                choosenOption = READ.nextInt();

                switch (choosenOption) {
                    case 1:                    
                        invalidChoice = false;
                        game = new EasyGame();
                        System.out.println("\n* Easy Game! oh, that's a good safe choice! *\n");
                        break;

                    case 2:                    
                        invalidChoice = false;
                        game = new HardGame();
                        System.out.println("\n* Hard Game! wooh, we have a risk-taker here! *\n");
                        break;

                    default:
                        System.out.println("Entered value is invalid, Please select from 1, 2");                    
                }
            } catch (InputMismatchException ex) {
                System.out.println("Input Mismatch");
                READ.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                READ.next();
            }                  
        } while (invalidChoice);  
        
        return game;
    }
}