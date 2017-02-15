@login
Feature: Accounts

  Scenario: show all accounts
    Given exists the following accounts
      | name | balance |
      | CMB  | 1000    |
      | HSBC | 0       |
    When show all accounts
    Then you will see all accounts as below
      | name | balance |
      | CMB  | 1000    |
      | HSBC | 0       |

  Scenario: add account
    When add account as name CMB and balance 1000
    Then you will see all accounts as below
      | name | balance |
      | CMB  | 1000    |

  Scenario: edit account
    Given exists the following accounts
      | name | balance |
      | CMB  | 1000    |
    When edit account as name HSBC and balance 2000
    Then you will see all accounts as below
      | name | balance |
      | HSBC | 2000    |


  Scenario: delete account
    Given exists the following accounts
      | name | balance |
      | CMB  | 1000    |
    When delete account as name CMB and balance 1000
    Then there is not exists the following accounts
      | name | balance |
      | CMB  | 1000    |

