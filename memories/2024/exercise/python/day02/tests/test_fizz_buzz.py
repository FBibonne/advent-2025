import unittest

from assertpy import assert_that

from games.fizz_buzz import FizzBuzz
from tests.ValidInput import ValidInput


class FizzBuzzTests(unittest.TestCase):
    def test_returns_its_numbers_representation(self):
        test_data = [
            ValidInput(7, "Whizz"),
            ValidInput(11, "Bang"),
            ValidInput(1, "1"),
            ValidInput(67, "67"),
            ValidInput(82, "82"),
            ValidInput(3, "Fizz"),
            ValidInput(66, "FizzBang"),
            ValidInput(99, "FizzBang"),
            ValidInput(21,"FizzWhizz"),
            ValidInput(42, "FizzWhizz"),
            ValidInput(5, "Buzz"),
            ValidInput(50, "Buzz"),
            ValidInput(85, "Buzz"),
            ValidInput(15, "FizzBuzz"),
            ValidInput(30, "FizzBuzz"),
            ValidInput(45, "FizzBuzz"),
            ValidInput(35, "BuzzWhizz"),
            ValidInput(55, "BuzzBang"),
            ValidInput(77,"WhizzBang")
        ]

        for data in test_data:
            with ((self.subTest(data=data))):
                specials = {11: "Bang", 3: "Fizz", 7: "Whizz", 5: "Buzz"}
                fizzBuzz = FizzBuzz(specials)
                assert_that(fizzBuzz.convert(data.input)
                            ).is_equal_to(data.expected_result)

    def test_fails_for_numbers_out_of_range(self):
        test_data = [0, -1, 101]
        fizzBuzz = FizzBuzz({11: "Bang"})

        for value in test_data:
            with self.subTest(value=value):
                assert_that(fizzBuzz.convert(value)).is_none()


if __name__ == "__main__":
    unittest.main()
