package rungame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameLoop implements ActionListener {
    private Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        game.getEntities().forEach(entity -> entity.action());

        game.checkGameOver();

        game.summonMonster();

        RunGame.getGamePanel().render();
    }
}
