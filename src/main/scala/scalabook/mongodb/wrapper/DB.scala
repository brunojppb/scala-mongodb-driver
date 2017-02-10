package scalabook.mongodb.wrapper

import com.mongodb.{DB => MongoDB}

import scala.collection.convert.Wrappers.JSetWrapper
import scalabook.interfaces.{Administrable, Updatable}

/**
  * Created by bruno on 09/02/17.
  */

class DB private(val underlying: MongoDB) {

  def collectionNames = for(name <- new JSetWrapper(underlying.getCollectionNames)) yield name

  private def collection(name: String) = underlying.getCollection(name)
  def readOnlyCollection(name: String) = new DBCollection(collection(name))
  def administrableCollection(name: String) = new DBCollection(collection(name)) with Administrable
  def updatableCollection(name: String) = new DBCollection(collection(name)) with Updatable

}

object DB { def apply(underlying: MongoDB) = new DB(underlying) }
