package javaRush.module2.model.animal.predator;

import static javaRush.module2.service.Settings.*;

// Орел -----------------
public class Eagle extends Predator {

    public Eagle(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Eagle(){
        super(EAGLE_WEIGHT, EAGLE_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, EAGLE_SPEED, EAGLE_SATURATION);
    }

    @Override
    public String toString() {
        return  "Eagle {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
