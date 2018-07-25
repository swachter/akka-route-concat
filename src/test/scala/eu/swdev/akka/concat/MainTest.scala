package eu.swdev.akka.concat

import org.scalatest.FunSuite

class MainTest extends FunSuite {

  test("3x3") {
    new Main_3_3().route1(new State_3_3)
  }

}
