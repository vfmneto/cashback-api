Feature: Consultar catálogo de disco filtrando pelo gênero e ordenando de forma crescente pelo nome do album

  Scenario: Consultar pelo gênero MPB na página 0 com 3 registros
    When consultar catálogo de discos pelo gênero "MPB", pagina 0 e quantidade por página 3 ordenado pelo nome de forma crescente
    Then deveria retornar os discos abaixo:
        | genero | nomeAlbum                                     |
        | MPB    | 20 Grandes Sucessos De Quarteto Em Cy & Mpb-4 |
        | MPB    | 40 anos de música (ao vivo)                   |
        | MPB    | Acústico Mpb                                  |

    # Simulando um cenário para ser tolerante a falha
  Scenario: Consultar pelo gênero que não existe, o sistema deveria se comportar para ser tolerante a falha
    When consultar catálogo de discos pelo gênero "NAO_EXISTE", pagina 0 e quantidade por página 3 ordenado pelo nome de forma crescente
    Then deveria retornar os discos abaixo:
        | genero | nomeAlbum |
