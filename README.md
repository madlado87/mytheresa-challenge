# Prueba de QA MyTheresa 

## Construido con 🛠️

* [Testng](https://testng.org/doc/index.html) - El framework de test usado
* [Cucumber](https://cucumber.io/) El framework de test usado
* [WebDriverExtensions](https://github.com/webdriverextensions/webdriverextensions) - El framework de selenium usado para test de UI
* [RestAssured](https://testng.org/doc/index.html) - El framework para test de backend usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias

### Pre-requisitos 📋

* Java 1.8
* Maven

## Ejecutando las pruebas ⚙️
### Ejecutar los test en tu maquina local
`mvn clean test -D test.execution=local -Pproduction`
### Ejecutar los test en selenium grid
```
./bin/build.sh -> construye la imagen
./bin/testrun.sh --help
```


## Autor ✒️

* **Miguel A. Delgado L.** - *codigo* - [madlado87](https://github.com/madlado87)
