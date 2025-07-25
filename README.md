# Loan Calculator CLI

A simple Java command-line app that computes monthly payments and total interest for a loan.

---

## Prerequisites

1. **Java JDK** (11 or higher)  
2. **Maven** (3.x) _or_ **Gradle** (optional—see below)

---

## Getting Started

Open your terminal and run:

```bash
# 1. Clone the repo
git clone https://github.com/Chief03/loan-calculator.git

# 2. Change into the project directory
cd loan-calculator
```

# Build & Package
With Maven
```
# bash
# Download dependencies, compile, run tests, and build a fat JAR
mvn clean package
```
# After this completes, you’ll find your runnable JAR in:

```
# bash
target/loan-calculator-1.0.0.jar
```

With Gradle
If you prefer Gradle, we’ve included a build.gradle—just run:

```
# bash
./gradlew clean build
```
Your JAR will land in:
```
# bash
build/libs/loan-calculator-1.0.0.jar
```

Run the App
```
# bash
java -jar target/loan-calculator-1.0.0.jar
```

or (if using Gradle)
```
# bash
java -jar build/libs/loan-calculator-1.0.0.jar
```
Once it starts, you’ll see prompts like:

```
#java
Enter loan principal (or 'quit'):
Enter annual rate (%)      :
Enter term (years)         :
→ Monthly payment: $536.82
→ Total interest: $93 255.20
```
Type quit at any prompt to exit.


