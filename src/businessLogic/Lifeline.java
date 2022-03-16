package businessLogic;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Lifeline {
    private static Scanner READ = new Scanner(System.in);
    private ArrayList<String> lifelines;

    public Lifeline() {
    }

    public Lifeline(ArrayList<String> lifelines) {
        this.lifelines = lifelines;
    }

    public ArrayList<String> getLifelines() {
        return lifelines;
    }

    public void setLifelines(ArrayList<String> lifelines) {
        this.lifelines = lifelines;
    }

    @Override
    public String toString() {
        return "Lifeline{" + "lifelines=" + lifelines + '}';
    }
    
    public void init(){
        this.lifelines = new ArrayList<>();
        this.lifelines.add("50-50");
        this.lifelines.add("Ask The Audience");
        this.lifelines.add("Phone A Friend");
    } 
    
    public void dispLifelines(){
        int noOfLifelines = this.lifelines.size();
        
        if( noOfLifelines == 0 ){
            System.out.println("Sorry, you have exhausted your lifelines!");
        }else{
            System.out.println("\n** Below are the Available Lifelines **");
            for(int i = 0; i < this.lifelines.size();i++ ){
                System.out.println((i+1) + ". " + this.lifelines.get(i));
            }
        }
    }
    
    public String getLifelineName(int lifelineNumb){
        return this.lifelines.get(lifelineNumb-1);
    }
    
    public String askUserToChooseLifeline(){ 
        boolean invalidLifeLine = true;
        int choosenOption = 0;       
        
        do{
            try{
                dispLifelines();

                System.out.println("* Enter Lifelife number you want to use:");
                choosenOption = READ.nextInt();

                if( choosenOption > 0 && choosenOption <= this.lifelines.size() ){
                    invalidLifeLine = false;                    
                }else{
                    System.out.println("Invalid Lifeline number choosen");
                }       
            } catch (InputMismatchException ex) {
                System.out.println("There is an Input Mismatch");
                READ.next();
            } catch(Exception ex){
                System.out.println(ex.getMessage());
                READ.next();
            }
        }while(invalidLifeLine);  
        
        return this.lifelines.get(choosenOption-1);
    }
    
    public void useLifeline(Question quest){
        // ask user to choose lifeline from the available lifelines
        String choosenLifeline = askUserToChooseLifeline();        
        
        // execute lifeline
        switch(choosenLifeline){
            case "50-50":
                lifeline_50_50(quest);
                break;  
                        
            case "Ask The Audience":
                askTheAudience(quest);
                break;
                
            case "Phone A Friend":
                phoneAfriend(quest);
                break;
               
            default:
                System.out.println("No such life line available.");
        }        
    }
    
    public void lifeline_50_50(Question quest){  
        quest.removeIncorrectOptions(2);
        quest.dispQuestion();
        this.lifelines.remove("50-50");
    }
    
    public void askTheAudience(Question quest){ 
        // generate a random number in between 51 (inclusive) and 100(exclusive)
        Random r = new Random();
        int low = 51;
        int high = 100;
        int pollResult = r.nextInt(high-low) + low;
        
        System.out.println("** Around " + pollResult + "% of the audience says, '" + quest.getCorrectAns() + "' is the best answer to your question! **\n");
        this.lifelines.remove("Ask The Audience");
    }
    
    public void phoneAfriend(Question quest){ 
        System.out.println("** Your friend says, '" + quest.getCorrectAns() + "' is statistically the best answer to your question! **\n");
        this.lifelines.remove("Phone A Friend");
    }   
}