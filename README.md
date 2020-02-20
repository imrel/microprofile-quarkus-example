# microprofile-quarkus example

This project uses Quarkus (https://quarkus.io/) - the Supersonic Subatomic Java Framework.

## IDE configuration 
Enable `"Prefer to use gradle wrapper that comes with the project"` configuration option  

## Build the application
```
./gradlew build
```

## Running the application in dev mode
You can run your application in dev mode that enables live coding using:
```
./gradlew quarkusDev
```

## Welcome page
Application welcome page available by http://0.0.0.0:8080/

## Open API Swagger UI
Application API through Swagger UI available in the development mode by http://0.0.0.0:8080/swagger-ui/


## Packaging and running the application
The application is packageable using `./gradlew quarkusBuild`.
It produces the executable `ua-hackaton-1.0.0-runner.jar` file in `build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

The application is now runnable using `java -jar build/ua-hackaton-1.0.0-runner.jar`.

If you want to build an _über-jar_, just add the `--uber-jar` option to the command line:
```
./gradlew quarkusBuild --uber-jar
```

## Creating a native executable
You can create a native executable using: `./gradlew buildNative`.
Or you can use Docker to build the native executable using: `./gradlew buildNative --docker-build=true`.
You can then execute your binary: `./build/ua-hackaton-1.0.0-runner`
If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling#building-a-native-executable .
