package rungame.framework.resources;

public class ResourcesLoader {
    public static void load() {
        Resources.load(Resources.WALL);
        Resources.load(Resources.PLAYER);
        Resources.load(Resources.MONSTER);
        Resources.load(Resources.SPEED_UP_PLAYER_ITEM);
        Resources.load(Resources.SPEED_DOWN_MONSTER_ITEM);
        Resources.load(Resources.ELIMINATE_MONSTERS_ITEM);
        Resources.load(Resources.SCARE_MONSTERS_ITEM);
    }
}
