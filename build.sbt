lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    name := """munro-library-challenge""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      // Testing libraries for dealing with CompletionStage...
      "org.assertj" % "assertj-core" % "3.14.0" % Test,
      "org.awaitility" % "awaitility" % "4.0.1" % Test,
    ),
    javacOptions ++= Seq(
      "-encoding", "UTF-8",
      "-parameters",
      "-Xlint:unchecked",
      "-Xlint:deprecation",
      "-Werror"
    ),
    jacocoExcludes in Test := Seq(
      "controllers.Reverse*",
      "controllers.javascript.*",
      "Module",
      "router.Routes*",
      "*.routes*"
    ),
    // Make verbose tests
    testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
  )

PlayKeys.devSettings += "play.server.http.address" -> "127.0.0.1"