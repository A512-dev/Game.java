package model.movement;

public interface Movable {
    void setSpeed(double speed);
    void setVelocity(double velocity);
    void setDirection(Direction direction);
    void move(Direction direction,double speed,double velocity);
    void move();
}
