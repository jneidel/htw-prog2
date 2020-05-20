# from: https://stackoverflow.com/a/27376721
# make only takes tabs: https://stackoverflow.com/a/16945143

JAVAC = javac
JVM = /bin/java
JUNIT = /home/jneidel/scripts/htw/java/junit
MKBIN = mkdir -p bin

JAVAC_FLAGS = -g
JAVAC_CP = -cp

MAINSRC = *.java
TESTSRC = *.java
LIB = '*'

MAINTEST = Tests

.SUFFIXES : .class .java

test:
	$(JAVAC) $(JAVAC_FLAGS) $(JAVAC_CP) $(LIB) $(TESTSRC)$(PKGSRC)
	$(JUNIT) $(MAINTEST)

clean:
	rm *.class *.
