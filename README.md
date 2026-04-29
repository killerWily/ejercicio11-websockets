La Arquitectura del Ejercicio 11
El flujo de datos funciona así:
*El Servidor (chat-server-quarkus): Actúa como el centro de mando. Recibe un mensaje de un cliente y lo reenvía a todos los demás.
*El Cliente Swing (chat-client-swing): Representa una aplicación corporativa de escritorio.
*El Cliente JS (chat-client-js): Representa el acceso rápido desde cualquier navegador.

Recomendaciones antes de ejecutar.
Requisitos
* Java 17+
* Maven 3.8+
Para desarrollo utilice VScode por ligero y flexible, ademas me permite hacer la ejecusion desde el mismo, a parte que todo por terminal integrada.
Recomendacion importate es ejecutar el editor con permiso de administrador...
Extensiones:
Extension for java - Microosoft (necesarias)
Live server - Ritwich dey (opcional)

Flujo de ejecucion...
Terminal 1: Ejecuta el servidor Quarkus "chat-server-quarkus", levantaremos el servidor con  ./mvnw quarkus:dev  
asi:
<img width="1600" height="850" alt="image" src="https://github.com/user-attachments/assets/3f3e8e41-0b82-49ef-a03c-cb4c89690f8b" />
<img width="1600" height="850" alt="image" src="https://github.com/user-attachments/assets/592ab306-2400-4eac-8c69-781986f58675" />

Servidor: nuestro servidor "chat-server-quarkus" se encuentra en http://localhost:8080/, podemos aceder en cualquier navegador incluso abrir barios asi:
<img width="3200" height="900" alt="image" src="https://github.com/user-attachments/assets/eedf8a30-7593-4563-915e-b5cd8f71afac" />

aqui podemos tomar dos camino en el orden que lo desees...

Navegador: ejecutaremos el index.html de "chat-cliente-js" de la manera que desees, este lo puedes abrir desde tu explorador de archivos o con Live Server...
como resultado la siguiente imagen: 
-le daremos un nombre de usuario 'Karito' y luego a conectar
<img width="3200" height="900" alt="image" src="https://github.com/user-attachments/assets/75ce55dd-6fbb-4f41-99bf-e4a0626db98a" />

Terminal 2: Ejecuta  Cliente Swing "chat-client-swing" con mvn compile exec:java "-Dexec.mainClass=org.equipo5.client.Main"
<img width="1600" height="850" alt="image" src="https://github.com/user-attachments/assets/ed0c1af4-fdbd-4a87-8347-64d29878c343" />
<img width="1600" height="450" alt="image" src="https://github.com/user-attachments/assets/76b4df5a-fc7a-484b-a3c2-89c2c938903c" />



