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



public class ClydeTest {


        PacManSprites sprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(sprites);
        LevelFactory levelFactory = new LevelFactory(sprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(sprites);
        private GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);


    @Test
    /*-T7: path_free => away from pacman*/
    void Free_and_equal_to_8_distance() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList("############", "##P       C#", "############"));

        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        Optional<Direction> direction = clyde.nextAiMove();

        assertNotNull(direction);

        assertThat(direction.get()).isEqualTo(Direction.EAST);

    }

    @Test
    void Free_and_greater_than_8_distance() {
        Level level = ghostMapParser.parseMap((Lists.newArrayList("############", "#P         C", "############")));

        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        Optional<Direction> direction = clyde.nextAiMove();

        assertNotNull(direction);

        assertThat(direction.get()).isEqualTo(Direction.WEST);
    }

    @Test
    void Blocked_and_greater_than_8_distance() {
        Level level = ghostMapParser.parseMap((Lists.newArrayList(
            "############",
            "#P    #    C",
            "############")));

        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        Optional<Direction> direction = clyde.nextAiMove();
        assertThat(direction).isEmpty();
    }

    @Test
    void Multiple_paths_and_greater_than_8_distance() {
        Level level = ghostMapParser.parseMap((Lists.newArrayList(
            "############",
            "#P        C#",
            "# ######## #",
            "# ######## #",
            "#          #",
            "############")));

        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        Optional<Direction> direction = clyde.nextAiMove();

        assertNotNull(direction);

        assertThat(direction.get()).isEqualTo(Direction.WEST);
    }
}

