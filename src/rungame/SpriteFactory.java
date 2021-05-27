package rungame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteFactory {
    public static Sprite getSprite(String type) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("res/" + type + ".png"));
        } catch (IOException e) {
            System.out.println(e);
        }

        Sprite sprite = new Sprite(image);
        return sprite;
    }
}
