import Helpers.GenericObservable
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Sorts.descending
import org.mongodb.scala.{Document, MongoClient, MongoCollection}

object Exercicio06 {

  def main(args: Array[String]): Unit = {

    val mongoClient = MongoClient("mongodb://172.17.0.3")

    val database = mongoClient.getDatabase("dsa_db")

    val collection: MongoCollection[Document] = database.getCollection("pessoas")

    collection.find().printResults()
    println("")

    collection.find().first().printHeadResult()
    println("")

    collection.find(gt("idade", 38)).sort(descending("sobrenome")).printResults()
    println("")

    collection.deleteOne(equal("idade", 41)).printHeadResult("Delete Result: ")
    println("")

    collection.find().printResults()
    println("")

    mongoClient.close()
    print("FIM")

  }
}
