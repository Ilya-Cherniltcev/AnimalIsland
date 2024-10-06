package javaRush.module2.service;

import javaRush.module2.model.Cell;
import javaRush.module2.model.Creature;
import javaRush.module2.model.Point;
import javaRush.module2.model.plant.Plant;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class ReproduceProcess implements Runnable {

    private final int x_size;
    private final int y_size;
    private ConcurrentMap<Point, Cell> mapIsland;
    private SelectCreature selectCreature;

    private Health health = new Health();

    public ReproduceProcess(ConcurrentMap<Point, Cell> mapIsland, int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
        this.mapIsland = mapIsland;
        selectCreature = new SelectCreature(mapIsland);
    }

    /**
     * All animals are reproducing
     *
     */
    @Override
    public void run() {
        log.info("ReprodeceProcess starting...");
        // make clone of mapIsland
        ConcurrentMap<Point, Cell> copyMapIsland = new ConcurrentHashMap<>(mapIsland);
        for (int x = 0; x < x_size; x++) {
            for (int y = 0; y < y_size; y++) {
                Point point = new Point(x, y);
                Cell thisCell = copyMapIsland.get(point);
                List<Creature> creatures = new CopyOnWriteArrayList<>(thisCell.getCreatures());
                // clone list of creatures
                List<Creature> men = new CopyOnWriteArrayList<>(new ArrayList<>(creatures));
                List<Creature> women = new CopyOnWriteArrayList<>(new ArrayList<>(creatures));

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
                                    log.warn("Something is wrong in ReproduceProcess " + e.getMessage());
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Counting of creatures of that kind in the cell
     *
     * @param creature  any creature which kind of creature we have to find in the cell and increase its number
     * @param cell  cell where we count of creatures
     * @return number of creatures in the cell
     */
    private int getNumberOfCreatures(Creature creature, Cell cell) {
        int creaturesInCell = 0;
            List<Creature> creaturesTemp = new CopyOnWriteArrayList<>(mapIsland.get(cell.getPoint()).getCreatures());
            if (creaturesTemp != null && !creaturesTemp.isEmpty()) {
                for (Creature some : creaturesTemp) {
                    if (some != null && some.equals(creature)) {
                        creaturesInCell++;
                    }
                }
            }
        return creaturesInCell;
    }
}
