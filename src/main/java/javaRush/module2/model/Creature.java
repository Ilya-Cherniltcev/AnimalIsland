package javaRush.module2.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Creature {
    private long id;
    @Setter
    private double weight;
    @Setter
    private int maxCreatureOnCell;
    @Setter
    private int x;
    @Setter
    private int y;

    private static long idCounter = 0;
    public Creature(double weight, int maxCreatureOnCell, int x, int y) {
        this.weight = weight;
        this.maxCreatureOnCell = maxCreatureOnCell;
        this.x = x;
        this.y = y;
        this.id = ++idCounter;
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
