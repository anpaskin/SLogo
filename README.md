# SLogo

SLogo is an IDE for the Logo programming language, a language used for early computer programming education that allows users to enter commands to be executed by turtles on a canvas. For example, entering "fd 100" commands the turtle to move forward 100 steps.

David Tran, Ryan Chung -- Front-End

Aaron Paskin, Ian Eldridge-Allegra -- Back-End

10/10/2017 to 10/30/2017

Resources cited in line where applicable. 

To run: Main.java

Several included resource files are required: 
* language properties files
* SyntaxErrors.properties
* choicebox.properties
* view.css
* view.properties
* completeCommands.properties
* data files of saved variables, including KnownFiles.properties

Included:
* All basic commands
* All extended commands
* Grouping
* Recursion

Some basic design decisions and errors:
* Unlimited arguments for differences and quotients are handled as the first argument minus/divided by all others.
* Tell, ask, askwith will throw an exception when nested inside turtle commands -- you can't ask each turtle to tell other turtles something.
