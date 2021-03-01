# kotlin-vue-mongo-app
This is an example of web application comprising of several components communicating to each other. The app itself is a simple blog app.

## Components

1. [Frontend](/frontend) provides UI. Created with [VueJS 3](http://vuejs.org).
2. [Blog API](/blog-api) is a Spring Boot project written in Kotlin. Provides full CRUD a Post entity that has title and description fields.
3. [MongoDB](https://www.mongodb.com/) used for volume post data storage.

## How to start

The easiest way is to use `docker-compose`:

```
docker-compose up
```

Then go to http://127.0.0.1:8080 for web UI.
