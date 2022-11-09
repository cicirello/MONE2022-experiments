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

/**
 * <p>Code to reproduce the experiments from the following article:</p>
 *
 * <p>Vincent A. Cicirello. 2022. <a href="https://www.cicirello.org/publications/Cicirello-MONE-2022.pdf">On 
 * Fitness Landscape Analysis of Permutation Problems: From Distance Metrics to Mutation Operator Selection</a>, 
 * <i>Mobile Networks and Applications</i>, 2022. 
 * doi:<a href="https://doi.org/10.1007/s11036-022-02060-z">10.1007/s11036-022-02060-z</a></p>
 *
 * @author <a href=https://www.cicirello.org/ target=_top>Vincent A. Cicirello</a>, 
 * <a href=https://www.cicirello.org/ target=_top>https://www.cicirello.org/</a>
 */
module org.cicirello.mone_article_experiments {
	exports org.cicirello.experiments.mone2022;
	
	requires org.cicirello.chips_n_salsa;
	requires org.cicirello.jpt;
	requires org.cicirello.rho_mu;
	requires org.cicirello.core;
}
