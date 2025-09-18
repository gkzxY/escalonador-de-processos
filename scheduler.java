public class Scheduler {
    private ListaDuplamenteEncadeada alta = new ListaDuplamenteEncadeada();
    private ListaDuplamenteEncadeada media = new ListaDuplamenteEncadeada();
    private ListaDuplamenteEncadeada baixa = new ListaDuplamenteEncadeada();
    private ListaDuplamenteEncadeada bloqueados = new ListaDuplamenteEncadeada();

    private int ciclo = 0;
    private int contAltaSeguidas = 0;

    public void adicionar(Processo p) {
        if (p.prioridade == 1) alta.addFim(p);
        else if (p.prioridade == 2) media.addFim(p);
        else baixa.addFim(p);
    }

    public boolean terminou() {
        return alta.vazia() && media.vazia() && baixa.vazia() && bloqueados.vazia();
    }

    public void executarTudo() {
        while (!terminou()) executarCiclo();
        System.out.println("=== FIM: todas as filas vazias e nenhum bloqueado ===");
    }

    private void cabecalho() {
        ciclo++;
        System.out.println("---------------------------------------------");
        System.out.println("=== CICLO " + ciclo + " ===");
    }

    private void imprimeFilas() {
        System.out.println("Alta  : " + alta);
        System.out.println("Média : " + media);
        System.out.println("Baixa : " + baixa);
        System.out.println("Bloq  : " + bloqueados);
    }

    private void desbloqueiaUmSeTiver() {
        Processo p = bloqueados.removeInicio();
        if (p != null) {
            if (p.prioridade == 1) alta.addFim(p);
            else if (p.prioridade == 2) media.addFim(p);
            else baixa.addFim(p);
            System.out.println("DESBLOQUEIO: " + p.resumo() + " voltou para pr=" + p.prioridade);
        }
    }

    private Processo pegaProximo() {
        if (contAltaSeguidas >= 5) {
            Processo m = media.removeInicio();
            if (m != null) return m;
            Processo b = baixa.removeInicio();
            if (b != null) return b;
        }
        Processo a = alta.removeInicio();
        if (a != null) return a;
        Processo m = media.removeInicio();
        if (m != null) return m;
        return baixa.removeInicio();
    }

    public void executarCiclo() {
        cabecalho();
        desbloqueiaUmSeTiver();

        Processo atual = pegaProximo();
        if (atual == null) {
            imprimeFilas();
            System.out.println("CPU OCIOSA: nenhuma tarefa para executar.");
            return;
        }

        if (atual.precisaDisco() && !atual.jaBloqueou) {
            atual.jaBloqueou = true;
            bloqueados.addFim(atual);
            System.out.println("BLOQUEIO: " + atual.resumo() + " solicitou DISCO e foi bloqueado.");

            Processo outro = pegaProximo();
            if (outro != null) {
                if (outro.precisaDisco() && !outro.jaBloqueou) {
                    outro.jaBloqueou = true;
                    bloqueados.addFim(outro);
                    System.out.println("BLOQUEIO: " + outro.resumo() + " solicitou DISCO e foi bloqueado.");
                    imprimeFilas();
                    System.out.println("CPU OCIOSA após bloqueios.");
                    return;
                } else {
                    System.out.println("EXECUTANDO: " + outro.resumo());
                    outro.ciclos = outro.ciclos - 1;
                    if (outro.ciclos <= 0) {
                        System.out.println("TERMINOU : P" + outro.id + " (" + outro.nome + ")");
                    } else {
                        if (outro.prioridade == 1) alta.addFim(outro);
                        else if (outro.prioridade == 2) media.addFim(outro);
                        else baixa.addFim(outro);
                        System.out.println("REINSERIDO: " + outro.resumo() + " no final da fila");
                    }
                    if (outro.prioridade == 1) contAltaSeguidas++;
                    else contAltaSeguidas = 0;
                    imprimeFilas();
                    return;
                }
            } else {
                imprimeFilas();
                System.out.println("CPU OCIOSA após bloqueio.");
                return;
            }
        }

        System.out.println("EXECUTANDO: " + atual.resumo());
        atual.ciclos = atual.ciclos - 1;

        if (atual.ciclos <= 0) {
            System.out.println("TERMINOU : P" + atual.id + " (" + atual.nome + ")");
        } else {
            if (atual.prioridade == 1) alta.addFim(atual);
            else if (atual.prioridade == 2) media.addFim(atual);
            else baixa.addFim(atual);
            System.out.println("REINSERIDO: " + atual.resumo() + " no final da fila");
        }

        if (atual.prioridade == 1) contAltaSeguidas++;
        else contAltaSeguidas = 0;

        imprimeFilas();
    }   
}
