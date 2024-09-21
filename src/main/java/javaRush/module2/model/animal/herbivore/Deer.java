package javaRush.module2.model.animal.herbivore;

import static javaRush.module2.service.Settings.*;

// Олень -----------------
public class Deer extends Herbivore {

    public Deer(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }

    public Deer(){
        super(DEER_WEIGHT, DEER_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, DEER_SPEED, DEER_SATURATION);
    }


    @Override
    public String toString() {
        return  "Deer {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
