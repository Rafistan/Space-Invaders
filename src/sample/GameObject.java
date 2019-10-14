package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GameObject extends Circle
{
    protected Rectangle rectangle;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;

    public GameObject(Vector2D position, Vector2D velocity, Vector2D acceleration, double width, double height)
    {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;

        rectangle = new Rectangle(0.0, 0.0, width, height);
        rectangle.setLayoutX(position.getX());
        rectangle.setLayoutY(position.getY());
    }

    public Vector2D getPosition()
    {
        return position;
    }

    public void setPosition(double x, double y)
    {
        if(x > 615){
            position.setX(615);
            position.setY(y);
            rectangle.setLayoutX(615);
            rectangle.setLayoutY(position.getY());
        }else if(x <= rectangle.getWidth() / 2 - 15){
            position.setX(0);
            position.setY(y);
            rectangle.setLayoutX(0);
            rectangle.setLayoutY(position.getY());
        }else{
            position.setX(x);
            position.setY(y);
            rectangle.setLayoutX(position.getX());
            rectangle.setLayoutY(position.getY());
        }
    }

    public Rectangle getRectangle()
    {
        return rectangle;
    }

    public void update(double dt)
    {
        // Euler Integration
        // Update velocity
        Vector2D frameAcceleration = acceleration.mul(dt);
        velocity = velocity.add(frameAcceleration);

        // Update position
        position = position.add(velocity.mul(dt));
        rectangle.setLayoutX(position.getX());
        rectangle.setLayoutY(position.getY());
    }
}
