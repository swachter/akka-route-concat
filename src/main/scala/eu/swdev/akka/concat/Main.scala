package eu.swdev.akka.concat

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Route, RouteResult}
import org.openjdk.jmh.annotations.Benchmark

import scala.concurrent.Await
import scala.concurrent.duration._


abstract class MainBase {

  protected def doBenchmark(state: BaseState, route: Route): Int = {
    var i = 0
    state.requestCtxs.foreach { ctx =>
      val future = route(ctx)
      val RouteResult.Complete(httpResponse) = Await.result(future, 5 seconds)
      require(httpResponse.status == StatusCodes.OK)
      i = i + 1
    }
    i
  }

}

class Main_3_3 extends MainBase {

  @Benchmark
  def route1(state: State_3_3): Int = {
    doBenchmark(state, state.routes.r1)
  }

  @Benchmark
  def route2(state: State_3_3): Int = {
    doBenchmark(state, state.routes.r2)
  }

  @Benchmark
  def route3(state: State_3_3): Int = {
    doBenchmark(state, state.routes.r3)
  }

  @Benchmark
  def route4(state: State_3_3): Int = {
    doBenchmark(state, state.routes.r4)
  }

}

class Main_10_3 extends MainBase {

  @Benchmark
  def route1(state: State_10_3): Int = {
    doBenchmark(state, state.routes.r1)
  }

  @Benchmark
  def route2(state: State_10_3): Int = {
    doBenchmark(state, state.routes.r2)
  }

  @Benchmark
  def route3(state: State_10_3): Int = {
    doBenchmark(state, state.routes.r3)
  }

  @Benchmark
  def route4(state: State_10_3): Int = {
    doBenchmark(state, state.routes.r4)
  }

}

class Main_3_10 extends MainBase {

  @Benchmark
  def route1(state: State_3_10): Int = {
    doBenchmark(state, state.routes.r1)
  }

  @Benchmark
  def route2(state: State_3_10): Int = {
    doBenchmark(state, state.routes.r2)
  }

  @Benchmark
  def route3(state: State_3_10): Int = {
    doBenchmark(state, state.routes.r3)
  }

  @Benchmark
  def route4(state: State_3_10): Int = {
    doBenchmark(state, state.routes.r4)
  }

}

class Main_10_10 extends MainBase {

  @Benchmark
  def route1(state: State_10_10): Int = {
    doBenchmark(state, state.routes.r1)
  }

  @Benchmark
  def route2(state: State_10_10): Int = {
    doBenchmark(state, state.routes.r2)
  }

  @Benchmark
  def route3(state: State_10_10): Int = {
    doBenchmark(state, state.routes.r3)
  }

  @Benchmark
  def route4(state: State_10_10): Int = {
    doBenchmark(state, state.routes.r4)
  }

}

