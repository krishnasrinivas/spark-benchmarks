logLevel := Level.Warn

resolvers += Resolver.url("bintray-sbt-plugins", url("http://dl.bintray.com/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "Spark Package Main Repo" at "https://dl.bintray.com/spark-packages/maven"

addSbtPlugin("de.heikoseeberger"                 % "sbt-header"       % "1.8.0")
addSbtPlugin("com.geirsson"                      % "sbt-scalafmt"     % "0.6.8")
addSbtPlugin("com.lightbend.paradox"             % "sbt-paradox"      % "0.2.9")
addSbtPlugin("com.eed3si9n"                      % "sbt-unidoc"       % "0.4.0")
addSbtPlugin("com.thoughtworks.sbt-api-mappings" % "sbt-api-mappings" % "1.0.0")
addSbtPlugin("com.eed3si9n"                      % "sbt-assembly"     % "0.14.4")
addSbtPlugin("com.eed3si9n"                      % "sbt-buildinfo"    % "0.7.0")
addSbtPlugin("com.jsuereth"                      % "sbt-pgp"          % "1.1.1")
addSbtPlugin("org.xerial.sbt"                    % "sbt-sonatype"     % "2.3")
