# Justificación de Omisiones Estructurales (JUSTIFICACION.md)

En el diseño arquitectónico del Sistema de Inscripción de Usuarios se analizaron todas las estructuras de datos vistas en clase: colas, pilas, listas enlazadas, árboles binarios, árboles 2-3, árboles B, grafos y hashing. 

Con el fin de garantizar la persistencia de datos binaria (`Serializable`) y maximizar la velocidad de respuesta de la interfaz gráfica (GUI) en tiempo real, se dividieron las estructuras entre las seleccionadas para producción y aquellas que fueron omitidas, bajo los siguientes criterios técnicos:

---

## 1. Estructuras Implementadas en el Backend

* **Listas Enlazadas (`ListaEnlazadaUsuarios.java`):** Utilizada como la base lineal de persistencia masiva. Al ser dinámica, no restringe el límite de registros de inscripciones y permite una reconstrucción secuencial óptima desde el archivo físico `usuarios.dat`.
* **Pilas (`pilaAcciones` / `pilaUI`):** Elegida para almacenar el historial de operaciones de la interfaz bajo el comportamiento LIFO (Last In, First Out). Esto permite al usuario ejecutar la función de "Deshacer" registros en reversa.
* **Colas (`colaEspera`):** Implementada con comportamiento FIFO (First In, First Out) para simular de forma interna un sistema de turnos de procesamiento y atención secuencial de los usuarios inscritos.
* **Hashing (`HashMapUsuarios.java`):** Estructura crítica de rendimiento. Al indexar el ID del usuario como clave primaria, nos permite realizar búsquedas y validaciones de duplicados en tiempo constante $O(1)$, evitando congelamientos (*lags*) en la GUI.
* **Árboles Binarios (`ArbolBinario.java`):** Utilizado para ordenar y procesar de manera jerárquica a los usuarios alfabéticamente a través del criterio `Nombre`. Su recorrido en `InOrden` facilita la exportación de listados ordenados de manera ascendente.

---

## 2. Estructuras Omitidas y Justificación Técnica

### A. Árboles 2-3
* **Justificación:** Los árboles 2-3 son estructuras de búsqueda perfectamente balanceadas que manejan de forma nativa nodos con 2 o 3 hijos para mantener el orden. En nuestra aplicación, los criterios de búsqueda rápida ya se encuentran delegados a la Tabla Hash ($O(1)$) y el ordenamiento al Árbol Binario tradicional. Implementar la compleja lógica de inserción, división de nodos (*splitting*) y fusión de un árbol 2-3 habría duplicado el uso de memoria RAM del programa de forma redundante, sin ofrecer ninguna mejora de rendimiento perceptible para el volumen de registros administrados por la interfaz Swing.

### B. Árboles B
* **Justificación:** Los árboles B (y sus variantes como B+) están diseñados específicamente para manejar volúmenes masivos de datos que no caben en la memoria RAM y deben indexarse directamente desde dispositivos de almacenamiento secundario (como discos duros o bloques de bases de datos masivas). Dado que nuestro sistema de inscripción empaqueta toda la información en memoria viva y la guarda en un archivo binario consolidado de tamaño intermedio (`usuarios.dat`), el uso de un Árbol B no tiene caso de uso práctico, pues la memoria de la computadora es más que suficiente para procesar los nodos lineales y jerárquicos estándar.

### C. Grafos (Matrices / Listas de Adyacencia)
* **Justificación:** Los grafos se utilizan para modelar redes complejas interconectadas donde existen relaciones complejas de muchos a muchos (como redes sociales, topologías de red o mapas de rutas de transporte). Al ser este un software lineal de inscripción corporativa, los usuarios registrados son entidades independientes y aisladas entre sí; no poseen relaciones de adyacencia ni conexiones de red topológica. Por lo tanto, codificar matrices o listas de adyacencia habría añadido líneas de código y estructuras sin ningún propósito funcional ni académico para este problema.
