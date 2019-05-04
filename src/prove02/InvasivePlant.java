package prove02;

import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Invasive plants have a 1% chance of spawning a new invasive plant in an adjacent space that doesn't contain a plant.
 * every turn.
 * They are represented by a purple circle.
 * @author  Andrew Barker
 * @since   2019-05-04
 * @see     Plant
 */
public class InvasivePlant extends Plant implements Aware, Spawner {
    private Random _rand;
    private List<Direction> _spawnableNeighbors;

    /**
     * Creates an invasive plant with 1 health points.
     */
    public InvasivePlant() {
        _rand = new Random();
        _health = 1;
        _spawnableNeighbors = new ArrayList<>();
    }

    public Color getColor() {
        return new Color(102, 32, 132);
    }

    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {
        _spawnableNeighbors.clear();

        if (!(above instanceof Plant)) {
            _spawnableNeighbors.add(Direction.Up);
        }
        if (!(below instanceof Plant)) {
            _spawnableNeighbors.add(Direction.Down);
        }
        if (!(left instanceof Plant)) {
            _spawnableNeighbors.add(Direction.Left);
        }
        if (!(right instanceof Plant)) {
            _spawnableNeighbors.add(Direction.Right);
        }
    }

    /**
     * While there are any empty adjacent spaces, there is a 1% chance that a new {@link InvasivePlant} will be spawned.
     * Spawning a new {@link InvasivePlant} increases the parent's health by one.
     * @return The spawned {@link Creature}.
     */
    public Creature spawnNewCreature() {
        if (_spawnableNeighbors.size() == 0 || _rand.nextInt(100) != 0)
            return null;

        InvasivePlant ip = new InvasivePlant();
        int i = _rand.nextInt(_spawnableNeighbors.size());
        switch(_spawnableNeighbors.get(i)){
            case Up:
                ip.setLocation(new Point(_location.x, _location.y + 1));
                break;
            case Down:
                ip.setLocation(new Point(_location.x, _location.y - 1));
                break;
            case Left:
                ip.setLocation(new Point(_location.x - 1, _location.y));
                break;
            case Right:
                ip.setLocation(new Point(_location.x + 1, _location.y));
                break;
        }

        _health++;

        return ip;
    }
}
