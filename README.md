# RESTAURANT-API - Technical assessment for Transbank

Se recomienda utilizar Spring Tool Suite para editar, e importr como proyecto Gradle.

*Ejecutar las siguientes instrucciones para compilar, generar ensamblado y ejecutar:



# Compilacion y ejecucion de pruebas unitarias

*En UNIX
    ./gradlew build
    
*En Windows
    gradlew.bat build



# Generacion de empaquetado

    java -jar build/libs/restaurant-api-1.0.0.jar
    
    
    
# URLs de las APIs de la solucion

Validar en clientes de servicios REST, tales como POSTMAN o SoapUI.

La solucion disponibiliza las siguientes 3 APIs:


* API de Autenticacion por medio de JSON Web Token

URL: http://localhost:8080/restaurant/api/v1/authentication/loginUser
Operacion: POST
Body JSON:

{
	"user": {
		"name": "Carlos Izquierdo",
		"username": "cizquierdo",
		"password": "abc123"
	}
}

Este es el unico usuario valido registrado en la aplicacion.
Una vez ejecutada esta API, tomar del response el valor del campo "token" completo,
incluido el prefijo "Bearer " y copiarlo, este sera el token de acceso valido para las APIs de gestion de ventas.
La validez de este token es de 5 minutos, una vez expirado, se debe generar uno nuevo.


* API de Consulta de Ventas

URL: http://localhost:8080/restaurant/api/v1/sales/{date}
Operacion: GET
{date}: Parametro para obtener las ventas filtradas segun la fecha especificada, en formato *dd-MM-yyyy* de Java.
Ejemplo: *05-01-2020* (sin los asteriscos).

Para toda consulta realizada a la API (adicional al despliegue de resultados via HTTP-JSON) genera un archivo de texto
llamado *Sales_dd-MM-yyyy.txt*, ubicado dentro de la raiz del proyecto (restaurant-api/), esto con la finalidad de
demostrar la implementacion del uso de Colas JMS, pues la generacion del archivo con la informacion de la consulta
realizada, es llevada a cabo via JMS, publicandose la solicitud como un mensaje en una cola de un Servidor embebido ActiveMQ,
para posteriormente ser procesado por el listener JMS.


* API de Creacion de Ventas

URL: http://localhost:8080/restaurant/api/v1/sales/create
Operacion: POST
Body JSON de ejemplo:

{
    "sales": [
        {
            "id": 13,
            "date": "07-01-2020",
            "totalAmount": 5.0,
            "currency": "USD",
            "clientId": "24668924-5",
            "payments": [
                {
                    "paymentMethod": "DEBIT",
                    "amount": 2.5
                },
                {
                    "paymentMethod": "CASH",
                    "amount": 2.5
                }
            ],
            "items": [
                {
                    "code": "C99874",
                    "brand": "Coca Cola",
                    "name": "Coca Cola Light",
                    "description": "Coca Cola Light 3 lt",
                    "price": 3.0,
                    "quantity": 1
                },
                {
                    "code": "C99899",
                    "brand": "Coca Cola",
                    "name": "Coca Cola Zero",
                    "description": "Coca Cola Zero 2 lt",
                    "price": 2.0,
                    "quantity": 1
                }
            ]
        }
    ]
}

Permite registrar una nueva venta, la cual ademas de contener informacion descriptiva propia, contiene una lista
de items asociados a la misma, asi como una lista de los distintos medios de pago con los que se concreto dicha venta.
Dentro del directorio *restaurant-api/postman* se encuentra el archivo *Restaurant-API.postman_collection.json* con
los request configurados para invocar a las 3 APIs de la solucion.








