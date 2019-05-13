import java.awt.*;
import java.util.ArrayList;

public class Boss {
    static final  int DOW =0;
    static final  int UP = 1;


    int orient = DOW;
    int x, y;
    Image[] images = {ImageLoader.getImange("Monster1_3.png"),
            ImageLoader.getImange("Monster1_4.png"),
            ImageLoader.getImange("Monster1_5.png"),
            ImageLoader.getImange("Monster1_6.png")};

    public Boss(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void draw (Graphics2D g2D){
        g2D.drawImage(images[3], x,y,null);
    }
    void move(){

        // kiểm tra
        switch (orient){
            case DOW:
                x++;
                break;
            case UP:
                x--;
                break;
        }
    }
}
