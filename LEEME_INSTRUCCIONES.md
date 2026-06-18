# Proyecto5 FINAL — Sistema de Gestión de Incidencias (Expreso Los Chankas S.A.C.)

ESTE SÍ ES UN PROYECTO COMPLETO DE NETBEANS. No necesitas copiar archivos
a mano. Ábrelo directamente.

## Cómo abrirlo

1. Descomprime este ZIP en cualquier carpeta de tu computadora (por ejemplo
   en tu Escritorio o en Documentos). Te va a quedar una carpeta llamada
   "Proyecto5 FINAL".
2. Abre NetBeans.
3. Ve a File → Open Project.
4. Busca la carpeta "Proyecto5 FINAL" que descomprimiste (debe verse con el
   ícono de taza de café de NetBeans, eso confirma que la reconoce como
   proyecto válido).
5. Selecciónala y dale "Open Project".
6. En el panel "Projects" (izquierda), expande el proyecto → Source Packages
   → ProyectoPlantilla. Ahí vas a ver TODOS los archivos ya integrados:
   tus archivos originales de ventas (FormVentanaPrincipal, etc.) Y los
   nuevos de Encomiendas/Incidencias (FormMenuPrincipal, FrmEncomienda,
   FrmIncidencia, FrmGestionIncidencias, más las carpetas modelo/ y dao/).

## Cómo ejecutarlo

Clic derecho sobre el proyecto (en el panel Projects) → "Run" (o presiona
F6). Esto va a ejecutar automáticamente `FormIniciarSesion` (ya configurado
como clase principal del proyecto).

Usuarios de prueba ya cargados en BDEncomiendas.accdb:

| usuario | password | rol            |
|---------|----------|----------------|
| admin   | admin123 | Administrador  |
| carmen  | 12345    | Coordinador    |

Al loguearte correctamente, se abre `FormMenuPrincipal` con botones para:
Registrar Encomienda, Registrar Incidencia, Gestionar Incidencias y Reportes.

## Sobre la base de datos — NO necesitas configurar ninguna ruta

`cConnection.java` ahora detecta automáticamente la carpeta del proyecto
en tiempo de ejecución (usa `System.getProperty("user.dir")`), así que
mientras `BDEncomiendas.accdb` esté en la raíz del proyecto (junto a
`build.xml`), todo funciona sin que tengas que escribir ninguna ruta a mano,
sin importar en qué carpeta de tu computadora lo guardes.

Esto ya fue probado de extremo a extremo (compilación completa del proyecto
+ ejecución real contra la base de datos): login, registrar encomienda,
buscar por código, registrar incidencia, listar, y reportes. Todo funciona.

## ¿Y FormVentanaPrincipal (la pantalla de ventas)?

La dejé intacta, tal como la tenías. No la borré ni la toqué, por si tu
profesora todavía va a revisar esa parte por separado. Simplemente ya no
es la pantalla que se abre después del login — ahora se abre
`FormMenuPrincipal`, que es la del sistema de incidencias.

## Estructura de paquetes dentro de ProyectoPlantilla

```
ProyectoPlantilla/
├── cConnection.java          (conexión, ya configurada)
├── FormIniciarSesion.java    (login, ya adaptado a la tabla Usuario nueva)
├── FormSplash.java           (pantalla de carga, sin cambios)
├── FormMenuPrincipal.java    (NUEVO: menú del sistema de incidencias)
├── FrmEncomienda.java        (NUEVO: registrar encomienda)
├── FrmIncidencia.java        (NUEVO: registrar incidencia)
├── FrmGestionIncidencias.java (NUEVO: tabla + cambio de estado/solución)
├── FormVentanaPrincipal.java (tu pantalla de ventas original, intacta)
├── modelo/
│   ├── Cliente.java
│   ├── Encomienda.java
│   ├── Incidencia.java
│   └── Usuario.java
└── dao/
    ├── EncomiendaDAO.java
    ├── IncidenciaDAO.java
    └── UsuarioDAO.java
```

## Siguiente paso sugerido

Si quieres, te ayudo a generar los diagramas que pide tu informe Word
(Casos de Uso, Clases, Entidad-Relación, Modelo Relacional), ya alineados
exactamente con este código para que no haya inconsistencias entre el
documento y el sistema real.
