package api

import akka.http.scaladsl.server.Directives.{complete, pathPrefix, get, concat}
import akka.http.scaladsl.server.Route

case object ApiRoutes {
  val routes: Route = {
    val register = pathPrefix("register") {
      get {
        complete("Register")
      }
    }

    val login = pathPrefix("login") {
      get {
        complete("Login")
      }
    }

    concat(register, login)
  }
}
