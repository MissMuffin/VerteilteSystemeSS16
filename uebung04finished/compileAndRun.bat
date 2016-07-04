@echo off
RMDIR /S /Q bin\uebung04
MKDIR bin
javac -d bin uebung04/*.java
start cmd /k java -cp ./bin uebung04.Server
start cmd /k java -cp ./bin uebung04.Client