package javaRush.module2.model.animal.predator;

import static javaRush.module2.service.CreatureSettings.*;

// Медведь -----------------
public class Bear extends Predator {

    public Bear(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Bear(){
        super(BEAR_WEIGHT, BEAR_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, BEAR_SPEED, BEAR_SATURATION);
    }

    @Override
    public String toString() {
        return  "Bear {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
