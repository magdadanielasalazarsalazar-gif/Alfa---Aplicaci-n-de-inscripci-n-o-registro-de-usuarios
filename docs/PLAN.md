Tabla 1:
Estructura de datos-	¿En qué parte del proyecto se usará? (ejemplo: módulo, función, caso de uso)	-¿Qué operaciones específicas se necesitarán?	-¿Se implementará desde cero o se usará alguna biblioteca?
Pila-	Control de deshacer acciones en la interfaz gráfica (por ejemplo, cancelar el último ingreso)-	Push, Pop, Mostrar contenido-	Se usará una biblioteca sencilla y bien documentada, como la clase Stack de Java (en java.util), para facilitar y agilizar el desarrollo.
Cola	-Gestión de la cola de espera para la inscripción, si hubiese múltiples usuarios en fila-	Encolar, Desencolar, Ver estado	-Se usará la clase LinkedList de Java, que implementa la interfaz Queue, para simplificar la gestión de la cola.
Lista enlazada	- Almacenamiento dinámico de usuarios registrados antes de guardarlos en la base de datos-	Añadir, Eliminar, Buscar, Recorrer	-Se implementará desde cero, para entender la estructura, aunque también se puede considerar LinkedList si se busca simplificación.
Árbol binario	-Ordenamiento y búsqueda rápida de usuarios por nombre o ID	-Insertar, Buscar, Recorrer (inorden, preorden)	-Se implementará desde cero, para aprendizaje, aunque para casos más complejos se podrían explorar bibliotecas específicas o utilidades de Java.
Árbol AVL-	No será utilizado en esta versión básica, ya que la estructura es más compleja y no necesaria		
Árbol 2-3	-No será utilizado en esta versión básica, por su complejidad		
Árbol B -	No será utilizado en esta versión básica, por su complejidad		
Grafo-	No se requiere en esta aplicación sencilla, ya que no involucra relaciones complejas		
Hashing-	Búsqueda rápida de usuarios por ID o nombre usando una tabla hash	-Insertar, Buscar, Eliminar	-Se usará la clase HashMap de Java, que es accesible y eficiente, facilitando la implementación y el rendimiento.

Para las estructuras de datos principales como Pila, Cola y Hashing, se utilizará las clases ya disponibles en Java (Stack, LinkedList, HashMap) para ahorrar tiempo y aprovechar la eficiencia de las bibliotecas estándar. Para estructuras más complejas como Árbol binario, si se desea, se puede implementar desde cero para fines didácticos, aunque también se puede optar por bibliotecas específicas si el proyecto lo permite.

División de tareas
Integrante-	Tareas asignadas	-Fecha tentativa de entrega
Jahilibeth González	-Implementar la estructura de pila	-26/06/2026
Magda Salazar-	Implementar la estructura de cola y lista enlazada-	26/06/2026
Nahomi Rivas-	Implementar la estructura de árbol binario	-03/07/2026
Rubén Molina	-Integrar las estructuras en la lógica de negocio y validaciones-	17/07/2026
Oma lanz	-Documentar, pruebas y conexión con la base de datos-	17/07/2026

Nota: las fechas estarán sujetas a cambios
Justificación general
Dado que la aplicación es de carácter muy sencillo y con pocos usuarios, no es necesario implementar estructuras más complejas como árboles AVL, árboles 2-3, árboles B o grafos. La prioridad es mantener el proyecto simple y comprensible, enfocándonos en estructuras básicas que ayuden en la gestión de datos y en la lógica de negocio sin complicaciones innecesarias.
