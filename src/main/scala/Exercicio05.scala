import Helpers.GenericObservable
import org.mongodb.scala.{Document, MongoClient, MongoCollection}

object Exercicio05 {

  def main(args: Array[String]): Unit = {

    val mongoClient = MongoClient("mongodb://172.17.0.3")

    val database = mongoClient.getDatabase("dsa_db")

    val doc: Document = Document(
      "_id" -> 2,
      "nome" -> "José",
      "sobrenome" -> "Souza",
      "idade" -> 41,
      "peso" -> 37,
      "cursos" -> Document("1" -> "Python", "2" -> "R")
    )

    val collection: MongoCollection[Document] = database.getCollection("pessoas")
    collection.insertOne(doc).printResults()

    mongoClient.close()
    print("FIM")

  }
}
