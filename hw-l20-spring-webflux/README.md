Использовать WebFlux
<br>Цель: разрабатывать Responsive и Resilent приложения на реактивном стеке Spring c помощью Spring Web Flux и Reactive Spring Data Repositories
<br>Результат: приложение на реактивном стеке Spring

1. Задание выполняется на основе ДЗ с MongoDB.
2. Вы можете выбрать другую доменную модель (не библиотеку) и другую БД (Redis).
3. Необходимо использовать Reactive Spring Data Repositories. Использовать PostgreSQL и экспериментальную R2DBC не рекомендуется.
4. RxJava vs Project Reactor - на Ваш вкус.
5. Вместо классического Spring MVC и embedded Web-сервера использовать WebFlux.
6. Опционально: реализовать на Functional Endpoints

Данное задание НЕ засчитывает предыдущие!

<p>Рекомендации:
<br>Старайтесь избавиться от лишних архитектурных слоёв. Самый простой вариант - весь flow в контроллере.