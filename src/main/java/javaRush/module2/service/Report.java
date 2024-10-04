package javaRush.module2.service;

import javaRush.module2.model.Cell;
import javaRush.module2.model.Point;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

import static javaRush.module2.service.CreatureSettings.ASTERICS_LINE;
import static javaRush.module2.service.CreatureSettings.LINE;

public class Report implements Runnable {
    private final ConcurrentMap<Point, Cell> mapIsland;
    private SelectCreature selectCreature;
    private final int x_size;
    private final int y_size;


    public Report(ConcurrentMap<Point, Cell> mapIsland, int x_size, int y_size) {
        this.mapIsland = mapIsland;
        this.x_size = x_size;
        this.y_size = y_size;

    }

    /**
     * Printing of report of all island
     *
     */
    @Override
    public void run() {
//        Main.log.info("Begin printing island's info...");
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
//        Main.log.info("... info has printed. The end");
    }

    /**
     * Printing of report of a specific cell
     *
     * @param cell  cell which must be printed
     */
    public void printCellInfo(Cell cell) {
        int predatorNumber = cell.getPredators().size();
        int herbivoresNumber = cell.getHerbivores().size();
        int plantsNumber = cell.getPlants().size();
        System.out.print(" | \uD83D\uDC79 " + predatorNumber + " \uD83D\uDC07 " + herbivoresNumber + " \uD83C\uDF3F " + plantsNumber);
    }
}



