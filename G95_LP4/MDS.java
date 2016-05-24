import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * The class representation of Multi-dimensional search for site like amazon to 
 * perform various search operation.
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 */
public class MDS {

    TreeMap<Long, Item> itemTree = new TreeMap<>();
    TreeMap<Double, LinkedList<Item>> priceTree = new TreeMap<>();
    TreeMap<Long, LinkedList<Item>> descriptionTree = new TreeMap<>();
    HashMap<Item, Integer> samesameHash = new HashMap<>();
    public double epsilon = 0.000001;

    /**
     * This method is to perform insertion of a Item Object into TreeMap 
     * based on Item id
     * @param id : long Item ID
     * @param price : double Item's Price
     * @param description : long[] Item's description
     * @param size : int description array's size
     * @return 0 - for already existing record
     *         1 - for success 
     */
    public int insert(long id, double price, long[] description, int size) {

        // Description of item is in description[0..size-1].
        // Copy them into your data structure.
        if (!itemTree.containsKey(id)) {
        	//created a new object item
            Item itemObj = new Item(id, price, description, size);
            
            //insert value into TreeMap with id as key
            itemTree.put(id, itemObj);
            
            //insert value into TreeMap with Description as key and TreeSet of Items as values
            for (int i = 0; i < size; i++) {
                if (!descriptionTree.containsKey(description[i])) {
                	LinkedList<Item> descObj = new LinkedList<>();
                    descObj.add(itemObj);
                    descriptionTree.put(description[i], descObj);
                } else {
                    descriptionTree.get(description[i]).add(itemObj);
                }
            }

            //TreeMap for itemTree based on price
            if (!priceTree.containsKey(price)) {
            	LinkedList<Item> priceObj = new LinkedList<>();
                priceObj.add(itemObj);
                priceTree.put(price, priceObj);
            } else {
                priceTree.get(price).add(itemObj);
            }
            
            //For SameSame() HashMap with Item Object as Key and their count as value
            if (size >= 8) {
                if (samesameHash.containsKey(itemObj)) {
                    int value = samesameHash.get(itemObj);
                    samesameHash.put(itemObj, value + 1);
                } else {
                    samesameHash.put(itemObj, 1);
                }
            }

            return 1;

        } else {//If the description is not given
            if (size == 0) {
                double old_price = itemTree.get(id).price;
                itemTree.get(id).price = price;
                
                //TreeMap for itemTree based on price
                priceTree.get(old_price).remove(itemTree.get(id));
                if (priceTree.get(old_price).isEmpty()) {
                    priceTree.remove(old_price);
                }
                Item temp = itemTree.get(id);
                if (!priceTree.containsKey(price)) {
                	LinkedList<Item> priceObj = new LinkedList<>();
                    priceObj.add(temp);
                    priceTree.put(price, priceObj);
                } else {
                    priceTree.get(price).add(temp);
                }
            } else {// Update the description and price for the item
                int temparrLength = itemTree.get(id).description.length;
                long[] temparr = Arrays.copyOf(itemTree.get(id).description, temparrLength);
                double old_price = itemTree.get(id).price;
                itemTree.get(id).price = price;
                
                //removing the item from HashMap before updating 
                if (size >= 8) {
                    Item itm = itemTree.get(id);
                    if (samesameHash.containsKey(itm)) {
                        int value = samesameHash.get(itm);
                        if ((value - 1) == 0) {
                            samesameHash.remove(itm);
                        } else {
                            samesameHash.put(itm, value - 1);
                        }
                    }
                }

                //removing the item from TreeMap before updating
                for (int i = 0; i < temparrLength; i++) {
                    descriptionTree.get(temparr[i]).remove(itemTree.get(id));
                    if (descriptionTree.get(temparr[i]).isEmpty()) {
                        descriptionTree.remove(temparr[i]);
                    }
                }

                itemTree.get(id).description = Arrays.copyOf(description, size);
                itemTree.get(id).size = size;

                //Updating TreeMap with the new description and price
                Item temp = itemTree.get(id);
                for (int i = 0; i < size; i++) {
                    if (!descriptionTree.containsKey(description[i])) {
                    	LinkedList<Item> ll = new LinkedList<>();
                        ll.add(temp);
                        descriptionTree.put(description[i], ll);
                    } else {
                        descriptionTree.get(description[i]).add(temp);
                    }
                }
                //for itemTree based on price
                priceTree.get(old_price).remove(itemTree.get(id));
                if (priceTree.get(old_price).isEmpty()) {
                    priceTree.remove(old_price);
                }
                Item tempItem = itemTree.get(id);
                if (!priceTree.containsKey(price)) {
                	LinkedList<Item> ll = new LinkedList<>();
                    ll.add(tempItem);
                    priceTree.put(price, ll);
                } else {
                    priceTree.get(price).add(tempItem);
                }
            }
            
            //Updating the item from HashMap before updating
            if (size >= 8) {
                Item itm = itemTree.get(id);
                if (samesameHash.containsKey(itm)) {
                    int value = samesameHash.get(itm);
                    samesameHash.put(itm, value + 1);
                } else {
                    samesameHash.put(itm, 1);
                }
            }
            return 0;
        }
    }

    /**
     * This method is to perform find price of a Item Object  
     * based on Item id
     * @param id : long Item's ID
     * @return 0 - for failure
     *         Item's price - for success 
     */
    double find(long id) {
        Item obj = itemTree.get(id);
        if (obj == null) {
            return 0;
        } else {
            return  Math.floor((obj.price + epsilon) * 100) / 100;
        }
    }

