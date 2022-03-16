package javaquizgame;

/**
 *
 * @author darji
 */

import businessLogic.EasyGame;
import businessLogic.Game;
import businessLogic.HardGame;
import businessLogic.QuestionBank;
import businessLogic.User;
import consoleInterface.ConsoleInterface;
import data.Data;
import exceptions.EndGameException;
import java.io.IOException;

public class JavaQuizGame {
    public static void main(String[] args){        
        // create object to get all hardcoded Data  
        Data data = new Data();
        try {            
            data.init();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        ConsoleInterface CI = new ConsoleInterface();
      
        // launching Screen with initial instructions for the game  
        if( CI.launchScreen(data) ){        
            // creating question Bank
            QuestionBank QB = new QuestionBank();
            QB.createQuestionBank(data.getQuestionsFilePath());

            // creating an user
            User user = CI.createUser();
            CI.welcomeUser(user);
            
            // get game object, based on user's choice of difficulty
            Game game = CI.createGame();
            
            // set question bank & user
            game.setQB(QB);
            game.setUser(user);
            
            // assign cash Prizes as per question number
            game.assignCashPrize(data);
            
            // enable lifeline in respective rounds based on game's difficulty level
            int enableLifelineInRound;
            if( game instanceof EasyGame ){
                enableLifelineInRound = 1;
            }else if( game instanceof HardGame){
                enableLifelineInRound = 2;
            }else{
                enableLifelineInRound = 0;
            }
                        
            // start the game
            try{  
                game.startGame(enableLifelineInRound);
            } catch(EndGameException ex){
                System.out.println(ex.getMessage());
            } catch(Exception ex){
                System.out.println(ex.getMessage());
            } 
        }else{ System.out.println("** Alreay Going? That's sad! **"); }   
    }
}