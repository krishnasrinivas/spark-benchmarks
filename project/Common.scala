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
      organization := "io.minio",
      organizationName := "MinIO, Inc.",
      organizationHomepage := Some(url("https://www.min.io")),
      version := "0.2.0",
      scmInfo := Some(
        ScmInfo(
          url("https://github.com/minio/spark-benchmarks"),
          "git@github.com:minio/spark-benchmarks.git"
        )
      ),

      developers += Developer(
        id = "minio",
        name = "MinIO",
        email = "dev@min.io",
        url = url("https://www.min.io")
      ),

      licenses := Seq(("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))),
      homepage := Some(url("https://github.com/minio/spark-benchmarks")),
      description := "Spark testDFSIO benchmarks",

      publishMavenStyle := true,

      publishTo := {
        val nexus = "https://oss.sonatype.org/"
        if (version.value.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/snapshots")
        else
          Some("releases"  at nexus + "service/local/staging/deploy/maven2")
      },

      pomIncludeRepository := { _ => false },

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
