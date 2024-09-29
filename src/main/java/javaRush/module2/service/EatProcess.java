package javaRush.module2.service;

import javaRush.module2.model.Cell;
import javaRush.module2.model.Creature;
import javaRush.module2.model.Point;
import javaRush.module2.model.animal.Animal;
import javaRush.module2.model.plant.Plant;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import static javaRush.module2.service.CreatureSettings.PROBABILITY_TO_EAT;

public class EatProcess {
    private Health health = new Health();
    private Map<Point, Cell> mapIsland;

    public EatProcess(Map<Point, Cell> mapIsland) {
        this.mapIsland = mapIsland;
    }

    public void eatEverybody() {
        for (Map.Entry<Point, Cell> entry : mapIsland.entrySet()) {
            entry.setValue(eatingInCell(entry.getValue()));
        }
    }

    private Cell eatingInCell(Cell someCell) {
//        List<Creature> creatures = Collections.synchronizedList(cell.getCreatures());
        List<Creature> creatures = someCell.getCreatures();
        // make copies of creatures: hunters and preys
        List<Creature> preys = new ArrayList<>(creatures);
        List<Creature> hunters = new ArrayList<>(creatures);
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
            Map<Class<? extends Creature>, Integer> possiblePreyClasses = PROBABILITY_TO_EAT.get(hunter.getClass());
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
                int newHunterHealth = health.decrease(hunter);
                if (newHunterHealth > 0) {
                    creatures.get(idCreatureInList).setHealth(newHunterHealth);
                }
                // if health = 0 then animal must die. Remove hunter from cell
                else {
                    someCell.deleteCreature(hunter);
                }
            }
        }
        return someCell;
    }
}
