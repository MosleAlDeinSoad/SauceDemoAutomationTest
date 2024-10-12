# SauceDemo Test Automation

## Project Structure
- **src/**: Contains test classes.
- **reports/**: Directory for test reports.
- **README.md**: This file.

## Prerequisites
- Java 22 or above
- ChromeDriver installed and path set
- Maven installed (if using Maven)

## Running Tests
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd assignment4
   ```

2. Compile the Java files:
    ```bash
    javac -d bin src/com/example/*.java
    ```

3. Run the tests::
    ```bash
    java -cp bin com.example.SauceDemoLoginTest1
    ava -cp bin com.example.SauceDemoPurchaseTest2
    java -cp bin com.example.SauceDemoPerformanceTest3

    ```

