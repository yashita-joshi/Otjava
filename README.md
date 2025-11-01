# Otjava

Instructions
How to Save and Compile the Code
Save Student.java, ClassRoom.java, and ClassManagementSystem.java in the same directory (e.g., ClassManagement/).

Use the .java extension for source files.

In a terminal (Command Prompt, PowerShell, or Terminal), navigate to this directory.

Compile with:

text
javac *.java
How to Deploy and Run Locally
After compiling, run the system with:

text
java ClassManagementSystem
The program will attempt to load previous saved data from classesData.ser in the same directory. If not found, it starts clean.

No external libraries are required beyond standard Java (Java 8+ recommended).

How to Use the System (Sample Interaction)
On launch, you'll see a numbered menu.

Choose actions by entering numbers:

(1) Add new class: Enter class name, course code, instructor, maximum seats.

(2) Register student: Input student ID and name.

(3) List all classes: See details of every class.

(4) Enroll: Enter student ID and course code.

(5) Remove: Enter student ID and course code.

(6) Show students in class: Enter course code to view enrolled students.

(7) Save: Persist data to file.

(8) Exit: Data auto-saves and program stops.

Where to Save Files
.java files: Your source code in one folder.

Data file: classesData.ser will be created and updated automatically in the same folder as your source code.

No manual creation of data files is needed.

How Data is Saved and Loaded
Saving: Choose menu option (7) or exit (8), data writes to classesData.ser using Java serialization.

Loading: On program start, it reads classesData.ser automatically if present. If file is missing or corrupted, the system starts fresh.
