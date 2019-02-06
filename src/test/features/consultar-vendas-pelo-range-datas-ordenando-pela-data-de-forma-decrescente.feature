Feature: Consultar vendas pelo range de datas ordenando de forma decrescente pela data da venda

  Background:
    Given que as vendas abaixo estejam cadastradas:
      |  id  | nomeCliente | cashbackTotal | data                |
      |  202 | Cliente 1   | 100           | 10/02/2018 10:00:00 |
      |  203 | Cliente 2   | 30            | 10/03/2018 12:10:45 |
      |  204 | Cliente 3   | 30            | 25/05/2018 14:59:59 |
      |  205 | Cliente 4   | 30            | 17/11/2018 17:44:59 |

  Scenario: Consultar vendas com data inicial e final entre as datas das vendas existentes
    When consultar venda entre as datas "11/02/2018 01:00:00" e "16/11/2018 23:59:59",  pagina 0 e quantidade por página 3
    Then deveria retornar as vendas abaixo:
      |  id  | nomeCliente | cashbackTotal | data                |
      |  204 | Cliente 3   | 30            | 25/05/2018 14:59:59 |
      |  203 | Cliente 2   | 30            | 10/03/2018 12:10:45 |

  Scenario: Consultar vendas com data inicial igual a final contendo uma venda
    When consultar venda entre as datas "17/11/2018 00:00:00" e "17/11/2018 23:59:59",  pagina 0 e quantidade por página 3
    Then deveria retornar as vendas abaixo:
      |  id  | nomeCliente | cashbackTotal | data                |
      |  205 | Cliente 4   | 30            | 17/11/2018 17:44:59 |

  Scenario: Consultar vendas com datas sem vendas no período
    When consultar venda entre as datas "10/01/2019 00:00:00" e "30/12/2019 23:59:59",  pagina 0 e quantidade por página 3
    Then deveria retornar as vendas abaixo:
      |  id  | nomeCliente | cashbackTotal | data       |
