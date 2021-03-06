package scalabook.mongodb.wrapper

import com.mongodb.Mongo

/**
  * Created by bruno on 07/02/17.
  */

class MongoClient (val host: String, val port: Int) {
  // Validate params
  require(host != null, "You have to provide a host name")
  private val underlying = new Mongo(host, port)
  // Overloaded constructor
  def this() = this("127.0.0.1", 27017)

  def version = underlying.getVersion

  def dropDB(name: String) = underlying.dropDatabase(name)

  // createDB(_) and db() do the same thing.
  // the MongoDB Java driver creates a db if one isn't found.
  // These two are added only for readability
  def createDB(name: String) = DB(underlying.getDB(name))

  def db(name: String) = DB(underlying.getDB(name))

}
