Feature: Consultar disco pelo identificador

  Scenario: Consultar disco existente
    When consultar disco pelo identificador 58
    Then deveria retornar o disco com nome "40 anos de música (ao vivo)" e gênero "MPB"