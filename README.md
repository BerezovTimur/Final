<h2>Тестирование сервиса "Бронирование тура".</h2>

В данном проекте реализована автоматизация тестирования сервиса бронирования тура в Марракеш.
План автоматизации можно просмотреть по следующей ссылке https://github.com/BerezovTimur/Final/blob/master/documentation/Plan.md

<h3>Необходимый инструментарий</h3>
Убедитесь, что на вашем ПК установлены или установите:

1. Git
2. IntelliJ IDEA
3. Docker
4. Google Chrome

<h3>Начало работы</h3>

Скопировать проект через GIT на свой ПК командой: git clone https://github.com/BerezovTimur/Final.git

<h3>Установка и запуск сборки на MySQL</h3>

1. Запустить контейнеры Docker командой: `docker-compose up -d`
3. Запускаем симулятор банковских сервисов:
    - `cd gate-simulator && npm start`
5. Запускаем приложение командой: `java -jar artifacts/aqa-shop.jar`
6. Запускаем тесты: `gradlew clean test`
7. Для повторного использования тестов необходимо перезапустить SUT

<h3>Установка и запуск сборки на PostgreSQL</h3>

1. Переключится на ветку postgre командой:
    `git checkout postgre`
2. Запустить контейнеры Docker командой: `docker-compose up`
3.
4. Запускаем симулятор банковских сервисов:
    - `cd gate-simulator && npm start`
5. Запускаем приложение командой: `java -jar artifacts/aqa-shop.jar`
6. Запускаем тесты: `gradlew clean test`
7. Для повторного использования тестов необходимо перезапустить SUT