name := "dsa-conexao-com-banco-de-dados-com-scala"

version := "0.1"

scalaVersion := "2.11.6"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.1"

// https://mvnrepository.com/artifact/org.apache.spark/spark-mllib
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.1.1" % "provided"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.1.1"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "4.2.0"
