
build:
	g++ ZeppApp.cpp ZeppTest.cpp ZeppRun.cpp -o Zepp.bin

test:
	g++ -D CATCH_CONFIG_MAIN ZeppApp.cpp ZeppTest.cpp ZeppRun.cpp -o ZeppTest.bin
	./ZeppTest.bin

run:
	make build
	./Zepp.bin

clean:
	rm -f Zepp.bin ZeppTest.bin
