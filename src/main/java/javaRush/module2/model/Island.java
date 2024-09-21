package javaRush.module2.model;

import javaRush.module2.model.animal.herbivore.Herbivore;
import javaRush.module2.model.animal.predator.Predator;
import javaRush.module2.model.plant.Plant;
import javaRush.module2.service.Report;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static javaRush.module2.service.Settings.LINE;

@Getter
public class Island {

    private final int x_size;
    private final int y_size;
    private final Map<Point, Cell> mapIsland;
    private final Report report;
    private final List<Predator> predatorsTotal;
    private final List<Herbivore> herbivoresTotal;
    private final List<Plant> plantsTotal;

    public Island(int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
        this.plantsTotal = new ArrayList<>();
        this.predatorsTotal = new ArrayList<>();
        this.herbivoresTotal = new ArrayList<>();
        report = new Report();
        mapIsland = new HashMap<>();
        initIsland();
    }

    private void initIsland() {
        for (int x = 0; x < x_size; x++) {
            for (int y = 0; y < y_size; y++) {
                // Make instance of Cell.class
                Cell newCell = new Cell(x, y);
                // get predators and herbivores from cell
                List<Predator> predatorsInCell = newCell.getPredators();
                List<Herbivore> herbivoresInCell = newCell.getHerbivores();
                List<Plant> plantsInCell = newCell.getPlants();
                // add cell lists to total lists of animals in the island
                predatorsTotal.addAll(predatorsInCell);
                herbivoresTotal.addAll(herbivoresInCell);
                plantsTotal.addAll(plantsInCell);
                // fill island map
                mapIsland.put(new Point(x, y), newCell);
            }
        }
//        report.printAnimalsCoordinates(predatorsTotal);
//        report.printAnimalsCoordinates(herbivoresTotal);
//        predatorsMoving();
//        herbivoresMoving();
//        System.out.println();
//        System.out.println(LINE);
//        System.out.println(LINE);
//        System.out.println(LINE);
//        report.printAnimalsCoordinates(predatorsTotal);
//        report.printAnimalsCoordinates(herbivoresTotal);

    }

    private void predatorsMoving() {
        // let predators move
        for (Predator each : predatorsTotal) {
            // get new coordinates after moving
            Point newCoordinates = each.move(each);
            // check correct limits of island
            if (newCoordinates.getX() > (x_size-1) || newCoordinates.getY() > (y_size-1) || newCoordinates.getX() < 0 || newCoordinates.getY() < 0) {
                continue;
            }
            // check possible max number of this type of animal in the cell and compare with animal's number in new cell
            int maxNumberAnimalsInTheCell = each.getMaxCreatureOnCell();
            int numberAnimalsInTheNewCell = 0;
            try {
                List<Predator> predatorListInTheCell = mapIsland.get(newCoordinates).getPredators();
                if (predatorListInTheCell != null || !predatorListInTheCell.isEmpty()) {
                    for (Predator some : predatorListInTheCell) {
                        if (some != null && some.equals(each)) {
                            numberAnimalsInTheNewCell++;
                        }
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            // if numberAnimalsInTheNewCell < maxNumberAnimalsInTheCell predator can move to new coordinates
            if (numberAnimalsInTheNewCell < maxNumberAnimalsInTheCell) {
                // change x, y of predator to new
                each.setX(newCoordinates.getX());
                each.setY(newCoordinates.getY());
            }
        }
    }
  private void herbivoresMoving() {
        // let herbivores move
        for(Herbivore each : herbivoresTotal){
            // get new coordinates after moving
            Point newCoordinates = each.move(each);
            // check correct limits of island
            if (newCoordinates.getX() > (x_size-1) || newCoordinates.getY() > (y_size-1) || newCoordinates.getX() < 0 || newCoordinates.getY() < 0) {
                continue;
            }
            // check possible max number of this type of animal in the cell and compare with animal's number in new cell
            int maxNumberAnimalsInTheCell = each.getMaxCreatureOnCell();
            int numberAnimalsInTheNewCell = 0;
            List<Herbivore> herbivoreListInTheCell = mapIsland.get(newCoordinates).getHerbivores();
            for(Herbivore some : herbivoreListInTheCell){
                if (some.equals(each)){
                    numberAnimalsInTheNewCell++;
                }
            }
            // if numberAnimalsInTheNewCell < maxNumberAnimalsInTheCell predator can move to new coordinates
            if (numberAnimalsInTheNewCell < maxNumberAnimalsInTheCell){
                // change x, y of herbivore to new
                each.setX(newCoordinates.getX());
                each.setY(newCoordinates.getY());
            }
        }

    }

    public void lifeIsStarting() {
        int xTemp = 3;
        int yTemp = 3;

//        for(Map.Entry<Point, Cell> entry : mapIsland.entrySet()){
//            System.out.println(entry.getKey() + ": ");
//            System.out.println("Predators: " + entry.getValue().getPredators().size() + " | Herbivores: "
//                   + entry.getValue().getHerbivores().size() );
//        }
        for (int i = 1; i < 30; i++) {
            Point tempPoint = new Point(xTemp, yTemp);
            report.printCellInfo(mapIsland.get(tempPoint));
            System.out.println("**** Everybody is eating... *** Iteration - " + i);
            mapIsland.get(tempPoint).eatEverybody();
            System.out.println("================================================================");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
