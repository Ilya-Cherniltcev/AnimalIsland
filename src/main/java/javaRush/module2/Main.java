package javaRush.module2;

import javaRush.module2.model.Cell;
import javaRush.module2.model.Island;
import javaRush.module2.model.Point;
import javaRush.module2.service.SelectCreature;


public class Main {
    public static void main(String[] args) {
        Island island = new Island(4, 4);
        island.lifeIsStarting();
    }
}