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
}
