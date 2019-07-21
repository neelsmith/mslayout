import smile._
import smile.util._
import smile.math._
import smile.data._

/*
import java.lang.Math._
import smile.math.Math.{log2, logistic, factorial, choose, random, randomInt, permutate, c, cbind, rbind, sum, mean, median, q1, q3, `var` => variance, sd, mad, min, max, whichMin, whichMax, unique, dot, distance, pdist, KullbackLeiblerDivergence => kld, JensenShannonDivergence => jsd, cov, cor, spearman, kendall, norm, norm1, norm2, normInf, standardize, normalize, scale, unitize, unitize1, unitize2, root}
import smile.math.distance._
import smile.math.kernel._
import smile.math.matrix._
import smile.math.matrix.Matrix._
import smile.stat.distribution._

import smile.interpolation._
import smile.validation._
import smile.association._
import smile.classification._
import smile.regression.{ols, ridge, lasso, svr, gpr}
import smile.feature._
import smile.clustering._
import smile.vq._
import smile.manifold._
import smile.mds._
import smile.sequence._
import smile.projection._
import smile.nlp._
import smile.wavelet._
*/

// Structure of data set in CSV file:
//   IliadRef,LineVertical,ScholionHeight
// where IliadRef is a passage identifier,
// LineVeritcal is Y position value normalized 0-1.0
// and Scholion is total height Y2 - Y1 normalized 0-1.0
val f = "dataexample.csv"

val data = read.csv(f, header = true, rowNames = true)
val df = DataFrame(data)


def energy(lineYs: Array[Double], heights: Array[Double], leading: Array[Double] ) = {
  println("Hieght values: " + heights.toVector)
  println("Fit to lines at " + lineYs.toVector)
  val htsPlusLeads = heights + leading
  println("Added: " + htsPlusLeads)
  val centers = htsPlusLeads - heights / 2
  println("Cetners: " + centers)
  val diffs = lineYs - centers
  println("Diffs: " + diffs)
  (diffs.toArray.map( d => d * d)).sum
}

// df.lineVertical.vector, df.scholionHeight.vector
val initialLead = Array.fill(df.lineVertical.size)(0.0)

val initialEnergy = energy(df.lineVertical.vector, df.scholionHeight.vector, initialLead)
/*   public static double min(
DifferentiableMultivariateFunction func,
int m,
double[] x,
double gtol,
int maxIter) {

*/
