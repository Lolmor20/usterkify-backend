ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.20"

ThisBuild / organization := "com.nimm"

val AkkaVersion = "2.8.6"
val AkkaHttpVersion = "10.5.3"

val ScalaLoggingVersion = "3.9.5"
val LogbackClassicVersion = "1.5.6"
val MongoDriverVersion = "5.1.4"

val Akka = Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
)

val Logging = Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % ScalaLoggingVersion,
  "ch.qos.logback" % "logback-classic" % LogbackClassicVersion
)

val MongoDriver = Seq("org.mongodb.scala" %% "mongo-scala-driver" % MongoDriverVersion)


lazy val api = (project in file("api"))
  .enablePlugins(
    JavaAppPackaging,
    DockerPlugin
  )
  .settings(
    dockerBaseImage := "openjdk:21-jdk",
    Compile / mainClass := Some("api.UsterkifyApiApp"),
    Docker / packageName := "usterkify/api",
    dockerExposedPorts := Seq(8080),
    libraryDependencies ++= Akka ++ Logging ++ MongoDriver
  )

lazy val setup = (project in file("setup"))
  .enablePlugins(
    JavaAppPackaging,
    DockerPlugin
  )
  .settings(
    dockerBaseImage := "openjdk:21-jdk",
    Compile / mainClass := Some("setup.UsterkifySetupApp"),
    Docker / packageName := "usterkify/setup",
    libraryDependencies ++= Akka ++ Logging ++ MongoDriver
  )

lazy val root = (project in file("."))
  .aggregate(api, setup)
