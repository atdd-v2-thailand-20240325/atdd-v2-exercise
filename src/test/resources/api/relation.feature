# language: en
@api-login
Feature: Relational data

  Scenario: Relational data
    Given Exists data "School":
      | name |
      | PKU  |
      | ZJU  |
    Given Exists data "Teacher":
      | name  |
      | tom   |
      | jerry |
    Given Exists data "Clazz":
      | name   | school.name |
      | classA | PKU         |
      | classB | PKU         |
      | class1 | ZJU         |
      | class2 | ZJU         |
    Given Exists data "Student":
      | name     | clazz.name |
      | Zhangsan | classA     |
      | Lisi     | classA     |
      | Wangwu   | classB     |
      | Zhaoliu  | classB     |
    Then API "/score" should be:
    """
    body.unzip: {
      PKU.xlsx: {
        Class: | A       | B   |
            1  | School: | PKU |

        classA: | A        |
            1   | Name     |
            2   | Zhangsan |
      }
    }
    """

  Scenario: Total score
    Given Exists data "Student":
      | name     | chinese | english | math | bonus |
      | Zhangsan | 90      | 80      | 70   | 8     |
    Then API "/score" should be:
    """
    totalScore: | A        | F          |
            1   | Name     | Total      |
            2   | Zhangsan | 90+80+70+8 |
    """
