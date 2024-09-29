package javaRush.module2.service;

import javaRush.module2.model.Cell;
import javaRush.module2.model.Point;
import javaRush.module2.model.animal.Animal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static javaRush.module2.service.CreatureSettings.ASTERICS_LINE;
import static javaRush.module2.service.CreatureSettings.LINE;

public class Report {
    private final Map<Point, Cell> mapIsland;
    private SelectCreature selectCreature;
    private final int x_size;
    private final int y_size;

    public Report(Map<Point, Cell> mapIsland, int x_size, int y_size) {
        this.mapIsland = mapIsland;
        this.x_size = x_size;
        this.y_size = y_size;

    }

    public void printIslandInfo() {
        selectCreature = new SelectCreature(mapIsland);
        /* print island's cells (rows and columns)
        ___________________________________________
        | herbivore-6 | herbivore-10 | herbivore-20 |
        | predator-10 | predator-5   | predator-18  |
        | plant-100   | plant-78     | plant-18     |
        ---------------------------------------------
        */
        System.out.println();
        System.out.printf("**********  ISLAND INFO: \uD83D\uDC79-predators(total %d), " +
                        "\uD83D\uDC07-herbivores(total %d), \uD83C\uDF3F-plants(total %d) **********",
                selectCreature.getPredators().size(), selectCreature.getHerbivores().size(), selectCreature.getPlants().size());
        System.out.println();
        for (int y = 0; y < y_size; y++) {
            System.out.println(LINE);
            for (int x = 0; x < x_size; x++) {
                printCellInfo(mapIsland.get(new Point(x, y)));
            }
            System.out.println();
        }
        System.out.println(ASTERICS_LINE);
    }


    public void printCellInfo(Cell cell) {

        int predatorNumber = cell.getPredators().size();
        int herbivoresNumber = cell.getHerbivores().size();
        int plantsNumber = cell.getPlants().size();
        System.out.print(" | \uD83D\uDC79 " + predatorNumber + " \uD83D\uDC07 " + herbivoresNumber + " \uD83C\uDF3F " + plantsNumber);
    }

    public void printAnimalsCoordinates(List<? extends Animal> animals) {
        int row = 0;
        int column = 0;
        for (Animal animal : animals) {
            System.out.printf(" ID-%d (x=%d, y=%d)", animal.getId(), animal.getX(), animal.getY());
            column++;
            if (column > 15) {
                column = 0;
                row++;
                System.out.println();
            }
            if (row > 15) {
                System.out.println(LINE);
                row = 0;
            }
        }
    }
}



