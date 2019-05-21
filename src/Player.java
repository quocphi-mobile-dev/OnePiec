import java.awt.*;
import java.util.ArrayList;

public class Player {
    static final int DOW = 0;
    static final int UP = 1;

    int orient = DOW;
    // tạo một mảng hình ảnh vì 2 ảnh up down
    Image[] images = {
            ImageLoader.getImange("SanjiDown.png"),
            ImageLoader.getImange("SanjiUp.png")
    };
    int x;
    int y;
    int score;

    // tạo constructor  => của tọa độ
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // tạo phương thức vẽ
    void draw(Graphics2D g2D) {
        // vẽ dưới dạng 4 tham số => truyền ảnh khởi tạo vào
        g2D.drawImage(images[orient], x, y, null);

    }

    void move() {
        int yR = y;
        // kiểm tra
        switch (orient) {
            case DOW:
                y ++;
                break;
            case UP:
               // SoundManager.play("Move.wav");
                y --;
                break;
        }
        if (y <= 0 || y >= MyFrame.BOUND - images[orient].getHeight(null)) {
            y = yR;
        }
    }

    // thời gian lần bắn
    long t;
    void fire(ArrayList<Bullet> arrayList) {
        //lấy thời gian hiện tại của hệ thống
        long T = System.currentTimeMillis();
        if (T - t < 500) {
            return;
        }
        t = T;
        int xB = x + images[orient].getWidth(null);
        int yB = y + images[orient].getHeight(null) / 2;
        Bullet b = new Bullet(ImageLoader.getImange("SanjiBullet.png"), xB, yB, 1);
        // add đạn vào mảng
        arrayList.add(b);
        SoundManager.play("AceBulletSound.wav");
    }
// tạo hình chữ nhật
    Rectangle getRect() {
        Rectangle rectangle = new Rectangle(
                x+10, y+10,
                images[orient].getWidth(null)-20,
                images[orient].getHeight(null)-20
        );
        return rectangle;
    }
    // có 2 tham số
    boolean checkDie(ArrayList<Bullet> arrBulletBoss,
                     ArrayList<Boss1> arrBoss) {
        // so sánh đạn với
        for (Bullet b : arrBulletBoss) {
            // sinh ra hình chữ nhật mới  => có 2 khả năng
            Rectangle rectangle = getRect().intersection(b.getRect());
            if (rectangle.isEmpty() == false) {
                // là chết luôn
                return true;
            }
        }
        for (Boss1 c : arrBoss) {
            // sinh ra hình chữ nhật mới  => có 2 khả năng
            Rectangle rectangle = getRect().intersection(c.getRect());
            if (rectangle.isEmpty() == false) {
                // là chết
                return true;
            }
        }return false;
        // là khong chết
    }
}


