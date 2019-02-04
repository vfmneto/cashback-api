Feature: Inicializar o catálogo de discos com dados Spotify

  Scenario: Inicializar com 50 primeiros discos de cada gênero
    Given que a aplicação esteja inicializada
    Then deveria conter as quantidades discos cadastrados por gênero:
        |   POP       | 50 |
        |   MPB       | 50 |
        |   CLASSICAL | 50 |
        |   ROCK      | 50 |