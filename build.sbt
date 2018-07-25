name := "Akka Route Concatenation Performance Test"

scalaVersion in ThisBuild := "2.12.6"

val akkaHttpVersion = "10.1.3"

enablePlugins(JmhPlugin)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % "2.5.13",
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
  "org.scalacheck" %% "scalacheck" % "1.14.0",
  "org.scalatest" %% "scalatest" % "3.0.5"
)
