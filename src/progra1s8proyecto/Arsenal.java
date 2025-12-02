package progra1s8proyecto;

/**
 *
 * @author samuelzun
 */
import java.util.Random;

public class Arsenal {
    
    private int[] armas;
    private String[] wpnNames = new String[]{
        "",
        "Sonar (normal)",
        "Sonar (ráfaga)",
        "Dron (vertical)",
        "Dron (horizontal)",
        "Recalibre"
    };

    public int[] getArmas() {
        return armas;
    }
    public void setArmas(int[] armas) {
        this.armas = armas;
    }

    public String[] getWpnNames() {
        return wpnNames;
    }
    public void setWpnNames(String[] wpnNames) {
        this.wpnNames = wpnNames;
    }
    
    public Arsenal() {
    }
    
    //Constructor
    
    public Arsenal(int amnt) {
        this.armas = new int[amnt];
        for (int i = 0; i < this.armas.length; i++) {
            int seed = new Random().nextInt(1,38);
            if (seed >= 1 && seed <= 12) {this.armas[i] = 1;} else
            if (seed > 12 && seed <= 18) {this.armas[i] = 2;} else
            if (seed > 18 && seed <= 27) {this.armas[i] = 3;} else
            if (seed > 27 && seed <= 36) {this.armas[i] = 4;} else
            if (seed == 37) {this.armas[i] = 5;}
        }
        int seed = new Random().nextInt(this.armas.length); if (this.armas[seed] != 5) {
            this.armas[seed] = 5;
        } 
    }
    
    
    
}
