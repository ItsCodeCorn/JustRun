package rungame.game.entities.items;

import java.awt.Rectangle;

import rungame.game.entities.StaticEntity;

public abstract class EffectItem extends StaticEntity {
    private int effectId;

    public EffectItem(int texture, char sign, Rectangle bounds, int effectId) {
        super(texture, sign, bounds);

        this.effectId = effectId;
    }

    public int getEffectId() {
        return this.effectId;
    }
}
