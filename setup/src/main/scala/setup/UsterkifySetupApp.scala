package setup

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.event.slf4j.Logger
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest

import scala.concurrent.ExecutionContext

object UsterkifySetupApp {

  private val logger = Logger(UsterkifySetupApp.getClass.getName)

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "usterkify")

    Http().singleRequest(HttpRequest(uri = "http://localhost:8080/login")).map(println)(ExecutionContext.global)
  }
}
