package javaRush.module2.service;

import javaRush.module2.model.Creature;
import lombok.NoArgsConstructor;

import static javaRush.module2.service.CreatureSettings.HEALTH_DECREASE_VALUE;

@NoArgsConstructor
public class Health {

    /**
     * Decrease health of creature
     *
     * @param creature  any creature which health must be decreased
     * @return new decreased level of the health
     */
    public int decrease(Creature creature) {
        int health = creature.getHealth();
        int newHealth = health - HEALTH_DECREASE_VALUE;
        if (newHealth <= 0) {
            newHealth = 0;
        }
        return newHealth;
    }

}
