package fpinscala.state

import fpinscala.state.RNG
import org.scalatest.{BeforeAndAfter, FunSpec}
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class RNG$Test extends FunSpec with BeforeAndAfter {
  var rng: RNG.Simple = _
  before {
    rng = RNG.Simple(12345)
  }

  describe("testNonNegativeInt") {
    it("should give non-negative numbers") {
      val (res, newRng) = RNG.nonNegativeInt(rng)
      assert(0 <= res)
    }
  }
}
