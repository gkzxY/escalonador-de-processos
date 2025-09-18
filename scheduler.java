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

    
}
