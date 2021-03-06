## Тестирование сервиса "Бронирование тура".
В данном проекте реализована автоматизация тестирования сервиса бронирования тура в Марракеш.

### Документация:
 1. План автоматизации: https://github.com/BerezovTimur/Final/blob/master/documentation/Plan.md; 
 2. Отчет по автоматизации: https://github.com/BerezovTimur/Final/blob/master/documentation/Report.md;
 3. Саммари: https://github.com/BerezovTimur/Final/blob/master/documentation/Summary.md

### Необходимый инструментарий
Убедитесь, что на вашем ПК установлены или установите:

1. Git
2. IntelliJ IDEA
3. Docker
4. Google Chrome

### Начало работы

Скопировать проект через GIT на свой ПК командой: git clone https://github.com/BerezovTimur/Final.git

### Установка и запуск сборки

1. Запустить контейнеры Docker командой: `docker-compose up -d`
2. Запускаем приложение командой: 
     - для mysql:
`java -Dspring.datasource.url=jdbc:mysql://192.168.99.100:3306/app -jar artifacts/aqa-shop.jar`
     - для postrgre:
 `java -Dspring.datasource.url=jdbc:postgresql://192.168.99.100:5432/postgres -jar artifacts/aqa-shop.jar`
3. Запускаем тесты:
     - для mysql:
       - `gradlew test -Ddb.url=jdbc:mysql://192.168.99.100:3306/app -Dlogin=app -Dpassword=pass -Dapp.url=http://localhost:8080`
     - для postrgre:
       - `gradlew test -Ddb.url=jdbc:postgresql://192.168.99.100:3306:5432/postgres -Dlogin=app -Dpassword=pass -Dapp.url=http://localhost:8080`
 
