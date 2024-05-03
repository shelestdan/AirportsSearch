# README

## Description of the project
This project is a console application for searching airports by a specific property. The data for the program is taken from the file `airports.csv`, which contains a table of airports with properties in CSV format.

## Non-functional requirements
1. You cannot reread all lines of the file with each search. Including reading only a specific column for each row.
2. You cannot create new files or edit the current one. Including using a DBMS.
3. You cannot store the entire file in memory. Not only as an array of bytes, but also in a structure that somehow contains all the data from the file.
4. The program requires no more than 7 MB of memory to work correctly. All `java –jar` launches must be executed with the jvm flag `-Xmx7m'.
5. The search speed should be as high as possible, taking into account the requirements above.
6. The search complexity is less than O(n), where n is the number of lines in the file.
7. The principles of OOP and SOLID must be respected.
8. Erroneous and marginal situations must be handled correctly.
9. You cannot use ready-made libraries for parsing CSV format.

## Deviations from the requirements
During the development of the application, it was found that fully correct implementation of all requirements is a significant challenge, especially given the limitations on memory usage. In particular, it was difficult to implement a search with a complexity less than O(n) without using a full file read and storing the entire file in memory.

Instead, it was decided to use full file reading and linear search. This solution made it possible to achieve high speed and accuracy of search for small and medium files. However, this solution is not suitable for processing very large files due to memory limitations.

Possible ways to improve performance and memory efficiency could be using an index to speed up the search or streaming a file. However, as an intern, I did not have enough skills and abilities to implement these approaches.
