
crossScalaVersions  :=  Seq("2.12.4") //Seq("2.11.8", "2.12.4")
scalaVersion := (crossScalaVersions ).value.last

name := "coursecal"
organization := "edu.holycross.shot"
version := "1.2.7"
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))
resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith", "maven")
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.wvlet.airframe" %% "airframe-log" % "19.8.10",
  "org.yaml" % "snakeyaml" % "1.14"

)
