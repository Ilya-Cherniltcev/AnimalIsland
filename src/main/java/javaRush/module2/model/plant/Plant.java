package javaRush.module2.model.plant;

import javaRush.module2.model.Creature;

public abstract class Plant extends Creature {


    public Plant(double weight, int maxCreatureOnCell,  int x, int y, int health) {
        super(weight, maxCreatureOnCell, x, y, health);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String toString() {
        return "Plant{" + "weight=" + super.getWeight() + ", maxCreatureOnCell=" + super.getMaxCreatureOnCell() + "}";
    }
}

