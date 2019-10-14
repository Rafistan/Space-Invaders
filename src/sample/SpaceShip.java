package sample;

public class SpaceShip extends GameObject
{
    public SpaceShip(Vector2D position, double width, double height)
    {
        super(position, new Vector2D(0.0, 0.0), new Vector2D(0.0, 0.0), width, height);

        rectangle.setFill(AssetManager.getSpaceshipImage());
    }


    public void update(double dt)
    {
        getRectangle().toFront();
    }
}
