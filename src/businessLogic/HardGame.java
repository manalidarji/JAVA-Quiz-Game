package businessLogic;

import data.Data;
import java.util.ArrayList;

public class HardGame extends Game {

    public HardGame() {
    }
    
    public HardGame(User user) {
        super(user);
    }    
    
    public HardGame(QuestionBank QB, User user) {
        super(QB, user);
    }  
    
    @Override
    public void assignCashPrize(Data data) {
        super.rounds = new ArrayList<>();
        ArrayList hardRoundsCashPrizes = new ArrayList<>();
        hardRoundsCashPrizes = data.getHardRoundCashPrizes();

        for(int i = 0; i < hardRoundsCashPrizes.size(); i++){
            super.rounds.add( new Round( (ArrayList<Double>) hardRoundsCashPrizes.get(i)) );
        }
    }
}