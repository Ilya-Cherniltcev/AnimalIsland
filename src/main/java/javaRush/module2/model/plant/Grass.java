package javaRush.module2.model.plant;


import static javaRush.module2.service.Settings.*;

public class Grass extends Plant {
    public Grass(double weight, int maxCreatureOnCell, int x, int y) {
        super(weight, maxCreatureOnCell, x, y);
    }

    public Grass() {
        super(GRASS_WEIGHT, GRASS_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT);
    }

    @Override
    public String toString() {
        return  "Grass {id= " + super.getId() + "}";
    }
}
