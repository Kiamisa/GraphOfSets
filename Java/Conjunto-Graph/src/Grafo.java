import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Grafo {
    private Map<Integer, Set<Integer>> grafo;
    private Boolean isDirecionado;

    public Grafo() throws FileNotFoundException {
        FileManager fileManager = new FileManager("grafo.txt");
        this.isDirecionado = fileManager.nextTipoGrafo().equals("D");
        this.grafo = new HashMap<>();
        while (fileManager.hasNextLine()) {
            int[] aresta = fileManager.nextAresta();
            this.addAresta(aresta[0], aresta[1]);
        }
    }

    public void addVertice(int v){
        grafo.put(v, new TreeSet<>());
    }

    public void addAresta(int v1, int v2) {
        if (!grafo.containsKey(v1)) this.addVertice(v1);
        if (!grafo.containsKey(v2)) this.addVertice(v2);

        grafo.get(v1).add(v2);
        if (!this.isDirecionado) grafo.get(v2).add(v1);
    }

    public boolean isAdjacente(int vX, int vY){
        return grafo.get(vX).contains(vY);
    }

    public int getGrau(int vX){
        return grafo.get(vX).size();
    }

    public TreeSet<Integer> getAdjacencias(int vX){
        return (TreeSet<Integer>) grafo.get(vX);
    }

    public void printAllArestas(){
        for (Map.Entry<Integer, Set<Integer>> entry : grafo.entrySet()) {
            Integer key = entry.getKey();
            Set<Integer> value = entry.getValue();
            System.out.println("Vertice: " + key + " Adjacencias: " + value);
        }
    }

public void gerarArquivoTXT(String file) throws IOException {
    	
        File arq= new File("D:\\Códigos\\ProjetoJavaJS\\JS\\Graph\\GraphgrafoFinal.txt");
        List<Integer> keys = new ArrayList<>();
        
        
        if (arq.exists()) {
           System.out.println("o arquivo ja existia e foi atualizado com sucesso!");
        }
        else {
        	System.out.println("Arquivo TXT gerado e escrito com sucesso!");
        }
        
        FileWriter escritor = new FileWriter(arq, false);        
        if (this.isDirecionado) {
        	escritor.write("D\n");
        	  for (Map.Entry<Integer, Set<Integer>> entry : grafo.entrySet()){
                  Integer vX = entry.getKey();
                  keys.add(vX);
                  for (Integer vY : entry.getValue()) {
                      escritor.write("(" + vX + "," + vY + ")\n");
                  }
                  
              }
        }else {
        	escritor.write("ND\n");
        	   for (Map.Entry<Integer, Set<Integer>> entry : grafo.entrySet()){
                   Integer vX = entry.getKey();
                   keys.add(vX);
                   for (Integer vY : entry.getValue()) {
                   	if(!keys.contains(vY)) {
                       escritor.write("(" + vX + "," + vY + ")\n");
                   	}
                   }
                   
               }
        }
        
        escritor.close();
    }
}