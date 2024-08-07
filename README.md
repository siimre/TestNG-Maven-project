# Loan Calculator Tests

This repository contains automated tests for the Loan Calculator application using TestNG. The tests cover various scenarios, including validation, default values, and calculations to ensure the robustness and reliability of the loan application page.

## Project Overview

The Loan Calculator Tests project aims to validate the functionality of the loan application page, ensuring it handles various user inputs correctly and provides accurate calculations. The tests are designed to cover:

- Validating input fields for loan amount and period.
- Checking default values displayed on the page.
- Ensuring calculations for monthly payments are correct.
- Verifying the responsiveness of the application across different screen sizes.

## Getting Started

To get started with running the tests, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone [repository_url]
   ```

2. **Navigate to the project directory:**
   ```bash
   cd LoanCalculatorTests
   ```

3. **Run the tests using Maven:**
   ```bash
   mvn clean test
   ```

## Test Cases

### InvalidLoanAmountTest
Tests rejection of invalid loan amounts.

### InvalidLoanPeriodTest
Verifies enforcement of loan period restrictions.

### UpdateLoanAmountTest
Ensures correct monthly payment update for valid loan amounts.

### UpdateLoanPeriodTest
Checks monthly payment recalculation for valid loan periods.

### DefaultLoanPeriodTest
Validates default loan period display on page load.

### DefaultLoanAmountTest
Ensures default loan amount display on page load.

### openLoanPageTest
Verifies the loan application page loads correctly.
