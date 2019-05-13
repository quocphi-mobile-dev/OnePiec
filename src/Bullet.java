import java.awt.*;

public class Bullet {
    Image image;
    int x,y;
    int orient;

    public Bullet(Image image, int x, int y, int orient) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.orient = orient;
    }
    void draw(Graphics2D g2d){
        g2d.drawImage(image,x,y,null);
    }
    // trả vê boolean vì phải xóa nó đi khi ra khỏi màn hình
    // mảng k đủ chỗ  => xóa bay ra

    boolean move (){
        // để => k cần xác đinh hướng
        x += orient;
        return x <= 0|| x>= MyFrame.W_FRAME;
    }
}
