/**
 * Testing for SegmentNodeDatabase
 * 
 * <p>Bugs: none known
 * 
 * @author Mason Taylor, Sam Luck-Leonard, & Josh Berger
 * @date 1/27/2023
 */
package input.components.segment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import input.components.point.PointNode;

class SegmentNodeDatabaseTest
{
	public SegmentNodeDatabase build(){

		//      A                                 
		//     / \                                
		//    B___C                               
		//   / \ / \                              
		//  /   X   \ 
		// D_________E
		//
		//
		PointNode a = new PointNode("A", 3, 6);

		PointNode b = new PointNode("B", 2, 4);

		PointNode c = new PointNode("C", 4, 4);

		PointNode d = new PointNode("D", 0, 0);

		PointNode e = new PointNode("E", 6, 0);

		PointNode x = new PointNode("X", 3, 3);

		SegmentNodeDatabase db = new SegmentNodeDatabase();

		db.addUndirectedEdge(a, b);

		db.addUndirectedEdge(a, c);

		db.addUndirectedEdge(b, c);

		db.addUndirectedEdge(b, x);

		db.addUndirectedEdge(b, d);

		db.addUndirectedEdge(c, x);

		db.addUndirectedEdge(c, e);

		db.addUndirectedEdge(x, d);

		db.addUndirectedEdge(x, e);

		db.addUndirectedEdge(d, e);

		return db;
	}

	@Test
	void testNumUndirectedEdgesEmpty() {

		SegmentNodeDatabase empty = new SegmentNodeDatabase();

		assertEquals(0, empty.numUndirectedEdges());

	}

	@Test
	void testNumUndirectedEdges(){

		SegmentNodeDatabase db = build();

		// makes sure the size of the built database is correct

		assertEquals(10, db.numUndirectedEdges());

	}

	@Test
	void testNumUndirectedEdgesAddingAlreadyTherePoints() {

		SegmentNodeDatabase db = build();

		assertEquals(10, db.numUndirectedEdges());

		PointNode a = new PointNode("A", 3, 6);

		PointNode b = new PointNode("B", 2, 4);

		db.addUndirectedEdge(a, b);

		assertEquals(10, db.numUndirectedEdges());

	}

	@Test
	void testAddAdjacencyListEmptyList() {

		SegmentNodeDatabase db = build();

		PointNode a = new PointNode("A", 3, 6);

		db.addAdjacencyList(a, Arrays.asList());

		assertEquals(10, db.numUndirectedEdges());

	}

	@Test
	void testAddAdjacencyListNullList() {

		SegmentNodeDatabase db = build();

		PointNode a = new PointNode("A", 3, 6);

		db.addAdjacencyList(a,null);

		assertEquals(10, db.numUndirectedEdges());

	}

	@Test
	void testAddAdjacencyListNullPoint() {

		SegmentNodeDatabase db = build();

		PointNode g = new PointNode("A", 12, 6);

		db.addAdjacencyList(null, new ArrayList<PointNode>(Arrays.asList(g)));

		assertEquals(10, db.numUndirectedEdges());

	}

	@Test
	void testAddAdjacencyListSimple() {

		SegmentNodeDatabase db = build();

		PointNode a = new PointNode("A", 3, 6);

		PointNode g = new PointNode("G", 12, 6);

		db.addAdjacencyList(a, new ArrayList<PointNode>(Arrays.asList(g)));

		assertEquals(11, db.numUndirectedEdges());

	}

	@Test
	void testAdjacencyListAddingAdjacenciesAlreadyThere() {

		SegmentNodeDatabase db = build();

		PointNode a = new PointNode("A", 3, 6);

		PointNode b = new PointNode("B", 2, 4);

		PointNode c = new PointNode("C", 4, 4);

		db.addAdjacencyList(c, new ArrayList<PointNode>(Arrays.asList(b, a)));


	}

