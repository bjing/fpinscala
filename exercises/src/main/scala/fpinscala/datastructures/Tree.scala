package fpinscala.datastructures

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  def size[A](tree: Tree[A]): Int = {
    def loop(subtree: Tree[A], acc: Int): Int = subtree match {
      case Leaf(_) => acc + 1
      case Branch(left, right) => loop(left, acc) + loop(right, acc)
    }
    loop(tree, 0)
  }

  // def fold[A, B](tree: Tree[A], acc: B, op: (A, B) => B): B = tree match {
  //   case Leaf(value) => op(value, acc)
  //   case Branch(left, right) => op(fold(left, acc, op), fold(right, acc, op))
  // }
}
