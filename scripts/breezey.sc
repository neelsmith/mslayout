import breeze.optimize._
import breeze.linalg._


case class Layout(linesY: DenseVector[Double], scholionHeight: DenseVector[Double]) {


  def plotSolution(solution: DenseVector[Double]) = {
    println("\n\nPlot solution:")
    println("Adjustments:" + solution )
    val cumul = accumulate(scholionHeight + solution) - scholionHeight
    println("Cumulative bases: " + cumul)
    val tops = cumul + scholionHeight
    println("Cumulative tops: " +  tops)
    for (i <- 0 until tops.size) {
      println(cumul(i) + "--" + tops(i))
    }
  }
  val diffFunc = new DiffFunction[DenseVector[Double]] {

    def calculate(leads: DenseVector[Double]) = {
      val htsPlusLeads = accumulate(scholionHeight + leads)
      println("Added: " + htsPlusLeads)
      val centers =  htsPlusLeads  + (scholionHeight :* -0.5)
      println("Centers: " + centers)
      val distance = centers - linesY
      println("Distance:" + distance)
      //
      // Function is sum of distance squared,
      // gradient is 2 x distance
      val sqs = distance.map( x => x*x)
      (sum(sqs), distance :* 2.0)
    }
  }
}



val iliadY = DenseVector(0.1, 0.3, 0.4, 0.6, 0.7, 0.8)
val scholionHt = DenseVector(0.2, 0.1, 0.2, 0.05, 0.05, 0.2)
val layout = Layout(iliadY, scholionHt)

val lbfgs = new LBFGS[DenseVector[Double]](maxIter=100, m=3)
val initialLeading = DenseVector.zeros[Double](6)

val optimum = lbfgs.minimize(layout.diffFunc,initialLeading)
val squared = layout.diffFunc(optimum)

layout.plotSolution(optimum)
