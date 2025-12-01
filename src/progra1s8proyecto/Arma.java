package progra1s8proyecto;

/**
 *
 * @author samuelzun
 */
import java.util.Random;

public class Arma {
    
    private int[] armas;

    public int[] getArmas() {
        return armas;
    }
    public void setArmas(int[] armas) {
        this.armas = armas;
    }

    public Arma() {
    }
    
    public Arma(int amnt) {
        this.armas = new int[amnt];
        for (int i = 0; i < this.armas.length; i++) {
            this.armas[i] = new Random().nextInt(1,5);
        }
    }
    
    
    
}
