mvn test -Dcucumber.filter.tags="@AddBook" - для запуска тестов с тегами из кукумбера

mvn clean test '-Dgroups = apiTest' - для запуска группы тестов

mvn clean test '-Dsurefire.suiteXmlFiles=src/test/resources/testng.xml'  - запуск файла testng.xml