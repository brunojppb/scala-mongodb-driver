package scalabook.interfaces

import com.mongodb.DBObject
import scala.collection.mutable.{Map => MutableMap}

/**
  * Created by bruno on 10/02/17.
  */
trait Memorizer extends ReadOnly {

  val history: MutableMap[Int, DBObject] = MutableMap[Int, DBObject]()

  override def findOne = {
    history.getOrElseUpdate(-1, { super.findOne })
  }

  override def findOne(doc: DBObject) = {
    history.getOrElseUpdate(doc.hashCode(), { super.findOne(doc) })
  }
}
