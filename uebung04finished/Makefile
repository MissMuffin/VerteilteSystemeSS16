all: clean compile

clean:
	rm -rf bin/*

compile: uebung04/*.java	
	mkdir -p bin
	javac -d bin uebung04/*.java


run_server: all
	java -cp ./bin uebung04.Server

run_client: all
	java -cp ./bin uebung04.Client
