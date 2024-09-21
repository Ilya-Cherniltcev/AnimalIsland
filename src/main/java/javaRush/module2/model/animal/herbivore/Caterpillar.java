package javaRush.module2.model.animal.herbivore;

import static javaRush.module2.service.Settings.*;

// Гусеница -----------------

public class Caterpillar extends Herbivore {

    public Caterpillar(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Caterpillar(){
        super(CATERPILLAR_WEIGHT, CATERPILLAR_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, CATERPILLAR_SPEED, CATERPILLAR_SATURATION);
    }


    @Override
    public String toString() {
        return  "Caterpillar {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
