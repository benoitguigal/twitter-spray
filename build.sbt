import SonatypeKeys._

// Import default settings. This changes `publishTo` settings to use the Sonatype repository and add several commands for publishing.
sonatypeSettings

organization := "org.reactivebird"

name := "reactivebird"

version := "1.2-SNAPSHOT"

scalaVersion := "2.10.3"

resolvers ++= {
  Seq(
    "spray repo" at "http://repo.spray.io",
    "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/")
}

libraryDependencies ++= {
  val sprayV = "1.3.1"
  Seq(
    "com.typesafe.akka"  %% "akka-actor"     % "2.3.0",
    "joda-time"          %  "joda-time"      % "2.3",
    "org.joda"           % "joda-convert"    % "1.2",
    "io.spray"           % "spray-http"      % sprayV,
    "io.spray"           % "spray-httpx"     % sprayV,
    "io.spray"           % "spray-util"      % sprayV,
    "io.spray"           % "spray-client"    % sprayV,
    "io.spray"           % "spray-can"       % sprayV,
    "io.spray"           % "spray-caching"   % sprayV,
    "com.typesafe.play"  %% "play-iteratees" % "2.3.0",
    "io.spray"           %% "spray-json"     % "1.2.6",
    "com.typesafe"       % "config"          % "1.2.1" ,
    "org.scalatest"      % "scalatest_2.10"  % "2.1.3" % "test",
    "org.mockito"        % "mockito-core"    % "1.9.5" % "test")
}

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/benoitguigal/reactive-bird</url>
  <licenses>
    <license>
      <name>BSD-style</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:benoitguigal/reactive-bird.git</url>
    <connection>scm:git:git@github.com:benoitguigal/reactive-bird.git</connection>
  </scm>
  <developers>
    <developer>
      <id>BGuigal</id>
      <name>Benoit Guigal</name>
      <url>http://benoitguigal.me</url>
    </developer>
  </developers>
  )


