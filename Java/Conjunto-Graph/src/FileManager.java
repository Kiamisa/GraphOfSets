import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {
    private Scanner scanner;

    private String path;

    public FileManager(String path) throws FileNotFoundException {
        this.path = path;
        this.scanner = new Scanner(new File(path));
    }

    public String nextLine() {
        return this.scanner.nextLine();
    }

    public boolean hasNextLine() {
        return this.scanner.hasNextLine();
    }

    public int[] nextAresta() {
        String[] parte = this.nextLine().split(";");
        if (parte.length != 2) {
            throw new IllegalArgumentException("Erro ao ler aresta!");
        }
        int vX = Integer.parseInt(parte[0]);
        int vY = Integer.parseInt(parte[1]);
        return new int[] {vX, vY};
    }

    public String nextTipoGrafo() throws FileNotFoundException {
        this.scanner = new Scanner(new File(path));
        return this.nextLine().trim();
    }

}
