name := """bookstore"""
organization := "com.example.addy"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += jdbc

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.27"
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.4"

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.2"

libraryDependencies += "com.auth0" % "java-jwt" % "3.18.3"

//libraryDependencies += "com.h2database" % "h2" % "2.1.210" % Test

libraryDependencies ++= Seq(
  javaJdbc
)