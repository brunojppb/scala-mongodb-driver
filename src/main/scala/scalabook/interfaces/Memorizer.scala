package scalabook.interfaces

import com.mongodb.DBObject

/**
  * Created by bruno on 10/02/17.
  */
trait Memorizer extends ReadOnly {
  val history = scala.collection.mutable.Map[Int, DBObject]()
  override def findOne = {
    history.getOrElseUpdate(-1, { super.findOne })
  }

  override def findOne(doc: DBObject) = {
    history.getOrElseUpdate(doc.hashCode(), { super.findOne(doc) })
  }
}
