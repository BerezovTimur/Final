<h2>Тестирование сервиса "Бронирование тура".</h2>
В данном проекте реализована автоматизация тестирования сервиса бронирования тура в Марракеш.

<h3>Документация:</h3>
 - План автоматизации: https://github.com/BerezovTimur/Final/blob/master/documentation/Plan.md
 - Отчет по автоматизации: https://github.com/BerezovTimur/Final/blob/master/documentation/Report.md
 - Саммари:

<h3>Необходимый инструментарий</h3>
Убедитесь, что на вашем ПК установлены или установите:

1. Git
2. IntelliJ IDEA
3. Docker
4. Google Chrome

<h3>Начало работы</h3>

Скопировать проект через GIT на свой ПК командой: git clone https://github.com/BerezovTimur/Final.git

<h3>Установка и запуск сборки</h3>

1. Запустить контейнеры Docker командой: `docker-compose up -d`
2. Запускаем приложение командой: 
     - для mysql:
`java -Dspring.datasource.url=jdbc:mysql://192.168.99.100:3306/app -jar artifacts/aqa-shop.jar`
     - для postrgre:
 `java -Dspring.datasource.url=jdbc:postgresql://192.168.99.100:5432/postgres -jar artifacts/aqa-shop.jar`
3. Запускаем тесты:
     - для mysql:
       - в SQLHelper устанавливаем public static String url = "jdbc:mysql://192.168.99.100:3306/app"; 
       - `gradlew test -Ddb.url=jdbc:mysql://192.168.99.100:3306/app -Dlogin=app -Dpassword=pass -Dapp.url=http://localhost:8080`
     - для postrgre:
       - в SQLHelper устанавливаем public static String url = "jdbc:postgresql://192.168.99.100:3306:5432/postgres";
       - `gradlew test -Ddb.url=jdbc:postgresql://192.168.99.100:3306:5432/postgres -Dlogin=app -Dpassword=pass -Dapp.url=http://localhost:8080`
 
