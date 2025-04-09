`docker image ls` - посмотреть все образы, которые есть

`docker build .` - собрать образ из докерфайла

`docker run -dit -p 5432:5432 <первые 4 цифры id образа>` - запустить контейнер с бд

`docker run -dit -p 5432:5432 -v postgres_data:/var/lib/postgresql/data <первые 4 цифры id образа>` - запуститьь контейнер с бд с volume

`docker ps` - посмотреть запущенные контейнеры

`docker stop <имя контейнера>` - остановить контейнер

