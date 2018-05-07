# KWIC Index Production System


## Requirements:
- KWIC accepts a file with a set of lines
- Each line has a set of words, each word consists of a set of characters
- Each line can be "circularly" shifted by removing the first word in the line and appending it to the end of the line
- KWIC outputs a listing (console + file) of all circular shifts of all lines in alphabetical order
- Possible future (but not required now): use of a database instead of a file, a GUI front end instead of a console.


#### Part 1
*Create an initial design of your own choosing for this in Java. Set up the classes, methods, and main method.*

The initial design of the KWIC places the entire KWIC system under one class. This method is very easy to read and 
follow.

- KwicV1()
- main() : void
- readLines() : ArrayList\<String>
- circularShift() : void
- circularShiftLine(line: String) : String
- alphabetize(lines: ArrayList\<String>) : void
- writeLines(lines:  ArrayList\<String>, outputPath: String) : void


#### Part 2
*Create an alternative design for the KWIC program in Java.*

The second design takes the Strategy Pattern approach. The line input/output is programmed against a ILineIO interface. 
The StringLineManagers are programmed against an IAlphabetizedCollection interface. The IndexProductionSystem is an 
abstract class that implements the ICircularShift interface. New versions of this abstract parent class are created 
and must implement the methods defined by the interface implemented by the parent class.

- \<package> interfaces
    - **IAlphabetizedCollection \<\<interface>>**
        - add(newLine: String) : void
        - setCollection(collection: Collection\<String>) : void
        - getCollection() : Collection\<String>
        - getAlphabetizedCollection() : Collection\<String>
    - **ICircularShift \<\<interface>>**
        - circularShift() : void
    - **ILineIO \<\<interface>>**
        - readLines() : void
        - writeLines() : void
- \<package> lineIO
    - **LineIO implements ILineIO**
        - \- inputFile: File
        - \- outputFile: File
        - \- lineManager: IAlphabetizableCollection
        - \+ LineIO()
        - \+ readLines() : void
        - \+ writeLines() : void
- \<package> lineManager
    - **StringLineManagerV2 implements IAlphabetizableCollection**
        - \- allLines: Collection\<String>
        - \+ StringLineManagerV2()
        - \+ StringLineManagerV2(collection: Collection\<String>)
        - \+ getAllLines() : Collection\<String>
        - \+ add(newLine: String) : void
        - \+ getCollection() : Collection\<String>
        - \+ setCollection(collection: Collection\<String>) : void
        - \+ getAlphabetizedCollection() : Collection \<String>
    - **StringLineManagerV3 implements IAlphabetizableCollection**
        - \+ StringLineManagerV3()
        - \+ StringLineManagerV3(collection: Collection\<String>)
        - \+ getAllLines() : Collection\<String>
        - \+ add(newLine: String) : void
        - \+ getCollection() : Collection\<String>
        - \+ setCollection(collection: Collection\<String>) : void
        - \+ getAlphabetizedCollection() : Collection \<String>
- \<package> model
    - **IndexProductionSystem implements ICircularShift \<abstract>**
        - \- lineManager: IAlphabetizableCollection
        - \- lineIO: ILineIO
        - \+ IndexProductionSystem(inputFilePath: String, outputFilePath: String)
        - \+ getLineManager() : IAlphabetizableCollection
        - \+ setLineManager(lineManager: IAlphabetizableCollection) : void
        - \+ getLineIO() : ILineIO
        - \+ setLineIO(lineIO: ILineIO)
        - \+ readLines() : void
        - \+ writeLines() : void
        - \+ circularShift() : void
        - \- circularShiftCollection() : void
        - \- circularShiftLine(line: String) : void
    - **KwicV2 extends IndexProductionSystem**
        - \+ KwicV2(inputFilePath: String, outputFilePath: String)
    - **KwicV3 extends IndexProductionSystem**
        - \+ KwicV3(inputFilePath: String, outputFilePath: String)
- Launcher.java


#### Part 3
*Implement the KWIC system functionality in both versions of your design. Create some sample files for testing to 
verify that the program is working correctly.*

- Found a text copy of War and Peace.
- Modified file to remove all special characters, extra whitespace, and lines like "BOOK", "EP".
- Output of KwicV1, V2, and V3 output to KWIC_Output.txt
- Created jUnit4 tests to test the timing each version takes and verify the output of each version.
    - Unfortunately, while nearly 50% faster than the other V1 and V3, V2 uses a data structure that does not allow 
    duplicates which is causing it to fail the verification test. A new data structure that acts like a TreeSet but 
    allows duplicates would need to be built from scratch.


