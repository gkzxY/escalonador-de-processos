public class Processo{
    public int id;
    public String nome;
    public int prioridade;
    public int ciclos;    
    public String recurso;
    public boolean jaBloqueou;
    public Processo(int id, String nome, int prioridade, int ciclos, String recurso) {
    this.id = id;

    if (nomeEntrada == null) {
        this.nome = "";
    } else {
        this.nome = nomeEntrada.trim();
}
        this.prioridade = prioridade;
        this.ciclos = ciclos;

        if (recursoEntrada == null) {
            this.recurso = "";
        } else {
            this.recurso = recursoEntrada.trim().toUpperCase();
        }

        this.jaBloqueou = false;
    }
    
    public boolean precisaDisco() {
        return this.recurso.equals("DISCO");
    }
    public String resumo() {
        String r;

        if (precisaDisco()) {
            r = "|DISCO";
        } else {
            r = "";
        }

        return " P:" + id + "[" + nome + "|pr=" + prioridade + "|cic=" + ciclos + r + "]";
    }
}





    
    

