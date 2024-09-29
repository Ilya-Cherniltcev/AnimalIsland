package javaRush.module2.model.plant;


import static javaRush.module2.service.CreatureSettings.*;

public class Grass extends Plant {
    public Grass(double weight, int maxCreatureOnCell, int x, int y, int health) {
        super(weight, maxCreatureOnCell, x, y, health);
    }

    public Grass() {
        super(GRASS_WEIGHT, GRASS_MAX_ON_CELL, X_DEFAULT, Y_DEFAULT, HEALTH);
    }

    @Override
    public String toString() {
        return  "Grass {id= " + super.getId() + "}";
    }
}
