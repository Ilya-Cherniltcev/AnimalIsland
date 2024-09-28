package javaRush.module2.model.animal;

import javaRush.module2.interfaces.Eatable;
import javaRush.module2.interfaces.Moveable;
import javaRush.module2.interfaces.Reproducable;
import javaRush.module2.model.Point;
import javaRush.module2.model.Creature;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Random;

@Setter
@Getter
public abstract class Animal extends Creature implements Eatable, Moveable, Reproducable {

//    private int health;
    private int speed;
    private double kgToFullSaturation;

    public Animal(double weight, int maxCreatureOnCell, int x, int y, int health, int speed, double kgToFullSaturation) {
        super(weight, maxCreatureOnCell, x, y, health);
        this.speed = speed;
        this.kgToFullSaturation = kgToFullSaturation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
        Animal animal = (Animal) o;
        return speed == animal.speed && kgToFullSaturation == animal.kgToFullSaturation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speed, kgToFullSaturation);
    }

    @Override
    public String toString() {
        return "Animal{ id= " + super.getId() + "weight=" + super.getWeight() + ", maxCreatureOnCell=" + super.getMaxCreatureOnCell() +
                ", health=" + super.getHealth() +
                ", speed=" + speed +
                ", kgToFullSaturation=" + kgToFullSaturation +
                '}';
    }

    @Override
    public int eat(Animal animal, Creature animalOrPlant) {
        double eatWeight = animalOrPlant.getWeight();
        double saturation = animal.kgToFullSaturation;
        int health = animal.getHealth();
        int plusHealth;
        // case 1
        if (eatWeight >= saturation) {
            return 100;
        }
        // case 2
        else {
            plusHealth = (int) (saturation / eatWeight) * 100;
        }
        health += plusHealth;
        if (health > 100) {
            health = 100;
        }
        return health;
    }

    @Override
    public Point move(Animal animal) {
        Point newPoint = new Point();
        int xOld = animal.getX();
        int yOld = animal.getY();
        // get animal's health
        int health = animal.getHealth();
        int speed = animal.getSpeed();
        // if health <= 15% then the animal couldn't to move. Power doesn't enough ((
        // if speed == 0 then we stop on the place
        if (health <= 15 || speed == 0) {
            newPoint.setX(xOld);
            newPoint.setY(yOld);
            return newPoint;
        }
        Random random = new Random();
        int movingSpeed = random.nextInt(speed) + 1;
        int movingSpeedX = random.nextInt(movingSpeed);
        // check where we move: 1 -> right, 0 -> left
        if (movingSpeedX > 0) {
            int rightLeft = random.nextInt(2);
            if (rightLeft == 0) {
                movingSpeedX = -movingSpeedX;
            }
        }
        int movingSpeedY = movingSpeed - movingSpeedX;
        // check where we move: 1 -> up, 0 -> down
        if (movingSpeedX > 0) {
            int upDown = random.nextInt(2);
            if (upDown == 1) {
                movingSpeedY = -movingSpeedY;
            }
        }
        int newX = xOld + movingSpeedX;
        int newY = yOld + movingSpeedY;
        newPoint.setX(newX);
        newPoint.setY(newY);
        return newPoint;
    }

    @Override
    public void reproduce() {

    }
}
