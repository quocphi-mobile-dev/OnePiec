import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// vẽ giao diện ?
public class MyPanel extends JPanel {
    // khai báo quản lý ra đây
    GameManager manager = new GameManager();
    // tạo cờ => xử lý sự kiện
    boolean[] flag = new boolean[256];


    public MyPanel() {
        // lấy player ra
        manager.initGame();
        // xác nhân theo đúng chỗ
        setFocusable(true);
        // cho nó chạy theo ý
        addKeyListener(key);

        Thread t = new Thread(runnable);
        // tạo một luồng mới để thực hiện cái di chuyển
        t.start();
    }

    // lắng nghe sự kiện từ bàn phím
    KeyListener key = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // mã key   = > đóng cờ + mở cờ
            flag[e.getKeyCode()] = true;

        }

        @Override
        public void keyReleased(KeyEvent e) {
            // đóng cở  + mở cờ
            flag[e.getKeyCode()] = false;

        }
    };

    //
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Thực hiện di chuyển
            while (true) {
                if (flag[KeyEvent.VK_UP] == true) {
                    manager.player.orient = Player.UP;

                } else {
                    manager.player.orient = Player.DOW;
                }

                if (flag[KeyEvent.VK_SPACE] == true) {
                    manager.player.fire(manager.arrBulletPlayer);
                }

                manager.AI();
                // để cập nhật lại giao diện
                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    // phương thức dùng để vẽ
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        // g2D  ? why ? = >vễ đẹp hơn => các thay đổi của nét vẽ từ trên xuống :
        super.paintComponent(g2D);
        manager.draw(g2D);

    }
}
