JFLAGS = -g @sources.txt -d out
JC = javac

all: JAR algo.ex stat.ex

SRCS:
	find -name "*.java" > sources.txt

CLASSES: SRCS
	mkdir out
	$(JC) $(JFLAGS)
	$(RM) sources.txt

JAR: CLASSES
	jar -cfe BinPacking.jar Main -C out/ .
	rm -rf out

algo.ex: 
	@echo "#!/bin/bash\njava -jar BinPacking.jar -bench" > algo.ex
	chmod u+x algo.ex

stat.ex: 
	@echo "#!/bin/bash\njava -jar BinPacking.jar -random -bench" > stat.ex
	chmod u+x stat.ex

clean:
	$(RM) algo.ex
	$(RM) stat.ex
	$(RM) BinPacking.jar
	$(RM) sources.txt
	rm -rf out
