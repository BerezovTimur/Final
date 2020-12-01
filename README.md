<h2>Тестирование сервиса "Бронирование тура".</h2>
Тестирование бронирования тура в Марракеш

<h3>Необходимый инструментарий</h3>
Убедитесь, что на вашем ПК установлены или установите:

1. Git
2. IntelliJ IDEA
3. Docker
4. Google Chrome

<h3>Начало работы</h3>

Скопировать проект через GIT на свой ПК командой: git clone https://github.com/BerezovTimur/Final.git

<h3>Установка и запуск сборки на MySQL</h3>

1. Переключится на ветку master командой:
    `git checkout master`
2. Запустить контейнеры Docker командой: `docker-compose up -d`
3. Подключить БД командой:
    `docker-compose exec mysql mysql -u app -p app -v`.
    Ввести пароль `pass`
3. Запускаем симулятор банковских сервисов:
    - `cd gate-simulator && npm start`
4. Запускаем приложение командой: `java -jar artifacts/aqa-shop.jar`
5. Запускаем тесты: `gradlew clean test`
6. Для повторного использования тестов необходимо перезапустить SUT

<h3>Установка и запуск сборки на PostgreSQL</h3>

1. Переключится на ветку postgre командой:
    git checkout postgre
2. Запустить контейнеры Docker командой: docker-compose up
3. Запускаем симулятор банковских сервисов:
    - переходим в папку симулятора командой: cd gate-simulator
    - запускаем симулятор командой: npm start
4. Запускаем приложение командой: java -jar artifacts/aqa-shop.jar
5. Запускаем тесты: gradlew clean test
6. Для повторного использования тестов необходимо перезапустить SUT