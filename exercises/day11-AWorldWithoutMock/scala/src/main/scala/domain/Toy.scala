package domain

object ToyState extends Enumeration {
  type ToyState = Value
  val UNASSIGNED, IN_PRODUCTION = Value
}

import ToyState._

case class Toy(name: String, var state: ToyState)
