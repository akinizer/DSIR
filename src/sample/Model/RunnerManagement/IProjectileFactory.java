package sample.Model.RunnerManagement;

import java.util.List;

public interface IProjectileFactory {

    void addProjectile(Projectile projectile);
    void removeProjectile(Projectile projectile);
    int getAmount();
    List<Projectile> getProjectiles();
    boolean doesProjectileExist(double x, double y);

}
