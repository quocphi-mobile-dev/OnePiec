import java.awt.*;

public class Backgroud {
    int x;
    Image image = ImageLoader.getImange("PlayBackground1.png");

    void draw (Graphics2D g2d){
        x--;
        if (x <= -MyFrame.W_FRAME ){
            x =0;
        }
        g2d.drawImage(image,x,0,
                MyFrame.W_FRAME,MyFrame.H_FRAME,null);

        g2d.drawImage(image,x +MyFrame.W_FRAME -5 ,0,
                MyFrame.W_FRAME,MyFrame.H_FRAME,null);
    }


}
