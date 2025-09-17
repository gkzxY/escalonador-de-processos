public class ListaDuplamenteEncadeada{
   private static class No{
        Processo val;
        No ant, prox;
        No(Processo p){this.val = p; }
    }
    private No ini;
    private No fim;
    private int size;
}
