lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    name := """munro-library-challenge""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      // OpenCSV library for parsing CSV file
      "com.opencsv" % "opencsv" % "5.3",
      // Mockito for creating mocks in testing
      "org.mockito" % "mockito-core" % "2.10.0" % "test",
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
    javaOptions in Test += "-Dlogger.file=conf/logback-test.xml",
    // Make verbose tests
    testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
  )

PlayKeys.devSettings += "play.server.http.address" -> "127.0.0.1"