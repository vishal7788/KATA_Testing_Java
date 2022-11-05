Feature: Book a room

  @book
  Scenario: Option to book a room is available on the page
    Given I am on the home page
    When I browse through the page
    Then I have the option to book a room

  @book
  Scenario Outline: book a room
    Given I am on the home page
    When I browse through the page
    And I fill in the booking details
    And select the checkin <checkin> and checkout <checkout> date
    And click on the book button
    Then I should see success message for <checkin> <checkout> booking

    Examples:
    |checkin     |checkout  |
    |"2022-11-01"|"2022-11-03"|