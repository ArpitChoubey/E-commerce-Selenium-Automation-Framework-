# 🛒 E-commerce Selenium Automation Framework

A robust and scalable Selenium Automation Framework designed for E-commerce web applications, built with Java, Selenium WebDriver, TestNG, Maven, and Extent Reports.  
This framework follows the Page Object Model (POM) and supports data-driven testing, logging, reporting, and screenshot capture.

---

## 📌 Key Features

- ✅ Selenium WebDriver with Java
- ✅ TestNG for test execution & assertions
- ✅ Page Object Model (POM) design pattern
- ✅ Data-Driven Testing using Excel
- ✅ Extent Reports for detailed execution reports
- ✅ Log4j for logging
- ✅ Screenshot capture on failure
- ✅ Cross-browser execution support
- ✅ Maven-based project structure
- ✅ Easy to extend and maintain

---

## 🏗️ Project Structure

EcommerceSeleniumAutomationFramework
│
├── TestData
│ └── CDAs.xlsx
│
├── logs
│ └── automation.log
│
├── reports
│ └── Extent-YYYY.MM.DD.HH.MM.SS.html
│
├── screenshots
│ ├── SparkleCart_Logo.png
│ ├── captureSparkleCartLogo_.png
│ ├── verifyFAQsNavigation_.png
│ └── verifyLogoIsDisplayed_*.png
│
├── src/test/java
│ ├── pageObjects
│ │ ├── HomePage.java
│ │ └── ContactPage.java
│ │
│ ├── testBase
│ │ └── BaseTest.java
│ │
│ ├── testCases
│ │ └── ECommerceTestCases.java
│ │
│ └── utilities
│ ├── DataProviders.java
│ ├── ExcelUtils.java
│ ├── ExtentReport.java
│ ├── Utilities.java
│ └── Utilities2.java
│
├── src/test/resources
│ └── log4j2.xml
│
├── testng.xml
├── crossbrowsertestng.xml
├── pom.xml
└── README.md


---

## ⚙️ Tech Stack

- **Programming Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Test Framework:** TestNG  
- **Build Tool:** Maven  
- **Reporting:** Extent Reports  
- **Logging:** Log4j  
- **Design Pattern:** Page Object Model (POM)  
- **Data Source:** Excel (Apache POI)

---

## 🚀 How to Run the Tests

### 1️⃣ Prerequisites
- Java JDK (8 or above)
- Maven
- Eclipse / IntelliJ IDE
- Chrome / Firefox browser
- WebDriver binaries added to system path

---

### 2️⃣ Clone the Repository
```bash
git clone https://github.com/ArpitChoubey/E-commerce-Selenium-Automation-Framework-.git
3️⃣ Import Project
Import as Maven Project in Eclipse or IntelliJ

Allow Maven dependencies to download

4️⃣ Execute Tests
Using TestNG XML

mvn test
OR

Right-click on testng.xml

Select Run As → TestNG Suite

📊 Reports & Logs
📄 Extent Reports:
Generated under /reports folder with timestamped HTML files

📝 Logs:
Stored in /logs/automation.log

📸 Screenshots:
Automatically captured on failure and stored in /screenshots

📈 Framework Highlights
Clean separation of test logic and page locators

Reusable utility methods

Centralized WebDriver management

Easy configuration and scaling

Suitable for real-world E-commerce automation projects

👨‍💻 Author
Arpit Choubey
SDET | QA | Automation Engineer

🔗 LinkedIn: https://www.linkedin.com/in/arpitchoubey/
✍️ Medium: https://medium.com/@ArpitChoubey9

⭐ Support
If this repository helped you, please star it to support and motivate further contributions.

🧪 Happy Testing 🚀
