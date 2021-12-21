Описание
===========

Сервис, который обращается к сервису курсов валют, и отображает gif:
--------------------------------------------------------------------
- если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
- если ниже - отсюда https://giphy.com/search/broke

Ссылки
------
- REST API курсов валют - https://docs.openexchangerates.org/
- REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide

Инструкция по запуску
---------------------
С использованием DockerHub:

`Выполнить команды в командной строке

- docker pull kolian0071/myrepo:latest
- docker run -d  -p8080:8080 gif-api

После запуска контейнера и старта сервиса необходимо в браузере (или Postman) ввести URL:
http://localhost:8080/api/v1/gif/[currency] , где

[currency] - код доступной валюты, доступной на https://docs.openexchangerates.org/docs/supported-currencies (например, USD)

Однако, для стандартного тарифа в качестве базовой валюты сервис openexchangerates.org предоставляет только USD. 

Настройки базовой валюты и другие параметры работы сервиса можно поменять в application.yml файле. 

Сервис возвращает клиенту JSON объект c адресом GIF и тэгом в соответствии с заданием.
Пример запроса:
http://localhost:8080/api/v1/gif/USD

Пример ответа:

![image](https://user-images.githubusercontent.com/96447510/146968353-52a78e5e-7bda-4211-8be7-5df00257bde7.png)

Также в сервисе реализована проверка на корректность введенной валюты.
Пример запроса:
http://localhost:8080/api/v1/gif/USDD

Пример ответа:

![image](https://user-images.githubusercontent.com/96447510/146968407-722a320e-6ac6-4490-81f7-ada90ff5aa1e.png)
