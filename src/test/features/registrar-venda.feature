Feature: Registrar venda

  Background:
    Given a tabela de porcetagem de cashback abaixo:
      | genero     | diaSemana  | porcetagem |
      |  MPB       | DOMINGO    |  30        |
      |  POP       | DOMINGO    |  25        |
      |  ROCK      | DOMINGO    |  40        |
      |  CLASSICAL | SEGUNDA    |  20        |

  Scenario: Registrar venda com vários discos com gêneros diferentes no dia da semana DOMINGO
    Given que a data atual seja "03/02/2019"
    When registrar a venda com nome cliente "Maria Eduarda" e com os discos selecionados:
      | genero | nomeAlbum  | preco |
      |  MPB   | MPB1       |  10   |
      |  POP   | POP1       |  20   |
      |  ROCK  | ROCK1      |  30   |
    Then deveria registrar venda com nome cliente "Maria Eduarda", data "03/02/2019" e com valor total de cashback 20
    And o valor de retorno de cada disco deveria:
      | MPB1      |    3     |
      | POP1      |    5     |
      | ROCK1     |   12     |

  Scenario: Registrar venda com um disco no dia da semana SEGUNDA
    Given que a data atual seja "18/03/2019"
    When registrar a venda com nome cliente "João da Silva" e com os discos selecionados:
      | genero     | nomeAlbum  | preco |
      | CLASSICAL  | CLASSICAL1 |  200  |
    Then deveria registrar venda com nome cliente "João da Silva", data "18/03/2019" e com valor total de cashback 40
    And o valor de retorno de cada disco deveria:
      | CLASSICAL1 | 40 |