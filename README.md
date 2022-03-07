# Cash-API
Proyecto base de examen tecnico para Cash hecho en JAVA, utilizando spring y maven.

## Inicializacion
Iniciar como Springboot Application, en http://localhost:8080

## Conexion a una base de datos embebida utilizando Java Persistence API (JPA)
Utilizacion de base de datos h2
Insercion de datos para prueba, con InitializingBean en CashApiAplication.java, main

4 Users (id 1,2,3,4)
30 Loans
Distribucion Loans:
* User 1 -> 1 Loan
* User 2 -> 4 Loans
* User 3 -> 9 Loans
* User 4 -> 16 Loans


## Acceso base de datos H2
* Accediendo a http://localhost:8080/h2-console
* JDBC URL: jdbc:h2:mem:test
* User Name: h2
* Password: h2

## Uso de logs Log4j 2
Implementacion basica de logs y errores en la clase ExceptionHandler.
Modelado de la informacion expuesta en el archivo "app.log" (logs/app.log) y su respectiva configuracion en "log4j2.properties"
