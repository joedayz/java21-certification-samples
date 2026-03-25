#!/bin/bash

# Script para demostración de módulos Java 21
# Uso: ./run-demo.sh [opción]

set -e

echo ""
echo "╔════════════════════════════════════════════════════════════════╗"
echo "║          Java 21 - Demostración de Módulos                    ║"
echo "║          Banking Application with Module System               ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

cd "$(dirname "$0")"

# Mostrar opciones
if [ $# -eq 0 ]; then
    echo "Uso: ./run-demo.sh [opción]"
    echo ""
    echo "Opciones disponibles:"
    echo "  build        - Compilar el proyecto"
    echo "  app          - Ejecutar la aplicación bancaria interactiva"
    echo "  inspect      - Inspeccionar todos los módulos del sistema"
    echo "  jar          - Empaquetar como JARs"
    echo "  clean        - Limpiar archivos compilados"
    echo ""
    exit 0
fi

case "$1" in
    build)
        echo "🔨 Compilando el proyecto..."
        mvn clean compile
        echo "✅ Compilación completada"
        ;;

    app)
        echo "🏦 Iniciando aplicación bancaria..."
        echo ""
        mvn -pl com.banking.app clean compile exec:java \
            -Dexec.mainClass="com.banking.app.BankingApplication"
        ;;

    inspect)
        echo "🔍 Inspeccionando módulos del sistema..."
        echo ""
        mvn -pl com.banking.app clean compile exec:java \
            -Dexec.mainClass="com.banking.app.ModuleInspector"
        ;;

    jar)
        echo "📦 Empaquetando como JARs..."
        mvn clean package -DskipTests
        echo "✅ JAR files creados:"
        find target -name "*.jar" -type f | head -10
        ;;

    clean)
        echo "🧹 Limpiando archivos compilados..."
        mvn clean
        echo "✅ Archivos limpios"
        ;;

    *)
        echo "❌ Opción no reconocida: $1"
        echo "Opciones válidas: build, app, inspect, jar, clean"
        exit 1
        ;;
esac

echo ""

