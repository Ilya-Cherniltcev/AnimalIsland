package javaRush.module2.model.animal.herbivore;

import static javaRush.module2.service.Settings.*;

// Корова -----------------
public class Cow extends Herbivore {

    public Cow(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }

    public Cow(){
        super(COW_WEIGHT, COW_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, COW_SPEED, COW_SATURATION);
    }


    @Override
    public String toString() {
        return  "Cow {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}

