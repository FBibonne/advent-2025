ThisBuild / scalaVersion     := "2.13.14"

lazy val root = (project in file("."))
  .settings(
    name := "a-world-without-mocks-scala-before",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.mockito" % "mockito-core" % "5.12.0" % Test
    )
  )
