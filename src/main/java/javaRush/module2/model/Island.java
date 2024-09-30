package javaRush.module2.model;

import javaRush.module2.model.animal.herbivore.Herbivore;
import javaRush.module2.model.animal.predator.Predator;
import javaRush.module2.model.plant.Plant;
import javaRush.module2.service.*;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
public class Island {

    private final int x_size;
    private final int y_size;
    private Map<Point, Cell> mapIsland;
    private Report report;
    private SelectCreature selectCreature;
    private  List<Predator> predatorsTotal;
    private  List<Herbivore> herbivoresTotal;
    private  List<Plant> plantsTotal;
    private MovingProcess movingProcess;
    private ReproduceProcess reproduceProcess;
    private EatProcess eatProcess;
//    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

    public Island(int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
        plantsTotal = new ArrayList<>();
        predatorsTotal = new ArrayList<>();
        herbivoresTotal = new ArrayList<>();

        mapIsland = new HashMap<>();

        initIsland();
    }

    private void initIsland() {
        for (int x = 0; x < x_size; x++) {
            for (int y = 0; y < y_size; y++) {
                // Make instance of Cell.class
                Point point = new Point(x, y);
                Cell newCell = new Cell(point);
                // fill island map
                mapIsland.put(point, newCell);
            }
        }
        selectCreature = new SelectCreature(mapIsland);
        predatorsTotal = selectCreature.getPredators();
        herbivoresTotal = selectCreature.getHerbivores();
        plantsTotal = selectCreature.getPlants();
        report = new Report(mapIsland, x_size, y_size);
        movingProcess = new MovingProcess(mapIsland, x_size, y_size);
        reproduceProcess = new ReproduceProcess(mapIsland, x_size, y_size);
        eatProcess = new EatProcess(mapIsland);
    }




    public void lifeIsStarting() {
        report.printIslandInfo();
        for (int i = 0; i < 5; i++) {
            movingProcess.moveEverybody();
            eatProcess.eatEverybody();
            reproduceProcess.letsReproduce();
            report.printIslandInfo();
        }

//        scheduler.scheduleAtFixedRate(eatProcess :: eatEverybody, 0, 500, TimeUnit.MILLISECONDS);
//        scheduler.scheduleAtFixedRate(movingProcess :: moveEverybody, 0, 500, TimeUnit.MILLISECONDS);
//        scheduler.scheduleAtFixedRate(reproduceProcess :: letsReproduce, 0, 500, TimeUnit.MILLISECONDS);
//        scheduler.scheduleAtFixedRate(report :: printIslandInfo, 0, 2, TimeUnit.SECONDS);

    }

}
