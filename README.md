# AB_Soft
## Prerequisites
You should download and install allure, maven, java

## Launch of the project
1. Fork github repository 
2. Clone github repository git clone <ссылка на репозиторий>
3. Open terminal in cloned project, and write command: mvn clean install
4. Open directory /target/surefire-reports/Suite and open emailable-report.html and index.html in Chrome browser, and as result you can see test reports

## Launch of the project (alternative)
1. First two steps are the same
2. Open terminal in cloned project, and write command: mvn clean install, as a result you will see created folder allure-results
3. Go to terminal, and write command allure serve <путь к allure-results папке>, wait for a few seconds, and as a result you will see allure report in Chrome browser
