package javaRush.module2.model;

import javaRush.module2.model.animal.herbivore.Herbivore;
import javaRush.module2.model.animal.predator.Predator;
import javaRush.module2.model.plant.Plant;
import javaRush.module2.service.EatProcess;
import javaRush.module2.service.MovingProcess;
import javaRush.module2.service.Report;
import javaRush.module2.service.SelectCreature;
import lombok.Getter;

import java.util.*;

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
    private EatProcess eatProcess;


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
        eatProcess = new EatProcess(mapIsland);
    }



    private void eatProcessing() {
        eatProcess.eatEverybody();
    }

    private void moveProcessing(){
        movingProcess.moveEverybody();
    }

    public void lifeIsStarting() {

        report.printIslandInfo();

        for (int i = 0; i <10; i++) {
            moveProcessing();
            eatProcessing();
            report.printIslandInfo();
        }

//        report.printAnimalsCoordinates(predatorsTotal);
//        report.printAnimalsCoordinates(herbivoresTotal);

//        report.printAnimalsCoordinates(predatorsTotal);
//        report.printAnimalsCoordinates(herbivoresTotal);


//        int xTemp = 3;
//        int yTemp = 3;

//        for(Map.Entry<Point, Cell> entry : mapIsland.entrySet()){
//            System.out.println(entry.getKey() + ": ");
//            System.out.println("Predators: " + entry.getValue().getPredators().size() + " | Herbivores: "
//                   + entry.getValue().getHerbivores().size() );
//        }
//        for (int i = 1; i < 30; i++) {
//            Point tempPoint = new Point(xTemp, yTemp);
//            report.printCellInfo(mapIsland.get(tempPoint));
//            System.out.println("**** Everybody is eating... *** Iteration - " + i);
//            mapIsland.get(tempPoint).eatEverybody();
//            System.out.println("================================================================");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

}
