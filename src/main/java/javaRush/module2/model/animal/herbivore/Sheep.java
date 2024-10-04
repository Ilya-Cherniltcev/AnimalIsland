package javaRush.module2.model.animal.herbivore;

import static javaRush.module2.service.CreatureSettings.*;

// Овца -----------------
public class Sheep extends Herbivore {

    public Sheep(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }

    public Sheep() {
        super(SHEEP_WEIGHT, SHEEP_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, SHEEP_SPEED, SHEEP_SATURATION);
    }


    @Override
    public String toString() {
        return "Sheep {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
