Short Project 0 Hashing README file

Team name: G95
Team Members:
Jithendhiralal Ramlal - jgr150030
Ramakrishnan Sathyavageeswaran - rxs142530
Thiagarajan ramakrishnan - txr150430 
####################################################################################################

The submission consist of 3 files.

Timer.java

a) RemoveDuplicates.java
b) findMostFrequentElement.java
README.txt


a) Removing duplicates problem:

In this problem we have used HashSet to store the unique numbers and push all unique numbers to front in an array.

Sample Input:
14 17 2 2 6 16 14 16 14 9 7 3 6 11 9 17 5 9 18 7

Sample Output:
11
Time: 0 msec.
Memory: 2 MB / 128 MB.

b) findMostFrequentElement.java:
In this problem we compared two different algorithms to find the most frequent element in an array. One program is using Hashtable which has O(n) and other is 
using Sorting technique to find the element which is in O(nlogn) where n being the size of an array.

After comparing both results for n <100000 , RT of O(n) algorithm is faster. Time: 53 msec.
For n = 200000, Both O(n) algorithm and O(nlogn) RT is same. Time: 84 msec. for both
For n = 300000, RT of O(nlogn) = Time: 56 msec. and for O(n) algorithm = Time: 176 msec.
For n = 3000000, RT of O(nlogn) = Time: 1560 msec. and for O(n) algorithm = Time: 392 msec.

As size of n grows O(nlogn) algorithm beats the O(n) algorithm.

Sample Input:
1 2 2 9 8 6 7 2 4 12 67 1 5 7 6 2 12 

Sample Output:
Most Frequent element using HashTable : O(n) 
2
Time: 0 msec.
Memory: 2 MB / 128 MB.
Most Frequent element using Sorting : O(nlogn) 
2
Time: 0 msec.
Memory: 2 MB / 128 MB.

