
Tech Stack:
- BDD Test Framework: Cucumber
- Test Runner: cucumber-testng
- Project Management: Maven
- Programming language: Java (SDK 11)
- Report: Extent Report (good customize ability)
- Web Automate Tool: Selenium
- IDE: IntelliJ

Test Environment:
- MAC OS Big Sur ( Apple M1)
- Chrome version 107.x.x

Requirement Analysis:
- Scenario 01:
  - Currently, given 'BusinessAccount.feature' file has defined Scenarios
  as 'Scenario Outline' with Data express via 'Examples' and tested data is arranged following column of option,
  example: 'Annual / Setup Fees' column contains all related data
  - My analysis: We can do it in other way as
    - Use Data Table for verification step without running multiple times for each line of data in table (Scenario Outline) as we could get all data from Sleek web in one run
    - Organize Data table to be same structure on web DOM, it would help reduce data customizing step in code & easy debugging, checking
    -> However, for this case, assumption that it is challenge for this task ^^ so I decided moving on with current design input of data table
- Scenario 02: For logic of 'select a date "10" days from now' step, I have not clear about case: if current date
is from '21 ->...', result will be next month, I am not able to check this behavior, so I marked it as Observation and further update for this logic might be required

Design:
- Divide test file (*.feature) & logic based on pages
  + 'Base' class contains common actions of web interaction (DRY)
  + Annotation '@FindBy' is used somewhere to archive POM
- Singleton for web-driver instance
- Driver version is managed by WebDriverManager
- Wrap common steps definition to 'CommonSteps' class for reusable script (DRY)
- Apply Explicit wait

How to run:
- Maven: Terminal > mvn clean test
- IntelliJ: From 'TestRunner.class' > Run As TestNG

Report:
- Report is generated at 'test-output/SleekReport/SleekReport.html'
