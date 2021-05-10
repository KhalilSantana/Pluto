# Pluto Compiler

Authors: Khalil Santana, Aream Luersen, Guilherme Cruz

---



## App screenshot

![](/home/khalil/Documentos/CS/Fase7/Compiladores/Projetos/Pluto/app-screenshot.png)

---

# Downloading source

```shell
git clone https://github.com/KhalilSantana/Pluto.git
```

---

# Building

This project uses Maven for depedency management and building.

### Requirements:

1. Maven

2. OpenJDK 14 (higher JDK versions also work)

To build this project run the command bellow, keep in mind this only compiles the program, and doesn't run or package it.

```shell
cd Pluto
mvn compile
```

---

# Packaging

This will create an executable multi-platform .JAR executable for all major desktop platforms (Windows/Linux/MacOS). This binary will be at `target/` by defautl.

```shell
mvn clean package
```

---

# Running

From .JAR archive/package:

```shell
java -jar target/Pluto-*-jar-with-dependencies.jar
```

Or skip the package step and run this directly:

```shell
mvn javafx:run
```

# Troubleshooting

## I've got red warnings on everything related to JavaCC!

The JavaCC classes will be generated in the 'generate-sources' phase, and should be automatically run if you use a maven lifecycle goal such as `mvn package` or `mvn javafx:run`

If you still face this issue, try running this:

```
mvn clean generate-sources
```

If the issue persists try restarting your IDE.