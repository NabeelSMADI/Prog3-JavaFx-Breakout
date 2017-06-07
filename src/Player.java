import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Smady91 on 17.05.2017.
 */
public class Player {
    Image playerImage = new Image(Main.class.getResourceAsStream("Player.png"));
    ImageView player = new ImageView(playerImage);
    double Height = 25;
    double Width = 100;
    double speed = 10;

    public double getX() {
        return player.getX();
    }

    public void setX(double x) {
        player.setX(x);
    }

    public double getY() {
        return player.getY();
    }

    public void setY(double y) {
        player.setY(y);
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public double getWidth() {
        return Width;
    }

    public void setWidth(double width) {
        Width = width;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
