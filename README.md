🌳 Concurrent Balanced Augmented Trees (BAT)

Implementação didática baseada no artigo Concurrent Balanced Augmented Trees (Wrench et al., 2026), demonstrando o mecanismo de patch replacement e balanceamento local em árvores aumentadas concorrentes.

📚 Sobre o Artigo

O artigo de Wrench et al. (2026) investiga o desenvolvimento de estruturas de dados para processamento paralelo, focando em árvores de busca binária balanceadas concorrentes.

O problema estudado é a dificuldade de implementar árvores aumentadas que armazenam metadados como:

tamanho de subárvore (size)
soma (sum)
rank
consultas de intervalo

em ambientes multi-threaded.

O desafio central é que atualizações nesses campos precisam ser propagadas até a raiz, causando:

alta contenção
perda de escalabilidade
dificuldade de manter atomicidade
inconsistências concorrentes
🧠 Estrutura Base

A solução apresentada no artigo utiliza:

BSTs Cromáticas (Chromatic Trees)
Técnica de Augmentation de Fatourou e Ruppert
Operações lock-free com LLX/SCX
Versionamento imutável de nós
🚀 Proposta do Artigo — BAT

A contribuição principal é a Balanced Augmented Tree (BAT), a primeira árvore balanceada aumentada totalmente lock-free.

Principais características:

Estrutura balanceada concorrente
Atualizações atômicas via SCX
Versionamento imutável
Propagação cooperativa entre threads
Separação entre balanceamento e augmentation
⚙️ Ideia Central

O BAT substitui patches locais da árvore em vez de atualizar toda a estrutura.

Antes:

        A
       /
      B
     /
    C

Depois:

        B'
       /  \
     C'    A

Essa substituição ocorre atomicamente, evitando estados intermediários inconsistentes.

🧩 Conceito de Patch

Um patch é uma pequena subárvore substituída durante o rebalanceamento.

Isso permite:

Atualização local
Concorrência segura
Operações lock-free
Melhor escalabilidade
📈 Vantagens do BAT
Estrutura totalmente lock-free
Alta escalabilidade concorrente
Linearizabilidade das operações
Suporte a dados augmentados
Excelente desempenho em range queries

Os autores relatam ganhos de até 30x em consultas de intervalo.

⚠️ Limitações

Apesar dos ganhos, o BAT apresenta:

Alta complexidade de implementação
Overhead de memória (multi-versionamento)
Maior custo de gerenciamento de versões
Dificuldade de uso em sistemas simples

Portanto, o BAT é mais adequado para:

sistemas analíticos
bancos de dados concorrentes
processamento paralelo em larga escala
🧪 Implementação Neste Repositório

Este repositório contém uma implementação didática que demonstra:

Árvore grande com altura elevada
Detecção automática de patch
Patch replacement
Rebalanceamento local
Preservação do restante da árvore

Arquivo principal:

Main.java
🖥️ Como Executar

Compilar:

javac Main.java

Executar:

java Main

O programa irá:

imprimir árvore antes
aplicar rebalanceamento
imprimir árvore depois
📄 Referência

WRENCH, Evan; SINGH, Ajay; ROH, Younghun; FATOUROU, Panagiota;
JAYANTI, Siddhartha; RUPPERT, Eric; WEI, Yuanhao.

Concurrent Balanced Augmented Trees.

Proceedings of the 31st ACM SIGPLAN Annual Symposium on Principles and Practice of Parallel Programming (PPoPP '26).
New York: Association for Computing Machinery, 2026.
p. 136–149.

DOI: https://doi.org/10.1145/3774934.3786437

👨‍💻 Autores do Projeto

Diogo Geovanni

Lóren Gabriela

Dayvson Henrique

Marcos Vinicius
