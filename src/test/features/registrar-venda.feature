Feature: Registrar venda

  Background:
    Given a tabela de porcetagem de cashback abaixo:
      | genero | diaSemana  | porcetagem |
      |  MPB   | DOMINGO    |  30        |
      |  POP   | DOMINGO    |  25        |
      |  ROCK  | DOMINGO    |  40        |

  Scenario: Registrar venda com vários discos com gêneros diferentes no Domingo
    Given que a data atual seja "03/02/2019"
    When registrar a venda com nome cliente "Maria Eduarda" e com os discos selecionados:
      | genero | nomeAlbum  | preco |
      |  MPB   | MPB1       |  10   |
      |  POP   | POP1       |  20   |
      |  ROCK  | ROCK1      |  30   |
    Then deveria registrar venda com valor total de retorno 20
    And o valor de retorno de cada disco deveria:
      | MPB1      |    3     |
      | POP1      |    5     |
      | ROCK1     |   12     |