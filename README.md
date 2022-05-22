# QuotationProject

**About the project**

- Project is creaeted based on BDD/Cucumber Framework.


- All necessary dependencies are configure in pom.xml file.


- Test Scenarios can be found in feature files under features folder. Each scenario is written in Gherkin language which is plain English just to make it easy to understand for Non-technical stuff as well.


- Steps are in Given - When - Then format and each test step has corresponding step definition files under stepDefinition folder where test cases meet with Java language. Assertions are handled in step definitions files by using Junit.


- Project apis are also handled in QuotationEngine file under engines folder by using RestAssured Libraries. It is created methods for end points where each of them are called from step definitions files.


- CreateNewMember method is overloaded in order to handle TestUser options which are created with random data by using faker class.


- In order to create a test user with random fake data, it is created a TestUserUtil under utilities folder.


- And in same folder it is also created data transfer object classes for each end point request in order to create payloads easily by using builder.


- Also it is created TestData.properties file under properties folder which allows to get some constant data dynamically outside of the project and can be read via TestDataReader.

**How to run project**

- It is created runner classes under runner folder which allows us to run all tests from one place and coordinate them.

- Test can be run from TestRunner Class and if we only need to run failed test which couple of bugs are spotted so some tests fail in the project, them can be run from FailedTestRunner.

- If you need to run some specific test, them can be run by changing the tags in runer class providing correct annotation for grouped tests.

- And also inside features files, any test can be run manually

- It is also provided cucumber-html-reports in the project which can be seen under target folder after running the tests.