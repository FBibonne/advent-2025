package domain

trait ToyRepository {
  def findByName(name: String): Toy
  def save(toy: Toy): Unit
}
