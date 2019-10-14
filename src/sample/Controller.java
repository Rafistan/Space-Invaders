package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    AnchorPane pane;
    @FXML
    Label scoreLabel;
    @FXML
    Label livesLabel;
    @FXML
    Label winLoseMessage;

    private int score, lives = 3;
    private SpaceShip spaceship = null;
    private double mouseX= 0.0;
    private final double mouseY = 550;
    private double lastFrameTime = 0.0;
    private ArrayList<GameObject> projectileList;
    private ArrayList<GameObject> alienProjectileList;
    private ArrayList<GameObject> aliens;
    private int shield1Count = 1, shield2Count = 1, shield3Count = 1, shield4Count = 1;
    private Rectangle shield1 = null, shield2 = null, shield3 = null, shield4 = null;

    @FXML
    public void setOnMouseMoved(MouseEvent event)
    {
        mouseX = event.getX();
        if(lives > 0)
            spaceship.setPosition(mouseX - 15, mouseY);
        else
            spaceship.setPosition(2465, 6742);
    }

    @FXML
    public void setOnMouseClicked(MouseEvent event)
    {
        if (event.getButton() == MouseButton.PRIMARY)
        {
            Vector2D mousePosition = new Vector2D(mouseX, mouseY);

            GameObject projectile = new ShipProjectile(mousePosition);
            projectileList.add(projectile);
            addToPane(projectile.getRectangle());
            AssetManager.getShootingSound().play();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();

        //load the assets
        AssetManager.preloadAllAssets();

        //create the projectileList
        projectileList = new ArrayList<>();
        alienProjectileList = new ArrayList<>();

        MediaPlayer musicPlayer = new MediaPlayer(AssetManager.getBackgroundMusic());
        musicPlayer.play();

        pane.setBackground(AssetManager.getBackgroundImage());

        setShieldsImage(AssetManager.getShield1Image());

        makeAliens();

        spaceship = new SpaceShip(new Vector2D(mouseX, mouseY), 30, 15);
        addToPane(spaceship.getRectangle());

        new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                // Time calculation
                double currentTime = (now - initialTime) / 1000000000.0;
                double  frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;

                aliensShoot();

                alienSpaceshipCollision();

                try{
                    for(GameObject obj : projectileList)
                    {
                        checkForAlienCollision();
                        obj.update(frameDeltaTime);
                    }
                    for(GameObject obj : alienProjectileList)
                    {
                        if(checkForShieldCollision(shield1))
                            changeShieldPicture(++shield1Count, shield1);
                        if(checkForShieldCollision(shield2))
                            changeShieldPicture(++shield2Count, shield2);
                        if(checkForShieldCollision(shield3))
                            changeShieldPicture(++shield3Count, shield3);
                        if(checkForShieldCollision(shield4))
                            changeShieldPicture(++shield4Count, shield4);

                        checkForShipCollision();

                        obj.update(frameDeltaTime);
                    }
                    for(GameObject obj : aliens)
                    {
                        if(obj.position.getX() > pane.getWidth() - obj.rectangle.getWidth() - 30)
                        {
                            double absVelocityX = Math.abs(obj.velocity.getX());
                            for(int i = 0; i < aliens.size(); i++)
                            {
                                aliens.get(i).velocity.setX(-absVelocityX);
                            }
                        }

                        if(obj.position.getX() < 30)
                        {
                            double absVelocityX = Math.abs(obj.velocity.getX());
                            for(int i = 0; i < aliens.size(); i++)
                            {
                                aliens.get(i).velocity.setX(absVelocityX);
                            }
                        }
                        obj.update(frameDeltaTime);
                    }
                }catch(Exception e){}

            }
        }.start();
    }

    private void addToPane(Node node)
    {
        pane.getChildren().add(node);
    }

    private void removeFromPane(Node node)
    {
        pane.getChildren().remove(node);
    }

    private void alienSpaceshipCollision()
    {
        for(int i = 0; i < aliens.size(); i++)
        {
            if(aliens.get(i).rectangle.getBoundsInParent().intersects(spaceship.getRectangle().getBoundsInParent()))
            {
                lives = 0;
                removeAliensAndPlayer();
            }
        }
    }

    private void makeAliens()
    {
        aliens = new ArrayList<>();

        double centerX = 50;
        double centerY = 50;

        for(int i = 0; i < 7; i++)
        {
            for(int j = 1; j <= 4; j++)
            {

                GameObject tempAlien = new Alien(new Vector2D(centerX, centerY), j);

                aliens.add(tempAlien);
                addToPane(tempAlien.getRectangle());

                centerY += 50;
            }
            centerY = 50;
            centerX += 50;
        }
    }

    private void changeShieldPicture(int count, Rectangle shield)
    {
        if(count == 2)
            shield.setFill(AssetManager.getShield2Image());
        else if(count == 3)
            shield.setFill(AssetManager.getShield3Image());
        else if(count == 4)
            shield.setFill(AssetManager.getShield4Image());
        else if(count == 5)
            shield.setFill(AssetManager.getShield5Image());
        else if(count == 6)
        {
            shield.setX(2000);
            shield.setY(2000);
            shield.setWidth(0);
            shield.setHeight(0);
            removeFromPane(shield);
        }
    }

    private void checkForAlienCollision()
    {
        for(int i = 0; i < projectileList.size(); i++)
        {
            GameObject temp = projectileList.get(i);

            for(int j = 0; j < aliens.size(); j++)
            {
                if (temp.getRectangle().getBoundsInParent().intersects(shield1.getBoundsInParent()))
                {
                    removeFromPane(projectileList.get(i).getRectangle());
                    projectileList.remove(i);
                }
                else if (temp.getRectangle().getBoundsInParent().intersects(shield2.getBoundsInParent()))
                {
                    removeFromPane(projectileList.get(i).getRectangle());
                    projectileList.remove(i);
                }
                else if (temp.getRectangle().getBoundsInParent().intersects(shield3.getBoundsInParent()))
                {
                    removeFromPane(projectileList.get(i).getRectangle());
                    projectileList.remove(i);
                }
                else if (temp.getRectangle().getBoundsInParent().intersects(shield4.getBoundsInParent()))
                {
                    removeFromPane(projectileList.get(i).getRectangle());
                    projectileList.remove(i);
                }
                else if (temp.getRectangle().getBoundsInParent().intersects(aliens.get(j).rectangle.getBoundsInParent()))
                {
                    removeFromPane(aliens.get(j).getRectangle());
                    removeFromPane(temp.getRectangle());

                    projectileList.remove(i);
                    aliens.remove(j);

                    score += 10;
                    scoreLabel.setText(String.format("%s: %03d", "Score", score));
                    if(aliens.isEmpty())
                        winLoseMessage.setText("YOU WIN!");

                    AssetManager.getAlienBlowUpSound().play();
                }
            }
        }
    }

    private void checkForShipCollision()
    {
        for(int i = 0; i < alienProjectileList.size(); i++)
        {
            GameObject temp = alienProjectileList.get(i);

            if(temp.getRectangle().getBoundsInParent().intersects(spaceship.getRectangle().getBoundsInParent()))
            {
                temp.setPosition(10000, 10000);

                removeFromPane(alienProjectileList.get(i).getRectangle());
                alienProjectileList.remove(i);
                lives--;
                livesLabel.setText(String.format("%s: %1d", "Lives: ", lives));

                if(lives == 0)
                    removeAliensAndPlayer();

                AssetManager.getPlayerBlowUpSound().play();

            }
        }
    }

    private void removeAliensAndPlayer()
    {
        for(int i = 0; i < aliens.size(); i++)
        {
            removeFromPane(aliens.get(i).getRectangle());
            aliens.remove(i);
            i--;
        }

        winLoseMessage.setText("YOU LOSE!");
    }

    private boolean checkForShieldCollision(Rectangle shield)
    {
        for(int i = 0; i < alienProjectileList.size(); i++)
        {
            GameObject temp = alienProjectileList.get(i);
            Bounds tempBounds = temp.getRectangle().getBoundsInParent();

            if(tempBounds.intersects(shield.getBoundsInParent()))
            {
                removeFromPane(alienProjectileList.get(i).getRectangle());
                alienProjectileList.remove(i);
                return true;
            }
        }
        return false;
    }

    private void aliensShoot()
    {
        int randNumber = (int)(Math.random() * 20);
        if(randNumber == 3)
        {
            try {
                int randomAlien = (int) (Math.random() * (aliens.size()));

                double alienX = aliens.get(randomAlien).rectangle.getLayoutX() + 12.5;
                double alienY = aliens.get(randomAlien).rectangle.getLayoutY();

                Vector2D alienPosition = new Vector2D(alienX, alienY);

                GameObject alienProjectile = new AlienProjectile(alienPosition);
                alienProjectileList.add(alienProjectile);
                addToPane(alienProjectile.getRectangle());

                AssetManager.getAlienShotSound().play();
            } catch (Exception e) {}
        }
    }

    private void setShieldsImage(ImagePattern pat)
    {
        shield1 = new Rectangle(62, 459, 75, 35);
        shield2 = new Rectangle(216, 459, 75, 35);
        shield3 = new Rectangle(360, 459, 75, 35);
        shield4 = new Rectangle(511, 459, 75, 35);

        shield1.setFill(pat);
        shield2.setFill(pat);
        shield3.setFill(pat);
        shield4.setFill(pat);

        pane.getChildren().addAll(shield1, shield2, shield3, shield4);
    }
}
