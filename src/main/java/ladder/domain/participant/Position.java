package ladder.domain.participant;

import ladder.domain.ladder.Direction;

import java.util.Objects;

public class Position {
    private int position;

    public Position(int position) {
        this.position = position;
    }

    public void move(Direction direction) {
        position = position + direction.move();
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
