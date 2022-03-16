package businessLogic;

import java.util.ArrayList;
import java.util.Random;

public class Question {    
    private static int QUES_ID_COUNTER = 0;
    private int questID;
    private String queText;
    private ArrayList<String> options;
    private String correctAns;

    public Question() {        
        QUES_ID_COUNTER++;
        this.questID = QUES_ID_COUNTER;
    }

    public Question(String queText, ArrayList<String> options, String correctAns) {
        QUES_ID_COUNTER++;
        this.questID = QUES_ID_COUNTER;
        this.queText = queText;
        this.options = options;
        this.correctAns = correctAns;
    }

    public int getQuestID() {
        return questID;
    }

    public void setQuestID(int questID) {
        this.questID = questID;
    }

    public static int getQUES_ID_COUNTER() {
        return QUES_ID_COUNTER;
    }

    public static void setQUES_ID_COUNTER(int QUES_ID_COUNTER) {
        Question.QUES_ID_COUNTER = QUES_ID_COUNTER;
    }

    public String getQueText() {
        return queText;
    }

    public void setQueText(String queText) {
        this.queText = queText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    @Override
    public String toString() {
        return "Question {" + "questID=" + questID + ", queText=" + queText + ", options=" + options + ", correctAns=" + correctAns + '}';
    }  
    
    public void dispQuestion(){
        System.out.println("\n***** QUESTION *****");
        System.out.println("Q: "+ this.queText + "?");
        for(int i = 0; i < this.options.size(); i++){
            System.out.println( (i+1) + ") " + this.options.get(i));
        }
        System.out.println("**********************\n");
    }
    
    public boolean checkAnswer(int choosenOption){
        boolean correctAns = false;        
        if( this.options.get(choosenOption-1).equals(this.getCorrectAns()) ){
            correctAns = true;
        }        
        return correctAns;
    }
    
    public void removeIncorrectOptions(int noOfOptions){
        // generate a random number in between 0 (inclusive) and 'options size'(exclusive)
        Random r = new Random();
        int low = 0, high = 4, optIdx = 0;
        boolean notRemoved = true;     
        String optionString = "";
        
        // run loop for noOfOptions times
        for(int j = 0; j < noOfOptions; j++){
            do{
                high = this.options.size();
                optIdx = r.nextInt(high-low) + low;
                optionString = this.options.get(optIdx);
                if( !optionString.equals(this.correctAns) ){
                    this.options.remove(optionString);
                    notRemoved = false;
                }else{
                    notRemoved = true;
                }             
            }while(notRemoved);           
        }    
    }
}