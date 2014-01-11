import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object DefW extends Build {
  val Organization = "defw"
  val Name = "DefW"
  val Version = "0.3.0"
  val ScalaVersion = "2.10.2"
 
  val baseSettings = Seq(
    version := Version,
    organization := Organization,
    scalaVersion := ScalaVersion,
    name := Name,
    resolvers += Classpaths.typesafeReleases,
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.8.1" % "test" withSources(),
      "org.scalatest" %% "scalatest" % "1.9.1" % "test",
      "org.scala-tools.testing" %% "specs" % "1.6.9" % "test" withSources()
    ),
    scalacOptions ++= Seq("-encoding", "utf8", "-unchecked", "-deprecation")
  )

  lazy val defwUtil = Project(
    id = "defw-util",
    base = file("./util"),
    settings = Project.defaultSettings ++
      baseSettings ++
      assemblySettings
  ).settings(
    name := "defw-util",
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.5",
      "ch.qos.logback" % "logback-classic" % "1.0.13",
      "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",
      "commons-io" % "commons-io" % "2.4",
      "org.apache.httpcomponents" % "httpclient" % "4.3.1",
      "postgresql" % "postgresql" % "9.1-901.jdbc4",
      "redis.clients" % "jedis" % "2.1.0",
      "com.twitter" % "util-eval_2.10" % "6.10.0"
    )
  )

  lazy val defwWebapp = Project(
    id = "defw-webapp",
    base = file("./webapp"),
    settings = Project.defaultSettings ++
      baseSettings ++
      ScalatraPlugin.scalatraWithJRebel ++
      scalateSettings
  ).settings(
    name := "defw-webapp",
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra" % "2.2.2",
      "org.scalatra" %% "scalatra-scalate" % "2.2.2",
      "org.scalatra" %% "scalatra-specs2" % "2.2.2" % "test",
      "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
      "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
      "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
    ),
    scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
      Seq(
        TemplateConfig(
          base / "webapp" / "WEB-INF" / "templates",
          Seq.empty,  /* default imports should be added here */
          Seq(
            Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
          ),  /* add extra bindings here */
          Some("templates")
        )
      )
    }
  ).dependsOn(defwUtil)

  lazy val defwWebsocket = Project(
    id = "defw-websocket",
    base = file("./websocket"),
    settings = Project.defaultSettings ++
      baseSettings ++
      ScalatraPlugin.scalatraWithJRebel
  ).settings(
    name := "defw-websocket",
    libraryDependencies ++= Seq(
      "org.eclipse.jetty" % "jetty-webapp" % "8.1.12.v20130726" % "container",
      "org.eclipse.jetty" % "jetty-websocket" % "8.1.12.v20130726",
      "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
    )
  ).dependsOn(defwUtil)
}
