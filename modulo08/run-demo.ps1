# Script para demostración de módulos Java 21
# Uso: .\run-demo.ps1 [opción]

param(
    [Parameter(Position=0)]
    [string]$Action
)

$ErrorActionPreference = "Stop"

Write-Host ""
Write-Host "╔════════════════════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║          Java 21 - Demostración de Módulos                    ║" -ForegroundColor Cyan
Write-Host "║          Banking Application with Module System               ║" -ForegroundColor Cyan
Write-Host "╚════════════════════════════════════════════════════════════════╝" -ForegroundColor Cyan
Write-Host ""

# Cambiar al directorio del script
Set-Location $PSScriptRoot

# Mostrar opciones si no se proporciona un argumento
if (-not $Action) {
    Write-Host "Uso: .\run-demo.ps1 [opción]"
    Write-Host ""
    Write-Host "Opciones disponibles:"
    Write-Host "  build        - Compilar el proyecto"
    Write-Host "  app          - Ejecutar la aplicación bancaria interactiva"
    Write-Host "  inspect      - Inspeccionar todos los módulos del sistema"
    Write-Host "  jar          - Empaquetar como JARs"
    Write-Host "  clean        - Limpiar archivos compilados"
    Write-Host ""
    exit 0
}

switch ($Action.ToLower()) {
    "build" {
        Write-Host "🔨 Compilando el proyecto..." -ForegroundColor Yellow
        mvn clean compile
        if ($LASTEXITCODE -eq 0) {
            Write-Host "✅ Compilación completada" -ForegroundColor Green
        } else {
            Write-Host "❌ Error en la compilación" -ForegroundColor Red
            exit 1
        }
    }

    "app" {
        Write-Host "🏦 Iniciando aplicación bancaria..." -ForegroundColor Yellow
        Write-Host ""
        mvn -pl com.banking.app clean compile exec:java `
            -D"exec.mainClass=com.banking.app.BankingApplication"
    }

    "inspect" {
        Write-Host "🔍 Inspeccionando módulos del sistema..." -ForegroundColor Yellow
        Write-Host ""
        mvn -pl com.banking.app clean compile exec:java `
            -D"exec.mainClass=com.banking.app.ModuleInspector"
    }

    "jar" {
        Write-Host "📦 Empaquetando como JARs..." -ForegroundColor Yellow
        mvn clean package -DskipTests
        if ($LASTEXITCODE -eq 0) {
            Write-Host "✅ JAR files creados:" -ForegroundColor Green
            Get-ChildItem -Path . -Recurse -Filter "*.jar" -File | 
                Where-Object { $_.DirectoryName -match 'target' } | 
                Select-Object -First 10 -ExpandProperty FullName
        } else {
            Write-Host "❌ Error al empaquetar" -ForegroundColor Red
            exit 1
        }
    }

    "clean" {
        Write-Host "🧹 Limpiando archivos compilados..." -ForegroundColor Yellow
        mvn clean
        if ($LASTEXITCODE -eq 0) {
            Write-Host "✅ Archivos limpios" -ForegroundColor Green
        } else {
            Write-Host "❌ Error al limpiar" -ForegroundColor Red
            exit 1
        }
    }

    default {
        Write-Host "❌ Opción no reconocida: $Action" -ForegroundColor Red
        Write-Host "Opciones válidas: build, app, inspect, jar, clean"
        exit 1
    }
}

Write-Host ""
