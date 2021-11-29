
# Jump2Digital Hackathon by Nuwe

Jump2Digital: el proyecto se basa en un servicio backend hecho en Java con el ecosistema Spring Boot; haciendo uso de su modulo "Spring Boot Data", más concretamente, con su implementacion reactiva para la base de datos MongoDB (NoSQL) gracias al modulo "Spring Boot MongoDB Reactive".

Este servicio REST se ha querido inmplementar con programacion reactiva-funcional mediante el framework "Spring Reactor" y los Functional Endpoints del modulo "Spring WebFlux".

> Servicio RESTful que conecta con el front, y ayuda al equipo a registrar las compras hechas a través de la plataforma y poder obtener posteriores analíticas.

#
![](https://img.shields.io/github/last-commit/Bob-Lennon/Nuwe-Jump2Digital) ![](https://img.shields.io/github/followers/Bob-Lennon?style=social)

## Technology Stack
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)

#### Tools
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white) ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white) ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white) ![Spotify](https://img.shields.io/badge/Spotify-1ED760?style=for-the-badge&logo=spotify&logoColor=white) ![Stack Overflow](https://img.shields.io/badge/-Stackoverflow-FE7A16?style=for-the-badge&logo=stack-overflow&logoColor=white)

Tambien se usó la libreria [Lombok](https://projectlombok.org/) para agilizar el proceso de desarrollo.

## Tasks
✅ Task 1 → Configurar un servicio REST utilizando alguno de las tecnologías propuestas que escuche a peticiones en el PUERTO=5000

✅ Task 2 → Configurar una base de datos (alguna de las propuestas) y añadir los modelos de TICKET y PRODUCT

✅ Task 3 → Los parámetros de productType y paymentType son validados antes de ser guardados.

✅ Task 4 → El endpoint de /ticket me permite: leer, crear y eliminar TICKETS

✅ Task 5 → El endpoint product /product me permite: leer, crear, eliminar y actualizar PRODUCTS

✅ Task 6 → El endpoint /ticket/analytics devuelve la siguiente información: Valor total de los productos vendidos, números de productos venidos por tipo, número de tickets que han utilizado Visa y número de tickets que han utilizado MasterCard

## Usage
### Product
Campos:
&emsp;name: String
&emsp;price: Double
&emsp;desc: String
```
POST: http://localhost:5000/product/create + Content-Type: application/json        // Crea un Producto
GET: http://localhost:5000/product/read/{id}                                       // Recupera un Producto
PUT: http://localhost:5000/product/update/{id} + Content-Type: application/json    // Actualiza un Producto
DELETE: http://localhost:5000/product/delete/{id}                                  // Elimina un Producto
```
### Ticket
Campos:
&emsp;productId: UUID
&emsp;amount: Integer
&emsp;paymentType: PaymentTypeEnum
```
POST: http://localhost:5000/ticket/create + Content-Type: application/json  // Crea un Ticket
GET: http://localhost:5000/ticket/read/{id}                                 // Recupera un Ticket
DELETE: http://localhost:5000/ticket/delete/{id}                            // Elimina un Ticket
GET: http://localhost:5000/ticket/findall                                   // Recupera todos los Tickets
GET: http://localhost:5000/ticket/analytics                                 // Recupera las analiticas del servicio
```

#### Resources

Coleccion de Peticiones en Postman: [descarga](https://drive.google.com/file/d/1t9LU7c6-ut87YBln-UUvpQ--FUmuYxkm/view?usp=sharing)

## License 
[MIT](https://github.com/Bob-Lennon/Nuwe-Jump2Digital/blob/master/LICENSE.md)
