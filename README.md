# Projeto "O Herói das Eras"

# Descrição do Projeto

300.000 anos antes da era comum, nasce um guerreiro.

Este rapaz está destinado, pelas forças da natureza, a lutar contra o mal ao
longo das centenas de milhares de anos da existência da humanidade.

Abençoado pelas moiras, três mulheres responsáveis por fabricar, tecer e cortar 
o fio da vida dos homens, somente aqueles que descendem do mal podem matá-lo.

Conseguirá o herói cumprir sua missão? Isso depende somente de você...


# Equipe

* Fábio de Andrade Barboza - RA 168817
* João Augusto Rosa Feltran - RA 174083

# Estrutura de Arquivos e Pastas

~~~
├── README.md          <- apresentação do projeto
│
├── assets             <- mídias usadas no projeto
│
├── data               <- dados usados pelo jogo (.csv)
│
└── src                <- projeto em Java 
    │
    ├── bin            <- arquivos em bytecode (.class)
    │
    ├── src            <- arquivos-fonte do projeto (.java)
    │
    └── README.md      <- instruções básicas de instalação/execução
~~~

# Vídeos do Projeto

## Vídeo da Prévia

[Vídeo da prévia](https://drive.google.com/file/d/1uszDQPCGeER__hJ6rSXI5SbTS8QNMBl6/view?usp=sharing)

## Vídeos do Jogo

[Vídeo do jogo](https://drive.google.com/file/d/1FMIGBxfel2OFO-7UKeGbSDYQVWsR2bZh/view?usp=sharing)

[Vídeo do jogo completo](https://drive.google.com/file/d/197np9WtcwXe0baI0MZ2JjPMWj9X8xRG_/view?usp=sharing)

# Slides do Projeto

## Slides da Prévia

[Slides da prévia](assets/Apresentacao/Slides-Previa.pdf)

## Slides da Apresentação Final

[Slides final](assets/Apresentacao/Slides-Final.pdf)

## Relatório de Evolução

O jogo foi planejado com base no estilo "Model-View-Control". O Jogador tem acesso a um painel ("View"), que apresenta um Mapa e seus Atores("Model") ao Jogador e permite que este se mova e interaja com outros Atores ("Control"). 
A maior dificuldade enfrentada durante a elaboração do Projeto foi conciliar múltiplas janelas. Inicialmente, uma única janela seria utlizada para os mapas de todas as fases, e, ao iniciar um combate, uma nova janela seria aberta para interação com o jogador. Não foi possível, porém, fazer o programa esperar até que essa janela fosse fechada antes de continuar a execução. Assim, optamos por uma versão um pouco mais simples, em que o jogador precisaria apenas clicar sobre o inimigo para realizar um ataque. Aleatoriamente, o programa decide se a ação foi bem sucedida ou se o inimigo se defendeu e revidou o ataque.
Outra mudança em relação ao planejamento inicial do jogo é a criação de uma classe própria para os Itens, que melhoram alguns atributos do Herói e permitem que o jogador conclua a fase atual. 
Neste projeto, tentamos ao máximo utilizar interfaces em java.

# Destaques de Código

~~~java
public class PainelPrincipal extends JFrame implements IPainelPrincipal {
    ...
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jogar1) { // Se o jogador apertar o botão "jogar" na tela inicial
            criarTela2(); // Cria a segunda tela inicial do jogo
        } else if (e.getSource() == jogar2) { // Se o jogador apertar o botão "jogar" na segunda tela inicial
            criarMapa(fase); // Cria o mapa e os botões
        } else { // Descobre o botão que o jogador clicou
            for (int i = 0; i < 7; i ++) { 
                for (int j = 0; j < 7; j ++) {
                    if (e.getSource() == botoes[i][j]) {
                        controle.jogada(i, j); // Informa a jogada ao controle
                        atualizarMapa(); // Atualiza a parte visual após a jogada
                    }
                }
            }
        }
    }
}
~~~

Destacamos este trecho de código do programa que representa a independência dos componentes Controle e PainelPrincipal, além de ilustrar a utilização da interface gráfica. 

~~~java
public class Mapa implements IMapa { 
    // Atributos
    private IAtor mapa[][]; // Matriz de objetos que implementam a interface IAtor
    ...

    // Construtor
    public Mapa() {
        mapa = new IAtor[7][7];
        ...
    }
    ...
}
~~~

Destacamos este trecho do código do programa que representa o uso do polimorfismo e de interfaces.

# Destaques de Pattern

## Diagrama do Pattern
![Diagrama Pattern](assets/Apresentacao/Diagrama-Pattern.png)

## Código do Pattern
~~~java
// Destaque: Componente "Mapa"

public class Mapa implements IMapa {
    // Atributos
    private IAtor mapa[][];
    private MapaVisual visual;

    // Construtor
    public Mapa() {
        mapa = new IAtor[7][7];
        visual = new MapaVisual();
    }
    ...
}

// Destaque: Componente "MapaVisual"

public class MapaVisual {
    // Atributos
    private AtorVisual icones[][];

    // Construtor
    public MapaVisual() {
        icones = new AtorVisual[7][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                icones[i][j] = new AtorVisual();
            }
        }
    }
    ...
}

