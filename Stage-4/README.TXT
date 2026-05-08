Airline Management System
Final Submission

This submission includes both:
1. The full NetBeans source code project
2. The executable JAR file with the required data files

==================================================
REQUIRED FILE/FOLDER STRUCTURE
==================================================

After downloading and unzipping the submission, the folder should look like this:

AirlineSystem_Final_Submission/
│
├── Source_Code/
│   └── AirlineSystem/
│       ├── src/
│       │   └── AirlineSystem/
│       │       └── .java and .form files
│       │
│       ├── nbproject/
│       ├── build.xml
│       └── manifest.mf
│
├── Executable_JAR/
│   ├── AirlineSystem.jar
│   └── data/
│       ├── airports.txt
│       ├── flights.txt
│       ├── staff.txt
│       ├── passengers.txt
│       ├── bookings.txt
│       └── tickets.txt
│
└── README.txt

The data folder should stay in the same folder as AirlineSystem.jar************************

==================================================
HOW TO RUN THE EXECUTABLE
==================================================

1. Open the Executable_JAR folder.
2. Make sure the data folder is next to AirlineSystem.jar.
3. Double-click AirlineSystem.jar.

(If that doesnt work)

1. Open Command Prompt
2. Navigate to the Executable_JAR folder.
3. Run:

java -jar AirlineSystem.jar


Ex:

cd path\to\AirlineSystem_Final_Submission\Executable_JAR
java -jar AirlineSystem.jar


==================================================
FOR SOURCE CODE FILES
==================================================

1. Open NetBeans.
2. Click File.
3. Click Open Project.
4. Navigate to:

AirlineSystem_Final_Submission/Source_Code/

5. Select the AirlineSystem project folder.
6. Click Open Project.
7. Run the project from Main.java.


+++++++++++++++++++++++++++++++++++++++++++++++++++

The project uses the package name:

AirlineSystem

Do not rename the package folder:

src/AirlineSystem/

The program saves and loads data from the data folder using text files.

The required text files are:

airports.txt
flights.txt
staff.txt
passengers.txt
bookings.txt
tickets.txt

If these files are moved away from the jar, the executable may not load the saved system data correctly