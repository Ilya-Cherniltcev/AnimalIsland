package javaRush.module2.model.animal.predator;

import static javaRush.module2.service.CreatureSettings.*;

// Лиса -----------------
public class Fox extends Predator {

    public Fox(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Fox(){
        super(FOX_WEIGHT, FOX_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, FOX_SPEED, FOX_SATURATION);
    }

    @Override
    public String toString() {
        return  "Fox {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
