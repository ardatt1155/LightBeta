
/*

Zeek. Recursion / DynamicProgramming
Given a text and a pattern, write a regex-matcher that checks if the text matches the pattern.
. Matches one of any character
* Matches zero or more of any character


------------------------------------------------
Zeek. Quiz
Q. What is a thread and what is a process?
Q. Why is there a randomization before we begin quicksort?
Q. What is a balanced-binary-search-tree? Tell me a few different kinds of BBST.
Q. Do you know any balanced-search-tree that is not binary?
Q. How does a collection like C++ vectors ensure constant-time operations?
Q. What is thrashing?
Q. What is a deadlock?
Q. What is the difference between interface and abstract-class.
Q. Explain encapsulation vs polymorphism.
Q. What is ACID?
Q. What is the purpose of a try-catch-finally statement?
Q. How does recursion compare with divide-and-conquer.
Q. Would there be any difference if a DFS uses the call-stack vs a heap-stack? Can you write BFS recursively?
Q. How do you find MST of a graph?
Q. Reverse a linked-list. Recurse? Or not to recurse?


------------------------------------------------
Zeek. Given a stream of hashtags, tokenize them into words using a dictionary and therefore come up with similar hashtags. Eg, #nationaldonutday or #donut or #covfefe. Use a simple Jaccard-Formula - Jaccard coefficient measures similarity between finite sample sets, and is defined as the size of the intersection divided by the size of the union of the sample sets. Now implement a search-service that maps keywords to relevent hashtags. 


------------------------------------------------
Zeek. Odds / Lists
You have a linked-list. The list is crucial enough that it should be stored in persistent storage. So you decide to store the list on disk. Assume that the list is sorted, and the key-space for lookup is evenly distributed between 1-100.
The disk has a few caveats. One is that the likelihood of failure for a given record is proportional to the frequency with which that record is accessed - think flash drives. Second is that the disk has a huge latency so you want to somehow load-balance this stuff - elements at the front will be lookedup more often. Assume that the list is read-only and disk is cheap with N slots. Assume this abstraction of the disk: an indexed array of slots, a global Disk object that supports a Disk.read(location, size) and a Disk.write(location, data).
Build a driver that given a list first persists that list to the disk and then exposes a lookup operation on that list. 


*/



