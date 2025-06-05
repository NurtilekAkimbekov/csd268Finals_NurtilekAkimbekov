import unittest
from canDriveTest.main_code import can_drive



class TestCanDrive(unittest.TestCase):
    def test_age_below_driving(self):
        self.assertFalse(can_drive(15))

    def test_exact_driving_age(self):
        self.assertTrue(can_drive(16))

    def test_above_driving_age(self):
        self.assertTrue(can_drive(20))

    def test_zero_age(self):
        self.assertFalse(can_drive(0))

    def test_negative_age(self):
        self.assertFalse(can_drive(-5))

if __name__ == '__main__':
    unittest.main()
