package model.board;

import org.junit.Test;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.Assert.*;


public class DirectionTest {

    @Test
    public void testGetOppositeDirection() {
        assertEquals(Direction.DOWN, Direction.getOppositeDirection(Direction.UP));
        assertEquals(Direction.UP, Direction.getOppositeDirection(Direction.DOWN));
        assertEquals(Direction.RIGHT, Direction.getOppositeDirection(Direction.LEFT));
        assertEquals(Direction.LEFT, Direction.getOppositeDirection(Direction.RIGHT));
    }

    @Test
    public void testGetDirectionExcept() {
        for (Direction original : Direction.values()) {
            Set<Direction> possibleResults = EnumSet.allOf(Direction.class);
            possibleResults.remove(original);

            Set<Direction> receivedResults = EnumSet.noneOf(Direction.class);

            for (int i = 0; i < 100; i++) {
                Direction result = Direction.getDirectionExcept(original);
                assertNotNull( "Returned direction should not be null", result);
                assertNotEquals("Returned direction should not be " + original, original, result);
                receivedResults.add(result);
            }

            assertTrue("Should return more than one unique direction for " + original, receivedResults.size() > 1);
            for (Direction received : receivedResults) {
                assertTrue( "Received direction should be a valid direction excluding " + original, possibleResults.contains(received));
            }
        }
    }
}