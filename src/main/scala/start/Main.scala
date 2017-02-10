package start

import com.mongodb.BasicDBObject

import scalabook.mongodb.wrapper.MongoClient

/**
  * Created by bruno on 09/02/17.
  */
object Main extends App {

  def client = new MongoClient
  def db = client.createDB("mydb")

  for(name <- db.collectionNames) println(name)

  val col = db.readOnlyCollection("test")
  print(col.name)

  val adminCol = db.administrableCollection("test")
  adminCol.drop

  val updatableCol = db.updatableCollection("test")

  val doc = new BasicDBObject()
  doc.put("name", "MongoDB")
  doc.put("type", "database")
  doc.put("count", 1)

  val info = new BasicDBObject()
  info.put("x", 203)
  info.put("y", 101)
  doc.put("info", info)
  updatableCol += doc

  println(updatableCol.findOne)

  updatableCol -= doc
  println(updatableCol.findOne)

  for(i <- 1 to 100) updatableCol += new BasicDBObject("i", i)

  val query = new BasicDBObject()
  query.put("i", 71)
  val cursor = col.find(query)
  while(cursor.hasNext) {
    println(cursor.next)
  }


}
