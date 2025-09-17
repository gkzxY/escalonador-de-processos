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
}