    /**
     * This method is to perform delete operation on TreeMap 
     * based on Item id
     * @param id : long Item's ID
     * @return 0 - for failure
     *         sum of description - for success
     */
    long delete(long id) {
        Item obj = itemTree.get(id);
        if (obj == null) {
            return 0;
        } else {
            long[] array;
            long sum = 0L;
            int size = obj.description.length;
            array = Arrays.copyOf(obj.description, size);
            //Sum of the description
            for (int i = 0; i < size; i++) {
                sum += array[i];
            }
            
            //Removing from TreeMap of Description
            for (int i = 0; i < size; i++) {
                descriptionTree.get(array[i]).remove(itemTree.get(id));
                if (descriptionTree.get(array[i]).isEmpty()) {
                    descriptionTree.remove(array[i]);
                }
            }
            
            //Removing from TreeMap of Price
            priceTree.get(obj.price).remove(itemTree.get(id));
            if (priceTree.get(obj.price).isEmpty()) {
                priceTree.remove(obj.price);
            }
            itemTree.remove(id);
            
            //Removing from HashMap of Item Object
            if (samesameHash.containsKey(obj)) {
                int value = samesameHash.get(obj);
                if ((value - 1) == 0) {
                    samesameHash.remove(obj);
                } else {
                    samesameHash.put(obj, value - 1);
                }
            }

            return sum;
        }
    }

    /**
     * This method is to find minimum Price of the Item 
     * based on their description
     * @param des : long Item's Description
     * @return 0 - for failure
     *         price - for success 
     */
    double findMinPrice(long des) {
    	LinkedList<Item> priceObj = descriptionTree.get(des);
    	double min_price = Double.MAX_VALUE;
        if (priceObj != null) {
        	for(Item obj : priceObj){
        		if(min_price > obj.price){
        			min_price = obj.price;
        		}
        	}
        	return Math.floor((min_price + epsilon) * 100) / 100;
        } else {
            return 0;
        }
    }

    /**
     * This method is to find maximum Price of the Item 
     * based on their description
     * @param des : long Item's Description
     * @return 0 - for failure
     *         price - for success 
     */
    double findMaxPrice(long des) {
    	LinkedList<Item> priceObj = descriptionTree.get(des);
    	double max_price = Double.MIN_VALUE;
        if (priceObj != null) {
        	for(Item obj : priceObj){
        		if(max_price < obj.price){
        			max_price = obj.price;
        		}
        	}
        	return Math.floor((max_price + epsilon) * 100) / 100;
        } else {
            return 0;
        }
    }

    /**
     * This method is to find number of Items  
     * based on the given price range and Item's description
     * @param des : long Item's Description
     * @param lowPrice : double Item's lowest price
     * @param highPrice : double Item's highest price
     * @return 0 - for failure
     *         No of Item counts - for success
     */
    int findPriceRange(long des, double lowPrice, double highPrice) {
        LinkedList<Item> priceObj = descriptionTree.get(des);
        int count = 0;
        if (priceObj != null) {
            for (Item i : priceObj) {
                if (i.price < lowPrice || i.price > highPrice) {
                    continue;
                } else {
                    count++;
                }
            }
            return count;
        } else {
            return 0;
        }
    }

    /**
     * this method is used to increase the price of the product 
     * in the given range
     * @param minid - Id of the item lower bound inclusive
     * @param maxid - Id of the item upper bound inclusive
     * @param rate - rate of the price increase in percentage
     * @return netIncrease - the sum of all amounts increased for 
     * all the items in the range
     */
    double priceHike(long minid, long maxid, double rate) {
        double netIncrease = 0;
        for (Map.Entry<Long, Item> m : itemTree.entrySet()) {
            if ((long) m.getKey() < minid) {
                continue;
            } else if ((long) m.getKey() > maxid) {
                break;
            } else {
            	//find the updated price
                Item obj = itemTree.get((long) m.getKey());
                double inc_price = (obj.price * (1 + rate / 100));
                inc_price = Math.floor((inc_price + epsilon) * 100) / 100;
                netIncrease += (inc_price - obj.price);
                double old_price = obj.price;

                //for itemTree based on price
                priceTree.get(old_price).remove(obj);
                if (priceTree.get(old_price).isEmpty()) {
                    priceTree.remove(old_price);
                }

                //Updating the TreeMap of Price
                obj.price = inc_price;
                if (!priceTree.containsKey(obj.price)) {
                    LinkedList<Item> ll = new LinkedList<>();
                    ll.add(obj);
                    priceTree.put(obj.price, ll);
                } else {
                    priceTree.get(obj.price).add(obj);
                }
            }
        }
        netIncrease = Math.floor((netIncrease + epsilon) * 100) / 100;
        return netIncrease;
    }
    
    /**
     *This method is used to find the total number of items in 
     *the given price range
     * @param lowPrice - price of the item lower bound inclusive
     * @param highPrice - price of the item upper bound inclusive
     * @return count - total number of items in the given price range
     */
    int range(double lowPrice, double highPrice) {
        NavigableMap<Double, LinkedList<Item>> temp = priceTree.subMap(lowPrice,true, highPrice, true);
        int count = 0;
        for (Map.Entry<Double, LinkedList<Item>> m : temp.entrySet()) {
            LinkedList<Item> ll = (LinkedList<Item>) m.getValue();
            count += ll.size();
        }
        return count;
    }

    /**
     * This method is implemented to find the Items in the System whose the
     * description of the item contains 8 or more numbers and the
     * description of the item contains exactly the same set of numbers
     * as another item
     * @return count : int - number of items that satisfy the condition 
     * specified
     */
    int samesame() {
        int count = 0;
        for (Integer i : samesameHash.values()) {
            if (i > 1) {
                count += i;
            }
        }
        return count;
    }
}
