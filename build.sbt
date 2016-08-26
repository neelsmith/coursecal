
libraryDependencies += "com.lihaoyi" % "ammonite" % "0.7.4" % "test" cross CrossVersion.full

libraryDependencies += "com.lihaoyi" %% "ammonite-ops" % "0.7.4"

libraryDependencies += "org.yaml" % "snakeyaml" % "1.14"

initialCommands in (Test, console) := """ammonite.Main().run()"""
