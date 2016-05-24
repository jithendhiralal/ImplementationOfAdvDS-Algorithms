/**
 *
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 * @param <T>
 */
public class Entry<T> {

    private T element;
    private Entry<T> left;
    private Entry<T> right;
    private Entry<T> parent;
    private int height;


    Entry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
        element = x;
        left = l;
        right = r;
        parent = p;
        height = 0;


    }

    /**
     * @return the element
     */
    public T getElement() {
        return element;
    }

    /**
     * @param element the element to set
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * @return the left
     */
    public Entry<T> getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(Entry<T> left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public Entry<T> getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(Entry<T> right) {
        this.right = right;
    }

    /**
     * @return the parent
     */
    public Entry<T> getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Entry<T> parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

   
}
