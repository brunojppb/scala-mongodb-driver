package start

import com.mongodb.BasicDBObject

import scalabook.mongodb.wrapper.{MongoClient, Query}
/**
  * Created by bruno on 12/02/17.
  */
object MainCaseClasses extends App {

  def client = new MongoClient
  def db = client.db("mydb")
  val collection = db.readOnlyCollection("test")
  val updatableCollection = db.updatableCollection("test")
  for (i <- 1 to 100) updatableCollection += new BasicDBObject("i", i)

  val rangeQuery = new BasicDBObject("i", new BasicDBObject("$gt", 20))
  val richQuery = Query(rangeQuery).skip(20).limit(10)
  val cursor = collection.find(richQuery)
  while(cursor.hasNext) {
    println(cursor.next)
  }
}
