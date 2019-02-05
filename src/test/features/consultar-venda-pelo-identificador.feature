Feature: Consultar venda pelo identificador

  Background:
    Given que as vendas abaixo estejam cadastradas:
      |  id  | nomeCliente   | cashbackTotal |
      |  202 | João da Silva | 100           |
      |  203 | Maria Eduarda | 30            |

  Scenario: Consultar venda existente
    When consultar venda pelo identificador 203
    Then deveria retornar a venda com nome cliente "Maria Eduarda" e cashback total 30

  Scenario: Consultar venda não existente
    When consultar venda pelo identificador 500
    Then deveria retornar venda não encontrada