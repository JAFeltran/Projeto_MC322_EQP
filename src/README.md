# Instalação

* Execute a Aplicação AppHeroiDasEras

# Como jogar?

* Este jogo utiliza uma interface gráfica.
* Para se mover, basta clicar em um quadrado adjacente ao Herói.
* Ao encontrar um inimigo, você pode clicar sobre ele para atacá-lo. Cuidado! Ele pode revidar!
* Inimigos derrotados podem derrubar itens que melhoram seu ataque, sua defesa ou sua visão.
* Cada fase possui um chefe, um inimigo mais difícil de ser derrotado, mas que fornece a chave para passar para a próxima fase.
* Vença todas as fases para vencer o jogo.
* Você pode sair do jogo a qualquer momento fechando a janela.

# Criar novas fases

* Para modificar as fases, edite os arquivos CSV correspondentes na pasta data.
* Todas as fases devem conter apenas um chefe, um herói e uma saída.
* Você pode incluir quantos inimigos e obstáculos quiser!
* Tome cuidado para sempre manter o formato 7X7 do arquivo.
* Sem modificar o código, o jogo possui até seis fases.
* Nos arquivos CSV, utilize o seguinte código para inserir Atores no Mapa:

Letra | Ator
----- | -----
_ | Espaço Vazio
c | Chefe
i | Inimigo
h | Herói
o | Obstáculo
s | Saída
