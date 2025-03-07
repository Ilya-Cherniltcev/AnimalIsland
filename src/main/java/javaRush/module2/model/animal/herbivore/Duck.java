package javaRush.module2.model.animal.herbivore;

import static javaRush.module2.service.CreatureSettings.*;

// Утка -----------------
public class Duck extends Herbivore {

    public Duck(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }

    public Duck(){
        super(DUCK_WEIGHT, DUCK_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, DUCK_SPEED, DUCK_SATURATION);
    }

    @Override
    public String toString() {
        return  "Duck {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}

