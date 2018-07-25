package akka.http

import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.{RequestContext, RequestContextImpl}
import akka.stream.Materializer

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

object Bridge {

  def requestContext(request: HttpRequest)(implicit ec: ExecutionContextExecutor, materializer: Materializer): RequestContext = new RequestContextImpl(request, null, null)
}
