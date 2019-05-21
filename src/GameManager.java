import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    // dùng để quản lý đống kia : boss + player + bullder + coin
    Player player;
    ArrayList<Boss> arrBoss;
    ArrayList<Bullet> arrBulletPlayer;
    ArrayList<Boss1> arrBoss1;
    ArrayList<Bullet> arrBulletBoss1;
    Backgroud backgroud = new Backgroud();




    void initGame() {
        player = new Player(20, 0);
        // khởi tao mảng chứa
        arrBulletPlayer = new ArrayList<>();
        arrBulletBoss1 = new ArrayList<>();
        arrBoss1 = new ArrayList<>();



    }

    // quản lý vẽ đống kia :
    //1. tạo player tryền các phương thức vẽ của nó vào + ts tryền vào
    void draw(Graphics2D g2d) {
        // backgroud phải vẽ đầu tiên
        backgroud.draw(g2d);
        // vì là 1 mảng nên phải truyền vào từng phần tử
        for (Bullet b : arrBulletPlayer) {
            b.draw(g2d);
        }

        for (Bullet b : arrBulletBoss1) {
            b.draw(g2d);
        }
        player.draw(g2d);

        for (Boss1 c : arrBoss1) {
            c.draw(g2d);
        }


    }

    int time = 0;

    void initBoss1() {
        time++;
        if (time > 50) {
            arrBoss1.add(new Boss1());
            time = 0;
        }

    }

    void bulletMove(ArrayList<Bullet> arr) {
        // duyệt ngược  => xóa phần tử là oke nhất
        for (int i = arr.size() - 1; i >= 0; i--) {
            boolean check = arr.get(i).move();
            if (check == true) {
                arr.remove(i);
            }
        }
    }

    // xong rồi kiểm tra trong ai
    void explosionBullet() {
        for (int i = arrBulletPlayer.size() - 1; i >= 0; i--) {

            for (int j = 0; j < arrBulletBoss1.size(); j++) {
                Rectangle rectangle = arrBulletPlayer.get(i).getRect()
                        .intersection(arrBulletBoss1.get(j).getRect());
                if (rectangle.isEmpty() == false) {
                    SoundManager.play("BulletImpact.wav");
                    arrBulletPlayer.remove(i);
                    arrBulletBoss1.remove(j);
                    break;
                }
            }
        }

    }


    void AI() {
        initBoss1();

        player.move();
        bulletMove(arrBulletPlayer);
        bulletMove(arrBulletBoss1);
        explosionBullet();

        // chạy từ - 1 đến  >=0
        for (int i = arrBoss1.size() - 1; i >= 0; i--) {
            // trước khi dy chuyển thì cho nó bắn
            arrBoss1.get(i).fire(arrBulletBoss1);
            boolean check = arrBoss1.get(i).move();
            if (check == true) {
                arrBoss1.remove(i);
            } else {
                for (int j = 0; j < arrBulletPlayer.size(); j++) {
                    Rectangle rectangle = arrBoss1.get(i).getRect()
                            .intersection(arrBulletPlayer.get(j).getRect());
                    if (rectangle.isEmpty() == false) {
                        SoundManager.play("MonsterImpact.wav");

                        arrBulletPlayer.remove(j);
                        arrBoss1.remove(i);
                        break;
                    }
                }
            }
        }

        // arrBoss1.add(new Boss1());
        // rồi sinh boss = > theo thời gian, theo số lượng còn lại thì ms sinh
        boolean isDie = player.checkDie(arrBulletBoss1, arrBoss1);
        if (isDie == true) {
            // hiện ra thông báo có muốn chơi tiếp hay k
            int resuls = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to replay ?",
                    "Game over",
                    JOptionPane.YES_NO_OPTION
            );
            if (resuls == JOptionPane.YES_OPTION) {
                initGame();
            } else {
                System.exit(0);
            }
        }

    }
}