#### Part 4
*Provide a brief technical summary to compare and contrast your two designs.*

- What criteria did you use to decompose the first design?
    - The criteria used to decompose the first design was to separate the main portions of the work into their own 
    separate functions.
    
- What criteria did you use to decompose the second design?
    - The criteria used to decompose the second design was to place the responsibility on the classes that implement a 
    user interface to fill the methods required by the interface. That way, the class is decoupled from the code that 
    uses the function calls by initializing an object based on the interface. This allows new versions of the code to 
    be created as long as they code against the interface the main program uses.
    
- Which design is more resilient to change? And why?
    - The second design is much more resilient to change. Due to the fact that the components have been decoupled 
    through the use of programming against an interface and initializing objects based off those interfaces, new 
    versions or classes can be used and substituded as long as they implement the proper interfaces.
    
- What would have to change in the first design/implementation if the file were changed out for a database?
    - Additional methods would need to be added to the class which violates the Open/Closed Principle
    
- What would have to change in the second design/implementation if the file were changed out for a database?
    - A new LineIO class would need to be created and tested as well as creating a new IndexProductionSystem extended 
    class that uses the new LineIO.
    
- What would have to change in the first vs second design/implementation if we wanted to use a graphical user interface 
instead of the traditional console UI (System.out)?
    - Currently, the program is outputing some extra information to the console for educational purposes. A controller 
    would need to be created and the output redirected through the controller. While the console output isn't necessary 
    to delete, especially for debugging purposes, it may be prudent to create a new IndexProductionSystem that removes 
    the System.out.println() method calls.
    
- Identify another change that may happen in the future, and how does design 1 compare to design 2 when it comes to 
impact of the change?
    - Should additional functionality, such as MicroServices need to be implemented, just to get it working and see 
    the functionality and the output, version 1 is much easier to follow. Version 2 can get complicated due to the 
    information hiding that is occuring.
    
- Which design/implementation is easier to understand?
    - Version 1 is much easier to understand.
    
- Which design/implementation is better performing?
    - Version 2 is much better performing (and version 3 within the version 2 package)
    
- How does the principle of information hiding relate to all of this?
    - Information hiding allows the classes to be built without clearly showing exactly what they are using such that 
    different classes and data structures can be swapped out with others as long as they provide the same type of 
    output.
    
#### Extra

- Implement jUnit unit testing for both of your designs/implementations, including some tests for some of the major 
functionality.
    - Algorithm's efficiency is tested
    - Version 1 v1 often fails a test of completing within 300 milliseconds.
    - Version 2 (with v2 and v3) pass. V2 is nearly 50% faster than the other V1. V3 is nearly 40% faster than V1 but 
    shows variation in efficiency due to the fact that it uses a Quick Sort.
    
    - **TimingTests**
        - \- TARGET_TIME_TO_COMPLETE: long
        - \+ testVersion1Timing() : void
        - \+ testVersion2Timing() : void
        - \+ testVersion3Timing() : void
        
    - Algoritm's accuracy is tested
    - Version 2 v2 fails due to the fact that it uses a TreeSet to build an alphabetical list with O(logn) efficiency 
    but it does not allow duplicates. A new data structure would need to be built from scratch to act like a Red-Black 
    Tree and allow duplicates.
    - Version 2 v2's TreeSet code has been commented out in favor of using Collections.sort()
    - Version 1 v1 and Version 2 v3 both pass the accuracy tests
    
    - **OutputTests**
        - \- ALPHABETIZE_CONTROL: ArrayList\<String>
        - \- FIRST_SHIFT_CONTROL: ArrayList\<String>
        - \- SECOND_SHIFT_CONTROL: ArrayList\<String>
        - \- MISMATCH_DATA: String
        - \- loadFileToList(filePath: String) : ArrayList\<String>
        - \+ testVersion1Output() : void
        - \+ testVersion2Output() : void
        - \+ testVersion3Output() : void
        - \- runOutputTest(kwic: IndexProductionSystem, outputFile: String) : void
        - \- testAgainstControl(control: String, outputToTest: List\<String>) : void
        - \- testAgainstControl(controlList: ArrayList\<String>, toTest: List\<String>) : void
    
- Is one design/implementation more testable than another? Is there anything that is not testable in either design? 
Explain why.
    - Version 2 is easier to test as each component is more modularized.
    - Version 1 required special accomodations for being able to test against a specific List due to the fact that 
    Version 2 v2 and v3 control tests used an IndexProductionSystem object.
    