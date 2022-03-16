package businessLogic;

import data.Data;
import exceptions.EndGameException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Game {
    private QuestionBank QB;
    private User user;
    protected ArrayList<Round> rounds;
    private Lifeline lifeline;

    public Game() {
    }
    
    public Game(User user) {
        this.user = user;
    }

    public Game(User user, ArrayList<Round> rounds) {
        this.user = user;
        this.rounds = rounds;
    }

    public Game(QuestionBank QB, User user) {
        this.QB = QB;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public QuestionBank getQB() {
        return QB;
    }

    public void setQB(QuestionBank QB) {
        this.QB = QB;
    }

    @Override
    public String toString() {
        return "Game{" + "QB=" + QB + ", user=" + user + ", rounds=" + rounds + '}';
    }
   
    public abstract void assignCashPrize(Data data);
    
    public void startGame(int enableLifelineInRound) throws EndGameException{     
        this.lifeline = null;
        double cashPrizeWon = this.user.getCashPrize();
        boolean startRound = false;
        
  
        for(int i = 0 ; i < this.rounds.size(); i++){
            // if previous round was won, then give user an option to walk way
            if(cashPrizeWon != 0 ){
                if(optionToWalkAway(cashPrizeWon)){
                    throw new EndGameException("** Your game ends here! **\n"
                            + "** Congratulations on your cash Prize of " + String.format("$%,.2f", cashPrizeWon) + " **");
                }else{
                    startRound = true;
                }
            }else{
                startRound = true;
            }
            
            if(startRound){
                // enable lifeline in a particular round
                if( enableLifelineInRound == (i+1) ){
                    this.lifeline = new Lifeline();
                    this.lifeline.init();
                }
                System.out.println("*** Welcome to Round " + (i+1) + " ***");
                cashPrizeWon = this.rounds.get(i).startRound(this.QB, this.lifeline);
                this.user.setCashPrize(cashPrizeWon);
                System.out.println("*** End of Round " + (i+1) + " ***");
            }
        }     
    }
    
    public boolean optionToWalkAway(double cashPrizeWon){
        Scanner read = new Scanner(System.in);
        boolean walkAway = true, invalidChoice = true;
        char decision = 'a';        
        
        do {
            System.out.println("** Wow, you have sucessfully completed the round! **\n"
                    + "Do you wish to walk away with " + String.format("$%,.2f", cashPrizeWon) + " cash Prize? Enter 'y' for yes OR 'n' to go to next round\n");
            decision = read.next().charAt(0);

            switch (Character.toUpperCase(decision)) {
                case 'Y':                    
                    invalidChoice = false;
                    break;

                case 'N':                    
                    invalidChoice = false;
                    walkAway = false;
                    break;

                default:
                    System.out.println("Entered value is invalid");
                    
            }   
        } while (invalidChoice);
  
        return walkAway;
    }
}
