
Understanding the files
============================

The first step was to understand the data patterns existing in the test files and select a data structure to store the data in a way that it could be easily accessed by the algorithm for further processing. I was in a dilemma to choose between n-ary trees and hashmaps, while n-ary trees had a straight forward recursive mapping it lacked the consistency I wanted to have in my data set i,e ability to terminate iteration at a certain point or when a certain condition is met e.g if a unit has it's own id in the prerequisites array or certain id of an incomplete unit.

HashMaps provided name value pairs and could be further extended with adding primitive data types. After choosing hashmap for my data stroring needs I had to come up with a project structure to perform operations on the stored data.


I came up with the following code structure with each class explained:

CourseManager  ==> Main java project

-src       ===> has all the files for code

-resources ===> has all the resource files i,e csv files

-src

--coursemanager

---CourseManager.java  ===> Main java class to instantiate the objects and test further functions.

----Course.java    ===> The class which stores the course information such as all units, the completed units and incomplete units. Also provide utility function such as loading all the units from files and then loading the prerequisites into the units and then performing data operations such as generating course structure and printing completed and incomplete courses.

----Unit.java     ===>The unit file stores the unit information for each indivdual unit such as the unit name, unit code and the prerequisites a unit contains.


Generating Course Structure
=============================
Generating a course structure was in particular the hardest part of this challenge. I followed a recursive approach to solve the problem via backtracking. Let's take the following structure:

2->1,6,11

Unit number two is dependant on unit 1, 6 and 11 in order to be completed. A possible solution is to have a hashmap with unit 2 and it's prerequisites and a function which explores all of the prerequisites of 2 and calls exploreMap with it's pre-requisites as a parameter, each of them as an instance of the Unit object in a sequential order. Once all of the dependencies have been resolved i,e the prerequisites, the unit is pushed into a completed units array which indicates a unit has been completed. Following pseudo code represents the above concept:

1. Have all of the units in an array and iterate over all of the units one by one passing each unit to a exploreMap function.
2. Check if the unit is already completed or it does not have any prerequisites. This is basically the base case of recursion which helps in terminating the recursive chain.
3. Explore the passed units prerequisites, check if the unit is a part of it's own prerequisites or has a prerequisite in the incomplete units array and terminate recursion.
3. Iterate over the prerequisites of the current unit if it has any and call the function itself with the first prerequisite as a parameter.
4. Maintain a current call variable to make sure the function does not crach due to infinite recursion i,e when the unit calls itself exploring the recursive chain.
5. Check if the last operation was successful and add the unit to a completed unit category.
6. Otherwise return false.

Printing Course Structure
==========================
The course structure is added to two queues, the completed queue and the incompleted queue. Call each queue in order and print out the units in sequence.


Running the project
===================

The project can be imported into any Java IDE preferrably NetBeans or Eclipse and then compiled and run for execution.
