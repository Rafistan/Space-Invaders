package sample;

public class ShipProjectile extends GameObject
{
    public ShipProjectile(Vector2D position)
    {
        super(position, new Vector2D(0.0, -700.0), new Vector2D(0.0, 0.0), 2, 10);

        rectangle.setFill(AssetManager.getSpaceshipProjectile());
    }
}