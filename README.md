# trabalho avaliativo de estrutura de dados 1 do ª2 periodo --Turma ALLEN--
Professor:
Dimmy Magalhões
-----------------------------------
Alunos integrantes:
Gabriel Kauã Borges da Silva
matrícula:
Isael Canuto de Carvalho Neto 
matrícula: 0030566
-----------------------------------
Descrição do projeto

Nome: Escalonador de Processos (fila por prioridade com bloqueio de inanição)

caracterização:
mantém três filas de prontos (prioridade 1=Alta, 2=Média, 3=Baixa) e uma fila de bloqueados;
escolhe o próximo processo para executar um ciclo por vez;

bloqueia processos que precisam de DISCO na primeira vez que são escalonados;

desbloqueia (no início de cada ciclo) um processo da fila de bloqueados, devolvendo-o para a fila da sua prioridade;

aplica uma anti‑inanição simples: se processos de Alta prioridade executarem 5 vezes seguidas, cede uma execução para Média/Baixa;

imprime no console o que está acontecendo: desbloqueios, bloqueios, execuções, finalizações e o estado das filas;


Componentes:

1. Main.java

Lê um arquivo .txt/.csv passado na linha de comando.

Espera linhas no formato CSV:
id,nome,prioridade,ciclos[,recurso]
(há suporte a cabeçalho começando com id, que é ignorado).

Para cada linha válida, cria um Processo e o adiciona ao Scheduler.

Ao final, chama scheduler.executarTudo() para rodar a simulação.



2. Processo.java

Modelo de processo com campos: id, nome, prioridade, ciclos, recurso e jaBloqueou.

precisaDisco() retorna true se recurso for "DISCO".

resumo() gera uma string curta para logs, ex.: P1[EditorTexto|pr=1|cic=4|DISCO].



3. ListaDuplamenteEncadeada.java

Implementa uma lista duplamente encadeada mínima usada como fila:

addFim(Processo p)

removeInicio()

olhaInicio()

toString() para visualização


É a base das filas de alta, média, baixa e bloqueados.



4. Scheduler.java

Mantém as quatro filas (alta, média, baixa, bloqueados).

Métodos principais:

adicionar(Processo p): adiciona o processo na fila de sua prioridade.

executarTudo(): roda ciclos até todas as filas ficarem vazias.

executarCiclo(): unidade de simulação (desbloqueia um, escolhe próximo, executa, reencaminha/finaliza).


Regras:

Bloqueio de DISCO na primeira vez que o processo é escalonado.

Desbloqueio de um processo por ciclo, antes da escolha do próximo.

Anti‑inanição: após 5 execuções seguidas da fila Alta, tenta escolher alguém de Média/Baixa.





Formato de entrada (arquivo de processos)

CSV com vírgula (com ou sem cabeçalho id,...):


id,nome,prioridade,ciclos,recurso
1,EditorTexto,1,4,DISCO
2,Navegador,1,2,
3,Musica,2,5,
4,Backup,3,5,DISCO

Campos:

id (inteiro > 0)

nome (texto não vazio)

prioridade (1, 2 ou 3)

ciclos (inteiro >= 1)

recurso (opcional; use DISCO para simular bloqueio na primeira vez)


Linhas inválidas são avisadas em System.err e ignoradas (o programa não quebra).


---

Como compilar e executar:

No terminal/cmd,java -version e javac -version devem responder.



1) Organize os arquivos

Coloque todos os .java na mesma pasta:

Main.java
Processo.java
ListaDuplamenteEncadeada.java
Scheduler.java
processos.txt   (arquivo de entrada)

2) Compilar

Abra o terminal (Windows CMD/PowerShell, Linux shell), navegue até a pasta com os arquivos e rode:

javac *.java

Isso vai gerar os .class.

3) Executar

Ainda na mesma pasta:

java Main processos.txt

(Substitua processos.txt pelo caminho do seu arquivo de entrada, se estiver em outra pasta.)


---
