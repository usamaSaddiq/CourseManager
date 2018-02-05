# CourseManager

This project serves the purpose for generating an automated course structure for a given course given it's units and pre-requisites.

The code generates possible structure based on the pre-requisites of a given unit and in order to generate such strucutre following rules are taken into account: 

1. A unit may or may not have pre-requisites.
2. All the prerequisites have to be completed in order to take a particular unit.


The code follows a recursive approach to construct a possible sub-tree of pre requisites using java hashmaps. Then it compiles the possible or not possible structure via backtracking.

