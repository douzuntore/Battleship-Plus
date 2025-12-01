/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra1s8proyecto;

/**
 *
 * @author samuelzun
 */
public class Tablero {
    
    private char[][] tabl;
    private Ship[] ships;
    
    private static Shortcuts SC = new Shortcuts();

    public char[][] getTabl() {
        return tabl;
    }
    public void setTabl(char[][] tabl) {
        this.tabl = tabl;
    }

    public Ship[] getShips() {
        return ships;
    }
    public void setShips(Ship[] ships) {
        this.ships = ships;
    }
    
    

    public Tablero() {
    }
    
    // Constructor

    public Tablero(int lng) {
        this.tabl = new char[lng][lng];
    }

    
    
}
