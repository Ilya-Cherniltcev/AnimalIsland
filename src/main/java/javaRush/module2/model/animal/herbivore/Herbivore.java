package javaRush.module2.model.animal.herbivore;

import javaRush.module2.model.animal.Animal;
import lombok.*;

// Травоядное -----------------------------
public abstract class Herbivore extends Animal {

    public Herbivore(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health, speed, kgToFullSaturation);
    }
    @Override
    public String toString() {
        return
                "Herbivore {id= " + super.getId() + "weight=" + super.getWeight() + ", maxCreatureOnCell=" + super.getMaxCreatureOnCell() +
                        ", health=" + super.getHealth() +
                        ", speed=" + super.getSpeed() +
                        ", kgToFullSaturation=" + super.getKgToFullSaturation() +
                        '}';
    }

}
