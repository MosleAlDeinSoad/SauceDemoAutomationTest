# SauceDemo Test Automation

## Project Structure
- **src/**: Contains test classes.
- **reports/**: Directory for test reports.
- **README.md**: This file.

## Prerequisites
- Java 22 or above
- ChromeDriver installed and path set
- Maven installed

## Running Tests
1. Clone the repository:
    ```bash
    git clone https://github.com/MosleAlDeinSoad/SauceDemoAutomationTest.git
    cd assignment4
    ```

2. Running Tests Sequentially using Maven:

    Run all tests sequentially by executing the following command in your terminal:
     ```bash
     mvn clean test
     ```

3. Running Individual Tests:

    To run individual tests, you can specify the test class name as follows:
    ```bash
    mvn -Dtest=SauceDemoTest#test1 clean test
    ```

4. Check the `reports/` directory for the generated reports.