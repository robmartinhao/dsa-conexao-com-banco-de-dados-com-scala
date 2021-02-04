import java.sql.{Connection, DriverManager}

object Exercicio03 {

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
      val resultSet = statement.executeQuery("SELECT * from movie")

      while ( resultSet.next() ) {
        val movie_title = resultSet.getString("movie_title")
        val director_name = resultSet.getString("director_name")
        val movie_duration = resultSet.getInt("duration")
        println("title, director, duration = " + movie_title + ", " + director_name + ", " + movie_duration)
      }
      resultSet.close()
    } catch {
      case e => e.printStackTrace()
    }
    connection.close()
  }
}
