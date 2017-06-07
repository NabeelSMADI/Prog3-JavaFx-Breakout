import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smady91 on 17.05.2017.
 */
public class Block {
    public static List<Block> blockList = new ArrayList<Block>(30);
    public static int offsetX = 50;
    public static int offsetY = 50;
    public static int spece = 10;

    Image b1 = new Image(Main.class.getResourceAsStream("b1.png"));
    Image b2 = new Image(Main.class.getResourceAsStream("b2.png"));
    Image b3 = new Image(Main.class.getResourceAsStream("b3.png"));
    Image b4 = new Image(Main.class.getResourceAsStream("b4.png"));
    Image b5 = new Image(Main.class.getResourceAsStream("b5.png"));
    ImageView block = new ImageView(b1);
    static double Height = 50;
    static double Width = 150;
    int type ;
    boolean del =false;

   public Block (int t,Double x, Double y){
        type = t;
        block.setX(x);
        block.setY(y);
        switch (t){
            case 1 :
            block = new ImageView(b1);
            break;
            case 2 :
            block = new ImageView(b2);
            break;
            case 3 :
                block = new ImageView(b3);
                break;
            case 4 :
                block = new ImageView(b4);
                break;
            case 5 :
                block = new ImageView(b5);
                break;
        }
    }

    public static double getHeight() {
        return Height;
    }

    public static void setHeight(double height) {
        Height = height;
    }

    public static double getWidth() {
        return Width;
    }

    public static  void setWidth(double width) {
        Width = width;
    }

    public double getX() {
        return block.getX();
    }

    public void setX(double x) {
        block.setX(x);
    }

    public double getY() {
        return block.getY();
    }

    public void setY(double y) {
        block.setY(y);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public static void createList(int r,int c) {
       int t = 1;
        for (int i = 0 ; i < r ; i++){
            t++;
            if(t>3)t=1;
            for (int j = 0 ; j < c ; j++){
                Double x = offsetX +((Block.getWidth()+spece)*j);
                Double y = offsetY +((Block.getHeight()+spece)*i);
                Block b = new Block(t,x,y);
                b.block.setX(x);
                b.block.setY(y);
                blockList.add(b);
            }
        }
    }

    public static void getDefault (int r,int c) {
        blockList.clear();
        createList(r,c);
    }
}
