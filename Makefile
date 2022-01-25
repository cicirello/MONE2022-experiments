ifeq ($(OS),Windows_NT)
	py = "python"
else
	py = "python3"
endif

JARFILE = "target/mone-article-experiments-1.0.0-jar-with-dependencies.jar"
pathToDataFiles = "data/"

.PHONY: build
build:
	mvn clean package

# Runs all experiments

.PHONY: experiments
experiments: tsp atsp

.PHONY: tsp
tsp:
		java -cp ${JARFILE} org.cicirello.experiments.mone2022.TSPExperiments > ${pathToDataFiles}/tsp.txt

.PHONY: atsp
atsp:
		java -cp ${JARFILE} org.cicirello.experiments.mone2022.ATSPExperiments > ${pathToDataFiles}/atsp.txt