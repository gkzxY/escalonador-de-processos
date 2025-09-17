public class ListaDuplamenteEncadeada{
   private static class No{
        Processo val;
        No ant, prox;
        No(Processo p){this.val = p; }
    }
    private No ini;
    private No fim;
    private int size;

   public boolean vazia() { return size == 0; }
   public int tamanho() { return size; }

   public void addFim(Processo p) {
        No n = new No(p);
        if (fim == null) {
            ini = n;
            fim = n;
        } else {
            n.ant = fim;
            fim.prox = n;
            fim = n;
        }
        size++;
  }
    public Processo removeInicio() {
        if (ini == null) {
           return null;
        }else{
        No n = ini;
        ini = n.prox;
       
        if (ini == null) { 
           fim = null; 
        }else{ 
           ini.ant = null;
        }
        n.ant = n.prox = null;
        size--;
        return n.val;
    }
  }

   public Processo olhaInicio() {
        return (ini == null) ? null : ini.val;
    }

   public String toString() {
        StringBuilder sb = new StringBuilder("[");
        No c = ini;
        while (c != null) {
            sb.append(c.val.resumo());
            c = c.prox;
            if (c != null) sb.append(" -> ");
        }
        sb.append("]");
        return sb.toString();
    }
}
