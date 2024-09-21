package javaRush.module2.model.animal.predator;

import static javaRush.module2.service.Settings.*;

// Змея -----------------
public class Snake extends Predator {

    public Snake(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    public Snake(){
        super(SNAKE_WEIGHT, SNAKE_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH, SNAKE_SPEED, SNAKE_SATURATION);
    }

    @Override
    public String toString() {
        return  "Snake {id= " + super.getId() + ", health=" + super.getHealth() + "}";
    }
}
