package eu.swdev.akka

import akka.http.scaladsl.server.{Directive0, Route, RouteResult}
import akka.http.scaladsl.util.FastFuture
import akka.http.scaladsl.util.FastFuture._

package object concat {

  implicit class RouteOps(val route: Route) extends AnyVal {

    def concat1(other: => Route): Route = { ctx ⇒
      import ctx.executionContext
      route(ctx).fast.flatMap {
        case x: RouteResult.Complete ⇒ FastFuture.successful(x)
        case RouteResult.Rejected(outerRejections) ⇒
          other(ctx).fast.map {
            case x: RouteResult.Complete ⇒ x
            case RouteResult.Rejected(innerRejections) ⇒
              RouteResult.Rejected(outerRejections ++ innerRejections)
          }
      }
    }

    def concat2(other: Route): Route = { ctx ⇒
      import ctx.executionContext
      route(ctx).fast.flatMap {
        case x: RouteResult.Complete ⇒ FastFuture.successful(x)
        case RouteResult.Rejected(outerRejections) ⇒
          other(ctx).fast.map {
            case x: RouteResult.Complete ⇒ x
            case RouteResult.Rejected(innerRejections) ⇒
              RouteResult.Rejected(outerRejections ++ innerRejections)
          }
      }
    }

  }

  implicit class RouteOps2(val route: Route) {

    def concat3(other: => Route): Route = { ctx ⇒
      import ctx.executionContext
      route(ctx).fast.flatMap {
        case x: RouteResult.Complete ⇒ FastFuture.successful(x)
        case RouteResult.Rejected(outerRejections) ⇒
          other(ctx).fast.map {
            case x: RouteResult.Complete ⇒ x
            case RouteResult.Rejected(innerRejections) ⇒
              RouteResult.Rejected(outerRejections ++ innerRejections)
          }
      }
    }

    def concat4(other: Route): Route = { ctx ⇒
      import ctx.executionContext
      route(ctx).fast.flatMap {
        case x: RouteResult.Complete ⇒ FastFuture.successful(x)
        case RouteResult.Rejected(outerRejections) ⇒
          other(ctx).fast.map {
            case x: RouteResult.Complete ⇒ x
            case RouteResult.Rejected(innerRejections) ⇒
              RouteResult.Rejected(outerRejections ++ innerRejections)
          }
      }
    }
  }

  class Routes(route1: => Route, route2: => Route, route3: => Route, route4: => Route) {

    def r1 = route1
    def r2 = route2
    def r3 = route3
    def r4 = route4

    def concat(other: Routes): Routes = new Routes(
      route1 concat1 other.r1,
      route2 concat2 other.r2,
      route3 concat3 other.r3,
      route4 concat4 other.r4
    )

    def below(dir: Directive0): Routes = {
      new Routes(
        dir(route1),
        dir(route2),
        dir(route3),
        dir(route4)
      )
    }

  }

}
