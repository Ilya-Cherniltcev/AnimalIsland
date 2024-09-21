package javaRush.module2.model.animal.herbivore;

import static javaRush.module2.service.Settings.*;

// Кенгуру -----------------
public class Kangaroo extends Herbivore {

    public Kangaroo(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Kangaroo(){
        super(KANGAROO_WEIGHT, KANGAROO_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, KANGAROO_SPEED, KANGAROO_SATURATION);
    }

    @Override
    public String toString() {
        return  "Kangaroo {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