	@Test
	void testAddAdjacencyListStress() {

		SegmentNodeDatabase db = build();

		PointNode a = new PointNode("A", 3, 6);

		PointNode b = new PointNode("B", 2, 4);

		PointNode c = new PointNode("C", 4, 4);

		PointNode d = new PointNode("D", 0, 0);

		PointNode x = new PointNode("X", 3, 3);

		PointNode a1 = new PointNode("A1", 12, 6);

		PointNode b1 = new PointNode("B1", 13, 52);

		PointNode c1 = new PointNode("C1", 16, 365);

		PointNode d1 = new PointNode("D1", 767, 666);

		PointNode e1 = new PointNode("E1", 30, 177777);

		PointNode x1 = new PointNode("X1", 76, 12);

		db.addAdjacencyList(a, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1, d1, e1, x1)));

		assertEquals(16, db.numUndirectedEdges());

		db.addAdjacencyList(b, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1, d1, e1, x1)));

		assertEquals(22, db.numUndirectedEdges());

		db.addAdjacencyList(c, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1)));

		assertEquals(25, db.numUndirectedEdges());

		db.addAdjacencyList(d, new ArrayList<PointNode>(Arrays.asList(x)));

		assertEquals(25, db.numUndirectedEdges());

		db.addAdjacencyList(x, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1, d1, e1, x1)));

		assertEquals(31, db.numUndirectedEdges());

	}

	@Test
	void testAsUniqueSegmentListEmpty() {

		SegmentNodeDatabase empty = new SegmentNodeDatabase();

		assertEquals(0, empty.asUniqueSegmentList().size());

	}

	@Test
	void testAsUniqueSegmentListSimple() {

		SegmentNodeDatabase db = build();

		assertEquals(20, db.asUniqueSegmentList().size());

		PointNode a = new PointNode("A", 3, 6);

		PointNode g = new PointNode("G", 12, 6);

		db.addAdjacencyList(a, new ArrayList<PointNode>(Arrays.asList(g)));

		assertEquals(22, db.asUniqueSegmentList().size());

	}

	@Test
	void testAsUniqueSegmentListStress() {

		SegmentNodeDatabase db = build();

		PointNode a = new PointNode("A", 3, 6);

		PointNode b = new PointNode("B", 2, 4);

		PointNode c = new PointNode("C", 4, 4);

		PointNode d = new PointNode("D", 0, 0);

		PointNode x = new PointNode("X", 3, 3);

		PointNode a1 = new PointNode("A1", 12, 6);

		PointNode b1 = new PointNode("B1", 13, 52);

		PointNode c1 = new PointNode("C1", 16, 365);

		PointNode d1 = new PointNode("D1", 767, 666);

		PointNode e1 = new PointNode("E1", 30, 177777);

		PointNode x1 = new PointNode("X1", 76, 12);

		db.addAdjacencyList(a, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1, d1, e1, x1)));

		assertEquals(32, db.asUniqueSegmentList().size());

		db.addAdjacencyList(b, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1, d1, e1, x1)));

		assertEquals(44, db.asUniqueSegmentList().size());

		db.addAdjacencyList(c, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1)));

		assertEquals(50, db.asUniqueSegmentList().size());

		db.addAdjacencyList(d, new ArrayList<PointNode>(Arrays.asList(x)));

		assertEquals(50, db.asUniqueSegmentList().size());

		db.addAdjacencyList(x, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1, d1, e1, x1)));

		assertEquals(62, db.asUniqueSegmentList().size());

	}
	
	@Test
	void testAsSegmentListEmpty() {
		
		SegmentNodeDatabase empty = new SegmentNodeDatabase();
		
		assertEquals(0, empty.asSegmentList().size());
		
	}
	
	@Test
	void testAsSegmentListSimple() {
		
		SegmentNodeDatabase db = build();
		
		assertEquals(10, db.asSegmentList().size());
		
	}
	
	@Test
	void testAsSegmentListStress() {
		
		SegmentNodeDatabase db = build();

		PointNode a = new PointNode("A", 3, 6);

		PointNode b = new PointNode("B", 2, 4);

		PointNode c = new PointNode("C", 4, 4);

		PointNode d = new PointNode("D", 0, 0);

		PointNode x = new PointNode("X", 3, 3);

		PointNode a1 = new PointNode("A1", 12, 6);

		PointNode b1 = new PointNode("B1", 13, 52);

		PointNode c1 = new PointNode("C1", 16, 365);

		PointNode d1 = new PointNode("D1", 767, 666);

		PointNode e1 = new PointNode("E1", 30, 177777);

		PointNode x1 = new PointNode("X1", 76, 12);

		db.addAdjacencyList(a, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1, d1, e1, x1)));

		assertEquals(16, db.asSegmentList().size());

		db.addAdjacencyList(b, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1, d1, e1, x1)));

		assertEquals(22, db.asSegmentList().size());

		db.addAdjacencyList(c, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1)));

		assertEquals(25, db.asSegmentList().size());

		db.addAdjacencyList(d, new ArrayList<PointNode>(Arrays.asList(x)));

		assertEquals(25, db.asSegmentList().size());

		db.addAdjacencyList(x, new ArrayList<PointNode>(Arrays.asList(a1, b1, c1, d1, e1, x1)));

		assertEquals(31, db.asSegmentList().size());
		
	}

	@Test
	void testConstructor() {

		// makes sure it handles passing null into the constructor

		SegmentNodeDatabase empty = new SegmentNodeDatabase();

		assertEquals(0, empty.numUndirectedEdges());

		// makes sure it handles passing null into the constructor

		SegmentNodeDatabase dbWithNull = new SegmentNodeDatabase(null);

		assertEquals(0, dbWithNull.numUndirectedEdges());
	}
	
	@Test
	void testEquals() {
		
		SegmentNodeDatabase snd = build();
		
		SegmentNodeDatabase pnd = build();
		
		assertTrue(snd.equals(pnd));
	}
}