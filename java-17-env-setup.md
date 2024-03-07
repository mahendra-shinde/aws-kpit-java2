Java 17 Dev Environment
-------------------------

Locate your JDK-17 Installation directories

My System:
C:\Program Files\Java\jdk-17

Your System:
C:\Program Files\jdk-17.0.1

Setup USER Environment Variable:
Start Menu > type "ENV" > "Edit Env Vars for YOUR account"

in "User Variables" section, click "NEW" button to create :
	Env Name: JAVA_HOME
	Value : C:\Program Files\jdk-17.0.1

Select another Variable "PATH" and click "Edit"
Add new value "%JAVA_HOME%\bin" (No space anywhere inside this value)

Remove any JDK path from "PATH" variable (of an older JDK)

"Install" apache maven from Self Service portal.
Folder to extract "C:\" 

Locate the folder in windows explorer and find the "bin" folder
your BIN folder path should be : 
	C:\apache-maven-3.9.5\bin

Edit Env Var for "Your Account" and add new Entry in "Path" variable
C:\apache-maven-3.9.5\bin

OPen a new CMD and try following commands:
javac -version
java -version
mvn --version






