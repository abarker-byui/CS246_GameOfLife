package prove02;

import java.awt.Color;

public class Zombie extends Creature implements Movable, Aggressor {
    /**
     * Creates a Zombie with 1 health points
     */
    public Zombie() {
        _health = 10;
    }

    public Shape getShape() {
        return Shape.Square;
    }

    public Color getColor() {
        return new Color(100, 125, 255);
    }

    public Boolean isAlive() {
        return true;
    }

    /**
     * Moves the Zombie from left to right.
     */
    public void move() {
        _location.x++;
    }

    /**
     * If the encountered creature is not a plant, attack. Otherwise, ignore it.
     * @param target The {@link Creature} we've encounterd.
     */
    public void attack(Creature target) {
        if (target != null && !(target instanceof Plant)) {
            target.takeDamage(10);
        }
    }
}
