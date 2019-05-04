package prove02;

import java.awt.*;
import java.util.Random;

public class Wolf extends Creature implements Spawner, Movable, Aware, Aggressor {
    private Random _rand;
    private Direction _prefDirection;
    private int _spawnIn;

    /**
     * Creates a wolf with 5 health points
     */
    public Wolf() {
        _health = 5;
        _rand = new Random();
        _spawnIn = -1;

        switch(_rand.nextInt(4))
        {
            case 0:
                _prefDirection = Direction.Left;
                break;
            case 1:
                _prefDirection = Direction.Up;
                break;
            case 2:
                _prefDirection = Direction.Right;
                break;
            case 3:
                _prefDirection = Direction.Down;
                break;
        }
    }

    public Shape getShape() {
        return Shape.Square;
    }

    public Color getColor() {
        return new Color(150, 150, 150);
    }

    public Boolean isAlive() {
        return _health > 0;
    }

    /**
     * Move the wolf in it's preferred direction.
     */
    public void move() {
        switch (_prefDirection) {
            case Left:
                _location.x--;
                break;
            case Up:
                _location.y--;
                break;
            case Right:
                _location.x++;
                break;
            case Down:
                _location.y++;
                break;
        }
    }

    /**
     * If the encountered creature is an animal, attack. Otherwise, ignore it.
     * @param target The {@link Creature} we've encounterd.
     */
    public void attack(Creature target) {
        if (target instanceof Animal) {
            target.takeDamage(5);
            _spawnIn = 1;
        }
    }

    /**
     * This method is called every frame and indicates what creatures are nearby.
     * If there is no creature in a particular location, that value will be null.
     * @param above The {@link Creature} directly above us.
     * @param below The {@link Creature} directly below us.
     * @param left The {@link Creature} directly to the left of us.
     * @param right The {@link Creature} directly to the right of us.
     */
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {
        Direction nextDir = _prefDirection;
        do {
            switch (nextDir) {
                case Left:
                    if (left instanceof Animal) {
                        _prefDirection = nextDir;
                    }
                    else {
                        nextDir = Direction.Up;
                    }
                    break;
                case Up:
                    if (above instanceof Animal) {
                        _prefDirection = nextDir;
                    }
                    else {
                        nextDir = Direction.Right;
                    }
                    break;
                case Right:
                    if (right instanceof Animal) {
                        _prefDirection = nextDir;
                    }
                    else {
                        nextDir = Direction.Down;
                    }
                    break;
                case Down:
                    if (below instanceof Animal) {
                        _prefDirection = nextDir;
                    }
                    else {
                        nextDir = Direction.Left;
                    }
                    break;
            }
        } while(nextDir != _prefDirection);
    }

    /**
     * If the wolf is able to spawn, create a new Wolf instance to the left
     * @return The spawned {@link Creature}.
     */
    public Creature spawnNewCreature() {
        Wolf w = null;

        if (_spawnIn == 0) {
            w = new Wolf();
            w.setLocation(new Point(_location.x - 1, _location.y));
        }

        if (_spawnIn >= 0)
            _spawnIn--;
        return w;
    }
}
