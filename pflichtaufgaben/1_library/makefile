# from: https://stackoverflow.com/a/27376721
# make only takes tabs: https://stackoverflow.com/a/16945143

JAVAC = javac
JVM = /bin/java
JUNIT = /home/jneidel/scripts/htw/java/junit
MKBIN = mkdir -p bin

JAVAC_FLAGS = -g -d 'bin/'
JAVAC_CP = -cp

MAINSRC = *.java
TESTSRC = *.java
LIB = '*'

TARGET = bin

MAIN = myPackage.Main

MAINTEST = BibliothekTest

.SUFFIXES : .class .java

all:
	$(MKBIN)
	$(JAVAC) $(JAVAC_FLAGS) $(MAINSRC)$(PKGSRC)*

test:
	$(JAVAC) $(JAVAC_FLAGS) $(JAVAC_CP) $(LIB) $(TESTSRC)$(PKGSRC)

# clean:
    # rm -rf $(TARGET)

run:
	$(JVM) $(JAVAC_CP) $(TARGET) $(MAIN)

run_test:
	$(JVM) $(JAVAC_CP) $(TARGET) $(MAINTEST)

junit:
	$(JUNIT) $(MAINTEST)

.PHONY: all test clean run run_test
