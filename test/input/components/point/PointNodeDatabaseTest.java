package input.components.point;

/**
 * Creates a test for the database for the PointNodes.
 *
 * <p>Bugs: none known
 *
 * @author Sam Luck-Leonard, Mason Taylor, and Josh Berger
 * @date 1/27/2023
 */
//package input.components.point;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class PointNodeDatabaseTest {

	public PointNodeDatabase build() {

		PointNodeDatabase db = new PointNodeDatabase();

		PointNode a = new PointNode("A", 1, 2);

		PointNode b = new PointNode("B", 3, 4);

		db.put(a);

		db.put(b);

		return db;

	}
	@Test
	void testPointNodeDatabase() {
		PointNodeDatabase _database = new PointNodeDatabase();
		assertNotNull(_database);
		//verifying the database to be initialized
		assertTrue(_database._points.isEmpty());
		//making sure the points are initialized as empty
	}

	@Test
	void testPointNodeDatabase2() {
		PointNode point1 = new PointNode("A", 1, 2);
		PointNode point2 = new PointNode("B", 3, 4);
		List<PointNode> points = new ArrayList<PointNode>();
		points.add(point1);
		points.add(point2);
		//creating a base set of points to work with throughout the tests

		PointNodeDatabase _database = new PointNodeDatabase(points);
		assertNotNull(_database);
		assertEquals(points.size(), _database._points.size());
		//ensuring that the points variable is the same size for both points and _points
		assertTrue(_database._points.containsAll(points));
		//further assurance with checking that they are the same list of points
	}

	@Test
	void testPutNull() {

		PointNodeDatabase db = build();

		db.put(null);

		assertEquals(2, db.size());

	}

	@Test
	void testPutAlreadyInThere() {

		PointNodeDatabase db = build();

		PointNode a = new PointNode("A", 1, 2);

		db.put(a);

		assertEquals(2, db.size());

	}

	@Test
	void testPutStress() {

		PointNodeDatabase db = build();

		PointNode c = new PointNode("C", 4, 4);

		PointNode d = new PointNode("D", 0, 0);

		PointNode e = new PointNode("E", 6, 0);

		PointNode x = new PointNode("X", 3, 3);

		db.put(c);

		assertEquals(3, db.size());

		db.put(d);

		assertEquals(4, db.size());

		db.put(e);

		assertEquals(5, db.size());

		db.put(x);

		assertEquals(6, db.size());

	}

	@Test
	void testPutSimple() {
		PointNodeDatabase _database = new PointNodeDatabase();
		PointNode point1 = new PointNode("A", 1, 2);
		_database.put(point1);
		assertTrue(_database._points.contains(point1));
		//making sure that put() successfully adds a point
	}

	@Test
	void testContainsPointNodeSimple() {
		PointNode point1 = new PointNode("A", 1, 2);
		PointNode point2 = new PointNode("B", 3, 4);
		List<PointNode> points = new ArrayList<PointNode>();
		points.add(point1);
		points.add(point2);
		PointNodeDatabase _database = new PointNodeDatabase(points);
		assertTrue(_database.contains(point1));
		//ensuring that the database will return true if the point is inside of it
		assertFalse(_database.contains(new PointNode("C", 5, 6)));
		//ensuring that the database will return false if the point is not inside of it
	}

	@Test
	void testContainsXY() {
		PointNode point1 = new PointNode("A", 1, 2);
		PointNode point2 = new PointNode("B", 3, 4);
		List<PointNode> points = new ArrayList<PointNode>();
		points.add(point1);
		points.add(point2);
		PointNodeDatabase _database = new PointNodeDatabase(points);
		assertTrue(_database.contains(1, 2));
		//ensuring the database will return true if the point exists with those parameters
		assertFalse(_database.contains(5, 6));
		//ensuring the database will return false if the point does not exist with those parameters
	}

	@Test
	void testGetPointNode() {
		PointNode point1 = new PointNode("A", Math.sqrt(2), 2);
		PointNode point2 = new PointNode("B", 3, 4);
		List<PointNode> points = new ArrayList<PointNode>();
		points.add(point1);
		points.add(point2);
		PointNodeDatabase _database = new PointNodeDatabase(points);
		assertEquals(point1, _database.getPoint(Math.sqrt(2), 2));
		//ensuring that the correct point is returned when searching by x and y coordinates
		assertNull(_database.getPoint(5, 6));
		//ensuring that a null value is returned if the point does not exist in the database
	}

	@Test
	void testGetNamePointNode() {
		PointNode point1 = new PointNode("A", 1, 2);
		PointNode point2 = new PointNode("B", 3, 4);
		List<PointNode> points = new ArrayList<PointNode>();
		points.add(point1);
		points.add(point2);
		PointNodeDatabase _database = new PointNodeDatabase(points);
		assertEquals("A", _database.getName(point1));
		//verifying that the correct name is returned
		assertNotEquals("B", _database.getName(point1));
		//verifying that the incorrect name is not returned
	}

	@Test
	void testGetNameXY() {
		PointNode point1 = new PointNode("A", Math.sqrt(2), 2);
		PointNode point2 = new PointNode("B", 3, 4);
		List<PointNode> points = new ArrayList<PointNode>();
		points.add(point1);
		points.add(point2);
		PointNodeDatabase _database = new PointNodeDatabase(points);
		assertEquals("A", _database.getName(Math.sqrt(2), 2));
		//verifying that the correct name is returned
		assertNotEquals("B", _database.getName(1, 2));
		//verifying that the incorrect name is not returned
	}

	@Test
	void testGetPointXY() {
		PointNode point1 = new PointNode("A", Math.sqrt(2), 2);
		PointNode point2 = new PointNode("B", 3, 4);
		List<PointNode> points = new ArrayList<PointNode>();
		points.add(point1);
		points.add(point2);
		PointNodeDatabase _database = new PointNodeDatabase(points);
		assertEquals(point1, _database.getPoint(Math.sqrt(2), 2));
		//verifying that the correct point is returned
		assertNotEquals(point2, _database.getPoint(1, 2));
		//verifying that the incorrect point is not returned
	}

	@Test
	void testGetPointName() {

		PointNodeDatabase pnd = build();

		PointNode a = new PointNode("A", 1, 2);

		PointNode b = new PointNode("B", 3, 4);
		
		assertTrue(a.equals(pnd.getPoint("A")));
		
		assertTrue(b.equals(pnd.getPoint("B")));
		
		assertEquals(null, pnd.getPoint("G"));
	}
}
