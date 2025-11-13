package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.Clyde;
import nl.tudelft.jpacman.sprite.AnimatedSprite;
import nl.tudelft.jpacman.sprite.Sprite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static java.util.Collections.emptyMap;
import static org.mockito.Mockito.mock;


public class PlayerCollisionTest {

    @Test
    public void testPlayerCollidesWithGhost() {
        AnimatedSprite deathSprite = mock(AnimatedSprite.class);
        Player player = new Player(emptyMap() , deathSprite);
        Ghost ghost = mock(Ghost.class);

        PlayerCollisions collisions = new PlayerCollisions();
        collisions.collide(player, ghost);

        assertFalse(player.isAlive());
    }

    @Test
    public void testGhostCollidesWithPlayer() {
        AnimatedSprite deathSprite = mock(AnimatedSprite.class);
        Player player = new Player(emptyMap() , deathSprite);
        Ghost ghost = mock(Ghost.class);

        PlayerCollisions collisions = new PlayerCollisions();
        collisions.collide(ghost, player);

        assertFalse(player.isAlive());
    }

    @Test
    public void testPlayerCollidesWithPellet() {
        AnimatedSprite deathSprite = mock(AnimatedSprite.class);
        Player player = new Player(emptyMap() , deathSprite);
        Sprite pelletsprite = mock(Sprite.class);
        Pellet pellet = new Pellet(10, pelletsprite);
        int score = player.getScore();
        PlayerCollisions collisions = new PlayerCollisions();
        collisions.collide(player, pellet);

        assert(score + 10 == player.getScore());
    }

    @Test
    public void testGhostCollidesWithPellets() {
        Ghost clyde = new Clyde(emptyMap());
        Sprite pelletsprite = mock(Sprite.class);
        Pellet pellet = new Pellet(10, pelletsprite);

        PlayerCollisions collisions = new PlayerCollisions();
        collisions.collide(pellet, clyde);


    }
}
