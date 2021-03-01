package sample.StatsManagement;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class StatsManager {

    //general variables
    private int exp;
    private int lvllimiter=100;

    ///stats
    private int level;
    private String name;
    private String classtype;
    private int atk,hp;

    //update experience
    public void updateExperience(Label level, int gain){
        int lvl = Integer.parseInt(level.getText());
        exp=exp+gain;

        if(exp>=lvl*lvllimiter++){
            exp=0;
            lvl++;
        }

        level.setText(lvl+"");
        setLevel(lvl);

        //classtype update//
        if(lvl>=100)
            setClasstype("GrandChampion");
        else if(lvl>=80)
            setClasstype("Champion");
        else if(lvl>=60)
            setClasstype("Knight");
        else if(lvl>=40)
            setClasstype("Squire");
        else if(lvl>=20)
            setClasstype("AspiringWarrior");

        //warrior
        String[] classtyperegex=getClasstype().split(" ");
        if(lvl%4!=0 && lvl%5==0) setClasstype(classtyperegex[0]+" "+lvl/5);
    }

    //update currencies
    public void updateGoldIncrease(Label goldamount, Label diamonamount, int gain){
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
    public void updateGoldDecrease(Label goldamount, Label diamonamount, int loss){
        int gold = Integer.parseInt(goldamount.getText());
        int diamond = Integer.parseInt(diamonamount.getText());
        gold=gold+loss;

        if(gold+loss>=0){
            gold=gold+loss;

            goldamount.setText(gold+"");
            diamonamount.setText(diamond+"");
            return;
        }

        int diamondasgold=diamond*100;
        int currencyasgold=diamondasgold+gold;

        if(currencyasgold+loss>=0){
            int newvalueasgold=currencyasgold+loss;
            int newdiamond=newvalueasgold/100;
            int newgold=newvalueasgold%100;

            goldamount.setText(newgold+"");
            diamonamount.setText(newdiamond+"");
        }


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

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setClasstype(String classtype) {
        this.classtype = classtype;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getAtk() {
        return atk;
    }

    public int getHp() {
        return hp;
    }

    public String getClasstype() {
        return classtype;
    }
}
