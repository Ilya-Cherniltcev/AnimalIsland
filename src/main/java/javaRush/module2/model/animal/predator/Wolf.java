package javaRush.module2.model.animal.predator;

import static javaRush.module2.service.CreatureSettings.*;

// Волк -----------------
public class Wolf extends Predator {
    public Wolf(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Wolf(){
        super(WOLF_WEIGHT, WOLF_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, WOLF_SPEED, WOLF_SATURATION);
    }

    @Override
    public String toString() {
        return  "Wolf {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