// Destaque: Componente "Controle"

public interface IControle {
    // Setters
    public void setHeroi(IHeroi heroi);
    public void setFase(int fase);
    public void connectPainel(JFrame painel);

    // Getters
    public String getVisualNaPosicao(int x, int y);

    // Responde ao evento do jogador de clicar em um botão do mapa
    public void jogada(int x, int y);
}
~~~

O jogo foi planejado seguindo o pattern Model-View-Cotrol. 
* O componente "Mapa" exerce a função de "Model", ou seja, armazena internamente o estado atual do mapa através de uma matriz de IAtores.
* O componente "MapaVisual", juntamente com "AtorVisual" exerce a função de "View", ou seja, armazena, atualiza e apresenta ao jogador as imagens na tela.
* O componente "Controle" exerce a função de "Control", ou seja, responde aos eventos de interação do jogador com a interface gráfica.

Vantagens deste pattern incluem a possibilidade de reaproveitar o código modificando apenas um dos componentes, facilitação da manutenção e debug e facilitação de trabalho em equipe para programar.

# Conclusões e Trabalhos Futuros

Este jogo possui um potencial muito grande para futuras melhorias. 
Gostaríamos de implementar a interface gráfica de batalha planejada inicialmente, além de adicionar uma barra de vida do Herói e do inimigo em questão.
Outra possibilidade de expansão seria a criação de fases aleatórias, em vez de lidas de um arquivo CSV.
Melhorias de design possíveis incluem a utilização de Factories para criação dos componentes do jogo.

# Documentação dos Componentes

# Diagramas

## Diagrama Geral do Projeto

![Diagrama Geral do Projeto](assets/Apresentacao/Diagrama-Geral-Final.png)

## Componente Atores

Representa as diferentes entidades que interagem durante o jogo: o Herói, Inimigos e Chefes.

![Componente Atores](assets/Apresentacao/Componente-Atores.png)

**Ficha Técnica**
Item | Detalhamento
----- | -----
Classe | src.Ator
Autores | João Augusto Rosa Feltran
Interfaces | IAcao, IAtor, ICombate, IHeroi

### Interfaces

![Diagrama Interfaces](assets/Apresentacao/Interfaces-Atores.png)

### Interface IAcao

Interação do Herói com o Mapa.

~~~java
package src.Ator;

public interface IAcao {
    // Getters
    public IAtor getInimigoNaPosicao(int x, int y);

    // Movimento
    public void removerInimigo(int x, int y);
    public char verificarMovimento(int x, int y);
    public void mover(int x, int y, int fase);
}
~~~

Método | Objetivo
-------| --------
void removerInimigo(int x, int y) | Remove o Inimigo do Mapa na posição (x, y) e transforma o local em um espaço vazio
IAtor getInimigoNaPosicao(int x, int y) | Retorna o tipo de Ator presente na posição (x, y)
char verificarMovimento(int x, int y) | Verifica se o movimento até a posição (x, y) é válido
void mover(int x, int y int fase) | Move o Ator para a posição (x, y) e atualiza as imagens ao seu redor de acordo com a fase em que o jogador está

### Interface IAtor

Atualiza e retorna propriedades comuns a objetos da classe Ator e suas herdeiras.

~~~java
package src.Ator;

public interface IAtor {
    // Setters
    public void setX(int x);
    public void setY(int y);
    public void setTipo(char tipo);

    // Getters
    public int getX();
    public int getY();
    public char getTipo();
}
~~~

Método | Objetivo
-------| --------
void setX(int x) | Atualiza a posição horizontal do Ator
void setY(int y) | Atualiza a posição vertical do Ator
void setTipo(char tipo) | Atualiza o tipo do Ator
int getX() | Retorna a posição horizontal do Ator
int getY() | Retorna a posição vertical do Ator
char getTipo() | Retorna o tipo do Ator

### Interface ICombate

Gerencia as interações de combate entre Atores.

~~~java
package src.Ator;

public interface ICombate {
    // Getters
    public boolean getVivo();

