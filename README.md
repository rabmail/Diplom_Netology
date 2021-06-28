# Тестирование веб-сервиса "Путешествие дня"
**Приложение предлагает купить тур по определённой цене с помощью двух способов:**

1. Обычная оплата по дебетовой карте
1. Оплата в кредит по дебетовой карте

**_Уникальная технология: выдача кредита по данным банковской карты_**
- Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
1. сервису платежей (Payment Gate)
1. кредитному сервису (Credit Gate)

Приложение должно в собственной СУБД сохранять информацию о том, каким способом был совершён платёж и успешно ли он был совершён,
для хранения информации можно использовать на выбор одну из перечисленных СУБД (MySql, PostgreSQL)

**_Полное описание задания доступно по ссылке:_** **_[Описание задания](https://github.com/netology-code/qa-diploma "Описание задания")_**

## Процедура запуска тестов:

## Запуск сервиса "Путешествие дня"
1. Запускаем контейнеры MySql, PostgreSQL и Node:  docker-compose up -d --force-recreate
1. Проверяем, что контейнеры запустились: docker-compose ps

## Запуск приложения с базой MySql:
1. Запускаем приложение и передаем базу MySql:
java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar

при успешном запуске на экране появится сообщение: _Started ShopApplication in 9.766 seconds (JVM running for 11.274)_

1. Запускаем тесты командой:
gradlew clean test -Durl=jdbc:mysql://localhost:3306/app -Duser=user -Dpassword=pass -Dwebsite=http://localhost:8080

## Запуск приложения с базой PostgreSQL
1. Запускаем приложение и передаем базу PostgreSQL: 
java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar

при успешном запуске на экране появится сообщение: _Started ShopApplication in 9.766 seconds (JVM running for 11.274)

1. Запускаем тесты командой:
   gradlew clean test -Durl=jdbc:postgresql://localhost:5432/app -Duser=user -Dpassword=pass -Dwebsite=http://localhost:8080

**Ссылки:

Перезапуск приложения и тестов
Если необходимо перезапустить приложение, нужно остановить работу сервисов в терминале, нажав в них Ctrl+С

Формирование отчета AllureReport по результатам тестирования
При первоначальном формировании отчета необходимо выполнить команду 1 раз - gradlew allureReport (для загрузки AllureReport )

Отчет формируется командой - gradlew allureServe

Отчет откроется в браузере автоматически. 

**[План тестирования](https://github.com/rabmail/Portfolio/blob/850c090453cb6c0ef8b7459c6cdbbe4f62e878bc/Report/Plan.md "План тестирования")**

**[Отчет о тестировании](https://github.com/rabmail/Portfolio/blob/b7187f6e455a59a1c86ce2b035e251c647cec4a4/Report/Report.md "Отчет о тестировании")**
