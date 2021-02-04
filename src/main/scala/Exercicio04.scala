import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.{LinearRegression}
import org.apache.log4j._
import org.apache.spark.sql.SparkSession

/**
  * Created by suema on 05/07/2017.
  * Oracle, Spark e ML
  */
object Exercicio04 {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    // Criando a sessão
    val spark = SparkSession.builder()
      .appName("DSA")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    // Definindo parâmetros de conexão
    val hostname = "localhost"
    val host_port = 1521
    val dbname = "xe"
    val username = "suemar"
    val password = "dsa123"

    // Definindo parâmetros de autenticação
    val connectionProperties = new java.util.Properties()
    connectionProperties.setProperty("user",username)
    connectionProperties.setProperty("password",password)

    // Criando URL JDBC
    val jdbc_url = "jdbc:oracle:thin:@%s:%d:%s".format(hostname, host_port, dbname)

    // Tabela a ser recuperada
    val table = "seguro"

    var data = spark.read.jdbc(url=jdbc_url, table=table, properties=connectionProperties)

    // Imprime o esquema
    data.printSchema()

    // Mostra 5 linhas do dataset
    data.show(5)

    // Mostrando números de linhas
    println("Total de instâncias: " + data.count())

    // Algumas coisas que precisamos fazer antes que o Spark possa aceitar os dados!
    // Definir o dataset na forma de duas colunas ("label", "features")
    // Isso nos permitirá juntar várias colunas de recursos em uma única coluna de uma matriz de valores feautre

    val df = data.select(data("valor").as("label"),$"reclamacoes")
    df.show(3)

    // Define as colunas de entrada das quais devemos ler os valores
    // Define o nome da coluna onde o vetor será armazenado
    val assembler = new VectorAssembler()
       .setInputCols(Array("reclamacoes"))
       .setOutputCol("features")

    // Transformamos o dataset em um objeto de duas colunas, no formato esperado pelo modelo
    val output = assembler.transform(df).select($"label",$"features")

    //Imprimindo a versão final do dataframe que vai alimentar o modelo de regressão
    println("OUTPUT")
    output.show()

    // Configurando o modelo de regressão
    // Criar um objeto de Regressão Linear
    val lr = new LinearRegression()

    // Fit do modelo nos dados
    val lrModel = lr.fit(output)

    println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

    // Avaliação
    val trainingSummary = lrModel.summary

    // Resíduos e Previsões
    trainingSummary.residuals.show()
    trainingSummary.predictions.show()

    // Métricas
    println(s"R2: ${trainingSummary.r2}")

    // Finalizando a sessão
    spark.stop()
  }
}
