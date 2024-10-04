package javaRush.module2.model;

import javaRush.module2.interfaces.Reproducible;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Creature implements Reproducible {
    private long id;
    @Setter
    private double weight;
    @Setter
    private int maxCreatureOnCell;
    @Setter
    private int x;
    @Setter
    private int y;
    @Setter
    private int health;

    private static long idCounter = 0;
    public Creature(double weight, int maxCreatureOnCell, int x, int y, int health) {
        this.weight = weight;
        this.maxCreatureOnCell = maxCreatureOnCell;
        this.x = x;
        this.y = y;
        this.health = health;
        this.id = ++idCounter;
    }

    @Override
    public boolean reproduce(Creature creature1, Creature creature2) {
        // Two creatures can reproduce if its health >= 50% and they are the same kind
        return creature1.health >= 50 && creature2.health >= 50
                && creature1.getClass() == creature2.getClass() && creature1.getId() != creature2.getId();
    }

    @Override
    public String toString() {
        return "Creature {id=" + getId() +
                ", weight=" + weight +
                ", maxCreatureOnCell=" + maxCreatureOnCell +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
