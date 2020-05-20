#! /bin/sh

if [ "$1" = "--help" ] || [ "$1" = "-h" ] || [ "$1" = "help" ] || [ -z "$1" ]; then
  cat << EOF
$ create.sh NAME
Create new subdir
EOF
  exit
fi

DIR="$1"
mkdir "$DIR"

cd "$DIR"
ln -s /home/jneidel/.local/share/junit.jar junit.jar

cp ../makefile ../watch .

printf "import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Der erste Test pro Methode ist immer der Normallfall,
 * alle anderen Grenzfälle
 */

  //@Test( expected = IllegalArgumentException.class )

public class Tests {
  <++>
}" > Tests.java

