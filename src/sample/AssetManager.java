package sample;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;

public class AssetManager
{
    static private Background backgroundImage = null;
    static private Media backgroundMusic = null;
    static private AudioClip shootingSound = null;
    static private AudioClip playerBlowUpSound = null;
    static private AudioClip alienBlowUpSound = null;
    static private AudioClip alienShotSound = null;
    static private ImagePattern spaceshipImg = null;
    static private ImagePattern spaceshipProjectile = null, alienProjectile = null;
    static private ImagePattern enemyImage1 = null, enemyImage2 = null, enemyImage3 = null, enemyImage4 = null;
    static private ImagePattern shield1 = null, shield2 = null, shield3 = null, shield4 = null, shield5 = null;

    static private String fileURL(String relativePath)
    {
        return new File(relativePath).toURI().toString();
    }

    static public void preloadAllAssets()
    {
        Image background = new Image(fileURL("./assets/images/background.png"));

        backgroundImage = new Background(
                new BackgroundImage(background,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT));

        backgroundMusic = new Media(fileURL("./assets/music/backgroundMusic.mp3"));

        playerBlowUpSound = new AudioClip(fileURL("./assets/sounds/explosion.wav"));

        alienBlowUpSound = new AudioClip(fileURL("./assets/sounds/invaderKilled.wav"));

        shootingSound = new AudioClip(fileURL("./assets/sounds/shoot.wav"));

        alienShotSound = new AudioClip(fileURL("./assets/sounds/alienShot.wav"));

        spaceshipImg = new ImagePattern(new Image(fileURL("./assets/images/ship.png")));

        spaceshipProjectile = new ImagePattern(new Image(fileURL("./assets/images/shipProjectile.png")));

        alienProjectile = new ImagePattern(new Image(fileURL("./assets/images/alienProjectile.png")));

        enemyImage1 = new ImagePattern(new Image(fileURL("./assets/images/enemy1.png")));

        enemyImage2 = new ImagePattern(new Image(fileURL("./assets/images/enemy2.png")));

        enemyImage3 = new ImagePattern(new Image(fileURL("./assets/images/enemy3.png")));

        enemyImage4 = new ImagePattern(new Image(fileURL("./assets/images/enemy4.png")));

        shield1 = new ImagePattern(new Image(fileURL("./assets/images/shieldState1.png")));

        shield2 = new ImagePattern(new Image(fileURL("./assets/images/shieldState2.png")));

        shield3 = new ImagePattern(new Image(fileURL("./assets/images/shieldState3.png")));

        shield4 = new ImagePattern(new Image(fileURL("./assets/images/shieldState4.png")));

        shield5 = new ImagePattern(new Image(fileURL("./assets/images/shieldState5.png")));
    }

    static public ImagePattern getSpaceshipProjectile()
    {
        return spaceshipProjectile;
    }

    static public ImagePattern getAlienProjectile()
{
    return alienProjectile;
}

    static public Background getBackgroundImage()
    {
        return backgroundImage;
    }

    static public Media getBackgroundMusic()
    {
        return backgroundMusic;
    }

    static public AudioClip getShootingSound()
    {
        return shootingSound;
    }

    static public AudioClip getPlayerBlowUpSound()
    {
        return playerBlowUpSound;
    }

    static public AudioClip getAlienBlowUpSound(){
        return alienBlowUpSound;
    }

    static public AudioClip getAlienShotSound(){
        return alienShotSound;
    }

    static public ImagePattern getSpaceshipImage()
    {
        return spaceshipImg;
    }

    static public ImagePattern getEnemyImage1()
    {
        return enemyImage1;
    }

    static public ImagePattern getEnemyImage2()
    {
        return enemyImage2;
    }

    static public ImagePattern getEnemyImage3()
    {
        return enemyImage3;
    }

    static public ImagePattern getEnemyImage4()
    {
        return enemyImage4;
    }

    static public ImagePattern getShield1Image() {
        return shield1;
    }

    static public ImagePattern getShield2Image() {
        return shield2;
    }

    static public ImagePattern getShield3Image() {
        return shield3;
    }

    static public ImagePattern getShield4Image() {
        return shield4;
    }

    static public ImagePattern getShield5Image() {
        return shield5;
    }
}

