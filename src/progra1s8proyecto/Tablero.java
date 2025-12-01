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
    
    private char[][] tabl = SC.fillCharBiArr(new char[10][10], ' ');
    private char[][] disp = SC.fillCharBiArr(new char[10][10], ' ');
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
        this.tabl = SC.fillCharBiArr(new char[lng][lng], ' ');
        this.disp = SC.fillCharBiArr(new char[lng][lng], ' ');
    }
    
    public void añadirNaves(int amnt) {
        this.ships = new Ship[amnt];
        for (int i = 0; i < amnt; i++) {
            Ship temp; do {
                temp = new Ship(1, 4, "Nave", this.tabl);
                if (this.tabl[temp.getPosY()][temp.getPosX()] == 'X') {continue;}
                for (int jy = 0; jy < temp.getYspaces().length; jy++) {
                    for (int jx = 0; jx < temp.getXspaces().length; jx++) {
                        this.tabl[temp.getYspaces()[jy]][temp.getXspaces()[jx]] = 'X';
                    }
                }
                this.ships[i] = temp;
            } while (false);
        }
    }
    
    
    
}
