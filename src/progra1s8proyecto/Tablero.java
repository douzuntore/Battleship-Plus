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

    public char[][] getDisp() {
        return disp;
    }
    public void setDisp(char[][] disp) {
        this.disp = disp;
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
    
    public void tableroCambio(int arma, Arma arsenal) {
        switch (arsenal.getArmas()[arma]) {
            case 1,2:
                
                break;
            case 3: //escaner de columna
                int colTarg; do {
                    colTarg = SC.scanInt("@Ingrese columna para tirar el escaner: ");
                } while (
                        !(colTarg >= 0 && colTarg < this.tabl.length)
                        );
                for (int i = 0; i < this.tabl.length; i++) {
                    for (int j = 0; j < this.tabl[0].length; j++) {
                        if (j == colTarg) {
                            this.disp[i][j] = this.scannerCheck(i, j);
                        }
                    }
                }
                break;
            case 4: //escaner de fila
                int rowTarg; do {
                    rowTarg = SC.scanInt("@Ingrese columna para tirar el escaner: ");
                } while (
                        !(rowTarg >= 0 && rowTarg < this.tabl.length)
                        );
                for (int i = 0; i < this.tabl.length; i++) {
                    for (int j = 0; j < this.tabl[0].length; j++) {
                        if (i == rowTarg) {
                            this.disp[i][j] = this.scannerCheck(i, j);
                        }
                    }
                }
                break;
        }
        
        arsenal.getArmas()[arma] = 0; SC.condenseIntArr(arsenal.getArmas(), 0);
        
    }
    
    private char scannerCheck(int i, int j) {
        boolean[] x2case = new boolean[]{false, false};
        char res = '*';
        if (j-1 >= 0) {
            if (this.tabl[i][j-1] == 'X') {
                res = '<';
                x2case[0] = true;
            }
        }
        if (j+1 < this.tabl.length) {
            if (this.tabl[i][j+1] == 'X') {
                res = '>';
                x2case[1] = true;
            }
        }
        if (x2case[0] && x2case[1]) {
            res = '!';
        }
        if ((!(x2case[0]) && !(x2case[1])) || this.tabl[i][j] == 'X') {
            res = '*';
        }
        return res;
    }
    
}
