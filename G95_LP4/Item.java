
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 */
public class Item {

    public long id;
    public double price;
    public long[] description;
    public int size;

    public Item() {

    }
    
    /**
     * 
     * @param id
     * @param price
     * @param description
     * @param size 
     */
    public Item(Long id, Double price, long[] description, int size) {
        this.id = id;
        this.price = price;
        this.size = size;
        this.description = Arrays.copyOf(description, size);
    }
    
    /**
     * 
     * @return 
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            str.append(description[i] + " ");
        }
        return "(" + price + ")" + " " + str;
    }
    
    /**
     * Overridden Equals function for SameSame which compares the descriptions
     * @param obj
     * @return 
     */
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        } else {
            Item itm = (Item) obj;
            if (itm.description.length == this.size) {
                for (int i = 0; i < this.size; i++) {
                    if (this.description[i] != itm.description[i]) {
                        return false;
                    }     
                }
                return true;
            } else {
                return false;
            }
        }
    }
    
    /**
     * Overridden hashCode function for SameSame which 
     * create hash code based on the descriptions
     * @param obj
     * @return 
     */
    @Override
    public int hashCode() {
//        int hash = 3;
//        for (int i = 0; i < this.size; i++) {
//            Long desc = description[i];
//            hash = hash * 7 * desc.hashCode();
//        }
//        return hash;
        
        int xorVal = 0;
		
		for(Long descId : description) {
			xorVal ^= descId;
		}
		
		return xorVal;
    }

}
