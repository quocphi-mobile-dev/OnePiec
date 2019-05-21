import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    static void play(String name){
        // chạy thì mở bài hát
        try {
            File file = new File("src/sound/"+ name);
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
