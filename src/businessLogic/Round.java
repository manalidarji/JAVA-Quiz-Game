package businessLogic;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import exceptions.EndGameException;

public class Round {
    private static Scanner READ = new Scanner(System.in);    
    private ArrayList<Double> questAndCash; 
    
    public Round() {
    }

    public Round(ArrayList<Double> questAndCash) {
        this.questAndCash = questAndCash;
    }

    public ArrayList<Double> getQuestAndCash() {
        return questAndCash;
    }

    public void setQuestAndCash(ArrayList<Double> questAndCash) {
        this.questAndCash = questAndCash;
    }

    @Override
    public String toString() {
        return "Round{" + "questAndCash=" + questAndCash + '}';
    }
    
    public double startRound(QuestionBank QB, Lifeline lifeline) throws EndGameException{ 
        double cashWon = 0; int choosenOption = 0;
        
        for(int i = 0; i < questAndCash.size(); i++){
                // generate random and unique question
                Question quest = QB.generateRandomUniqueQuestion();
                
                // disp quest & get answer from user
                choosenOption = dispAndGetAnswerFromUser(quest, lifeline);

                // check answer and get cash prize
                cashWon = finalAnswerCheck(quest, choosenOption, i);
                
                if(cashWon == 0){
                    throw new EndGameException("\n** Uh Oh! That was Inorrect, the correct answer is: '" + quest.getCorrectAns() + "' **\n" +
                    "* Sorry, your game ends here! *");
                }else if(cashWon == 1000000.0){
                    throw new EndGameException("\n** Woohooo! You have made it to the End! **\n"
                            + "You are now a Millionaire! Congratulations on winning " + String.format("$%,.2f", cashWon) );
                }else{
                    System.out.println("\n** Bang on! That was Correct! Congratulations! You have won " + String.format("$%,.2f", cashWon) + " uptill now! **");
                }
            }        
        return cashWon;    
    }
    
    
    
    public int dispAndGetAnswerFromUser(Question quest, Lifeline lifeline){
        boolean validChoice = true;
        int choosenOption = 0;
        
        // display question with all options
        quest.dispQuestion();   
        
        // give choice of lifeline only if lifelines are enabled and are available
        if( !(lifeline == null || lifeline.getLifelines().isEmpty()) ){
            if( optionForLifeline() ){            
                lifeline.useLifeline(quest);
            }
        }

        // taking valid input for answer from the user
        try{
            do{
                validChoice = true;
                System.out.println("* Please enter valid Answer number: ");
                choosenOption = READ.nextInt();

                if(choosenOption < 1 || choosenOption > 4){
                    System.out.println("Entered value is invalid.");
                    validChoice = false;
                }
                
            }while( !validChoice ); 
        } catch(InputMismatchException ex){
            System.out.println("Input Mismatch");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        // confirm the answer
        choosenOption = confirmAnswerFromUser(quest, choosenOption, lifeline);
        
        return choosenOption;
    }   
    
    public boolean optionForLifeline(){
        boolean useLifeline = true, invalidChoice = true;
        char decision = 'z';        
        
        do {
            System.out.println("** Oh look, you have an option to use Lifeline! **\n" +
                "* Enter 'y' for 'Yes, I want to use a Lifeline' OR 'n' to move ahead *");
            decision = READ.next().charAt(0);

            switch (Character.toUpperCase(decision)) {
                case 'Y':                    
                    invalidChoice = false;
                    break;

                case 'N':                    
                    invalidChoice = false;
                    useLifeline = false;
                    break;

                default:
                    System.out.println("Entered value is invalid");
                    
            }   
        } while (invalidChoice);
        
        return useLifeline;
    }
    
    public int confirmAnswerFromUser(Question quest, int choosenOption, Lifeline lifeline){
        boolean validChoice = true;

        try {
            do {
                validChoice = true;
                System.out.println("** You have chosen answer: '" + quest.getOptions().get(choosenOption-1) + "' **\n");
                System.out.println("* Are you sure about your answer? To confirm please enter 1 or 2 *");
                System.out.println("1. Yes, I am pretty sure about this!");
                System.out.println("2. No, I would like to change my answer!");

                int confirmation = READ.nextInt();

                switch (confirmation) {
                    case 1:
                        return choosenOption;

                    case 2:
                        return dispAndGetAnswerFromUser(quest, lifeline);

                    default:
                        System.out.println("Entered value is invalid, Please select from 1, 2");
                        validChoice = false;
                }
            } while (!validChoice);
        } catch (InputMismatchException ex) {
            System.out.println("Input Mismatch");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return choosenOption;
    }
    
    //check answer
    public double finalAnswerCheck(Question quest, int choosenOption, int i) throws EndGameException{
        double cashWon = 0;       
        if( quest.checkAnswer(choosenOption) ){
            cashWon = questAndCash.get(i);           
        }
        return cashWon;
    }
}