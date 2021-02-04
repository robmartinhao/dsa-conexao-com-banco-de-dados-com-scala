import java.sql.{Connection, DriverManager}

object Exercicio01 {

  def main(args: Array[String]): Unit = {

    val driver = "com.mysql.cj.jdbc.Driver"
    val url = "jdbc:mysql://172.17.0.2:3306/dsaalunos"
    val username = "root"
    val password = "root"

    var connection: Connection = null

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
    } catch {
      case e => e.printStackTrace()
    }
    connection.close()
  }
}
