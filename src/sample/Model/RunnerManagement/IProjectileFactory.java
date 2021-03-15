package sample.Model.RunnerManagement;

import java.util.List;

public interface IProjectileFactory {

    public void addProjectile(Projectile projectile);

    public void removeProjectile(Projectile projectile);

    public int getAmount();

    public List<Projectile> getProjectiles();

    public boolean doesProjectileExist(double x,double y);

}
