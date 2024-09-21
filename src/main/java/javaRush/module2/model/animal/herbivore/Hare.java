package javaRush.module2.model.animal.herbivore;

import static javaRush.module2.service.Settings.*;

// Заяц -----------------
public class Hare extends Herbivore {

    public Hare(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Hare(){
        super(HARE_WEIGHT, HARE_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, HARE_SPEED, HARE_SATURATION);
    }


    @Override
    public String toString() {
        return  "Hare {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
