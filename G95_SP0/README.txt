Short Project 0 README file
####################################################################################################

Implementation of Merge Sort and comparing its running time with Heap Sort:

The submission consist of 3 files:
HeapSort.java
MergeSort.java
README.txt

HeapSort.java:
Implemented heapsort in generics using Arraylist. Heapsort uses the PriorityQueue from Java Collections.

MergeSort.java:
Implemented MergeSort in generics using divide and conquer method.

Testcases:
All inputs are hardcoded in the program. To test this program, we have taken worst case input of numbers
in descending(reverse) order. Thus 3 different runs are made for 2 million records, 5 million records and
20 million records with numbers in descending order.

RUN 1 - 2 million records 
MergeSort - 0.35
HeapSort  - 0.34

RUN 2 - 5 million records 
MergeSort - 0.78
HeapSort  - 0.81

RUN 3 - 20 million records 
MergeSort - 3.0
HeapSort  - 3.4

As I observe when the number of records increases, the MergeSort runs faster than the HeapSort.