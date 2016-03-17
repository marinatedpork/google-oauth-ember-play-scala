name := """oauth-google-ember"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.google.http-client"    %  "google-http-client-jackson2"   % "1.19.0",
  "com.google.oauth-client"   %  "google-oauth-client-jetty"     % "1.19.0",
  "com.google.apis"           %  "google-api-services-oauth2"    % "v2-rev59-1.17.0-rc",
  "com.google.gdata"          %  "core"                          % "1.47.1"
)

scalacOptions ++= Seq("-feature")
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
excludeFilter in (Assets, JshintKeys.jshint) := "*.js"

routesGenerator := InjectedRoutesGenerator
