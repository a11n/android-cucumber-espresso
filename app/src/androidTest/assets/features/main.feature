Feature: Main

  Scenario: User notification
    Given I've launched "com.android.example.MainActivity"
    When I click fab
    Then I should see "Replace with your own action"