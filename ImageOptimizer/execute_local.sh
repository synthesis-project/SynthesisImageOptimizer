#! /bin/bash

echo Running Image Processor program locally

mvn compile && mvn exec:java -Dexec.mainClass=com.synthesis.App -DinputPlant="Majesty Palm"

