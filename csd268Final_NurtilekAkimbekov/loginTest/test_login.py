from selenium import webdriver
from selenium.webdriver.common.by import By
import time

# Run a login test with given credentials and expected outcome
def test_login(username, password, should_pass):
    driver = webdriver.Chrome()
    driver.get("https://letsusedata.com")
    time.sleep(2)

    # Fill in login form
    driver.find_element(By.ID, "txtUser").send_keys(username)
    driver.find_element(By.ID, "txtPassword").send_keys(password)
    driver.find_element(By.ID, "javascriptLogin").click()
    time.sleep(2)

    # Check result
    print("Current URL after login:", driver.current_url)

    if should_pass:
        # Expect redirect to CourseSelection on success
        if "CourseSelection" in driver.current_url:
            print("Test Passed: Successful login")
        else:
            print("Test Failed: Did not reach expected page")
    else:
        # Expect error message on failure
        try:
            error_msg = driver.find_element(By.ID, "lblMessage")
            if error_msg.is_displayed():
                print("Test Passed: Error shown on failed login")
            else:
                print("Test Failed: Error not visible")
        except:
            print("Test Failed: Error element not found")

    driver.quit()

# Test cases
test_login("test1", "Test12456", True)
test_login("test1", "test1234", False)
