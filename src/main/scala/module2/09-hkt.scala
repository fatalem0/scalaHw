package module2

object higher_kinded_types {

  def tuple[A, B](a: List[A], b: List[B]): List[(A, B)] =
    a.flatMap{ a => b.map((a, _))}

  def tuple[A, B](a: Option[A], b: Option[B]): Option[(A, B)] =
    a.flatMap{ a => b.map((a, _))}

  def tuple[E, A, B](a: Either[E, A], b: Either[E, B]): Either[E, (A, B)] =
    a.flatMap{ a => b.map((a, _))}

  implicit class BindableSyntax[F[_] : Bindable, A](fa: F[A]) {
    def map[B](f: A => B)(implicit bindable: Bindable[F]): F[B] =
      bindable.map(fa, f)
    def flatMap[B](f: A => F[B])(implicit bindable: Bindable[F]): F[B] =
      bindable.flatMap(fa, f)
  }

  implicit object BindableList extends Bindable[List] {
    override def map[A, B](fa: List[A], f: A => B): List[B] = fa.map(f)
    override def flatMap[A, B](fa: List[A], f: A => List[B]): List[B] = fa.flatMap(f)
  }

  implicit object BindableOption extends Bindable[Option] {
    override def map[A, B](fa: Option[A], f: A => B): Option[B] = fa.map(f)
    override def flatMap[A, B](fa: Option[A], f: A => Option[B]): Option[B] = fa.flatMap(f)
  }

  def tuplef[F[_] : Bindable, A, B](fa: F[A], fb: F[B]): F[(A, B)] =
    fa.flatMap(a => fb.map((a, _)))


  trait Bindable[F[_]] {
    def map[A, B](fa: F[A], f: A => B): F[B]

    def flatMap[A, B](fa: F[A], f: A => F[B]): F[B]
  }

  val optA: Option[Int] = Some(1)
  val optB: Option[Int] = Some(2)

  val list1: List[Int] = List(1, 2, 3)
  val list2: List[Int] = List(4, 5, 6)

 // val r4 = ???

//  val r1 = println(tuplef(optA, optB))
//  val r2 = println(tuplef(list1, list2))

}