package javaRush.module2.model;

import javaRush.module2.model.animal.Animal;
import javaRush.module2.model.animal.herbivore.Herbivore;
import javaRush.module2.model.animal.predator.Predator;
import javaRush.module2.model.plant.Plant;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static javaRush.module2.service.CreatureSettings.*;


@Getter
public class Cell {
    private List<Animal> animals;

    private List<Predator> predators;
    private List<Herbivore> herbivores;
    private List<Plant> plants;
    private final Point point;
    @Setter
    private List<Creature> creatures;

    public Cell(Point point) {
        this.animals = new CopyOnWriteArrayList<>();
        this.predators = new CopyOnWriteArrayList<>();
        this.herbivores = new CopyOnWriteArrayList<>();
        this.plants = new CopyOnWriteArrayList<>();
        this.point = point;
        this.creatures = new CopyOnWriteArrayList<>();
        initCreatures();
    }

    /**
     * Birth of creatures in the cell
     * (we apply mechanism of reflection)
     */
    private void initCreatures() {
        for (Class<? extends Creature> creatureClass : CREATURE_SET) {
            try {
                // We make instance of the class for checking of max probably number of thing creating in the cell
                Creature testCreature = creatureClass.getDeclaredConstructor().newInstance();
                // Take max numbers of animals in the cell, use Random to this number...
                int creatureNumbersForCell = ThreadLocalRandom.current().nextInt(testCreature.getMaxCreatureOnCell()) + 1;

                for (int i = 0; i < creatureNumbersForCell; i++) {
                    Creature creature = creatureClass.getDeclaredConstructor().newInstance();
                    creatures.add(creature);
                    if (creature instanceof Predator) {
                        predators.add((Predator) creature);
                    } else if (creature instanceof Herbivore) {
                        herbivores.add((Herbivore) creature);
                    } else if (creature instanceof Plant) {
                        plants.add((Plant) creature);
                    }
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException("Creature is not created!");
            }
        }
        animals.addAll(predators);
        animals.addAll(herbivores);
    }

    /**
     * Add creature to lists
     *
     * @param creature any creature which we have to add
     */
    public void addCreature(Creature creature) {
        creatures.add(creature);
        if (creature instanceof Predator) {
            predators.add((Predator) creature);
            animals.add((Animal) creature);
        } else if (creature instanceof Herbivore) {
            herbivores.add((Herbivore) creature);
            animals.add((Animal) creature);
        } else if (creature instanceof Plant) {
            plants.add((Plant) creature);
        }
    }

    /**
     * Erasing of the creature from lists
     *
     * @param creature any creature which we have to erase
     */
    public void deleteCreature(Creature creature) {
        if (creature != null) {
            creatures.remove(creature);
            if (creature instanceof Predator) {
                predators.remove(creature);
                animals.remove(creature);
            } else if (creature instanceof Herbivore) {
                herbivores.remove(creature);
                animals.remove(creature);
            } else if (creature instanceof Plant) {
                plants.remove(creature);
            }
        }
    }
}

