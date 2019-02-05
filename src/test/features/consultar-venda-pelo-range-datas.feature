Feature: Consultar venda pelo range de datas ordenando de forma decrescente pela data da venda

  Background:
    Given que as vendas abaixo estejam cadastradas:
      |  id  | nomeCliente | cashbackTotal | data       |
      |  202 | Cliente 1   | 100           | 10/02/2018 |
      |  203 | Cliente 2   | 30            | 10/03/2018 |
      |  204 | Cliente 3   | 30            | 25/05/2018 |
      |  205 | Cliente 4   | 30            | 17/11/2018 |

  Scenario: Consultar venda pelo range de datas
    When consultar venda entre "09/01/2018" e "16/11/2018"
    Then deveria retornar as vendas abaixo:
      |  id  | nomeCliente | cashbackTotal | data       |
      |  204 | Cliente 3   | 30            | 25/05/2018 |
      |  203 | Cliente 2   | 30            | 10/03/2018 |
      |  202 | Cliente 1   | 100           | 10/02/2018 |