    public int causarDano();
    public void receberDano(int dano);
}
~~~

Método | Objetivo
-------| --------
boolean getVivo() | Verifica se a Vida do Ator é maior que zero
int causarDano() | Calcula o dano causado pelo Ator ao atacar
void receberDano(int dano) | Diminui a vida do Ator com base no dano recebido e seus atributos

### Interface IHeroi

Agrega as interfaces implementadas pelo Herói e gerencia o inventário do mesmo.

~~~java
package src.Ator;

public interface IHeroi extends IAtor, ICombate, IAcao {
    // Setters
    public void setItemInventario(int posicao, int valor, String nome);
    public void setVisualNaPosicao(int x, int y, char tipo, int fase);

    // Getters
    public String getVisualNaPosicao(int x, int y);
    public int getValorItemInventario(int posicao);

    // Outras funcoes
    public void definirItensIniciais(int fase);
    public boolean pegouChave(int fase);
    public void curar(int porcentagem);
}
~~~

Método | Objetivo
-------| --------
void setItemInventario(int posicao, int valor, String nome) | Cria e adiciona um novo item no inventário, de acordo com os parâmetros recebidos
void setVisualNaPosicao(int x, int y, char tipo, int fase) | Cria e adiciona um novo AtorVisual na posição (x, y) do View (MapaVisual), de acordo com os parâmetros recebidos
String getVisualNaPosicao(int x, int y) | Retorna o endereço da imagem na posição (x, y)
int getValorItemInventario(int posicao) | Retorna o valor do modificador item de acordo com sua posição no vetor de itens do Herói
void definirItensIniciais(int fase) | Define os stats iniciais do Herói no início da fase indicada
boolean pegouChave(int fase)| Verifica se o Herói já pegou a chave de saída da fase
void curar(int porcentagem) | Restaura uma porcentagem da vida do Herói

## Componente Controle

Controla o estado do jogo.

![Componente Controle](assets/Apresentacao/Componente-Controle.png)

**Ficha Técnica**
Item | Detalhamento
----- | -----
Classe | src.Controle
Autores | Fábio de Andrade Barboza
Interfaces | IControle

### Interfaces

![Diagrama Interfaces](assets/Apresentacao/Interfaces-Controle.png)

### Interface IControle

Atualiza e retorna propriedades do Controle e controla o estado do jogo.

~~~java
package src.Controle;

import javax.swing.JFrame;

import src.Ator.IHeroi;

public interface IControle {
    // Setters
    public void setHeroi(IHeroi heroi);
    public void setFase(int fase);
    public void connectPainel(JFrame painel);

    // Getters
    public String getVisualNaPosicao(int x, int y);

    public void jogada(int x, int y);
}
~~~

Método | Objetivo
-------| --------
void setHeroi(IHeroi heroi) | Atualiza o Herói controlado
void setFase(int fase) | Atualiza a fase atual do jogo
void connectPainel(JFrame painel) | Conecta o Painel ao Controle
String getVisualNaPosicao(int x, int y) | Retorna o endereço da imagem do ator na posição (x, y)
void jogada(int x, int y) | Responde ao evento do jogador de clicar em um botão do Painel

## Componente Mapa

Armazena a posição dos componentes Atores e seus visuais.

![Componente Mapa](assets/Apresentacao/Componente-Mapa.png)

**Ficha Técnica**
Item | Detalhamento
----- | -----
Classe | src.Mapa
Autores | Fábio de Andrade Barboza, João Augusto Rosa Feltran
Interfaces | IMapa, IMapaPropriedades, IMapaVisual

### Interfaces

![Diagrama Interfaces](assets/Apresentacao/Interfaces-Mapa.png)

### Interface IMapa

Interface agregadora do componente em Java.

~~~java
package src.Mapa;

public interface IMapa extends IMapaPropriedades, IMapaVisual {

}
~~~

### Interface IMapaPropriedades

Atualiza e retorna propriedades do Mapa.

~~~java
package src.Mapa;

import src.Ator.IAtor;

public interface IMapaPropriedades {
    // Setters
    public void setAtorNaPosicao(IAtor ator, int x, int y);

    // Getters
    public IAtor getAtorNaPosicao(int x, int y);
    public MapaVisual getVisual();
}
~~~

Método | Objetivo
-------| --------
void setAtorNaPosicao(IAtor ator, int x, int y) | Insere o Ator recebido na posição (x, y)
IAtor getAtorNaPosicao(int x, int y) | Retorna o Ator presente na posição (x, y)
MapaVisual getVisual() | Retorna um ponteiro para o MapaVisual

### Interface IMapaVisual

Medeia interações com os componentes visuais.

