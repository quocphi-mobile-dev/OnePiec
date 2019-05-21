import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Boss1 {
    Image[] images;
    // vi trí phải random

    int x;
    int y;
    //tạo tốc độ chạy
    int speed;
    // di chuyển

    int index;

    public Boss1() {

        Random rd = new Random();
        //
        speed = rd.nextInt(3) + 1;
        int type = rd.nextInt(4);
        x = MyFrame.W_FRAME;
        if (type == 0) {
            images = new Image[]{
                    ImageLoader.getImange("Monster1_3.png"),
                    ImageLoader.getImange("Monster1_4.png"),
                    ImageLoader.getImange("Monster1_5.png"),
                    ImageLoader.getImange("Monster1_6.png")};

        } else if (type == 1) {
            images = new Image[]{
                    ImageLoader.getImange("Marine1.png"),
                    ImageLoader.getImange("Marine2.png"),
                    ImageLoader.getImange("Marine3.png"),
                    ImageLoader.getImange("Marine4.png"),

            };
        } else if (type == 2) {
            images = new Image[]{
                    ImageLoader.getImange("Monster3_1.png"),
                    ImageLoader.getImange("Monster3_2.png"),
                    ImageLoader.getImange("Monster3_3.png"),
                    ImageLoader.getImange("Monster3_4.png")
            };
        } else {
            images = new Image[]{
                    ImageLoader.getImange("Monster2_1.png"),
                    ImageLoader.getImange("Monster2_2.png"),
                    ImageLoader.getImange("Monster2_3.png"),
                    ImageLoader.getImange("Monster2_4.png"),
            };

            if (type < 2) {
                y = rd.nextInt(MyFrame.BOUND - 100 - 128);

            } else {
                y = MyFrame.BOUND - 128
                        - rd.nextInt(100);
            }
        }
    }

    void draw(Graphics2D g2d) {
        g2d.drawImage(images[index], x +30, y+30, 128-60, 128-60, null);
    }

    int cout = 0;

    boolean move() {
        // tốc độ đổi ảnh cao = > giảm
        cout++;
        if (cout > 50) {
            index++;
            if (index >= images.length) {
                index = 0;
            }
            //refresh để đếm lại
            cout = 0;
        }
        x -= speed;
        // đúng thì xóa false thì vẫn trong hiện thị => ý là phải ra hết khỏi màn hình thì ms xóa
        return x < 0 - images[index].getWidth(null);
    }

    long t;

    void fire(ArrayList<Bullet> arrayList) {
        //lấy thời gian hiện tại của hệ thống
        long T = System.currentTimeMillis();
        if (T - t < 2000) {
            return;
        }
        t = T;
        int xB = x;
        int yB = y +  64;
        // - (speed +1 ) đạn bắn đổi ngược
        Bullet b = new Bullet(ImageLoader.getImange("Monster2Bullet.png"), xB, yB, -(speed +1));
        // add đạn vào mảng
        arrayList.add(b);
    }


    Rectangle getRect() {
        Rectangle rectangle = new Rectangle(
                x, y, 128,128

        );
        return rectangle;


    }


}
