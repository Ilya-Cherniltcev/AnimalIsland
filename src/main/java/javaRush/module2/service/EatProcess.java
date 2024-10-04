package javaRush.module2.service;

import javaRush.module2.Main;
import javaRush.module2.model.Cell;
import javaRush.module2.model.Creature;
import javaRush.module2.model.Point;
import javaRush.module2.model.animal.Animal;
import javaRush.module2.model.plant.Plant;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static javaRush.module2.service.CreatureSettings.PROBABILITY_TO_EAT;

public class EatProcess implements Runnable{
    private volatile Health health = new Health();
    private ConcurrentMap<Point, Cell> mapIsland;

    public EatProcess(ConcurrentMap<Point, Cell> mapIsland) {
        this.mapIsland = mapIsland;
    }

    public void run() {
            for (Map.Entry<Point, Cell> entry : mapIsland.entrySet()) {
                entry.setValue(eatingInCell(entry.getValue()));
            }
    }

    /**
     * Process of eating (check every creature in the cell)
     *
     * @param someCell  cell where is creatures are eating each other
     * @return modificated cell
     */
    private Cell eatingInCell(Cell someCell) {
        List<Creature> creatures = new CopyOnWriteArrayList<>(someCell.getCreatures());
        // make copies of creatures: hunters and preys
        List<Creature> preys = new CopyOnWriteArrayList<>(creatures);
        List<Creature> hunters = new CopyOnWriteArrayList<>(creatures);
        for (Creature hunter : hunters) {
            /* Here "hunter" can be real predator or herbivore as "predator" for plant
                (and some cases for example duck = predator for caterpillar etc.)
                but not a plant:))
               And If it doesn't exist in Creature list than we do next iteration
            */
            int idCreatureInList = creatures.indexOf(hunter);
            if (hunter instanceof Plant | idCreatureInList < 0) {
                continue;
            }
            ConcurrentMap<Class<? extends Creature>, Integer> possiblePreyClasses = new ConcurrentHashMap<>(PROBABILITY_TO_EAT.get(hunter.getClass()));
            // get class of herbivore with probability to eating
            int probablyToEat = (ThreadLocalRandom.current().nextInt(10) + 1) * 10;
            Class<? extends Creature> preyClass = null;
            for (Map.Entry<Class<? extends Creature>, Integer> clazz : possiblePreyClasses.entrySet()) {
                if (probablyToEat >= clazz.getValue()) {
                    preyClass = clazz.getKey();
                    break;
                }
            }
            // Try to find real animal (prey) of that class
            // Set flag: Process of eating has end or no
            boolean hasEaten = false;
            for (Creature prey : preys) {
                if (preyClass != null && prey.getClass() == preyClass) {
                    // now we can eat!
                    Animal animal = (Animal) hunter;
                    int newHunterHealth = animal.eat(animal, prey);
                    // set new health
                    creatures.get(idCreatureInList).setHealth(newHunterHealth);
                    hasEaten = true;
                    // remove prey from cell
                    someCell.deleteCreature(prey);
                    break;
                }
            }
            // hunter is hungry and so his health is decreasing
            if (!hasEaten) {
//                Main.log.info(hunter + " is hungry and must die");
                int newHunterHealth = health.decrease(hunter);
                if (newHunterHealth > 0) {
                    creatures.get(idCreatureInList).setHealth(newHunterHealth);
                }
                // if health = 0 then animal must die. Remove hunter from cell
                else {
                    someCell.deleteCreature(hunter);
//                    Main.log.info("!!! " + hunter + " has deleted !!!");
                }
            }
        }
        return someCell;
    }
}
