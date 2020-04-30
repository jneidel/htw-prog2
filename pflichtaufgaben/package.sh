#! /bin/sh

MOVE_TO=$HOME/Downloads/prog_submission

if [ "$1" = "--help" ] || [ "$1" = "-h" ] || [ "$1" = "help" ] || [ -z "$1" ]; then
  cat << EOF
$ package.sh DIR
Prepare java files for submission, by adding a header and moving them to $MOVE_TO

Parameters:
  \$1: directory to submit
EOF
  exit
fi

mkdir -p $MOVE_TO

DIR=$1
cp -rv $(find $DIR -name '*.java') $MOVE_TO

sed -i '1s|^|// Author: Neidel, Jonathan - 573619\n\n|' $(find $MOVE_TO -name '*.java')
