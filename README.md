# My Scalatra Web App

## Build & Run

```sh
$ cd my-scalatra-web-app
$ sbt
> jetty:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.

## generating swagger documentation

this project uses scalatra-swagger to generate swagger documentation.
the generated documentation is in generated.yaml file.
