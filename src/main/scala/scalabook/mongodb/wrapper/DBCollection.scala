package scalabook.mongodb.wrapper
import com.mongodb.{DBCollection => MongoDBCollection}

import scalabook.interfaces.{Administrable, ReadOnly, Updatable}

/**
  * Created by bruno on 09/02/17.
  */
class DBCollection(override val underlying: MongoDBCollection) extends ReadOnly
