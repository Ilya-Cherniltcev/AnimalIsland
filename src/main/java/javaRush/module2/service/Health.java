package javaRush.module2.service;

import javaRush.module2.model.Creature;
import lombok.NoArgsConstructor;

import static javaRush.module2.service.CreatureSettings.HEALTH_DECREASE_VALUE;

@NoArgsConstructor
public class Health {

    public int decrease(Creature creature) {
        int health = creature.getHealth();
        int newHealth = health - HEALTH_DECREASE_VALUE;
        if (newHealth <= 0) {
            newHealth = 0;
        }
        return newHealth;
    }

}
