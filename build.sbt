
scalaVersion := "2.12.3"

name := "coursecal"
organization := "edu.holycross.shot"
version := "1.0.0"
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))
//resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith", "maven")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",

  "org.yaml" % "snakeyaml" % "1.14"
)
