package collections

import module1.list.List.{incList, shoutString}
import module1.{list, opt}
import org.scalatest.flatspec.AnyFlatSpec
import module1.opt.Option
import org.scalatest.matchers.must.Matchers.the

class O3_hw extends AnyFlatSpec {
  "get some" should "get an element" in {
    val optSome: Option[Int] = Option(1)
    val myOptSome: opt.Option[Int] = opt.Option(1)
    assert(optSome.get == myOptSome.get)
  }

  "get none" should "get a None element" in {
    val optNone = the [NoSuchElementException] thrownBy None.get
    val myOptNone = the [NoSuchElementException] thrownBy opt.Option.None.get
    assert(optNone.getMessage == myOptNone.getMessage)
  }

  "zip some" should "create an Option[(T, A)] from an Option[T] and an Option[A]" in {
    val optSome1: Option[Int] = Option(1)
    val optSome2: Option[Int] = Option(2)
    val zippedSomeOpts: (Int, Int) = optSome1.zip(optSome2).get
    val myOptSome1: opt.Option[Int] = opt.Option(1)
    val myOptSome2: opt.Option[Int] = opt.Option(2)
    val myZippedSomeOpts: (Int, Int) = myOptSome1.zip(optSome2).get
    assert(zippedSomeOpts == myZippedSomeOpts)
  }

  "::" should "prepend an element" in {
    val scalaList = List(1, 2)
    val myList = list.List(1, 2)
    val elem = 3
    assert(scalaList.::(elem).mkString == myList.::(elem).mkString(""))
  }

  "mkString" should "make a string with delimiter" in {
    val scalaList = List(1, 2, 3)
    val myList = list.List(1, 2, 3)
    assert(scalaList.mkString("*") == myList.mkString("*"))
  }

  "reverse" should "reverse a list" in {
    val scalaList = List(1, 2, 3)
    val myList = list.List(1, 2, 3)
    assert(scalaList.reverse.mkString("") == myList.reverse().mkString(""))
  }

  "map" should "map elements to a given function" in {
    val scalaList = List(1, 2, 3)
    val myList = list.List(1, 2, 3)
    assert(scalaList.map(_ * 2).mkString("") == myList.map(_ * 2).mkString(""))
  }

  "++" should "concatenate two lists" in {
    val scalaList = List(1, 2) ++ List(3, 4)
    val myList = list.List(1, 2) ++ list.List(3, 4)

    assert(scalaList.mkString("") == myList.mkString(""))
  }

  "flatMap" should "flatten a list" in {
    val scalaList = List(List(1, 2), List(3, 4))
    val myList = list.List(list.List(1, 2), list.List(3, 4))
    assert(scalaList.flatMap(x => x.map(_ * 2)).mkString("") ==
      myList.flatMap(x => x.map(_ * 2)).mkString(""))
  }

  "filter" should "filter elements on some predicate" in {
    val scalaList = List(1, 2, 3)
    val myList = list.List(1, 2, 3)
    assert(scalaList.filter(_ % 2 == 0).mkString("") == myList.filter(_ % 2 == 0).mkString(""))
  }

  "incList" should "increment each element" in {
    val myList = list.List(1, 2, 3)
    assert(incList(myList) == list.List(2, 3, 4))
  }

  "shoutString" should "append '!' to each element" in {
    val myList = list.List("first", "second", "third")
    assert(shoutString(myList) == list.List("first!", "second!", "third!"))
  }

}
