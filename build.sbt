name := "car-advert-system"

version := "1.0.0"

scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  "com.wix" %% "accord-core" % "0.6",
  "net.codingwell" %% "scala-guice" % "4.0.0",
  "org.reactivemongo" % "play2-reactivemongo_2.11" % "0.11.7.play24",
  specs2 % Test
)

routesGenerator := InjectedRoutesGenerator