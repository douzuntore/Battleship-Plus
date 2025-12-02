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
        
        JuegoNormal.añadirNaves(new Random().nextInt(5,8));
        
        SC.printCharBiArr(JuegoNormal.getTabl(), "-", "]");
        
        Arma arsn = new Arma(8);
        
        SC.printIntArr(arsn.getArmas(), "_", " ");
        
        JuegoNormal.tableroCambio(SC.scanInt("_"), arsn);
        
        JuegoNormal.printTablero("_");
        
        //JuegoNormal.tableroCambio(SC.scanInt("_"), arsn);
        
        //JuegoNormal.printTablero("_");
        
        JuegoNormal.shootingPhase();
        
    }
    
}
