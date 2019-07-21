
scalaVersion := "2.11.1"

// Smile:
libraryDependencies += "com.github.haifengl" % "smile-core" % "1.5.3"
libraryDependencies += "com.github.haifengl" %% "smile-scala" % "1.5.3"


// alternative: bruneli:

libraryDependencies += "com.github.bruneli.scalaopt" % "scalaopt-core_2.11" % "0.2"


// breeze:
libraryDependencies  ++= Seq(
  // Last stable release
  "org.scalanlp" %% "breeze" % "0.13.2",

  // Native libraries are not included by default. add this if you want them (as of 0.7)
  // Native libraries greatly improve performance, but increase jar sizes.
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % "0.13.2",

  // The visualization library is distributed separately as well.
  // It depends on LGPL code
  "org.scalanlp" %% "breeze-viz" % "0.13.2"
)
