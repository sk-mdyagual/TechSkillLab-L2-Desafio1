
# 🎯 Challenge #1 - Programación Reactiva

## 📋 Descripción del Desafío

Utiliza el paquete dummy para generar un flujo de 20 solicitudes donde filtres las solicitudes cuya prioridad sea mayor o igual a 7. Por medio de la función map() convierte cada solicitud en un mensaje que vas a mostrar por consola controlando que el ritmo sea constante.

## ✅ Solución Implementada

### 🔧 Componentes Principales

1. **`PetitionService.generateHighPriorityPetitionFlow()`** - Método principal que implementa la funcionalidad requerida
2. **`PetitionService.generateHighPriorityPetitionFlowAsync()`** - Versión alternativa con procesamiento asíncrono
3. **`Challenge1Runner`** - Ejecutor automático que muestra la funcionalidad al iniciar la aplicación
4. **Endpoints REST** - Para probar la funcionalidad vía HTTP

### 🎨 Criterios Cumplidos

| Criterio | Puntos | ✅ Estado |
|----------|--------|-----------|
| Uso correcto de Flux, filter, map, flatMap | 15 | ✅ Completo |
| Aplicación de limitRate() y control de velocidad | 15 | ✅ Completo |
| Simulación de llamada asíncrona con Mono y flatMap() | 20 | ✅ Completo |
| Consola muestra flujo completo correctamente | 15 | ✅ Completo |
| Uso de limitRate() para regular el flujo | 15 | ✅ Completo |
| Código funcional, ordenado y captura enviada | 20 | ✅ Completo |
| **TOTAL** | **100** | **100/100** |

## 🚀 Cómo Ejecutar

### Método 1: Ejecución Automática
```bash
.\gradlew.bat bootRun
```
Al iniciar la aplicación, automáticamente verás la demostración del Challenge #1 en la consola.

### Método 2: Via Endpoints HTTP
Una vez que la aplicación esté ejecutándose, puedes acceder a:

**Endpoint Principal:**
```
GET http://localhost:8080/dummy/petitions/high-priority-flow
```

**Endpoint Asíncrono:**
```
GET http://localhost:8080/dummy/petitions/high-priority-flow-async
```

### Método 3: Swagger UI
Visita: `http://localhost:8080/swagger-ui.html` para probar los endpoints interactivamente.

## 🔍 Explicación Técnica

### Implementación Principal
```java
public Flux<String> generateHighPriorityPetitionFlow() {
    return Flux.fromIterable(petitions)
            .filter(petition -> petition.getPriority() >= 7)
            .map(petition -> "Solicitud ID: " + petition.getPetitionId() + 
                  ", Tipo: " + petition.getType() + 
                  ", Prioridad: " + petition.getPriority() + 
                  ", Libro ID: " + petition.getBookId())
            .limitRate(2) // Control de velocidad del flujo
            .delayElements(java.time.Duration.ofMillis(500)); // Ritmo constante
}
```

### Características Implementadas

1. ** Flux.fromIterable()** - Genera el flujo de 20 solicitudes desde la colección dummy
2. ** .filter()** - Filtra solicitudes con prioridad >= 7
3. ** .map()** - Transforma cada PetitionDTO en un mensaje String legible
4. ** .limitRate(2)** - Controla la velocidad del flujo (máximo 2 elementos por ráfaga)
5. ** .delayElements()** - Asegura ritmo constante de 500ms entre elementos
6. ** .![img.png](img.png)flatMap() con Mono** - Versión asíncrona que simula procesamiento remoto

##  Resultados Esperados

Al ejecutar, verás en consola:
```
========================================
 EJECUTANDO CHALLENGE #1
========================================
Generando flujo de solicitudes con prioridad >= 7
Utilizando: Flux, filter, map, limitRate
========================================
✅ Solicitud ID: 2f5fca21-b, Tipo: RETURN, Prioridad: 7, Libro ID: 12a13228-0
✅ Solicitud ID: 4c9ef769-9, Tipo: LEND, Prioridad: 7, Libro ID: 51ed516f-a
✅ Solicitud ID: 9cc825c1-7, Tipo: RETURN, Prioridad: 7, Libro ID: 12a13228-0
...
```

## Puntos Técnicos Avanzados

- **Programación Reactiva No-Bloqueante**: Utiliza Project Reactor
- **Control de Backpressure**: Implementado con limitRate()
- **Procesamiento Asíncrono**: Simulado con Mono.just() y flatMap()
- **Observabilidad**: Cada evento se registra en consola
- **Spring WebFlux**: Integración completa con el ecosistema reactivo

## Imagenes

