package scalabook.interfaces

import com.mongodb.{DBCursor, DBObject, DBCollection => MongoDBCollection}

import scalabook.mongodb.wrapper._

/**
  * Created by bruno on 09/02/17.
  */
trait ReadOnly {

  val underlying: MongoDBCollection

  def name = underlying.getName
  def fullName = underlying.getFullName

  def find(query: Query): DBCursor = {
    def applyOptions(cursor: DBCursor, option: QueryOption): DBCursor = {
      option match {
        case Skip(skip, next) => applyOptions(cursor.skip(skip), next)
        case Sort(sorting, next) => applyOptions(cursor.sort(sorting), next)
        case Limit(limit, next) => applyOptions(cursor.limit(limit), next)
        case NoOption => cursor
      }
    }
    applyOptions(find(query.q), query.option)
  }

  def find(doc: DBObject): DBCursor = underlying.find(doc)
  def findOne(doc: DBObject) = underlying.findOne(doc)
  def findOne = underlying.findOne()
  def getCount(doc: DBObject) = underlying.getCount(doc)

}
