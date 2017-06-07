import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * Created by Smady91 on 17.05.2017.
 */
public class Ball {
    Image ballImage = new Image(Main.class.getResourceAsStream("ball.png"));
    ImageView ball = new ImageView(ballImage);
    Random random = new Random();
    double Height = 48;
    double Width = 50;
    double stepX = 1;
    double stepY = -1;
    double speed = 5;

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

    public double getX() {
        return ball.getX();
    }

    public void setX(double x) {
       ball.setX(x);
    }

    public double getY() {
        return ball.getY();
    }

    public void setY(double y) {
        ball.setY(y);
    }

    public double getStepX() {
        return stepX;
    }

    public void setStepX(double stepX) {
        this.stepX = stepX;
    }

    public double getStepY() {
        return stepY;
    }

    public void setStepY(double stepY) {
        this.stepY = stepY;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


}
