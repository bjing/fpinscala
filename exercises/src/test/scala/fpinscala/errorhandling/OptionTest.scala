package fpinscala.errorhandling

import scala.{Option => _, Some => _, Either => _, _} // hide std library `Option`, `Some` and `Either`, since we are writing our own in this chapter

import org.scalatest.FunSpec
import fpinscala.errorhandling.Option
import fpinscala.errorhandling.Some
import fpinscala.errorhandling.None

class OptionTest extends FunSpec {
  describe("Option trait") {
    it("should map") {
      val a = Some(5)
      val result = a.map(x => x + 2)

      assert(result === Some(7))
    }

    it("should flatMap") {
      val a = Some(5)
      val result = a.flatMap(x => Some(x + 2))

      assert(result === Some(7))
    }

    it("should get actual value if it's defined") {
      val a = Some(5)
      val result = a.getOrElse(6)

      assert(result === 5)
    }

    it("should get default value if it's not defiend") {
      val a = None
      val result = a.getOrElse(6)

      assert(result === 6)
    }

    it("should return actual Option if it's defined") {
      val a = Some(5)
      val result = a.orElse(Some(6))

      assert(result === Some(5))
    }

    it("should return default Option if it's not defined") {
      val a = None
      val result = a.orElse(Some(6))

      assert(result === Some(6))
    }

    it("should return actual Option if it satisfies predicate") {
      val a = Some(5)
      val result = a.filter(_ == 5)

      assert(result === a)
    }

    it("should return None if it doesn't predicate") {
      val a = Some(5)
      val result = a.filter(_ == 6)

      assert(result === None)
    }
  }

  describe("variance") {
    it("should calculate variance") {
      val l = Seq(5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0)
      val result = Option.variance(l)

      assert(result === Some(4.0))
    }
  }

  describe("map2") {
    it("should combine two Options via map2") {
      val a = Some(5)
      val b = Some(6)
      val result = Option.map2(a, b)((x, y) => x + y)

      assert(result === Some(11))
    }

    it("should return None if either of the Options is None") {
      val a = Some(5)
      val b = None
      val result = Option.map2[Int, Int, Int](a, b)((x, y) => x + y)

      assert(result === None)
    }
  }

  describe("sequence") {
    it("should sequence a list of non None values") {
      val l = List(Some(3), Some(4), Some(5))
      val result = Option.sequence(l)

      assert(result === Some(List(3,4,5)))

    }

    it("should return None if the list contains None") {
      val l = List(Some(5), None, Some(4))
      val result = Option.sequence(l)

      assert(result === None)
    }

  }

  describe("traverse") {
    it("should traverse a list of Options") {
      val l = List(3, 4, 5)
      val result = Option.traverse(l)(a => Some(a))

      assert(result === Some(List(3, 4, 5)))
    }
  }
}
