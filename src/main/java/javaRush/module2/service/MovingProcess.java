package javaRush.module2.service;

import javaRush.module2.model.Cell;
import javaRush.module2.model.Creature;
import javaRush.module2.model.Point;
import javaRush.module2.model.animal.Animal;
import javaRush.module2.model.animal.herbivore.Herbivore;
import javaRush.module2.model.animal.predator.Predator;
import javaRush.module2.model.plant.Plant;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MovingProcess implements Runnable {
    private final int x_size;
    private final int y_size;
    private ConcurrentMap<Point, Cell> mapIsland;
    private SelectCreature selectCreature;
    private List<Predator> predators;
    private List<Herbivore> herbivores;
    private List<Plant> plants;
    private List<Animal> animals;
    private List<Creature> creatures;
    private Health health = new Health();

    public MovingProcess(ConcurrentMap<Point, Cell> mapIsland, int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
        this.mapIsland = mapIsland;
        selectCreature = new SelectCreature(mapIsland);
        predators = new CopyOnWriteArrayList<>(selectCreature.getPredators());
        herbivores = new CopyOnWriteArrayList<>(selectCreature.getHerbivores());
        plants = new CopyOnWriteArrayList<>(selectCreature.getPlants());
        animals = new CopyOnWriteArrayList<>(selectCreature.getAnimals());
        creatures = new CopyOnWriteArrayList<>(selectCreature.getCreatures());
    }

    /**
     * All animals are moving
     *
     */
    @Override
    public void run() {
        // let animals move
        for (Creature each : creatures) {
            // plant can't move - skip iteration
            if (each instanceof Plant) {
                continue;
            }
            Animal animal = (Animal) each;
            Point oldCoordinates = new Point(animal.getX(), animal.getY());
            // get new coordinates after moving
            Point newCoordinates = animal.move(animal);
            // check correct limits of island
            if (newCoordinates.getX() > (x_size - 1) || newCoordinates.getY() > (y_size - 1)
                    || newCoordinates.getX() < 0 || newCoordinates.getY() < 0) {
                continue;
            }
            // check possible max number this kind of animal in the cell and compare with animal's number in new cell
            int maxAnimalsInTheCell = animal.getMaxCreatureOnCell();
            int animalsInNewCell = 0;
            try {
                List<Animal> animalsTemp = new CopyOnWriteArrayList<>(mapIsland.get(newCoordinates).getAnimals());
                if (animalsTemp != null && !animalsTemp.isEmpty()) {
                    for (Animal some : animalsTemp) {
                        if (some != null && some.equals(animal)) {
                            animalsInNewCell++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // if animalsInNewCell < maxAnimalsInTheCell animal can move to new coordinates
            if (animalsInNewCell < maxAnimalsInTheCell) {
                // change x, y of animal to new
                animal.setX(newCoordinates.getX());
                animal.setY(newCoordinates.getY());
                // add animal to new cell
                mapIsland.get(newCoordinates).addCreature(animal);
                // delete animal from old cell
                mapIsland.get(oldCoordinates).deleteCreature(animal);
            }
        }
    }
}
