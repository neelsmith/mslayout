import com.github.bruneli.scalaopt.core._
import gradient.BFGS._
import smile._
import smile.util._
import smile.math._
import smile.data._

 def energy(lineYs: Array[Double], heights: Array[Double], leading: Array[Double] ) = {
   val htsPlusLeads = heights + leading
   val centers = htsPlusLeads - heights / 2
   val diffs = lineYs - centers
   (diffs.toArray.map( d => d * d)).sum
 }


val f = "dataexample.csv"

val data = read.csv(f, header = true, rowNames = true)
val df = DataFrame(data)

val initialLead = Array.fill(df.lineVertical.size)(0.0)
val initialEnergy = energy(df.lineVertical.vector, df.scholionHeight.vector, initialLead)

minimize((x: Variables) => {println("Dothework"); x dot x} , Vector(2.0, 4.0)) // Approximate derivatives
