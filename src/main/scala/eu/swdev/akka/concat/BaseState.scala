package eu.swdev.akka.concat

import akka.actor.ActorSystem
import akka.http.Bridge
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, StatusCodes, Uri}
import akka.http.scaladsl.server.Directives.{complete, pathPrefix}
import akka.stream.ActorMaterializer
import org.openjdk.jmh.annotations.{Scope, State, TearDown}
import org.scalacheck.Gen
import org.scalacheck.rng.Seed

import scala.concurrent.Await
import scala.concurrent.duration._

@State(Scope.Benchmark)
abstract class BaseState(depth: Int, branches: Int) {

  val samples = 1000

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  import system.dispatcher

  def buildRoutes(depth: Int): Routes = {
    if (depth > 0) {
      val inner = buildRoutes(depth - 1)
      (0 until branches).map { i =>
        inner.below(pathPrefix(('A'.toInt + i).toChar.toString))
      }.reduce(_ concat _)
    } else {
      new Routes(
        complete(StatusCodes.OK),
        complete(StatusCodes.OK),
        complete(StatusCodes.OK),
        complete(StatusCodes.OK)
      )
    }
  }



  val routes = buildRoutes(depth)

  val genRequestCtx = Gen
      .listOfN(depth, Gen.choose(0, branches - 1).map(i => (i + 'A').toChar.toString))
      .map(_.mkString("/", "/", ""))
      .map(uri => HttpRequest(HttpMethods.GET, Uri(uri)))
      .map(Bridge.requestContext(_))

  val genRequestCtxs = Gen.listOfN(samples, genRequestCtx)

  val requestCtxs = genRequestCtxs(Gen.Parameters.default, Seed(1234567890)).get

  @TearDown
  def tearDown(): Unit = {
    Await.result(system.terminate(), 5 seconds)
  }

}

class State_3_3 extends BaseState(3, 3)

class State_10_3 extends BaseState(10, 3)

class State_3_10 extends BaseState(3, 10)

class State_10_10 extends BaseState(10, 10)

