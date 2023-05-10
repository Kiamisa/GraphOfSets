import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Grafo grafo = new Grafo();

        Scanner scanner = new Scanner(System.in);
        int vX, vY;

        int op = 0;
        while(op != 7){
            System.out.println("\nMenu:");
            System.out.println("1 - Adicionar dois vértices");
            System.out.println("2 - Verificar se vertices sao adjacentes");
            System.out.println("3 - Calcular grau de um vertice");
            System.out.println("4 - Buscar vizinhos de um vertice");
            System.out.println("5 - Visitar todas as arestas do grafo");
            System.out.println("6 - Gerar arquivo de texto com o grafo armazenado no programa");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opcao: ");
            op = scanner.nextInt();
            switch (op) {
                case 1 -> {
                    System.out.print("Digite o primeiro vertice: ");
                    vX = scanner.nextInt();
                    System.out.print("Digite o segundo vertice: ");
                    vY = scanner.nextInt();
                    grafo.addAresta(vX, vY);
                }
                case 2 -> {
                    System.out.print("Digite o primeiro vertice: ");
                    vX = scanner.nextInt();
                    System.out.print("Digite o segundo vertice: ");
                    vY = scanner.nextInt();
                    if (grafo.isAdjacente(vX, vY)) {
                        System.out.println("Os vertices sao adjacentes!");
                    } else {
                        System.out.println("Os vertices nao sao adjacentes!");
                    }
                }
                case 3 -> {
                    System.out.print("Digite o vertice: ");
                    vX = scanner.nextInt();
                    System.out.println("O grau do vertice " + vX + " eh: " + grafo.getGrau(vX));
                }
                case 4 -> {
                    System.out.print("Digite o vertice: ");
                    vX = scanner.nextInt();
                    System.out.println("Os vizinhos do vertice " + vX + " sao: " + grafo.getAdjacencias(vX));
                }
                case 5 -> grafo.printAllArestas();
               case 6 -> {
                   try {
                       grafo.gerarArquivoTXT("grafoFinal.txt");
                   } catch (IOException e) {
                       System.err.println("Erro ao gerar arquivo TXT " + e.getMessage());
                   }
                }
                case 7 -> System.out.println("Saindo...");
                default -> System.out.println("Opcao invalida!");
            }
        }
    }
}