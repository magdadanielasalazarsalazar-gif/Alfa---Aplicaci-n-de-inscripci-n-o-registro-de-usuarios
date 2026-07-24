# Alfa---Aplicaci-n-de-inscripci-n-o-registro-de-usuarios
Tematica: Una aplicación de inscripción o registro de usuarios  que implica tres componentes principales: la interfaz gráfica (GUI), la lógica de negocio (validación) y la conexión a la base de datos para guardar los datos.

Lenguaje: Java.

Integrantes: Magda Salazar , Jahilibeth González , Nahomi Rivas, Rubén Molina , Omar lanz.
# Documentación Técnica: Sistema de Inscripción de Usuarios

Este proyecto implementa una arquitectura híbrida de persistencia y procesamiento de datos mediante el uso paralelo de estructuras de datos lineales y no lineales en Java Swing.

## Descripción de las Estructuras de Datos Utilizadas

### 1. Lista Enlazada Personalizada (`ListaEnlazadaUsuarios.java`)
* **Uso en el código:** Es la estructura central de persistencia binaria (`cabeza`). Mantiene el orden cronológico de inserción de los usuarios.
* **Propósito:** Actúa como el puente principal para el empaquetado y desempaquetado del archivo `usuarios.dat`. El método `obtenerTodosLosUsuarios()` recorre los nodos dinámicos para devolver un arreglo estático que repobla el resto de las estructuras al arrancar.

### 2. Tabla Hash (`HashMapUsuarios.java`)
* **Uso en el código:** Instanciada internamente a través de `java.util.HashMap` bajo la variable `mapa`.
* **Propósito:** Optimiza las búsquedas de usuarios por su clave primaria (`ID`). Permite obtener registros en tiempo constante promedio $O(1)$, evitando tener que recorrer secuencialmente la lista enlazada cada vez que se valida un duplicado o se procesa una baja.

### 3. Pila Estándar (`java.util.Stack`)
* **Uso en el código:** Declarada como `pilaAcciones` dentro de `GestorUsuarios.java` y como `pilaUI` en `VentanaRegistro.java`.
* **Propósito:** Implementa la funcionalidad de deshacer acciones mediante el comportamiento LIFO (Last In, First Out). Almacena cadenas de texto con comandos formateados (ej. `"Agregar 101"`) para revertir operaciones en orden inverso al que se ejecutaron.

### 4. Cola Secuencial (`java.util.LinkedList`)
* **Uso en el código:** Instanciada bajo la interfaz `java.util.Queue` con el objeto `colaEspera` en `GestorUsuarios.java`.
* **Propósito:** Administra el flujo de usuarios bajo el principio FIFO (First In, First Out). Diseñada para simular un sistema de atención en consola o procesamiento asíncrono respetando estrictamente el orden de llegada.

### 5. Árbol Binario de Búsqueda (`ArbolBinario.java`)
* **Uso en el código:** Controlado mediante el puntero `raiz` de tipo `NodoArbol`.
* **Propósito:** Organiza y ordena los objetos `Usuario` de forma alfabética utilizando el criterio `usuario.getNombre()`. El método `inOrden()` permite realizar un recorrido ordenado ascendentemente.

### 6. Árbol AVL (`ArbolAVL.java`)
* **Uso en el código:** Gestionado por el nodo `raiz` de tipo `NodoAVL`.
* **Propósito:** Indexa a los usuarios de manera auto-balanceada utilizando su `ID`. Calcula las alturas de las ramas dinámicamente para asegurar que la eficiencia de búsqueda se mantenga en el límite teórico de $O(\log n)$ incluso con grandes volúmenes de datos.
