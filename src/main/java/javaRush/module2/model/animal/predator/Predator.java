package javaRush.module2.model.animal.predator;

import javaRush.module2.model.animal.Animal;

// Хищник -----------------------------

public abstract class Predator extends Animal {

    public Predator(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }



    @Override
    public String toString() {
        return
        "Predator {id= " + super.getId() + "weight=" + super.getWeight() + ", maxCreatureOnCell=" + super.getMaxCreatureOnCell() +
                ", health=" + super.getHealth() +
                ", speed=" + super.getSpeed() +
                ", kgToFullSaturation=" + super.getKgToFullSaturation() +
                '}';
    }
}