~~~java
package src.Mapa;

public interface IMapaVisual {
    // Getters
    public String getVisualNaPosicao(int x, int y);

    public void ajustarVisibilidade(int visao, int xHeroi, int yHeroi, int fase);
}
~~~

Método | Objetivo
-------| --------
String getVisualNaPosicao(int x, int y) | Retorna o endereço da imagem do Ator na posição (x, y)
void ajustarVisibilidade(int visao, int xHeroi, int yHeroi, int fase) | Revela áreas escondidas do Mapa com base na visão do Herói

## Componente Montador

Cria os componentes Atores e Mapa.

![Componente Montador](assets/Apresentacao/Componente-Montador.png)

**Ficha Técnica**
Item | Detalhamento
----- | -----
Classe | src.Montador
Autores | João Augusto Rosa Feltran
Interfaces | IMontador, IMontaAtor, IMontadorPropriedades, IMontaMapa

### Interfaces

![Diagrama Interfaces](assets/Apresentacao/Interfaces-Montador.png)

### Interface IMontador

Interface agregadora do componente em Java.

~~~java
package src.Montador;

public interface IMontador extends IMontaAtor, IMontaMapa, IMontadorPropriedades {

}
~~~

### Interface IMontaAtor

Cria componentes da classe Ator e suas herdeiras.

~~~java
package src.Montador;

public interface IMontaAtor {
    public int[] modificarHabilidades(int vida, int ataque, int defesa);
    public void gerarInimigo(int x, int y);
    public void gerarChefe(int x, int y);
}
~~~

Método | Objetivo
-------| --------
int[] modificarHabilidades(int vida, int ataque, int defesa) | Modifica aleatoriamente os valores de ataque, defesa e vida do inimigo, retornando um vetor com os novos valores
void gerarInimigo(int x, int y) | Cria um inimigo e o posiciona em (x, y)
void gerarChefe(int x, int y) | Cria um chefe e o posiciona em (x, y)

### Interface IMontadorPropriedades

Atualiza e retorna propriedades do Montador.

~~~java
package src.Montador;

import src.Ator.IHeroi;
import src.Mapa.IMapa;

public interface IMontadorPropriedades {
    // Setters
    public void setFase(int fase);

    // Getters
    public int getFase();

    public IHeroi getHeroi();

    public IMapa getMapa();
}
~~~

Método | Objetivo
-------| --------
void setFase(int fase) | Atualiza a fase atual do jogo
int getFase() | Retorna a fase atual do jogo
IHeroi getHeroi() | Retorna o Herói criado
IMapa getMapa() | Retorna o Mapa criado

### Interface IMontaMapa

~~~java
package src.Montador;

public interface IMontaMapa {
    public void criarFase();
    public void definirTerritorioChefe(int x, int y);
}
~~~

Método | Objetivo
-------| --------
void criarFase() | Cria a fase, posicionando os atores em seus devidos locais e inicializando-os
void definirTerritorioChefe(int x, int y) | Modifica as células ao redor do chefe (em um raio de 1 célula) para serem do tipo 't'

## Componente Painel

Interação com o jogador e apresentação do jogo na tela. 

![Componente Painel](assets/Apresentacao/Componente-Painel.png)

**Ficha Técnica**
Item | Detalhamento
----- | -----
Classe | src.Painel
Autores | Fábio de Andrade Barboza, João Augusto Rosa Feltran
Interfaces | IPainelPrincipal

### Interfaces

![Diagrama Interfaces](assets/Apresentacao/Interfaces-Painel.png)

### Interface IPainelPrincipal

Herdeira de ActionListener.

~~~java
package src.Painel;

import java.awt.event.*;

public interface IPainelPrincipal extends ActionListener {
}
~~~

Método | Objetivo
-------| --------
void actionPerformed(ActionEvent e) | Lida com o evento gerado pelo jogador

## Componente Visual

Apresenta os Atores e o Mapa na tela para o jogador.

![Componente Visual](assets/Apresentacao/Componente-Visual.png)

**Ficha Técnica**
Item | Detalhamento
----- | -----
Classe | src.Ator
Autores | João Augusto Rosa Feltran

### Classes 

![Diagrama Classes](assets/Apresentacao/Classes-Visual.png)

# Plano de Exceções

## Diagrama da hierarquia de exceções

![Hierarquia de Exceções](assets/Apresentacao/Diagrama-Excecoes.png)

## Descrição das classes de exceção

Classe | Descrição
----- | -----
IOException | Verifica se o arquivo foi aberto corretamente.
IllegalStateException | Verifica se foi solicitada uma fase incorreta.
