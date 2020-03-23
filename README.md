# jptests
Java Primitive Tests solution, for when you cannot (or don't want to) use a full unit test framework like junit, but still want to do some basic testing with minimal dependency. Simply copy a file to your project and start writing tests. 

JPTest started as I started learning java as part of a university course. Coming from some professional programming experience, tests were the thing that I missed most. 
In java setting up full testing framework is difficult, at least to somebody just strating to learn the Java eco-system. Most of the learning resources show how to start 
developing java application without external packages, without build tools to make understanding what is going on easy. But without external packages and build tools 
it appears it is not possible. 

This project attempts to bridge that gap. By simply dropping the JPTests.java file next to the class file you are developing allows you to create a simply test class, that helps to create some basic automated, unit tests. 

I am also using this project to experiment with how programming language course could be structured to introduce stricter than usually adhearance to the SOLID principles, TDD and OOP earlier rather than later. At the same time looking for useful real life examples. 

## Steps

In each step the problem will be implemented in a number of ways to look for a good starting point. 

the same example problems will be implemented in different ways

1. procedural - typical start to programming
2. object oriented - typical entry level object programming examples, breaking good design principles in the OOP, for simplicity of the example. 
3. testable - oop, but with good practicies that allow simple testing with JPTest

### 01 Hello World
The most basic programming example

The object oriented example is difficult to test, it also breaks the single responsibility principle. The `Greeter` class is responsible for constructing the message, and communicating with the user. It has also unnecessary dependencies on `System.out`.

The `Greeter` is an object with behaviour but without data. 

### 02 Counter
A step after the hello world. 