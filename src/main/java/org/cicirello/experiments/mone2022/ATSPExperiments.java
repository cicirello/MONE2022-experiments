/*
 * Experiments for article in the journal MONE 2022.
 * Copyright (C) 2022 Vincent A. Cicirello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.cicirello.experiments.mone2022;

import java.util.ArrayList;
import org.cicirello.permutations.Permutation;
import org.cicirello.search.operators.MutationOperator;
import org.cicirello.search.operators.permutations.AdjacentSwapMutation;
import org.cicirello.search.operators.permutations.SwapMutation;
import org.cicirello.search.operators.permutations.InsertionMutation;
import org.cicirello.search.operators.permutations.ReversalMutation;
import org.cicirello.search.operators.permutations.ThreeOptMutation;
import org.cicirello.search.operators.permutations.BlockMoveMutation;
import org.cicirello.search.operators.permutations.BlockInterchangeMutation;
import org.cicirello.search.operators.permutations.CycleMutation;
import org.cicirello.search.operators.permutations.ScrambleMutation;
import org.cicirello.search.operators.permutations.UniformScrambleMutation;
import org.cicirello.search.problems.tsp.RandomTSPMatrix;
import org.cicirello.search.evo.GenerationalMutationOnlyEvolutionaryAlgorithm;
import org.cicirello.search.evo.NegativeIntegerCostFitnessFunction;
import org.cicirello.search.evo.TruncationSelection;
import org.cicirello.search.operators.permutations.PermutationInitializer;

/**
 * Experiments with the ATSP.
 *
 * @author <a href=https://www.cicirello.org/ target=_top>Vincent A. Cicirello</a>, 
 * <a href=https://www.cicirello.org/ target=_top>https://www.cicirello.org/</a>
 */
public class ATSPExperiments {
	
	/**
	 * Runs the experiments.
	 * @param args Ignored as there are no command line arguments.
	 */
	public static void main(String[] args) {
		final int NUM_CITIES = 100;
		final int MAX_DISTANCE_BETWEEN_CITIES = 1000;
		final int NUM_INSTANCES = 100;
		final int POPULATION_SIZE = 100;
		final int INTERVAL = 10; // generations
		final int NUM_INTERVALS = 100;
		
		ArrayList<MutationOperator<Permutation>> mutationOps = new ArrayList<MutationOperator<Permutation>>();
		ArrayList<String> columnLabels = new ArrayList<String>(); 
		mutationOps.add(new AdjacentSwapMutation());
		columnLabels.add("AdjSwap");
		mutationOps.add(new SwapMutation());
		columnLabels.add("Swap");
		mutationOps.add(new InsertionMutation());
		columnLabels.add("Insertion");
		mutationOps.add(new ReversalMutation());
		columnLabels.add("Reversal");
		mutationOps.add(new ThreeOptMutation());
		columnLabels.add("3opt");
		mutationOps.add(new BlockMoveMutation());
		columnLabels.add("BlockMove");
		mutationOps.add(new BlockInterchangeMutation());
		columnLabels.add("BlockSwap");
		mutationOps.add(new CycleMutation(10));
		columnLabels.add("Cycle");
		mutationOps.add(new ScrambleMutation());
		columnLabels.add("Scramble");
		mutationOps.add(new UniformScrambleMutation(1.0/3.0));
		columnLabels.add("Uniform");
		
		System.out.print("Instance\tGenerations");
		for (String label : columnLabels) {
			System.out.print("\t" + label);
		}
		System.out.println();
		for (int seed = 1; seed <= NUM_INSTANCES; seed++) {
			RandomTSPMatrix.Integer problem = new RandomTSPMatrix.Integer(NUM_CITIES, MAX_DISTANCE_BETWEEN_CITIES, false, false, seed);
			
			ArrayList<GenerationalMutationOnlyEvolutionaryAlgorithm<Permutation>> evos = new ArrayList<GenerationalMutationOnlyEvolutionaryAlgorithm<Permutation>>();
			for (MutationOperator<Permutation> mutation : mutationOps) {
				evos.add(
					new GenerationalMutationOnlyEvolutionaryAlgorithm<Permutation>(
						POPULATION_SIZE,
						mutation.split(),
						1.0,
						new PermutationInitializer(NUM_CITIES),
						new NegativeIntegerCostFitnessFunction<Permutation>(problem),
						new TruncationSelection(POPULATION_SIZE / 4)
					)
				);
			}
			
			System.out.print(seed + "\t" + INTERVAL);
			for (GenerationalMutationOnlyEvolutionaryAlgorithm<Permutation> ea : evos) {
				ea.optimize(INTERVAL);
				System.out.print("\t" + ea.getProgressTracker().getCost());
			}
			System.out.println();
			for (int i = 1; i < NUM_INTERVALS; i++) {
				int totalNumGens = INTERVAL * (i + 1);
				System.out.print(seed + "\t" + totalNumGens);
				for (GenerationalMutationOnlyEvolutionaryAlgorithm<Permutation> ea : evos) {
					ea.reoptimize(INTERVAL);
					System.out.print("\t" + ea.getProgressTracker().getCost());
				}
				System.out.println();
			}
		}
	}
}