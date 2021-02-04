import java.sql.{Connection, DriverManager}

object Exercicio02 {

  def main(args: Array[String]): Unit = {

    val driver = "com.mysql.cj.jdbc.Driver"
    val url = "jdbc:mysql://172.17.0.2:3306/dsaalunos"
    val username = "root"
    val password = "root"

    var connection: Connection = null

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)

      val statement = connection.createStatement()
      statement.executeUpdate("CREATE TABLE alunos(ra BIGINT(5) PRIMARY KEY , nome VARCHAR(60) NOT NULL)")
      statement.executeUpdate("INSERT INTO alunos (ra, nome) values(1, 'Suemar')")
      println("Linhas afetadas pelo insert: " + statement.executeUpdate("INSERT INTO alunos (ra, nome) values (2, 'Daniel')"))
      println("Linhas afetadas pelo update: " + statement.executeUpdate("UPDATE alunos SET nome = 'João Silva'"))
    } catch {
      case e => e.printStackTrace()
    }
    connection.close()
  }
}
