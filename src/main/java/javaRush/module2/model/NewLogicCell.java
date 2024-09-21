package javaRush.module2.model;

import javaRush.module2.model.animal.Animal;
import javaRush.module2.model.animal.herbivore.Herbivore;
import javaRush.module2.model.animal.predator.Predator;
import javaRush.module2.model.plant.Plant;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static javaRush.module2.service.Settings.*;


public class NewLogicCell {
    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
    private int y;
    private List<Animal> animals;
    @Getter
    private final List<Plant> plants;
    private List<Plant> plantsToRemove;
    @Getter
    private final List<Predator> predators;
    private List<Predator> predatorsToRemove;
    @Getter
    private final List<Herbivore> herbivores;
    private List<Herbivore> herbivoresToRemove;

    private Map<Class<? extends Creature>, List<? extends Creature>> creatureMap;


    public NewLogicCell(int x, int y) {
        this.x = x;
        this.y = y;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.herbivores = new ArrayList<>();
        this.predators = new ArrayList<>();

        this.creatureMap = new HashMap<>();
        initAnimalsAndPlants(); // ===================  ВОЗМОЖНО, ПОТОМ СЛЕДУЕТ УБРАТЬ ДАННЫЙ МЕТОД ИЗ КОНСТРУКТОРА =====
    }

