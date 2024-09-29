package javaRush.module2.model;

import javaRush.module2.model.animal.Animal;
import javaRush.module2.model.animal.herbivore.Herbivore;
import javaRush.module2.model.animal.predator.Predator;
import javaRush.module2.model.plant.Plant;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        this.animals = new ArrayList<>();
        this.predators = new ArrayList<>();
        this.herbivores = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.point = point;
        this.creatures = new ArrayList<>();
        initCreatures();
    }

    private void initCreatures() {
        Random random = new Random();
        for (Class<? extends Creature> creatureClass : creatureSet) {
            try {
                // We make instance of the class for checking of max probably number of thing creating in the cell
                Creature testCreature = creatureClass.getDeclaredConstructor().newInstance();
                // Take max numbers of animals in the cell, use Random to this number...
                int creatureNumbersForCell = random.nextInt(testCreature.getMaxCreatureOnCell()) + 1;

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
                e.printStackTrace();
            }
        }
        animals.addAll(predators);
        animals.addAll(herbivores);
    }

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

