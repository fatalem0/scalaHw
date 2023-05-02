package module1

import scala.util.Random

class BallsExperiment {
  private val balls = List(0, 0, 0, 1, 1, 1)

  def isFirstBlackSecondWhite: Boolean =
    Random.shuffle(balls).take(2).contains(1)
}

object BallsTest {
  def main(args: Array[String]): Unit = {
    val count = 10000
    val listOfExperiments: List[BallsExperiment] = List.fill(count)(new BallsExperiment)
    val countOfExperiments = listOfExperiments.map(_.isFirstBlackSecondWhite)
    val countOfPositiveExperiments: Float = countOfExperiments.count(_ == true)
    println(countOfPositiveExperiments / count)
  }
}