    private void initAnimalsAndPlants() {
        Random random = new Random();
        for (Class<? extends Creature> creatureClass : creatureSet) {

            try {
                // make instance of class
                Creature creature = creatureClass.getDeclaredConstructor().newInstance();
                // Take max numbers of animals in the cell, use Random to this number...
                int creatureNumbersForCell = random.nextInt(creature.getMaxCreatureOnCell());
                // check instance for equals of Predator

                if (creature instanceof Predator) {
                    // ... and create that number of instance of class
                    // Then we add created instance to list of predators
                    for (int i = 0; i < creatureNumbersForCell; i++) {
                        Predator predator = (Predator) creatureClass.getConstructor().newInstance();
                        // change default coordinates to coordinates of this cell
                        predator.setX(x);
                        predator.setY(y);
                        predators.add(predator);
                    }
                } else if (creature instanceof Herbivore) {
                    // ... and create that number of instance of class
                    // Then we add created instance to list of herbivore
                    for (int i = 0; i < creatureNumbersForCell; i++) {
                        Herbivore herbivore = (Herbivore) creatureClass.getConstructor().newInstance();
                        // change default coordinates to coordinates of this cell
                        herbivore.setX(x);
                        herbivore.setY(y);
                        herbivores.add(herbivore);
                    }
                } else if (creature instanceof Plant) {
                    for (int i = 0; i < creatureNumbersForCell; i++) {
                        Plant plant = (Plant) creatureClass.getConstructor().newInstance();
                        plant.setX(x);
                        plant.setY(y);
                        plants.add(plant);
                    }
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        animals = getAnimals();
        // init lists of animals and plants for delayed deletion
        predatorsToRemove = new ArrayList<>();
        herbivoresToRemove = new ArrayList<>();
        plantsToRemove = new ArrayList<>();
    }

    public List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<>(predators);
        animals.addAll(herbivores);
        return animals;
    }

    public List<Creature> getCreatures() {
        List<Creature> creatures = new ArrayList<>(animals);
        creatures.addAll(plants);
        return creatures;
    }

    public void eatEverybody() {
        // let animas to eat - u are welcome))
        // First predators are eating
        timeToEat(predators, herbivores);
        // Now it's time to eat for herbivores (if there's anybody)
        timeToEat(herbivores, plants);
        killCreatures();
    }

    private void timeToEat(List<? extends Animal> someAnimals, List<? extends Creature> animalsOrPlants) {
        Random random = new Random();
        for (Animal hunter : someAnimals) {
            /* Here "hunter" can be real predator or herbivore as "predator" for plant
                (and some cases for example duck = predator for caterpillar etc.)
            */
            Map<Class<? extends Creature>, Integer> possiblePreyClasses = PROBABILITY_TO_EAT.get(hunter.getClass());
//            do {
                // get class of herbivore with probability to eating
                int probablyToEat = (random.nextInt(10) + 1) * 10;
                // НАДО СДЕЛАТЬ ПЕРЕБОР ЗНАЧЕНИЙ В ЦИКЛЕ while мапы possiblePreyClasses, ЕСЛИ ЖИВОТНЫХ НЕ НАЙДЕНО
                // ДЛЯ ВЫБОРА ПОЕЖАНИЯ ДРУГОГО ЖИВОТНОГО
                Class<? extends Creature> preyClass = null;
                for (Map.Entry<Class<? extends Creature>, Integer> clazz : possiblePreyClasses.entrySet()) {
                    if (probablyToEat >= clazz.getValue()) {
//                    max = clazz.getValue();
                        preyClass = clazz.getKey();
                        break;
                    }
                }
                // Try to find real animal (prey) of that class
                boolean hasEaten = false;
                boolean isAnybodyToEat = true;
                // --- 1 case - hunter is predator, prey is herbivore
                if (hunter instanceof Predator) {
                    hasEaten = findEatForPredator((Predator) hunter, preyClass);
                    if(herbivores.size() - herbivoresToRemove.size() == 0){
                        isAnybodyToEat = false;
                    }
                }
                // --- 2 case - hunter is herbivore, prey is plant
                else if (hunter instanceof Herbivore) {
                    hasEaten = findEatForHerbivore((Herbivore) hunter, preyClass);
                    if(plants.size() - plantsToRemove.size() == 0){
                        isAnybodyToEat = false;
                    }
                }
                // --- 3 case - there are't any animals or plants. Animal is hungry. Health is decreasing ((
                // if (!hasEaten) call method healthDecrease
                if (!hasEaten && !isAnybodyToEat) {
                    healthDecrease(hunter);
                    break;
                }
//        while ();
        }
    }

    private boolean findEatForPredator(Predator predator, Class<? extends Creature> preyClass) {
        // Set flag: Process of eating has end or no
        boolean hasEaten = false;
        for (Herbivore herbivore : herbivores) {
            if (herbivore.getClass() == preyClass) {
                // now we can eat!
                int newPredatorHealth = predator.eat(predator, herbivore);
                predator.setHealth(newPredatorHealth);
                hasEaten = true;
                // add herbivore to list for remove
                herbivoresToRemove.add(herbivore);
                break;
            }
        }
        return hasEaten;
    }

    private boolean findEatForHerbivore(Herbivore herbivore, Class<? extends Creature> preyClass) {
        // Set flag: Process of eating has end or no
        boolean hasEaten = false;
        for (Plant plant : plants) {
            if (plant.getClass() == preyClass) {
                // now we can eat!
                int newHerbivoreHealth = herbivore.eat(herbivore, plant);
                herbivore.setHealth(newHerbivoreHealth);
                hasEaten = true;
                // add herbivore to list for remove
                plantsToRemove.add(plant);
                break;
            }
        }
        return hasEaten;
    }

    private void healthDecrease(Animal animal) {
        int health = animal.getHealth();
        int newHealth = health - HEALTH_DECREASE_VALUE;
        if (newHealth <= 0) {
            if (animal instanceof Predator) {
                predatorsToRemove.add((Predator) animal);
            } else if (animal instanceof Herbivore) {
                herbivoresToRemove.add((Herbivore) animal);
            }
        } else {
            animal.setHealth(newHealth);
        }
    }

    private void killCreatures() {
        predators.removeAll(predatorsToRemove);
        herbivores.removeAll(herbivoresToRemove);
        plants.removeAll(plantsToRemove);
        predatorsToRemove = new ArrayList<>();
        herbivoresToRemove = new ArrayList<>();
        plantsToRemove = new ArrayList<>();
    }

}

