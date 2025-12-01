package progra1s8proyecto;

/**
 *
 * @author samuelzun
 */
public class Progra1S8Proyecto {
    
    public static void main(String[] args) {
        
        Shortcuts SC = new Shortcuts();
        
        Tablero tablDeJuego = new Tablero(10);
        
        Ship nave = new Ship(1, 4, "Tahoe", tablDeJuego.getTabl());
        
        nave.printShip();
        
    }
    
}
