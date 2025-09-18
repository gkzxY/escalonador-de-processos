import java.util.Scanner;

public class main{
  public static void main(String[] args){
     if (args.length < 1) {
            System.err.println("Uso: java Main <caminho-arquivo.txt|.csv>");
            return;
        }
    
        String caminho = args[0];
        Scheduler scheduler = new Scheduler();
     try {
        Scanner sc = new Scanner(new java.io.FileInputStream(caminho), "UTF-8");
        boolean primeira = true;
        while (sc.hasNextLine()) {
                String linha = sc.nextLine().trim();
                if (linha.isEmpty()) continue;
                if (primeira && linha.toLowerCase().startsWith("id")) {
                    primeira = false;
                    continue;
                   }
                primeira = false;
                String[] p = linha.split(",", -1);
                if (p.length < 4) {
                    System.err.println("Linha inválida ignorada: " + linha);
                    continue;
                }
                int id = parseIntSeguro(p[0], 0);
                String nome = p[1].trim();
                int prioridade = parseIntSeguro(p[2], 3);
                int ciclos = parseIntSeguro(p[3], 1);
                String recurso = (p.length >= 5) ? p[4].trim() : "";
          
                if (id <= 0 || nome.isEmpty()) {
                    System.err.println("Dados inválidos, linha ignorada: " + linha);
                    continue;
                }

          
          
          
       

      
