package businessLogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuestionBank {
    private static ArrayList<Integer> QUEST_ID_USED = new ArrayList<>();
    private ArrayList<Question> allQuestions;
    private static int VALID_QUEST_NUMB = 1;
    
    public QuestionBank() {
    }

    public QuestionBank(ArrayList<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }

    public ArrayList<Question> getAllQuestions() {
        return allQuestions;
    }

    public void setAllQuestions(ArrayList<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }

    @Override
    public String toString() {
        return "QuestionBank{" + "allQuestions=" + allQuestions + '}';
    }    
    
    public void createQuestionBank(String filePath){
        String line, data[], queText, correctAns;        
        try {
            allQuestions = new ArrayList<Question>();
            
            FileReader file = new FileReader(filePath);
            BufferedReader input = new BufferedReader(file);
            
            line = input.readLine();
            
            while(line != null){
                data = line.split(",");
                
                queText = data[0];
                ArrayList<String> options = new ArrayList<String>();
                options.add(data[1]);
                options.add(data[2]);
                options.add(data[3]);
                options.add(data[4]);
                correctAns = data[5];

                allQuestions.add(new Question(queText, options, correctAns));               
                line = input.readLine();
            }   
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Question generateRandomUniqueQuestion(){
        int randomID; 
        int low = 1, high = allQuestions.size() + 1;
        boolean validQuestion = false;
        Random rand = new Random();
        
        do{
            // generate a random number in between 'low' (inclusive) and 'high'(exclusive)
            randomID = rand.nextInt(high-low) + low;
        
            if (!QUEST_ID_USED.contains(randomID)) {
                QUEST_ID_USED.add(randomID); 
                validQuestion = true;
            }
        }while(!validQuestion);
        
        return getQuestionByID(randomID);
    }
    
    public Question getQuestionByID(int questID){
        for(int i = 0; i < allQuestions.size(); i++ ){
            if(allQuestions.get(i).getQuestID() == questID){
                return allQuestions.get(i);
            }
        }
        return null;
    } 
}