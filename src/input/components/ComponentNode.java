package input.components;

public interface ComponentNode
{
	Object accept(ComponentNodeVisitor visitor, Object o);
}