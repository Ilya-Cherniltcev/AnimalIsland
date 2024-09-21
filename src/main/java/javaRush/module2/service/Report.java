package javaRush.module2.service;

import de.vandermeer.asciitable.AsciiTable;
import javaRush.module2.model.Cell;
import javaRush.module2.model.Creature;
import javaRush.module2.model.animal.Animal;
import lombok.Getter;

import java.util.List;

import static javaRush.module2.service.Settings.LINE;

public class Report {


    private AsciiTable table = new AsciiTable();

    public void printIslandInfo() {
//        System.out.printf("*** Island size: x=%d, y=%d ***", xSize, ySize);
        System.out.println();
        System.out.println(LINE);
        /* print island's cells (rows and columns)
        ___________________________________________
        | herbivore-6 | herbivore-10 | herbivore-20 |
        | predator-10 | predator-5   | predator-18  |
        | plant-100   | plant-78     | plant-18     |
        ---------------------------------------------
        */

        System.out.println();
        System.out.println(LINE);

//                System.out.println("-".repeat( 15 * xSize));
//                System.out.printf("| %-15s%-%d ", );
        table.addRule();
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



