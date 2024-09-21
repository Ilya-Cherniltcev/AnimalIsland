package javaRush.module2.service;

import javaRush.module2.model.Creature;
import javaRush.module2.model.animal.herbivore.*;
import javaRush.module2.model.animal.predator.*;
import javaRush.module2.model.plant.Grass;
import javaRush.module2.model.plant.Plant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Settings {
    public static String LINE = "_______________________________________________________________________________________________________________________________________________________";
    public static Set<Class<? extends Creature>> creatureSet = new HashSet<>(Set.of(
            Bear.class,
            Eagle.class,
            Fox.class,
            Snake.class,
            Wolf.class,

            Caterpillar.class,
            Cow.class,
            Deer.class,
            Duck.class,
            Goat.class,
            Hamster.class,
            Hare.class,
            Horse.class,
            Kangaroo.class,
            Sheep.class,

            Grass.class));

    public static int X_DEFAULT = 0;
    public static int Y_DEFAULT = 0;
    public static int HEALTH = 100;
    public static int HEALTH_DECREASE_VALUE = 25;

    public static Map<Class<? extends Creature>,
            Map<Class<? extends Creature>, Integer>> PROBABILITY_TO_EAT = new HashMap<>();

    static {
        PROBABILITY_TO_EAT.put(Wolf.class, Map.of(Horse.class, 10 , Deer.class, 15, Hare.class, 60, Goat.class, 60, Sheep.class, 70, Kangaroo.class, 15, Cow.class, 10, Duck.class, 40));
        PROBABILITY_TO_EAT.put(Snake.class, Map.of(Fox.class, 15 , Hare.class, 20, Hamster.class, 40, Duck.class, 10));
        PROBABILITY_TO_EAT.put(Fox.class, Map.of(Hare.class, 70, Hamster.class, 90, Duck.class, 60, Caterpillar.class, 40));
        PROBABILITY_TO_EAT.put(Bear.class, Map.of(Snake.class, 80, Horse.class, 40, Deer.class, 80, Hamster.class, 90, Goat.class, 70, Sheep.class, 70, Kangaroo.class, 50, Cow.class, 20, Duck.class, 10));
        PROBABILITY_TO_EAT.put(Eagle.class, Map.of(Fox.class, 10, Hare.class, 90, Hamster.class, 90, Duck.class, 80));

        PROBABILITY_TO_EAT.put(Horse.class, Map.of(Grass.class, 100));
        PROBABILITY_TO_EAT.put(Deer.class, Map.of(Grass.class, 100));
        PROBABILITY_TO_EAT.put(Hare.class, Map.of(Grass.class, 100));
        PROBABILITY_TO_EAT.put(Hamster.class, Map.of(Caterpillar.class, 90, Grass.class, 100));
        PROBABILITY_TO_EAT.put(Goat.class, Map.of(Grass.class, 100));
        PROBABILITY_TO_EAT.put(Sheep.class, Map.of(Grass.class, 100));

        PROBABILITY_TO_EAT.put(Kangaroo.class, Map.of(Hamster.class, 50, Caterpillar.class, 90, Grass.class, 100));
        PROBABILITY_TO_EAT.put(Cow.class, Map.of(Grass.class, 100));
        PROBABILITY_TO_EAT.put(Duck.class, Map.of(Caterpillar.class, 90, Grass.class, 100));
        PROBABILITY_TO_EAT.put(Caterpillar.class, Map.of(Grass.class, 100));
    }

    // === Herbivores ===
    public static double CATERPILLAR_WEIGHT = 0.01;
    public static int CATERPILLAR_MAX_ON_CELL = 3;
//    public static int CATERPILLAR_MAX_ON_CELL = 1000;
    public static int CATERPILLAR_SPEED = 0;
    public static double CATERPILLAR_SATURATION = 0;

    public static double COW_WEIGHT = 700;
    public static int COW_MAX_ON_CELL = 2;
//    public static int COW_MAX_ON_CELL = 10;
    public static int COW_SPEED = 3;
    public static double COW_SATURATION = 100;

    public static double DEER_WEIGHT = 300;
    public static int DEER_MAX_ON_CELL = 1;
//    public static int DEER_MAX_ON_CELL = 20;
    public static int DEER_SPEED = 4;
    public static double DEER_SATURATION = 50;

    public static double DUCK_WEIGHT = 1;
    public static int DUCK_MAX_ON_CELL = 1;
//    public static int DUCK_MAX_ON_CELL = 200;
    public static int DUCK_SPEED = 4;
    public static double DUCK_SATURATION = 0.15;

    public static double GOAT_WEIGHT = 60;
    public static int GOAT_MAX_ON_CELL = 1;
//    public static int GOAT_MAX_ON_CELL = 140;
    public static int GOAT_SPEED = 3;
    public static double GOAT_SATURATION = 10;

    public static double HAMSTER_WEIGHT = 0.05;
    public static int HAMSTER_MAX_ON_CELL = 1;
//    public static int HAMSTER_MAX_ON_CELL = 500;
    public static int HAMSTER_SPEED = 1;
    public static double HAMSTER_SATURATION = 0.01;

    public static double HARE_WEIGHT = 2;
    public static int HARE_MAX_ON_CELL = 1;
//    public static int HARE_MAX_ON_CELL = 150;
    public static int HARE_SPEED = 2;
    public static double HARE_SATURATION = 0.45;


    public static double HORSE_WEIGHT = 400;
    public static int HORSE_MAX_ON_CELL = 1;
//    public static int HORSE_MAX_ON_CELL = 20;
    public static int HORSE_SPEED = 4;
    public static double HORSE_SATURATION = 60;

    public static double KANGAROO_WEIGHT = 400;
    public static int KANGAROO_MAX_ON_CELL = 1;
//    public static int KANGAROO_MAX_ON_CELL = 30;
    public static int KANGAROO_SPEED = 4;
    public static double KANGAROO_SATURATION = 50;

    public static double SHEEP_WEIGHT = 70;
    public static int SHEEP_MAX_ON_CELL = 1;
//    public static int SHEEP_MAX_ON_CELL = 140;
    public static int SHEEP_SPEED = 3;
    public static double SHEEP_SATURATION = 15;


    // === predators ===
    public static double BEAR_WEIGHT = 500;
    public static int BEAR_MAX_ON_CELL = 1;
//    public static int BEAR_MAX_ON_CELL = 5;
    public static int BEAR_SPEED = 2;
    public static double BEAR_SATURATION = 80;

    public static double EAGLE_WEIGHT = 6;
    public static int EAGLE_MAX_ON_CELL = 20;
    public static int EAGLE_SPEED = 3;
    public static double EAGLE_SATURATION = 1;

    public static double FOX_WEIGHT = 8;
    public static int FOX_MAX_ON_CELL = 30;
    public static int FOX_SPEED = 2;
    public static double FOX_SATURATION = 2;

    public static double SNAKE_WEIGHT = 15;
    public static int SNAKE_MAX_ON_CELL = 30;
    public static int SNAKE_SPEED = 1;
    public static double SNAKE_SATURATION = 3;


    public static double WOLF_WEIGHT = 50;
    public static int WOLF_MAX_ON_CELL = 2;
//    public static int WOLF_MAX_ON_CELL = 30;
    public static int WOLF_SPEED = 3;
    public static double WOLF_SATURATION = 8;


    // === Plants ===
    public static double GRASS_WEIGHT = 1;
    public static int GRASS_MAX_ON_CELL = 5;
//    public static int GRASS_MAX_ON_CELL = 2_000;

}
