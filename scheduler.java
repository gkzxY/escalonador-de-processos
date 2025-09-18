public class Scheduler {
    private ListaDuplamenteEncadeada alta = new ListaDuplamenteEncadeada();
    private ListaDuplamenteEncadeada media = new ListaDuplamenteEncadeada();
    private ListaDuplamenteEncadeada baixa = new ListaDuplamenteEncadeada();
    private ListaDuplamenteEncadeada bloqueados = new ListaDuplamenteEncadeada();

    private int ciclo = 0;
    private int contAltaSeguidas = 0;
}
