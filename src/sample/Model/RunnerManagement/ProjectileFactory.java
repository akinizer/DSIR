package sample.Model.RunnerManagement;

import java.util.ArrayList;
import java.util.List;

public class ProjectileFactory implements  IProjectileFactory{

    private List<Projectile> projectiles;

    public ProjectileFactory(){
        projectiles=new ArrayList<>();
    }

    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
    }

    public void removeProjectile(Projectile projectile){
        projectiles.remove(projectile);
    }

    public int getAmount(){
        return projectiles.size();
    }

    public List<Projectile> getProjectiles(){
        return projectiles;
    }

    public boolean doesProjectileExist(double x,double y){
        for (Projectile p:projectiles) {
            if(p.getTranslateX()==x && p.getTranslateY()==y) return true;
        }
        return false;
    }


}
