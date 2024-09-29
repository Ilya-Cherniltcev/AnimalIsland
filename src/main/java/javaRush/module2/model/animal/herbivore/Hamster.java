package javaRush.module2.model.animal.herbivore;

import static javaRush.module2.service.CreatureSettings.*;

// Хомяк -----------------
public class Hamster extends Herbivore {

    public Hamster(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Hamster(){
        super(HAMSTER_WEIGHT, HAMSTER_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, HAMSTER_SPEED, HAMSTER_SATURATION);
    }

    @Override
    public String toString() {
        return  "Hamster {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}

