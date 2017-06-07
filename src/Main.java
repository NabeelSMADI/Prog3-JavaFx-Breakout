import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.Debug;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Smady91 on 17.05.2017.
 */
public class Main  extends Application {

    private Group group = new Group();
    final Pane pane= new Pane(group);
    Scene scene;
    private final Button bt = new Button("Restart");
    Player player = new Player();
    Ball ball = new Ball();
    boolean goLeft = false;
    boolean goRight = false;
    boolean fin =false;
    int level =1 ;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final BorderPane borderPane;
        String title = "Arkanoid - Level " + level;
        primaryStage.setTitle(title);
        //create a pane for a group with all moving objects
        pane.setPrefSize(1200,900);
       // pane.setStyle("-fx-background-color: Black;");
        Image back = new Image(Main.class.getResourceAsStream("b1.png"));

        pane.setStyle("-fx-background-image: url('" + "https://media1.s-nbcnews.com/j/newscms/2017_05/1885986/ss-170131-misp-mn-01_72bfd4eb284b03e8248423ba19032856.nbcnews-ux-1024-900.jpg" + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-size: cover;");

        //create a restart button
        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
           getDefault();
            }
        });
        //create the main window lauout
        borderPane = new BorderPane();
        borderPane.setTop(bt);
        borderPane.setCenter(pane);
        scene = new Scene(borderPane, 1200, 900, Color.ANTIQUEWHITE);

        group.getChildren().add(player.player);
        player.setX((scene.getWidth()/2)-player.getWidth());
        player.setY((scene.getHeight()-player.getWidth()));
        group.getChildren().add(ball.ball);
        ball.setX(player.getX()+(player.getWidth()/2)-(ball.getWidth()/2));
        ball.setY(player.getY()-ball.getHeight()-1);

        Block.createList(6,7);
        for (Block b:Block.blockList) {
            group.getChildren().add(b.block);
        }

        primaryStage.setScene(scene);
        //set pane autoresisable
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                System.out.println("Width: " + newSceneWidth);
                pane.setPrefWidth(scene.getWidth());
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                System.out.println("Height: " + newSceneHeight);
                pane.setPrefHeight(scene.getHeight());
            }
        });

        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) { //create one more moving circle


            }
        });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) { //create one more moving circle
                if(e.getCode() == KeyCode.LEFT ){
                    goLeft = true;
                }
                if(e.getCode() == KeyCode.RIGHT ){
                    goRight = true;
                }
            }
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) { //create one more moving circle
                if(e.getCode() == KeyCode.LEFT ){
                    goLeft = false;
                }
                if(e.getCode() == KeyCode.RIGHT ){
                    goRight = false;
                }
            }
        });


        primaryStage.show();




        new AnimationTimer() { //animate all circles
            @Override
            public void handle(long now) {
            if(goLeft && player.getX() > 0  && !fin){
                player.setX(player.getX() - player.speed);
               }
            if(goRight && player.getX() < scene.getWidth() - player.getWidth()*2  && !fin){
                player.setX(player.getX() + player.speed);
             }

                checkBorders();
                checkPlayer();
                checkBlocks();

                if(!fin){
                    ball.setX(ball.getX() + ball.getStepX() * ball.getSpeed());
                    ball.setY(ball.getY() + ball.getStepY() * ball.getSpeed());
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }.start();





    }



    public void checkBorders() {
        final Bounds bounds = pane.getBoundsInLocal();
        boolean atRightBorder = ball.getX() >= (scene.getWidth() - ball.getWidth());
        boolean  atLeftBorder = ball.getX() <= (0);
        boolean  atBottomBorder = ball.getY() >= (scene.getHeight() - bt.getHeight()  - ball.getWidth());
        boolean  atTopBorder = ball.getY() <= (- bt.getHeight() +  (ball.getWidth()/2));

        if (atLeftBorder) {
            ball.setStepX(1);
        }
        if (atRightBorder) {
            ball.setStepX(-1);
        }
        if ( atTopBorder) {
            ball.setStepY(ball.getStepY()*-1);
        }
        if (atBottomBorder ) {
            ball.setStepX(0);
            ball.setStepY(0);
            if(!fin){
                finish(false);
                fin = true;
                goRight=false;
                goLeft=false;
            }

        }

    }


    public void checkPlayer() {

        boolean atLeftBorder = ball.getX() >= (player.getX()-ball.getWidth()+10);
        boolean  atRightBorder = ball.getX() <= (player.getX()+player.getWidth()+ball.getWidth()-10);
        boolean  atBottomBorder = ball.getY() >= (player.getY()- ball.getHeight());

        if (atRightBorder && atLeftBorder && atBottomBorder ) {
            ball.setStepY(-1);
            if(goLeft){
                ball.setStepX(-1);
            }
            if(goRight){
                ball.setStepX(1);
            }
        }

        boolean atLeftBorderleft = ball.getX() >= player.getX()-ball.getWidth() && ball.getX() <= (player.getX()-ball.getWidth()+10);
        boolean  atBottomBorderleft = ball.getY() >= (player.getY()- ball.getHeight()+3);
        if (atLeftBorderleft && atBottomBorderleft) {
            ball.setStepY(-1);
            ball.setStepX(-1);
        }

        boolean  atRightBorderRight = ball.getX() <= player.getX()+player.getWidth()+ball.getWidth()+5 && ball.getX() >= player.getX()+player.getWidth()+ball.getWidth()-10;
        boolean  atBottomBorderRight = ball.getY() >= (player.getY()- ball.getHeight()+3);
        if (atRightBorderRight && atBottomBorderRight) {
            ball.setStepY(-1);
            ball.setStepX(1);
        }
    }

    public void checkBlocks() {
        for (Block b:Block.blockList) {
            boolean  blockDown = ball.getX() <= b.getX() + Block.getWidth() && ball.getX() >= b.getX() -ball.getWidth() && ball.getY() <= b.getY() +Block.getHeight() +3&& ball.getY() >= b.getY() +Block.getHeight()-3 ;
            boolean  blockUP =   ball.getX() <= b.getX() + Block.getWidth() && ball.getX() >= b.getX() -ball.getWidth() && ball.getY() <= b.getY() -ball.getHeight() + 3 && ball.getY() >= b.getY() -ball.getHeight() -3 ;
            boolean  blockLeft = ball.getY() <= b.getY() + Block.getHeight() && ball.getY() >= b.getY() - ball.getHeight() && ball.getX() <= b.getX() - ball.getWidth() +3 && ball.getX() >= b.getX() - ball.getWidth() -3;
            boolean  blockRight= ball.getY() <= b.getY() + Block.getHeight() && ball.getY() >= b.getY() - ball.getHeight() && ball.getX() <= b.getX() + Block.getWidth() +3&& ball.getX() >= b.getX() + Block.getWidth() -3  ;
            if ( blockUP ) {
                if(!b.isDel()){
                    b.setDel(true);
                    group.getChildren().remove(b.block);
                    ball.setStepY(-1);
                 if(checkWin()){
                     if(!fin){
                         finish(true);
                         fin = true;
                     }
                 }
                }

            }

            if ( blockLeft) {
                if(!b.isDel()){
                    b.setDel(true);
                    group.getChildren().remove(b.block);
                    ball.setStepX(-1);
                    if(checkWin()){
                        if(!fin){
                            finish(true);
                            fin = true;
                        }
                    }
                }

            }
            if (  blockRight) {
                if(!b.isDel()){
                    b.setDel(true);
                    group.getChildren().remove(b.block);
                    ball.setStepX(1);
                    if(checkWin()){
                        if(!fin){
                            finish(true);
                            fin = true;
                        }
                    }
                }

            }
            if (blockDown  ) {
                if(!b.isDel()){
                    b.setDel(true);
                    group.getChildren().remove(b.block);
                    ball.setStepY(1);
                    if(checkWin()){
                        if(!fin){
                            finish(true);
                            fin = true;
                        }
                    }
                }

            }
        }
    }

    public boolean checkWin() {
        for (Block b:Block.blockList) {
          if(!b.isDel())return false;
            }
      return true;

    }

    public  void getDefault () {
        for (Block b:Block.blockList) {
            group.getChildren().remove(b.block);
        }
        Block.getDefault(6,7);
        for (Block b:Block.blockList) {
            group.getChildren().add(b.block);
        }
        player.setX((scene.getWidth()/2)-player.getWidth());
        player.setY((scene.getHeight()-player.getWidth()));
        ball.setX(player.getX()+(player.getWidth()/2)-(ball.getWidth()/2));
        ball.setY(player.getY()-ball.getHeight()-1);
        ball.setStepX(1);
        ball.setStepY(-1);
    }

    public void finish(boolean win){
        Stage finishStage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,400,100);
        HBox Label = new HBox(15);
        Label.setAlignment(Pos.BOTTOM_CENTER);
        String labels ;
        if(win){
            labels = "You Win";
        }else{
            labels = "Game Over";
        }
        Label label = new Label(labels);
        final double MAX_FONT_SIZE = 30.0; // define max font size you need
        label.setFont(new Font(MAX_FONT_SIZE)); // set to Label
        Label.getChildren().add(label);
        root.setCenter(Label);
        HBox bBox = new HBox(20);
        String restartText;
        if(win){
            restartText = "Next Level";
        }else{
            restartText = "Restart";
        }
        Button restart = new Button(restartText);
        restart.setAlignment(Pos.CENTER_LEFT);
        restart.setOnAction(new EventHandler<ActionEvent>() {
                               @Override
                               public void handle(ActionEvent e) {
                                   fin = false;
                                   finishStage.close();
                                   getDefault();
                                  if(win){
                                      ball.speed += 1;
                                      level++;
                                    }else{
                                      ball.speed = 5;
                                      level = 1;
                                  }

                               }
                           }
        );

        Button close = new Button("Close");
        restart.setAlignment(Pos.CENTER_RIGHT);
        close.setOnAction(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent e) {
                                  Platform.exit();

                              }
                          }
        );
        bBox.getChildren().add(restart);
        bBox.getChildren().add(close);
        root.setBottom(bBox);
        finishStage.setScene(scene);
        finishStage.show();
    };
}
