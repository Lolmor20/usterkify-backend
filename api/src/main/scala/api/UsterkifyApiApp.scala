package api

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.event.slf4j.Logger
import akka.http.scaladsl.Http
import org.mongodb.scala.MongoClient

object UsterkifyApiApp {

  private val logger = Logger(UsterkifyApiApp.getClass.getName)

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "usterkify")

    val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")

    Http().newServerAt("0.0.0.0", 8080).bind(ApiRoutes.routes)

    logger.info("Server started")
  }
}
