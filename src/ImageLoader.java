import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    static Image getImange(String name) {
        Image image = new ImageIcon(
                new ImageLoader()
                        .getClass()
                        .getResource("/image/" + name)).getImage();

        return image;
    }
}
