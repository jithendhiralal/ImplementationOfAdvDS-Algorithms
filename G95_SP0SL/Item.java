/**
 *
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 *
 */

class Item <T extends Comparable<? super T>> implements Comparable
{
	T element;
	Item(T x)
	{
		element = x;
	}
	public int compareTo(Object another)
	{
		if(another!=null)
		{
			return element.compareTo(((Item<T>) another).element);
		}
		else
			return 0;
	}
	
	//conversion
	public String toString()
	{
		return element.toString();
	}
}
