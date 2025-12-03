from typing import Optional

MIN = 1
MAX = 100

class FizzBuzz:

    def __init__(self, specials):
        self.specials = dict(sorted(specials.items()))

    def convert(self, number_to_convert: int) -> Optional[str]:
        if FizzBuzz.is_out_of_range(number_to_convert):
            return None
        else:
            return self.convert_safely(number_to_convert)

    def convert_safely(self, number_to_convert: int) -> str:
        conversion = ""
        for divisor, replacement in self.specials.items():
            if FizzBuzz.is_divisible_by(divisor, number_to_convert):
                conversion = conversion + replacement
        if conversion == "" :
            conversion=str(number_to_convert)
        return conversion

    @staticmethod
    def is_divisible_by(divisor: int, input: int) -> bool:
        return input % divisor == 0


    @staticmethod
    def is_out_of_range(input: int) -> bool:
        return input < MIN or input > MAX
