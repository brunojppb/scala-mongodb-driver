package scalabook.interfaces

/**
  * Created by bruno on 09/02/17.
  */
trait Administrable extends ReadOnly {
  def drop: Unit = underlying.drop()
  def dropIndexes: Unit = underlying.dropIndexes()
}
