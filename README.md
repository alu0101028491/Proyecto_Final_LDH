[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=alu0101028491_Proyecto_Final_LDH&metric=bugs)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=alu0101028491_Proyecto_Final_LDH&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=alu0101028491_Proyecto_Final_LDH&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=alu0101028491_Proyecto_Final_LDH&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH)  [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=alu0101028491_Proyecto_Final_LDH&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=alu0101028491_Proyecto_Final_LDH&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH) 

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=alu0101028491_Proyecto_Final_LDH&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH) [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=alu0101028491_Proyecto_Final_LDH&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=alu0101028491_Proyecto_Final_LDH&metric=coverage)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=alu0101028491_Proyecto_Final_LDH&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=alu0101028491_Proyecto_Final_LDH) [![CircleCI](https://dl.circleci.com/status-badge/img/gh/alu0101028491/Proyecto_Final_LDH/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/alu0101028491/Proyecto_Final_LDH/tree/main)


## Sistema recomendador de peliculas 
Proyecto final de la asignatura de laboratorio de desarrollo y herramientas. Este trabajo ha consistido en el an??lisis y mejora de un proyecto software heredado sobre motores de recomendaci??n realizado previamente por otro estudiante, de forma que mediante todas las herramientas utilizadas en la asignatura se ha conseguido crear una nueva versi??n del mismo bajo determinados est??ndares de calidad y procesos de control.

Para la realizaci??n de las distintas tareas del proyecto se han utilizado Maven, Doxygen, SonarCloud y CircleCI.

## Instalaci??n

Inicialmente podemos clonar el repositorio de github
```bash
git clone https://github.com/alu0101028491/Proyecto_Final_LDH.git
```
Ahora simplemente se deben instalar las dependencias con Maven
```bash
mvn clean install
```

## Build
Tras los comandos de instalaci??n se nos generar?? una archivo JAR dentro del directorio target con todas la depedencias. Dentro de este directorio se debe ejecutar el siguiente comando:

```bash
java -jar Proyecto_Final_LDH-1.0-jar-with-dependencies
```
Una vez ejecutado e introducido el ID del rater a analizar se generar?? una salida HTML tanto por la salida est??ndar como en un archivo index.html en el propio directorio de ejecuci??n.

La ejecuci??n se ha realizado con JDK 11, de forma que se recomienda su uso para replicar la simulaci??n y evitar cualquier error.

## Resultados de simulaci??n

### Puede consultar el resultado de la salida HTML de la aplicaci??n ??[AQU??](https://alu0101028491.github.io/Proyecto_Final_LDH/)!


## Informaci??n adicional
This is a Capstone project, which is the last Course ([Java Programming: Build a Recommendation System](https://www.coursera.org/learn/java-programming-design-principles?specialization=java-programming)) of the <br />
[Java Programming and Software Engineering Fundamentals Specialization](https://www.coursera.org/specializations/java-programming) that designed by Duke University.

