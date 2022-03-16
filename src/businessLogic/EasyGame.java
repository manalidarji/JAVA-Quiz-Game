package businessLogic;

import data.Data;
import java.util.ArrayList;

public class EasyGame extends Game {

    public EasyGame() {
    }
    
    public EasyGame(User user) {
        super(user);
    }    
    
    public EasyGame(QuestionBank QB, User user) {
        super(QB, user);
    }
    
    @Override
    public void assignCashPrize(Data data) {
        super.rounds = new ArrayList<Round>();
        ArrayList easyRoundsCashPrizes = new ArrayList<>();
        easyRoundsCashPrizes = data.getEasyRoundCashPrizes();
        
        for(int i = 0; i < easyRoundsCashPrizes.size(); i++){
            super.rounds.add( new Round( (ArrayList<Double>) easyRoundsCashPrizes.get(i)) );
        }
    }  
}