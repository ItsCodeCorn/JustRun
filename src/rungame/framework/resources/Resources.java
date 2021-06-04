package rungame.framework.resources;

import java.util.ArrayList;

import rungame.game.factories.SpriteFactory;

public class Resources {
    public static final int WALL = 0;
    public static final int PLAYER = 1;
    public static final int MONSTER = 2;
    public static final int SPEED_UP_PLAYER_ITEM = 3;
    public static final int SPEED_DOWN_MONSTER_ITEM = 4;
    public static final int ELIMINATE_MONSTERS_ITEM = 5;
    public static final int SCARE_MONSTERS_ITEM = 6;
    public static final int GAME_OVER = 7;

    private static final ArrayList<Sprite> TEXTURES = new ArrayList<>();

    public static void load(int texture) {
        switch (texture) {
            case WALL:
                TEXTURES.add(SpriteFactory.getSprite("Wall"));
                break;

            case PLAYER:
                TEXTURES.add(SpriteFactory.getSprite("Player"));
                break;

            case MONSTER:
                TEXTURES.add(SpriteFactory.getSprite("Monster"));
                break;

            case SPEED_UP_PLAYER_ITEM:
                TEXTURES.add(SpriteFactory.getSprite("SpeedUpPlayerItem"));
                break;

            case SPEED_DOWN_MONSTER_ITEM:
                TEXTURES.add(SpriteFactory.getSprite("SpeedDownMonsterItem"));
                break;

            case ELIMINATE_MONSTERS_ITEM:
                TEXTURES.add(SpriteFactory.getSprite("EliminateMonstersItem"));
                break;

            case SCARE_MONSTERS_ITEM:
                TEXTURES.add(SpriteFactory.getSprite("ScareMonstersItem"));
                break;

            case GAME_OVER:
                TEXTURES.add(SpriteFactory.getSprite("GameOver"));
                break;
            default:
                break;
        }
    }

    public static Sprite getSprite(int texture) {
        try {
            return TEXTURES.get(texture);
        } catch (Exception e) {
            System.out.println("[錯誤][Resources] 無法取得Sprite. 檢查是否有將其加入.");
            System.exit(0);
        }

        return null;
    }
}
