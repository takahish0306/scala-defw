import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

object ScalaFramework extends Build { 
  val baseSettings = Seq(
    version := "0.1.0",
    organization := "com.github.takahish0306.framework",
    crossScalaVersions := Seq("2.10.2"),
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.8.1" % "test" withSources(),
      "org.scalatest" %% "scalatest" % "1.9.1" % "test",
      "org.scala-tools.testing" %% "specs" % "1.6.9" % "test" withSources()
    ),
    scalacOptions ++= Seq("-encoding", "utf8")
  )

  lazy val scalaFramework = Project(
    id = "scala-framework",
    base = file("."),
    settings = Project.defaultSettings ++
      baseSettings ++
      assemblySettings
  ).settings(
    name := "scala-framework",
    libraryDependencies ++= Seq(
      "commons-io" % "commons-io" % "2.4",
      "commons-httpclient" % "commons-httpclient" % "3.1",
      "mysql" % "mysql-connector-java" % "5.1.+",
      "redis.clients" % "jedis" % "2.1.0",
      "com.twitter" % "util-eval_2.10" % "6.5.0"
    )
  )
}
