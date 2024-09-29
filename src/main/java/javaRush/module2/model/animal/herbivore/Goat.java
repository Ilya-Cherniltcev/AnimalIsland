package javaRush.module2.model.animal.herbivore;

import static javaRush.module2.service.CreatureSettings.*;

// Коза -----------------
public class Goat extends Herbivore {

    public Goat(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Goat(){
        super(GOAT_WEIGHT, GOAT_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, GOAT_SPEED, GOAT_SATURATION);
    }

    @Override
    public String toString() {
        return  "Goat {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
