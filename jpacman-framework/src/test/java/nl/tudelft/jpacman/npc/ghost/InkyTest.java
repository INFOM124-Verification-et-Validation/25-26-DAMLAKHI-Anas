package nl.tudelft.jpacman.npc.ghost;

import com.google.common.collect.Lists;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class InkyTest {

    PacManSprites sprites = new PacManSprites();
    GhostFactory ghostFactory = new GhostFactory(sprites);
    LevelFactory levelFactory = new LevelFactory(sprites, ghostFactory);
    BoardFactory boardFactory = new BoardFactory(sprites);
    private GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);


    @Test
        /*-T7: path_free => away from pacman*/
    void No_blinky_and_blocked_inky() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
            "############",
            "#P       #I#",
            "############"));

        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);

        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());

        Optional<Direction> direction = inky.nextAiMove();


        assertThat(direction).isEmpty();

    }


    void blinky_pacman_straight_line() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
            "####################",
            "#    B  P         I#",
            "####################"));

        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);

        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());

        Optional<Direction> direction = inky.nextAiMove();


        assertThat(direction).isEmpty();

    }
}
