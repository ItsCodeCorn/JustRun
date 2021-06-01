package rungame.game.factories;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import rungame.framework.resources.Sprite;

public class SpriteFactory {
    public static Sprite getSprite(String filename) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("res/sprites/" + filename + ".png"));
        } catch (IOException e) {
            System.out.println("[錯誤][SpriteFactory] 找不到圖片路徑。確定是否是在RunGame資料夾下執行。");
            System.exit(0);
        }

        Sprite sprite = new Sprite(image);
        return sprite;
    }
}
