# Calculadora Estadística XML

Aplicación Java de línea de comandos que calcula media, desviación estándar, varianza, máximo y mínimo de una variable numérica de un XML. También calcula la correlación cuando se indican dos variables.

Se usa `ArrayList<Double>` del Java Collections API para almacenar los datos. La varianza y la desviación estándar se calculan como población, dividiendo entre `n`.

## Ejecutar en IntelliJ IDEA

Abre esta carpeta como proyecto Gradle. En la configuración de ejecución, usa como clase principal `mx.unison.Calculadora` y agrega los argumentos deseados.

## Ejecutar desde terminal

Con Gradle instalado:

```text
gradle run --args="datos.xml edad"
gradle run --args="datos.xml edad horas"
```

También puedes compilar directamente con Java 17:

```text
javac -d out src/main/java/mx/unison/*.java
java -cp out mx.unison.Calculadora datos.xml edad
java -cp out mx.unison.Calculadora datos.xml edad horas
```

## Ejemplos

```text
java -cp out mx.unison.Calculadora datos.xml edad
java -cp out mx.unison.Calculadora datos.xml edad horas
```

También funciona con los XML incluidos en el repositorio base, por ejemplo `startup_profits.xml profit marketing_spend`. Los nombres de variables deben coincidir con las etiquetas del XML. Si el archivo no se puede abrir, la etiqueta no existe, contiene texto no numérico, o no se puede calcular la correlación, se muestra un mensaje de error.
