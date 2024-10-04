package javaRush.module2.service;

import javaRush.module2.model.Cell;
import javaRush.module2.model.Creature;
import javaRush.module2.model.Point;
import javaRush.module2.model.animal.Animal;
import javaRush.module2.model.animal.herbivore.Herbivore;
import javaRush.module2.model.animal.predator.Predator;
import javaRush.module2.model.plant.Plant;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class SelectCreature {
    @Getter(AccessLevel.PRIVATE)
    private ConcurrentMap<Point, Cell> mapIsland;
    private List<Animal> animals;
    private List<Predator> predators;
    private List<Herbivore> herbivores;
    private List<Plant> plants;

    private List<Creature> creatures;

    public SelectCreature(ConcurrentMap<Point, Cell> mapIsland) {
        this.mapIsland = mapIsland;
        this.animals = new CopyOnWriteArrayList<>();
        this.predators = new CopyOnWriteArrayList<>();
        this.herbivores = new CopyOnWriteArrayList<>();
        this.plants = new CopyOnWriteArrayList<>();
        this.creatures = new CopyOnWriteArrayList<>();
        getAllCreatures();
    }

    /**
     * Getting of all creatures on the island
     * and add them to lists
     */
    private synchronized void getAllCreatures() {
        for (Map.Entry<Point, Cell> entry : mapIsland.entrySet()) {
            Cell tempCell = entry.getValue();
            animals.addAll(tempCell.getAnimals());
            predators.addAll(tempCell.getPredators());
            herbivores.addAll(tempCell.getHerbivores());
            plants.addAll(tempCell.getPlants());
            creatures.addAll(tempCell.getCreatures());
        }
    }
}
