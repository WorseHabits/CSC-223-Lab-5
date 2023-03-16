package input.visitor;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

import input.components.*;
import input.components.point.*;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;
import utilities.io.StringUtilities;

//
// This file implements a Visitor (design pattern) with 
// the intent of building an unparsed, String representation
// of a geometry figure.
//
public class UnparseVisitor implements ComponentNodeVisitor
{
	@Override
	public Object visitFigureNode(FigureNode node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		sb.append("Figure" + "\n" + StringUtilities.indent(level));

		level++;

		sb.append("Description : " + node.getDescription());

		sb.append("\n Points: \n" + StringUtilities.indent(level));
		node.getPointsDatabase().accept(this, pair);
		sb.append("\n" + StringUtilities.indent(level) + "}");

		sb.append("\n Segments: \n" + StringUtilities.indent(level));
		node.getSegments().accept(this, pair);
		sb.append("\n" + StringUtilities.indent(level) + "}");

		level--;

		return o;
	}

	@Override
	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o)
	{		
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		//Iterates through the map to get each directed edge
		for (PointNode DirectedEdge : node.getAdjList().keySet()) {
			sb.append("\n" + StringUtilities.indent(level) + DirectedEdge.getName() + " : ");

			//Adds each undirected edge to it corresponding DirectedEdge
			for(PointNode UndirectedEdge : node.getAdjList().get(DirectedEdge))
			{
				sb.append(UndirectedEdge.getName() + " ");
			}
		}
		return o;
	}

	/**
	 * This method should NOT be called since the segment database
	 * uses the Adjacency list representation
	 */
	@Override
	public Object visitSegmentNode(SegmentNode node, Object o)
	{
		if(o instanceof StringBuilder) {

			StringBuilder sb = (StringBuilder) o;  
		}

		return null;
	}

	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o)
	{
		if(o instanceof StringBuilder) {

			StringBuilder sb = (StringBuilder) o;  
		}
		// TODO

		return null;
	}

	@Override
	public Object visitPointNode(PointNode node, Object o)
	{
		@SuppressWarnings("unchecked")
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		sb.append("\n" + StringUtilities.indent(level) +
				"Point(" + node.getName() + ")(" + node.getX()+
				", " + node.getY() + ")");

		return o;
	}
}