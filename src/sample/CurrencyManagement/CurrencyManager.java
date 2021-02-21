package sample.CurrencyManagement;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class CurrencyManager {

    private int exp;
    private int lvllimiter=100;

    //update experience
    public void updateExperience(Label level, int gain){
        int lvl = Integer.parseInt(level.getText());
        exp=exp+gain;

        if(exp>=lvl*lvllimiter++){
            exp=0;
            lvl++;
        }

        level.setText(lvl+"");
    }

    //update currencies
    public void updateGold(Label goldamount, Label diamonamount, int gain){
        int gold = Integer.parseInt(goldamount.getText());
        int diamond = Integer.parseInt(diamonamount.getText());
        gold=gold+gain;

        if(gold>99){
            diamond=diamond+(gold/100);
            gold=gold%100;
        }

        goldamount.setText(gold+"");
        diamonamount.setText(diamond+"");
    }

    public void updateDiamond(Label diamonamount, int gain){
        int diamond = Integer.parseInt(diamonamount.getText());
        diamond=diamond+gain;

        diamonamount.setText(diamond+"");
    }

    public void updatBattlecoin(Label battlecoinamount, int gain){
        int bc = Integer.parseInt(battlecoinamount.getText());
        bc=bc+gain;

        battlecoinamount.setText(bc+"");
    }

    //update energybar
    public void updateEnergyStatus(ProgressBar progressBar, double energychange){ //energy is 0 to 100, percentage is 0 to 1
        double ratio=energychange/100;
        double newratio=progressBar.getProgress()+ratio;
        if(newratio>1) newratio=1;
        else if(newratio<0) newratio=0;

        progressBar.setProgress(newratio);
    }
}
