package start

import com.mongodb.BasicDBObject

import scalabook.mongodb.wrapper.MongoClient

/**
  * Created by bruno on 09/02/17.
  */
object Main extends App {

  def client = new MongoClient
  def db = client.createDB("mydb")

  for {
    name <- db.collectionNames
  } yield print(s"DB name: $name\n\n")

  val collection = db.readOnlyCollection("test")
  print(collection.name)

  val adminCollection = db.administrableCollection("test")
  adminCollection.drop

  val updatableCollection = db.updatableCollection("test")

  val doc = new BasicDBObject()
  doc.put("name", "MongoDB")
  doc.put("type", "database")
  doc.put("count", 1)

  val info = new BasicDBObject()
  info.put("x", 203)
  info.put("y", 101)
  doc.put("info", info)
  updatableCollection += doc

  println(updatableCollection.findOne)

  updatableCollection -= doc
  println(updatableCollection.findOne)

  for(i <- 1 to 100) updatableCollection += new BasicDBObject("i", i)

  val query = new BasicDBObject()
  query.put("i", 71)
  val cursor = updatableCollection.find(query)
  while(cursor.hasNext) {
    println(cursor.next)
  }


}
