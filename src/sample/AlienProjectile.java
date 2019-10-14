package sample;

public class AlienProjectile extends GameObject
{
    public AlienProjectile(Vector2D position)
    {
        super(position, new Vector2D(0.0, 200.0), new Vector2D(0.0, 0.0), 2, 10);

        rectangle.setFill(AssetManager.getAlienProjectile());
    }
}
