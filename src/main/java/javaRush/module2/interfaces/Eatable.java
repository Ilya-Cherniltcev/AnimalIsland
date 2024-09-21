package javaRush.module2.interfaces;

import javaRush.module2.model.Creature;
import javaRush.module2.model.animal.Animal;

public interface Eatable {
    int eat(Animal animal, Creature animalOrPlant);
}
