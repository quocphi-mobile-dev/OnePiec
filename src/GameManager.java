import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    // dùng để quản lý đống kia : boss + player + bullder + coin
    Player player;
    ArrayList<Boss> arrBoss;
     ArrayList<Bullet> arrBulletPlayer;
     Backgroud backgroud = new Backgroud();


    void initGame(){
        player = new Player(20,0);
        arrBulletPlayer = new ArrayList<>();


    }
    // quản lý vẽ đống kia :
    //1. tạo player tryền các phương thức vẽ của nó vào + ts tryền vào
    void draw (Graphics2D g2d){
        // backgroud phải vẽ đầu tiên
        backgroud.draw(g2d);
        // vì là 1 mảng nên phải truyền vào từng phần tử
        for (Bullet b : arrBulletPlayer){
            b.draw(g2d);
        }
        player.draw(g2d);


    }
    void AI(){
        player.move();
        // duyệt ngược  => xóa phần tử là oke nhất
        for (int i  =arrBulletPlayer.size()-1;i >=0;i--){
            boolean check = arrBulletPlayer.get(i).move();
            if (check == true){
            arrBulletPlayer.remove(i);
            }

        }

    }
}
