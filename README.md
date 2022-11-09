# MONE2022-experiments

Copyright &copy; 2018-2019, 2022 Vincent A. Cicirello

This repository contains code to reproduce the experiments, and analysis of 
experimental data, from the following paper:

> Vincent A. Cicirello. 2022. [On Fitness Landscape Analysis of Permutation Problems: From Distance Metrics to Mutation Operator Selection](https://www.cicirello.org/publications/Cicirello-MONE-2022.pdf). *Mobile Networks and Applications* (2022). doi:[10.1007/s11036-022-02060-z](https://doi.org/10.1007/s11036-022-02060-z)

The above article is an extended version the following earlier conference paper:

> Vincent A. Cicirello. 2019. [Classification of Permutation Distance Metrics for Fitness Landscape Analysis](https://www.cicirello.org/publications/cicirello-bict2019.pdf). In *Proceedings of the 11th International Conference on Bio-inspired Information and Communication Technologies*, pages 81-97. Springer Nature, March 2019. doi:[10.1007/978-3-030-24202-2_7](https://doi.org/10.1007/978-3-030-24202-2_7)

|  |  |
| :--- | :--- |
| __License__ | [![GitHub](https://img.shields.io/github/license/cicirello/MONE2022-experiments)](LICENSE) | 
| __Packages and Releases__ | [![Maven Central](https://img.shields.io/maven-central/v/org.cicirello/mone-article-experiments.svg?label=Maven%20Central)](https://search.maven.org/artifact/org.cicirello/mone-article-experiments) [![GitHub release (latest by date)](https://img.shields.io/github/v/release/cicirello/MONE2022-experiments?logo=GitHub)](https://github.com/cicirello/MONE2022-experiments/releases) |

## Requirements to Build and Run the Experiments

To build and run the experiments on your own machine, you will need the following:
* __JDK 11__: I used OpenJDK 11, but you should be fine with Oracle's 
  JDK as well. 
* __Apache Maven__: In the root of the repository, there is a `pom.xml` 
  for building the Java programs for the experiments. Using this `pom.xml`, 
  Maven will take care of downloading the exact versions of 
  [Chips-n-Salsa](https://chips-n-salsa.cicirello.org/) (release 4.2.1), 
  [JavaPermutationTools](https://jpt.cicirello.org) (release 3.0.0), and
  [&rho;&mu;](https://rho-mu.cicirello.org) (release 1.1.0)  that were 
  used in the experiments. 
* __Python 3__: The repository contains Python programs that were used to 
  compute summary statistics, and to generate
  graphs for the figures of the paper. If you want to run the Python programs, 
  you will need Python 3. I specifically used Python 3.9.6. You also need  
  matplotlib installed.
* __Make__: The repository contains a Makefile to simplify running the build, 
  running the experiment's Java programs, and running the Python program to 
  analyze the data. If you are familiar with using the Maven build tool, 
  and running Python programs, then you can just run these directly, although 
  the Makefile may be useful to see the specific commands needed.

## Building the Java Programs (Option 1)

The source code of the Java programs, implementing the experiments
is in the [src/main](src/main) directory.  You can build the experiment 
programs in one of the following ways.

__Using Maven__: Execute the following from the root of the
repository.

```shell
mvn clean package
```

__Using Make__: Or, you can execute the following from the root
of the repository.

```shell
make build
```

This produces a jar file containing 6 Java programs for running 
different parts of the experiments and analysis. The jar also contains all
dependencies, including [Chips-n-Salsa](https://chips-n-salsa.cicirello.org/), 
[JavaPermutationTools](https://jpt.cicirello.org), and 
[&rho;&mu;](https://rho-mu.cicirello.org).
If you are unfamiliar with the usual structure of the directories of 
a Java project built with Maven, the `.class` files, the `.jar` file, 
etc will be found in a `target` directory that is created by the 
build process.

## Running the Experiments

If you just want to inspect the data from my runs, then you can find that output
in the [/data](data) directory. If you instead want to run the experiments yourself,
you must first either follow the build instructions or download a prebuilt jar (see above
sections). Once the jar of the experiments is either built or downloaded, you can then run 
the experiments with the following executed at the root of the repository:

```shell
make experiments
```

If you don't want to overwrite my original data files, then first change the variable
`pathToDataFiles` in the `Makefile` before running the above command.

This will run each of the experiment programs in sequence, 
with the results piped to text files in the [/data](data) directory. Note that
this directory already contains the output from my runs, so if you execute this command,
you will overwrite the data that was used in the paper. Some parts of this will not
change, but certain parts, due to randomization may not be exactly the same, although should
be statistically consistent. 

There are also several other targets in the Makefile if you wish to 
run only some of the experiments from the paper. See the Makefile for
details.

## Analyzing the Experimental Data

To run the Python program that I used to generate summary statistics,  
and generate the graphs for the figures from the paper,
you need Python 3 installed. The source code of the Python programs is 
found in the [src/analysis](src/analysis) directory.  To run the analysis
execute the following at the root of the repository:

```shell
make analysis
```

If you don't want to overwrite my original data files, and figures, then change the variable
`pathToDataFiles` in the `Makefile` before running the above command.

This will analyze the data from the [/data](data) directory. It will also 
generate the figures in that directory, as well as output a few txt files with
summary statistics into that directory. This make command will also take
care of installing any required Python packages if you don't already have them
installed, such as matplotlib.

To convert the `eps` versions of the figures to `pdf`, then after running the above
analysis, run the following (this assumes that you have epstopdf installed):

```shell
make epstopdf
```

## Running the Principle Component Analysis and Fitness Distance Correlation Examples

The above Makefile targets only runs and analyzes the experiments that originated with the 
paper for MONE. That paper is an extended version of a conference paper from the
BICT 2019 conference. A couple sections of the MONE paper originated in that BICT 2019 
paper, including sections that classified the various distance metrics for permutations
using principal component analysis, along with a few examples of fitness distance
correlation. 

To run the code to recreate that, use the following Makefile target:

```shell
make bict2019
```

## Other Files in the Repository

There are a few other files, potentially of interest, in the repository,
which include:
* `system-stats.txt`: This file contains details of the system I 
  used to run the experiments, such as operating system, processor 
  specs, Java JDK and VM. It is in the [/data](data) directory.

## License

The code to replicate the experiments from the paper, as well as the
Chips-n-Salsa, JavaPermutationTools, and &rho;&mu; libraries are licensed 
under the [GNU General Public License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
