Long Project 4 (Multi-dimensional search) README file

Team name: G95
Team Members:
Jithendhiralal Ramlal - jgr150030
Ramakrishnan Sathyavageeswaran - rxs142530
Thiagarajan ramakrishnan - txr150430 
####################################################################################################

The submission consist of 5 files.

Item.java
LP4Driver.java
MDS.java
Timer.java
README.txt

Open LP$Driver.java to run the program.

For Multi-dimensional search, 

We have used following data structures for 
1. Insert(id,price,description) :  We have used TreeMap (Key:id and Value:Item) for maintaining Items based on id.
2. Find(id) : We have used TreeMap (Key:id and Value:Item) for finding the information based on id.
3. Delete(id): We delete item from the TreeMap and accordingly maintain other datastructures.
4. FindMinPrice(n): We have used HashMap of Description with LinkedList of Item Object for finding the Min Price.
5. FindMaxPrice(n): We have used HashMap of Description with LinkedList of Item Object for finding the Max Price.
6. FindPriceRange(n,low,high): We have used HashMap of Description with LinkedList of Item object for performing range search on prices.
7. PriceHike(l,h,r): We have used TreeMap (Key:Description and Value:LinkedList<Item>) for PriceHike.
8. Range(low, high): We have created a NavigableMap (Key:Price and Value:LinkedList<Item>) from the existing TreeMap for finding the range.
9  SameSame(): We used HashMap (Key:Item and Value:Integer(count of same items)).


