resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"


libraryDependencies += "com.lihaoyi" % "ammonite" % "0.7.4" % "test" cross CrossVersion.full

libraryDependencies += "com.lihaoyi" %% "ammonite-ops" % "0.7.4"

libraryDependencies += "org.yaml" % "snakeyaml" % "1.14"


libraryDependencies += "org.specs2" %% "specs2-core" % "3.6.5" % "test"


scalacOptions in Test ++= Seq("-Yrangepos")

initialCommands in (Test, console) := """ammonite.Main().run()"""

scalaVersion := "2.11.8"

name := "coursecal"

version := "0.9.15"
