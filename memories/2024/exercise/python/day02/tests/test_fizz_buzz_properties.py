import unittest

from hypothesis import given, strategies as st

from games.fizz_buzz import FizzBuzz, MIN, MAX


class FizzBuzzProperties(unittest.TestCase):
    fizz_buzz_strings = ["Fizz", "Buzz", "Whizz", "Bang", "FizzBuzz", "WhizzBang", "BuzzWhizz", "BuzzBang", "FizzBang", "FizzWhizz"]

    def valid_strings_for(self, x: int) -> list:
        return self.fizz_buzz_strings + [str(x)]

    @given(st.integers(min_value=MIN, max_value=MAX))
    def test_parse_returns_valid_string_for_numbers_between_1_and_100(self, x):
        fizzBuzz = FizzBuzz({11: "Bang", 3: "Fizz", 7: "Whizz", 5: "Buzz"})
        self.assertIn(
            fizzBuzz.convert(x),
            self.valid_strings_for(x)
        )

    @given(st.integers().filter(lambda i: i < MIN or i > MAX))
    def test_parse_fails_for_numbers_out_of_range(self, x):
        fizzBuzz = FizzBuzz({11: "Bang", 3: "Fizz", 7: "Whizz", 5: "Buzz"})
        self.assertIsNone(
            fizzBuzz.convert(x)
        )


if __name__ == "__main__":
    unittest.main()
