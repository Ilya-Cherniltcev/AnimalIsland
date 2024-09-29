package javaRush.module2.model.animal.herbivore;

import static javaRush.module2.service.CreatureSettings.*;

// Лошадь -----------------
public class Horse extends Herbivore {

    public Horse(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Horse(){
        super(HORSE_WEIGHT, HORSE_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, HORSE_SPEED, HORSE_SATURATION);
    }

    @Override
    public String toString() {
        return  "Horse {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
