import javax.swing.*;
import java.awt.*;

// class khung chứa giao diện
public class MyFrame extends JFrame {
    static final int W_FRAME = 1200;
    static final int H_FRAME = 700;
    //
    static final int BOUND = H_FRAME -100 ;


    public MyFrame() throws HeadlessException {
        setTitle("ONE PIECE");
        setSize(W_FRAME, H_FRAME);
        // ra giữa màn hình
        setLocationRelativeTo(null);
        // k cho thay đổi giao diện
        setResizable(false);
        //Thoát hẳn khi đóng chương trình => giải phóng tài nguyên
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set color cho cái giao diện
        // hàm main ở đây => tạo bên kia chạy bên này
        MyPanel panel = new MyPanel();
        add(panel);
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    }
}
