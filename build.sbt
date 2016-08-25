//libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "0.4.2"

libraryDependencies += "com.lihaoyi" % "ammonite" % "0.7.4" % "test" cross CrossVersion.full

libraryDependencies += "com.lihaoyi" %% "ammonite-ops" % "0.7.4"

libraryDependencies +=  "codes.reactive" %% "scala-time" % "0.4.0"

initialCommands in (Test, console) := """ammonite.Main().run()"""
