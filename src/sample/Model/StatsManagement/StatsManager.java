package sample.Model.StatsManagement;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public abstract class StatsManager {

    //general variables
    private static int exp;
    private static int lvllimiter=100;

    ///stats
    private static int level;
    private static String name;
    private static String classtype;
    private static int atk,hp;

    //constructor
    public StatsManager(String name,String occupation){
        StatsManager.name =name;
        StatsManager.classtype=occupation;
        StatsManager.atk=100;
        StatsManager.hp=1000;
    }

    //update experience
    public static void updateExperience(Label level, int gain){
        int lvl = Integer.parseInt(level.getText());
        exp=exp+gain;

        if(exp>=lvl*lvllimiter++){
            exp=0;
            lvl++;
            atk=atk+2;
            hp=hp+250;
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
    public static void updateGoldIncrease(Label goldamount, Label diamonamount, int gain){
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
    public static void updateGoldDecrease(Label goldamount, Label diamonamount, int loss){
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
/*
    public void updateDiamond(Label diamonamount, int gain){
        int diamond = Integer.parseInt(diamonamount.getText());
        diamond=diamond+gain;

        diamonamount.setText(diamond+"");
    }
*/
    public static void updatBattlecoin(Label battlecoinamount, int gain){
        int bc = Integer.parseInt(battlecoinamount.getText());
        bc=bc+gain;

        battlecoinamount.setText(bc+"");
    }

    //update energybar
    public static void updateEnergyStatus(ProgressBar progressBar, double energychange){ //energy is 0 to 100, percentage is 0 to 1
        double ratio=energychange/100;
        double newratio=progressBar.getProgress()+ratio;
        if(newratio>1) newratio=1;
        else if(newratio<0) newratio=0;

        progressBar.setProgress(newratio);
    }

    //setters
    public static void setName(String name) {
        StatsManager.name = name;
    }

    public static void setLevel(int level) {
        StatsManager.level = level;
    }

    public static void setAtk(int atk) {
        StatsManager.atk = atk;
    }

    public static void setClasstype(String classtype) {
        StatsManager.classtype = classtype;
    }

    public static void setHp(int hp) {
        StatsManager.hp = hp;
    }

    //getters
    public static String getName() {
        return name;
    }

    public static int getLevel() {
        return level;
    }

    public static int getAtk() {
        return atk;
    }

    public static int getHp() {
        return hp;
    }

    public static String getClasstype() {
        return classtype;
    }

    public static void setCharacterBaseStats() {
        if (!name.isEmpty())
            StatsManager.setName(name);

        if (!classtype.isEmpty())
            StatsManager.setClasstype(classtype);

        StatsManager.setLevel(1);
        StatsManager.setAtk(100);
        StatsManager.setHp(1000);

    }
}
