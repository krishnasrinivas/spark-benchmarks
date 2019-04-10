import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport._
import de.heikoseeberger.sbtheader._
import de.heikoseeberger.sbtheader.license.Apache2_0
import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

object Common extends AutoPlugin {

  override def trigger = allRequirements

  override def requires = JvmPlugin && AutomateHeaderPlugin

  override lazy val projectSettings =
    Dependencies.Common ++ Seq(
      organization := "io.minio.spark",
      organizationName := "MinIO, Inc.",
      version := "0.2.0",
      organizationHomepage := Some(url("https://github.com/minio")),
      scmInfo := Some(ScmInfo(url("https://github.com/minio/spark-benchmarks"), "git@github.com:minio/spark-benchmarks.git")),
      developers += Developer("contributors", "Contributors", "", url("https://github.com/minio/spark-benchmarks/contributors")),

      licenses := Seq(("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))),

      publishMavenStyle := true,
      publishArtifact in Test := false,

      pomIncludeRepository := { _ => false },

      publishTo := {
        val nexus = "https://oss.sonatype.org/"
        if (version.value.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/snapshots")
        else
          Some("releases"  at nexus + "service/local/staging/deploy/maven2")
      },

      pomExtra := (
        <url>https://github.com/minio/spark-benchmarks</url>
            <scm>
          <connection>scm:git:github.com/minio/spark-benchmarks</connection>
          <developerConnection>scm:git:git@github.com:minio/spark-benchmarks</developerConnection>
          <url>github.com/minio/spark-benchmarks</url>
          </scm>
          <developers>
          <developer>
          <id>minio</id>
          <name>MinIO</name>
          <url>http://www.min.io</url>
            </developer>
          </developers>),

      scalaVersion := Dependencies.ScalaVersion,

      scalacOptions ++= Seq(
        "-encoding", "UTF-8",
        "-feature",
        "-unchecked",
        "-deprecation",
        "-Xlint",
        "-Yno-adapted-args",
        "-Ywarn-dead-code",
        "-Xfuture"
      ),

      javacOptions ++= Seq(
        "-Xlint:unchecked"
      ),

      headers := headers.value ++ Map(
        "scala" -> Apache2_0("2019", "MinIO, Inc."),
        "java" -> Apache2_0("2019", "MinIO, Inc."),
        "conf" -> Apache2_0("2019", "MinIO, Inc.", "#")
      )
    )

}
