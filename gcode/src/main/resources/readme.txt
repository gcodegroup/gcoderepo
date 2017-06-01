1. Use NetBeans to run the Gcode application.
    1.05 Right click the Gcode project node and select "Git/Remote/Pull...".
    1.10 Right click the Gcode project node and select "Clean and Build".
    1.20 Open the uk.ac.bangor.gcode package and open the RunningParameters.java class.
    1.30 Change the default parameters.
    1.40 right click the CommandLineRunner.java class and select "Run".

2. Install the Gcode Translator on a Windows Computer.

3. Logging Configuration
The logging system has several logging levels which can be set in the first line of the file log4j.properties.
OFF   - The logging is turned off.
FATAL - Only severe errors that cause premature termination will be logged. 
ERROR - All runtime errors or unexpected conditions will be logged. All FATAL level information will also be logged. 
WARN  - Poor of use the application will be logged. All ERROR level information will also be logged. 
INFO  - Interesting runtime information are logged. All WARN level information will also be logged. 
DEBUG - More information on the flow through the system will be logged. All INFO level information will also be logged.
TRACE - The Most detailed information will be logged. In this application. The TRACE level is the same as DEBUG level.