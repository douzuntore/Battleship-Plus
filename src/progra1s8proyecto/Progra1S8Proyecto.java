package progra1s8proyecto;

/**
 *
 * @author samuelzun
 */
import java.util.Random;

public class Progra1S8Proyecto {
    
    public static void main(String[] args) {
        
        Shortcuts SC = new Shortcuts();
        
        
        Tablero JuegoNormal = new Tablero();
        
        Ship nave = new Ship(1, 4, "Tahoe", JuegoNormal.getTabl());
        
        nave.printShip();
        
        JuegoNormal.añadirNaves(new Random().nextInt(5,8));
        
        SC.printCharBiArr(JuegoNormal.getTabl(), "-", "]");
        
    }
    
}
