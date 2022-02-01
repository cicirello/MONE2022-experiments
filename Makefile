ifeq ($(OS),Windows_NT)
	py = "python"
else
	py = "python3"
endif

JARFILE = "target/mone-article-experiments-1.0.0-jar-with-dependencies.jar"
pathToDataFiles = "data"

.PHONY: build
build:
	mvn clean package

# Analyzes data assuming experiments already run

.PHONY: analysis
analysis:
	$(py) -m pip install --user pycairo
	$(py) -m pip install --user matplotlib
	$(py) src/analysis/summarize-stats.py ${pathToDataFiles}/tsp.txt > ${pathToDataFiles}/summary.tsp.txt
	$(py) src/analysis/summarize-stats.py ${pathToDataFiles}/atsp.txt > ${pathToDataFiles}/summary.atsp.txt
	$(py) src/analysis/summarize-stats.py ${pathToDataFiles}/haystack.em.txt > ${pathToDataFiles}/summary.haystack.em.txt
	$(py) src/analysis/summarize-stats.py ${pathToDataFiles}/haystack.tau.txt > ${pathToDataFiles}/summary.haystack.tau.txt
	$(py) src/analysis/summarize-stats.py ${pathToDataFiles}/haystack.lee.txt > ${pathToDataFiles}/summary.haystack.lee.txt

.PHONY: epstopdf
epstopdf:
	epstopdf ${pathToDataFiles}/tsp.eps
	epstopdf ${pathToDataFiles}/atsp.eps
	epstopdf ${pathToDataFiles}/haystack.em.eps
	epstopdf ${pathToDataFiles}/haystack.tau.eps
	epstopdf ${pathToDataFiles}/haystack.lee.eps

# Runs all experiments

.PHONY: experiments
experiments: tsp atsp HaystackEM HaystackTau HaystackLee

.PHONY: tsp
tsp:
	java -cp ${JARFILE} org.cicirello.experiments.mone2022.TSPExperiments > ${pathToDataFiles}/tsp.txt

.PHONY: atsp
atsp:
	java -cp ${JARFILE} org.cicirello.experiments.mone2022.ATSPExperiments > ${pathToDataFiles}/atsp.txt

.PHONY: HaystackEM
HaystackEM:
	java -cp ${JARFILE} org.cicirello.experiments.mone2022.HaystackEMExperiments > ${pathToDataFiles}/haystack.em.txt

.PHONY: HaystackTau
HaystackTau:
	java -cp ${JARFILE} org.cicirello.experiments.mone2022.HaystackTauExperiments > ${pathToDataFiles}/haystack.tau.txt

.PHONY: HaystackLee
HaystackLee:
	java -cp ${JARFILE} org.cicirello.experiments.mone2022.HaystackLeeExperiments > ${pathToDataFiles}/haystack.lee.txt
