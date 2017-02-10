package scalabook.interfaces

import com.mongodb.DBObject

/**
  * Created by bruno on 09/02/17.
  */
trait Updatable extends ReadOnly {
  def -=(doc: DBObject): Unit = underlying.remove(doc)
  def +=(doc: DBObject): Unit = underlying.save(doc)
}
