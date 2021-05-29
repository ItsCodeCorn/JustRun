package rungame.framework.resources;

import java.util.ArrayList;

import rungame.game.factories.SpriteFactory;

public class Resources {
    public static final int WALL = 0;
    public static final int PLAYER = 1;
    public static final int MONSTER = 2;

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

            default:
                break;
        }
    }

    public static Sprite getSprite(int texture) {
        return TEXTURES.get(texture);
    }
}
