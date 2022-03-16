package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
    private String gameRules;
    private String questionsFilePath;
    private ArrayList<ArrayList> easyRoundCashPrizes;
    private ArrayList<ArrayList> hardRoundCashPrizes;

    public Data() {
    }

    public String getQuestionsFilePath() {
        return questionsFilePath;
    }

    public void setQuestionsFilePath(String questionsFilePath) {
        this.questionsFilePath = questionsFilePath;
    }

    public ArrayList<ArrayList> getEasyRoundCashPrizes() {
        return easyRoundCashPrizes;
    }

    public void setEasyRoundCashPrizes(ArrayList<ArrayList> easyRoundCashPrizes) {
        this.easyRoundCashPrizes = easyRoundCashPrizes;
    }

    public ArrayList<ArrayList> getHardRoundCashPrizes() {
        return hardRoundCashPrizes;
    }

    public void setHardRoundCashPrizes(ArrayList<ArrayList> hardRoundCashPrizes) {
        this.hardRoundCashPrizes = hardRoundCashPrizes;
    }

    public String getGameRules() {
        return gameRules;
    }

    public void setGameRules(String gameRules) {
        this.gameRules = gameRules;
    }

    public void init() throws FileNotFoundException, IOException{
        this.questionsFilePath = "question_bank.txt";
        setEasyGameCashPrizes();        
        setHardGameCashPrizes();
        this.gameRules = readGameRules();
    }
    
    public void setEasyGameCashPrizes(){
        // Round 1 cash prizes
        ArrayList<Double> round1 = new ArrayList<>();  
        round1.add(100.0); 
        round1.add(500.0);
        round1.add(1000.0);
        
        // Round 2 cash prizes
        ArrayList<Double> round2 = new ArrayList<>();  
        round2.add(8000.0); 
        round2.add(16000.0);
        round2.add(32000.0);
        
        // Round 3 cash prizes
        ArrayList<Double> round3 = new ArrayList<>();  
        round3.add(125000.0); 
        round3.add(500000.0);
        round3.add(1000000.0);
        
        // set whole easy game
        this.easyRoundCashPrizes = new ArrayList<>();
        this.easyRoundCashPrizes.add(round1); 
        this.easyRoundCashPrizes.add(round2);
        this.easyRoundCashPrizes.add(round3);
    }
    
    public void setHardGameCashPrizes(){
        // Round 1 cash prizes
        ArrayList<Double> round1 = new ArrayList<>();  
        round1.add(100.0); 
        round1.add(200.0); 
        round1.add(300.0); 
        round1.add(500.0); 
        round1.add(1000.0); 

        // Round 2 cash prizes
        ArrayList<Double> round2 = new ArrayList<>();  
        round2.add(2000.0);
        round2.add(4000.0);
        round2.add(8000.0); 
        round2.add(16000.0); 
        round2.add(32000.0); 

        // Round 3 cash prizes
        ArrayList<Double> round3 = new ArrayList<>();  
        round3.add(64000.0); 
        round3.add(125000.0); 
        round3.add(250000.0); 
        round3.add(500000.0); 
        round3.add(1000000.0);

        // set whole hard game
        this.hardRoundCashPrizes = new ArrayList<>();
        this.hardRoundCashPrizes.add(round1); 
        this.hardRoundCashPrizes.add(round2);
        this.hardRoundCashPrizes.add(round3);
    }

    public String readGameRules() throws FileNotFoundException, IOException{
        String gameRules = "";
        String gameRulesFilePath = "game_rules.txt";
        
        FileReader file = new FileReader(gameRulesFilePath);
        BufferedReader input = new BufferedReader(file);
        
        String line = input.readLine();
            
        while(line != null){
            gameRules += "\n" + line;               
            line = input.readLine();
        }   
        input.close();                
        return gameRules;
    }
    
    @Override
    public String toString() {
        return "Data{" + "questionsFilePath=" + questionsFilePath + ", easyRoundCashPrizes=" + easyRoundCashPrizes + '}';
    }   
}