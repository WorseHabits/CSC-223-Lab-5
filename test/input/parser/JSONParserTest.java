/**
* Testing for JSONParser. 
*
* <p>Bugs: none known
*
* @author Sam Luck-Leonard, Mason Taylor, and Grace Houser
* @date 2/24/2023
*/

package input.parser;

import static org.junit.jupiter.api.Assertions.*;

//import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import input.builder.DefaultBuilder;
import input.builder.GeometryBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;
import input.exception.ParseException;

class JSONParserTest
{
	public static ComponentNode runFigureParseTest(String filename)
	{
		JSONParser parser = new JSONParser(new GeometryBuilder());

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);

		return parser.parse(figureStr);
	}
	
	public static ComponentNode runFigureParseTestNull(String filename)
	{
		JSONParser parser = new JSONParser(new DefaultBuilder());

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);

		return parser.parse(figureStr);
	}

	@Test
	void empty_json_string_test()
	{
		JSONParser parser = new JSONParser(new GeometryBuilder());

		assertThrows(ParseException.class, () -> { parser.parse("{}"); });
	}
	
	void empty_json_string_testNull()
	{
		JSONParser parser = new JSONParser(new DefaultBuilder());

		assertThrows(ParseException.class, () -> { parser.parse("{}"); });
	}

	
	// testing the single_triangle.json file
	@Test
	void singleTriangle_test()
	{
		//
		// The input String ("single_triangle.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/single_triangle.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("single_triangle.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		jsonNode.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void singleTriangle_descriptionTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("single_triangle.json");

		assertTrue(jsonNode instanceof FigureNode);

		assertTrue(((FigureNode) jsonNode).getDescription().equals("Right Triangle in the first quadrant."));
	}

	@Test
	void singleTriangle_pointNodeDatabaseNameTest() {

		ComponentNode jsonComp = JSONParserTest.runFigureParseTest("single_triangle.json");

		ComponentNode javaComp = buildSingleTriangle();

		assertTrue(jsonComp instanceof FigureNode);

		assertTrue(javaComp instanceof FigureNode);

		FigureNode javaNode = (FigureNode) javaComp;

		FigureNode jsonNode = (FigureNode) jsonComp;

		for(PointNode point : jsonNode.getPointsDatabase()) {

			String name = point.getName();

			assertTrue(point.equals(javaNode.getPointsDatabase().getPoint(name)));
		}
	}

	@Test
	void singleTriangle_segmentNodeDatabaseTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("single_triangle.json");

		ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode instanceof FigureNode);

		FigureNode json = (FigureNode) jsonNode;

		FigureNode java = (FigureNode) javaNode;

		assertTrue(json.getSegments().equals(java.getSegments()));
	}


	// testing the fully_connected_irregular_polygon.json file
	@Test
	void fullyConnectedIrregularPolygon_test()
	{
		//
		// The input String ("fully_connected_irregular_polygon.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/fully_connected_irregular_polygon.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("fully_connected_irregular_polygon.json");

		//ComponentNode javaNode = buildFullyConnectedIrregularPolygon();

		assertTrue(jsonNode instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		jsonNode.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void fullyConnectedIrregularPolygon_descriptionTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("fully_connected_irregular_polygon.json");

		assertTrue(jsonNode instanceof FigureNode);

		assertTrue(((FigureNode) jsonNode).getDescription().equals("Irregular pentagon in which each vertex is connected to each other."));
	}

	@Test
	void fullyConnectedIrregularPolygon_pointNodeDatabaseNameTest() {

		ComponentNode jsonComp = JSONParserTest.runFigureParseTest("fully_connected_irregular_polygon.json");

		ComponentNode javaComp = buildFullyConnectedIrregularPolygon();

		assertTrue(jsonComp instanceof FigureNode);

		assertTrue(javaComp instanceof FigureNode);

		FigureNode javaNode = (FigureNode) javaComp;

		FigureNode jsonNode = (FigureNode) jsonComp;

		for(PointNode point : jsonNode.getPointsDatabase()) {

			String name = point.getName();

			assertTrue(point.equals(javaNode.getPointsDatabase().getPoint(name)));
		}
	}

	@Test
	void fullyConnectedIrregularPolygon_segmentNodeDatabaseTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("fully_connected_irregular_polygon.json");

		ComponentNode javaComp = buildFullyConnectedIrregularPolygon();

		assertTrue(jsonNode instanceof FigureNode);

		FigureNode json = (FigureNode) jsonNode;

		FigureNode java = (FigureNode) javaComp;

		assertTrue(json.getSegments().equals(java.getSegments()));
	}

	
	// testing the collinear_line_segments.json file
	@Test
	void collinearLineSegments_test()
	{
		//
		// The input String ("collinear_line_segments.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/collinear_line_segments.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("collinear_line_segments.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		jsonNode.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void collinearLineSegments_descriptionTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("collinear_line_segments.json");

		assertTrue(jsonNode instanceof FigureNode);

		assertTrue(((FigureNode) jsonNode).getDescription().equals("A seqeunce of collinear line segments mimicking one line with 6 points."));
	}

	@Test
	void collinearLineSegments_pointNodeDatabaseNameTest() {

		ComponentNode jsonComp = JSONParserTest.runFigureParseTest("collinear_line_segments.json");

		ComponentNode javaComp = buildCollinearLineSegments();

		assertTrue(jsonComp instanceof FigureNode);

		assertTrue(javaComp instanceof FigureNode);

		FigureNode javaNode = (FigureNode) javaComp;

		FigureNode jsonNode = (FigureNode) jsonComp;

		for(PointNode point : jsonNode.getPointsDatabase()) {

			String name = point.getName();

			assertTrue(point.equals(javaNode.getPointsDatabase().getPoint(name)));
		}
	}

	@Test
	void collinearLineSegments_segmentNodeDatabaseTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("collinear_line_segments.json");

		ComponentNode javaNode = buildCollinearLineSegments();

		assertTrue(jsonNode instanceof FigureNode);

		FigureNode json = (FigureNode) jsonNode;

		FigureNode java = (FigureNode) javaNode;

		assertTrue(json.getSegments().equals(java.getSegments()));
	}

	
	// testing the crossing_symmetric_triangle.json file
	@Test
	void crossingSymmetricTriangle_test()
	{
		//
		// The input String ("crossing_symmetric_triangle.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/crossing_symmetric_triangle.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("crossing_symmetric_triangle.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		jsonNode.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void crossingSymmetricTriangle_descriptionTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("crossing_symmetric_triangle.json");

		assertTrue(jsonNode instanceof FigureNode);

		assertTrue(((FigureNode) jsonNode).getDescription().equals("Crossing symmetric triangle construction."));
	}

	@Test
	void crossingSymmetricTriangle_pointNodeDatabaseNameTest() {

		ComponentNode jsonComp = JSONParserTest.runFigureParseTest("crossing_symmetric_triangle.json");

		ComponentNode javaComp = buildCrossingSymmetricTriangle();

		assertTrue(jsonComp instanceof FigureNode);

		assertTrue(javaComp instanceof FigureNode);

		FigureNode javaNode = (FigureNode) javaComp;

		FigureNode jsonNode = (FigureNode) jsonComp;

		for(PointNode point : jsonNode.getPointsDatabase()) {

			String name = point.getName();

			assertTrue(point.equals(javaNode.getPointsDatabase().getPoint(name)));
		}
	}

	@Test
	void crossingSymmetricTriangle_segmentNodeDatabaseTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("crossing_symmetric_triangle.json");

		ComponentNode javaNode = buildCrossingSymmetricTriangle();

		assertTrue(jsonNode instanceof FigureNode);

		FigureNode json = (FigureNode) jsonNode;

		FigureNode java = (FigureNode) javaNode;

		assertTrue(json.getSegments().equals(java.getSegments()));
	}

	
	
	// testing the intersecting_star.json file



	@Test
	void star_test()
	{
		//
		// The input String ("intersecting_star.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/intersecting_star.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("intersecting_star.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		jsonNode.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void star_descriptionTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("intersecting_star.json");

		assertTrue(jsonNode instanceof FigureNode);

		assertTrue(((FigureNode) jsonNode).getDescription().equals("A star in which line segments intersect"));
	}

	@Test
	void star_pointNodeDatabaseNameTest() {

		ComponentNode jsonComp = JSONParserTest.runFigureParseTest("intersecting_star.json");

		ComponentNode javaComp = buildStar();

		assertTrue(jsonComp instanceof FigureNode);

		assertTrue(javaComp instanceof FigureNode);

		FigureNode javaNode = (FigureNode) javaComp;

		FigureNode jsonNode = (FigureNode) jsonComp;

		for(PointNode point : jsonNode.getPointsDatabase()) {

			String name = point.getName();

			assertTrue(point.equals(javaNode.getPointsDatabase().getPoint(name)));
		}
	}

	@Test
	void star_segmentNodeDatabaseTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("intersecting_star.json");

		ComponentNode javaNode = buildStar();

		assertTrue(jsonNode instanceof FigureNode);

		FigureNode json = (FigureNode) jsonNode;

		FigureNode java = (FigureNode) javaNode;

		assertTrue(json.getSegments().equals(java.getSegments()));
	}

	
	
	// testing the one_point.json file

	@Test
	void onePoint_test()
	{
		//
		// The input String ("one_point.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/one_point.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("one_point.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		jsonNode.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void onePoint_descriptionTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("one_point.json");

		assertTrue(jsonNode instanceof FigureNode);

		assertTrue(((FigureNode) jsonNode).getDescription().equals(buildOnePoint().getDescription()));
	}

	@Test
	void onePoint_pointNodeDatabaseNameTest() {

		ComponentNode jsonComp = JSONParserTest.runFigureParseTest("one_point.json");

		ComponentNode javaComp = buildOnePoint();

		assertTrue(jsonComp instanceof FigureNode);

		assertTrue(javaComp instanceof FigureNode);

		FigureNode javaNode = (FigureNode) javaComp;

		FigureNode jsonNode = (FigureNode) jsonComp;

		for(PointNode point : jsonNode.getPointsDatabase()) {

			String name = point.getName();

			assertTrue(point.equals(javaNode.getPointsDatabase().getPoint(name)));
		}
	}

	@Test
	void onePoint_segmentNodeDatabaseTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("one_point.json");

		ComponentNode javaNode = buildOnePoint();

		assertTrue(jsonNode instanceof FigureNode);

		FigureNode json = (FigureNode) jsonNode;

		FigureNode java = (FigureNode) javaNode;

		assertTrue(json.getSegments().equals(java.getSegments()));
	}
	
	@Test
	void rectangleTest()
	{
		//
		// The input String ("single_triangle.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/single_triangle.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("square_outlier_point.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		jsonNode.unparse(sb, 0);
		System.out.println(sb.toString());
	}

	@Test
	void rectangleDescriptionTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("square_outlier_point.json");

		assertTrue(jsonNode instanceof FigureNode);

		assertTrue(((FigureNode) jsonNode).getDescription().equals(buildRectangle().getDescription()));

	}

	@Test
	void rectanglePointNodeDatabaseNameTest() {

		ComponentNode jsonComp = JSONParserTest.runFigureParseTest("square_outlier_point.json");

		ComponentNode javaComp = buildRectangle();

		assertTrue(jsonComp instanceof FigureNode);

		assertTrue(javaComp instanceof FigureNode);

		FigureNode javaNode = (FigureNode) javaComp;

		FigureNode jsonNode = (FigureNode) jsonComp;

		for(PointNode point : jsonNode.getPointsDatabase()) {

			String name = point.getName();

			assertTrue(point.equals(javaNode.getPointsDatabase().getPoint(name)));

		}

	}

	@Test
	void rectangleSegmentNodeDatabaseTest() {

		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("square_outlier_point.json");

		ComponentNode javaNode = buildRectangle();

		assertTrue(jsonNode instanceof FigureNode);

		FigureNode json = (FigureNode) jsonNode;

		FigureNode java = (FigureNode) javaNode;

		assertTrue(json.getSegments().equals(java.getSegments()));

	}

	@Test
	void singleTriangle_testNull()
	{
		//
		// The input String ("single_triangle.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/single_triangle.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTestNull("single_triangle.json");

		assertTrue(jsonNode == null);
	}

	// testing the fully_connected_irregular_polygon.json file
	@Test
	void fullyConnectedIrregularPolygon_testNull()
	{
		//
		// The input String ("fully_connected_irregular_polygon.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/fully_connected_irregular_polygon.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTestNull("fully_connected_irregular_polygon.json");

		//ComponentNode javaNode = buildFullyConnectedIrregularPolygon();

		assertTrue(jsonNode == null);
	}

	
	// testing the collinear_line_segments.json file
	@Test
	void collinearLineSegments_testNull()
	{
		//
		// The input String ("collinear_line_segments.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/collinear_line_segments.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTestNull("collinear_line_segments.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode == null);
		
	}

	// testing the crossing_symmetric_triangle.json file
	@Test
	void crossingSymmetricTriangle_testNull()
	{
		//
		// The input String ("crossing_symmetric_triangle.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/crossing_symmetric_triangle.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTestNull("crossing_symmetric_triangle.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode == null);
	}

	
	
	// testing the intersecting_star.json file



	@Test
	void star_testNull()
	{
		//
		// The input String ("intersecting_star.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/intersecting_star.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTestNull("intersecting_star.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode == null);
	}

	// testing the one_point.json file

	@Test
	void onePoint_testNull()
	{
		//
		// The input String ("one_point.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/one_point.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTestNull("one_point.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode == null);
	}

	@Test
	void rectangleTestNull()
	{
		//
		// The input String ("single_triangle.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/single_triangle.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTestNull("square_outlier_point.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode == null);
	}
	
	// building FigureNodes of JSON files in java for comparison

	public FigureNode buildCrossingSymmetricTriangle() {

		PointNodeDatabase pnd = new PointNodeDatabase();

		PointNode d = new PointNode("D", 0.0, 0.0);
		pnd.put(d);

		PointNode e = new PointNode("E", 6.0, 0.0);
		pnd.put(e);

		PointNode b = new PointNode("B", 2.0, 4.0);
		pnd.put(b);

		PointNode c = new PointNode("C", 4.0, 4.0);
		pnd.put(c);

		PointNode a = new PointNode("A", 3.0, 6.0);
		pnd.put(a);

		SegmentNodeDatabase snd = new SegmentNodeDatabase();

		snd.addUndirectedEdge(a, b);
		snd.addUndirectedEdge(a, c);
		snd.addUndirectedEdge(b, c);
		snd.addUndirectedEdge(b, d);
		snd.addUndirectedEdge(b, e);
		snd.addUndirectedEdge(c, d);
		snd.addUndirectedEdge(c, e);
		snd.addUndirectedEdge(d, e);

		return new FigureNode("Crossing symmetric triangle construction.", pnd, snd);
	}

	public FigureNode buildFullyConnectedIrregularPolygon() {
		PointNodeDatabase pnd = new PointNodeDatabase();

		PointNode a = (new PointNode("A", 0.0, 0.0));
		pnd.put(a);

		PointNode b = (new PointNode("B", 4.0, 0.0));
		pnd.put(b);

		PointNode c = (new PointNode("C", 6.0, 3.0));
		pnd.put(c);

		PointNode d = (new PointNode("D", 3.0, 7.0));
		pnd.put(d);

		PointNode e = (new PointNode("E", -2.0, 4.0));
		pnd.put(e);

		PointNode f = (new PointNode("F", 26.0, 0.0));
		pnd.put(f);


		SegmentNodeDatabase snd = new SegmentNodeDatabase();

		snd.addUndirectedEdge(a, b);
		snd.addUndirectedEdge(a, c);
		snd.addUndirectedEdge(a, d);
		snd.addUndirectedEdge(a, e);

		snd.addUndirectedEdge(b, c);
		snd.addUndirectedEdge(b, d);
		snd.addUndirectedEdge(b, e);

		snd.addUndirectedEdge(c, d);
		snd.addUndirectedEdge(c, e);

		snd.addUndirectedEdge(d, e);

		return (new FigureNode("Irregular pentagon in which each vertex is connected to each other.",pnd, snd));
	}

	public FigureNode buildCollinearLineSegments() {

		String description = "A seqeunce of collinear line segments mimicking one line with 6 points.";

		PointNodeDatabase points = new PointNodeDatabase();

		PointNode A = new PointNode("A", 0, 0);
		points.put(A);

		PointNode B = new PointNode("B", 4, 0);
		points.put(B);

		PointNode C = new PointNode("C", 9, 0);
		points.put(C);

		PointNode D = new PointNode("D", 11, 0);
		points.put(D);

		PointNode E = new PointNode("E", 16, 0);
		points.put(E);

		PointNode F = new PointNode("F", 26, 0);
		points.put(F);

		SegmentNodeDatabase segs = new SegmentNodeDatabase();
		segs.addUndirectedEdge(A, B);
		segs.addUndirectedEdge(B, C);
		segs.addUndirectedEdge(C, D);
		segs.addUndirectedEdge(D, E);
		segs.addUndirectedEdge(E, F);

		return new FigureNode(description, points, segs);
	}

	public FigureNode buildSingleTriangle() {

		String description = "Right Triangle in the first quadrant.";

		PointNodeDatabase points = new PointNodeDatabase();

		PointNode A = new PointNode("A", 0, 0);
		points.put(A);

		PointNode B = new PointNode("B", 1, 1);
		points.put(B);

		PointNode C = new PointNode("C", 1, 0);
		points.put(C);

		SegmentNodeDatabase segs = new SegmentNodeDatabase();
		segs.addUndirectedEdge(A, B);
		segs.addUndirectedEdge(A, C);
		segs.addUndirectedEdge(B, C);

		return new FigureNode(description, points, segs);
	}

	public FigureNode buildStar() {

		String description = "A star in which line segments intersect";

		PointNodeDatabase points = new PointNodeDatabase();

		PointNode A = new PointNode("A", 0, 0);
		points.put(A);

		PointNode B = new PointNode("B", 4, 0);
		points.put(B);

		PointNode C = new PointNode("C", 6, 5);
		points.put(C);

		PointNode D = new PointNode("D", 3, 8);
		points.put(D);

		PointNode E = new PointNode("E", -2, 5);
		points.put(E);

		SegmentNodeDatabase segs = new SegmentNodeDatabase();
		segs.addUndirectedEdge(A, C);
		segs.addUndirectedEdge(A, D);
		segs.addUndirectedEdge(B, D);
		segs.addUndirectedEdge(B, E);
		segs.addUndirectedEdge(C, E);
		segs.addUndirectedEdge(D, E);

		return new FigureNode(description, points, segs);
	}

	public FigureNode buildOnePoint() {

		String description = "One point with a segment that reflexes the point.";

		PointNodeDatabase points = new PointNodeDatabase();

		PointNode A = new PointNode("A", 7, 8);
		points.put(A);

		SegmentNodeDatabase segs = new SegmentNodeDatabase();
		segs.addUndirectedEdge(A, A);

		return new FigureNode(description, points, segs);

	}

	public FigureNode buildTwoPoints() {

		String description = "";

		PointNodeDatabase points = new PointNodeDatabase();

		PointNode A = new PointNode("A", 0, 0);
		points.put(A);

		PointNode B = new PointNode("B", 1, 1);
		points.put(B);

		PointNode C = new PointNode("C", 1, 0);
		points.put(C);

		SegmentNodeDatabase segs = new SegmentNodeDatabase();
		segs.addUndirectedEdge(A, B);
		segs.addUndirectedEdge(A, C);
		segs.addUndirectedEdge(B, C);

		return new FigureNode(description, points, segs);

	}

	public FigureNode buildRectangle() {

		String description = "A regular rectangle with an unconnected outlier point.";

		PointNodeDatabase points = new PointNodeDatabase();

		PointNode A = new PointNode("A", 2, 0);
		points.put(A);

		PointNode B = new PointNode("B", 4, 0);
		points.put(B);

		PointNode C = new PointNode("C", 4, 7);
		points.put(C);
		
		PointNode D = new PointNode("D", 2, 7);
		points.put(D);

		PointNode E = new PointNode("E", 10, 5);
		points.put(E);

		SegmentNodeDatabase segs = new SegmentNodeDatabase();
		segs.addUndirectedEdge(A, B);
		segs.addUndirectedEdge(A, D);
		segs.addUndirectedEdge(B, C);
		segs.addUndirectedEdge(C, D);

		return new FigureNode(description, points, segs);

	}
}