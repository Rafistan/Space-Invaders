package sample;

public class Alien extends GameObject
{
    public Alien(Vector2D position, int j)
    {
        super(position, new Vector2D(75, 7), new Vector2D(0.0, 0.0), 25, 25);

        if(j == 1)
            rectangle.setFill(AssetManager.getEnemyImage1());
        else if(j == 2)
            rectangle.setFill(AssetManager.getEnemyImage2());
        else if(j == 3)
            rectangle.setFill(AssetManager.getEnemyImage3());
        else
            rectangle.setFill(AssetManager.getEnemyImage4());
    }
}
