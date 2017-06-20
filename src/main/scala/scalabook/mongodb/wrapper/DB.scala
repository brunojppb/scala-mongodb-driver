package scalabook.mongodb.wrapper

// Feature: Can rename imports to avoid namespace conflicts
import com.mongodb.{DB => MongoDB}

import scala.collection.convert.Wrappers.JSetWrapper
import scala.collection.mutable
import scalabook.interfaces.{Administrable, Memorizer, Updatable}

/**
  * Created by bruno on 09/02/17.
  */

// private makes the constructor only available for
// the companion object
class DB private(val underlying: MongoDB) {

  private def collection(name: String) = underlying.getCollection(name)
  def readOnlyCollection(name: String) = new DBCollection(collection(name)) with Memorizer
  def administrableCollection(name: String) = new DBCollection(collection(name)) with Administrable with Memorizer
  def updatableCollection(name: String) = new DBCollection(collection(name)) with Updatable with Memorizer

  def collectionNames: mutable.Set[String] = {
    for {
    // Wrapper helper to convert java.util.set to Scala Set.
    // That will allow us to use for-comprehension
      name <- new JSetWrapper(underlying.getCollectionNames)
    } yield name
  }

}

object DB {
  def apply(underlying: MongoDB) = new DB(underlying)
}
