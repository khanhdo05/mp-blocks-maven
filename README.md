# Mini Project 3: Blocks Maven

## Description

Explorations with the creation, composition, and mutation of blocks of ASCII text.

## Authors

- Samuel A. Rebelsky (starter code).
- Khanh Do
- Alex Pollock

## Github Repo

This code may be found at <https://github.com/khanhdo05/mp-blocks-maven/tree/alex>. The original project can be found at <https://github.com/Grinnell-CSC207/mp-blocks-maven>.

## Running the Program

### Prerequisites

Before running the code, make sure you have the following installed:

1. **Java Development Kit (JDK) 8 or higher**

   - Download and install from [Oracle's JDK page](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://openjdk.java.net/).
   - Verify installation by running `java -version` and `javac -version` in your terminal.

2. **Apache Maven**

   - Download and install Maven from [Maven's official website](https://maven.apache.org/download.cgi).
   - Set up environment variables `M2_HOME` and add Maven's `bin` directory to your `PATH`.
   - Verify installation by running `mvn -version` in your terminal.

3. **Text Editor or Integrated Development Environment (IDE)**

   - **Visual Studio Code** or any other text editor/IDE of your choice for editing and managing code files.

4. **Build Tools**

   - Maven will handle project dependencies and building. Ensure Maven is configured correctly as described above.

5. **Compile**
   ```bash
   mvn compile -q
   ```

### To run test

```bash
mvn test -q
```

### To check style

```bash
mvn checkstyle:check -q
```
