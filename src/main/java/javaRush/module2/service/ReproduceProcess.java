package javaRush.module2.service;

import javaRush.module2.model.Cell;
import javaRush.module2.model.Creature;
import javaRush.module2.model.Point;
import javaRush.module2.model.plant.Plant;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReproduceProcess {
    private final int x_size;
    private final int y_size;
    private Map<Point, Cell> mapIsland;
    private SelectCreature selectCreature;

    private Health health = new Health();

    public ReproduceProcess(Map<Point, Cell> mapIsland, int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
        this.mapIsland = mapIsland;
        selectCreature = new SelectCreature(mapIsland);
    }

    public void letsReproduce() {
        // make clone of mapIsland
        Map<Point, Cell> copyMapIsland = new HashMap<>(mapIsland);
        for (int x = 0; x < x_size; x++) {
            for (int y = 0; y < y_size; y++) {
                Point point = new Point(x, y);
                Cell thisCell = copyMapIsland.get(point);
                List<Creature> creatures = thisCell.getCreatures();
                // clone list of creatures
                List<Creature> men = new ArrayList<>(creatures);
                List<Creature> women = new ArrayList<>(creatures);

                for (Creature man : men) {
                    // Now we think that plant can't reproduce - skip iteration
                    // Then we can change it logics
                    if (man instanceof Plant) {
                        continue;
                    }
                    for (Creature woman : women) {
                        // Now we think that plant can't reproduce - skip iteration
                        // Then we can change it logics
                        if (woman instanceof Plant) {
                            continue;
                        }
                        // check possible max number this kind of creature in the cell
                        int maxCreaturesInTheCell = man.getMaxCreatureOnCell();
                        // check fact number of creatures
                        int creaturesInCell = getNumberOfCreatures(man, thisCell);
                        // if creatures in cell < then maxCreaturesInTheCell man and woman can reproduce :)
                        if ((maxCreaturesInTheCell - creaturesInCell) > 0) {
                            // check probability of reproducible
                            if (man.reproduce(man, woman)) {
                                // add animal to cell
                                Class<? extends Creature> clazz = man.getClass();
                                try {
                                    Creature creature = clazz.getDeclaredConstructor().newInstance();
                                    thisCell.addCreature(creature);
                                    mapIsland.put(point, thisCell);
                                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                                         InvocationTargetException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    private int getNumberOfCreatures(Creature creature, Cell cell) {
        int creaturesInCell = 0;
        try {
            List<Creature> creaturesTemp = mapIsland.get(cell.getPoint()).getCreatures();
            if (creaturesTemp != null && !creaturesTemp.isEmpty()) {
                for (Creature some : creaturesTemp) {
                    if (some != null && some.equals(creature)) {
                        creaturesInCell++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creaturesInCell;
    }
}
