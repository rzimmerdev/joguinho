package de.gurkenlabs.litiengine.pathfinding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.gurkenlabs.litiengine.pathfinding.astar.AStarGrid;
import de.gurkenlabs.litiengine.pathfinding.astar.AStarNode;

public class AStarTests {

  @Test
  public void testCostCalculationNode() {
    AStarNode node = new AStarNode(false, new Rectangle(50, 50, 10, 10), 5, 5);
    AStarNode nodeDown = new AStarNode(false, new Rectangle(50, 100, 10, 10), 5, 10);
    AStarNode nodeUp = new AStarNode(false, new Rectangle(50, 40, 10, 10), 5, 4);
    AStarNode nodeLeft = new AStarNode(false, new Rectangle(30, 50, 10, 10), 3, 5);
    AStarNode nodeRight = new AStarNode(false, new Rectangle(60, 50, 10, 10), 6, 5);

    AStarNode nodeDownLeft = new AStarNode(false, new Rectangle(40, 60, 10, 10), 4, 6);
    AStarNode nodeDownRight = new AStarNode(false, new Rectangle(60, 60, 10, 10), 6, 6);
    AStarNode nodeUpLeft = new AStarNode(false, new Rectangle(40, 40, 10, 10), 4, 4);
    AStarNode nodeUpRight = new AStarNode(false, new Rectangle(60, 40, 10, 10), 6, 4);

    double costDown = node.getCosts(nodeDown);
    double costUp = node.getCosts(nodeUp);
    double costLeft = node.getCosts(nodeLeft);
    double costRight = node.getCosts(nodeRight);

    double costDownLeft = node.getCosts(nodeDownLeft);
    double costDownRight = node.getCosts(nodeDownRight);
    double costUpLeft = node.getCosts(nodeUpLeft);
    double costUpRight = node.getCosts(nodeUpRight);

    assertEquals(5.0, costDown);
    assertEquals(1.0, costUp);
    assertEquals(2.0, costLeft);
    assertEquals(1.0, costRight);

    assertTrue(costDownLeft > 1);
    assertTrue(costDownRight > 1);
    assertTrue(costUpLeft > 1);
    assertTrue(costUpRight > 1);
  }

  @Test
  public void testGridPopulation() {
    AStarGrid grid = new AStarGrid(320, 240, 4);

    AStarNode node = grid.getNode(new Point2D.Double(10, 10));
    AStarNode nullNode = grid.getNode(Integer.MAX_VALUE, Integer.MAX_VALUE);

    List<AStarNode> nodes = grid.getIntersectedNodes(new Rectangle(2, 2, 12, 6));

    assertNotNull(grid.getGrid());
    assertNotNull(grid.getGrid()[0][0]);

    assertEquals(2, node.getGridX());
    assertEquals(2, node.getGridY());
    assertEquals(12, nodes.size());
    assertNull(nullNode);
  }

  @Test
  public void testGetNeighbors() {
    AStarGrid grid = new AStarGrid(320, 240, 4);

    AStarNode node = grid.getNode(new Point2D.Double(10, 10));
    List<AStarNode> neighbors = grid.getNeighbors(node);

    grid.setAllowDiagonalMovement(false);
    List<AStarNode> neighbors2 = grid.getNeighbors(node);

    assertEquals(8, neighbors.size());
    assertEquals(4, neighbors2.size());
  }
}
