/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra1s8proyecto;

/**
 *
 * @author samuelzun
 */
import java.util.Random;

public class Tablero {
    
    private char[][] tabl = SC.fillCharBiArr(new char[10][10], ' ');
    private char[][] disp = SC.fillCharBiArr(new char[10][10], ' ');
    private Ship[] ships;
    private int sunkenShips = 0;
    private boolean win = false;
    
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

    public boolean isWin() {
        return win;
    }
    public void setWin(boolean win) {
        this.win = win;
    }
    
    

    public Tablero() {
    }
    
    // Constructor

    public Tablero(int lng) {
        this.tabl = SC.fillCharBiArr(new char[lng][lng], ' ');
        this.disp = SC.fillCharBiArr(new char[lng][lng], ' ');
    }
    
    // Creacion de naves dentro de la memoria
    
    public void añadirNaves(int amnt) {
        this.ships = new Ship[amnt];
        for (int i = 0; i < amnt; i++) {
            Ship temp; do {
                temp = new Ship(1, 4, "Nave", this.tabl);
                if (this.shipCheck(temp)) {i--; continue;}
                for (int jy = 0; jy < temp.getYspaces().length; jy++) {
                    for (int jx = 0; jx < temp.getXspaces().length; jx++) {
                        this.tabl[temp.getYspaces()[jy]][temp.getXspaces()[jx]] = 'X';
                    }
                }
                this.ships[i] = temp;
            } while (false);
        }
    }
    
    private boolean shipCheck(Ship shp) {
        boolean invalid = false;
        for (int i = 0; i < shp.getYspaces().length; i++) {
            for (int j = 0; j < shp.getXspaces().length; j++) {
                if (this.tabl[shp.getYspaces()[i]][shp.getXspaces()[j]] == 'X') {
                    invalid = true;
                }
            }
        }
        return invalid;
    }
    // Cambio del tablero en base a la arma usada
    
    public void tableroCambio(int arma, Arma arsenal) {
        switch (arsenal.getArmas()[arma]) {
            case 1: //sonar
                this.normalSonar();
                break;
            case 2: //micro sonares
                this.miniSonar();
                break;
            case 3: //laser escaner de columna
                int colTarg; do {
                    colTarg = SC.scanInt("@Ingrese fila por la que pasará el drón: ");
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
            case 4: //laser escaner de fila
                int rowTarg; do {
                    rowTarg = SC.scanInt("@Ingrese fila por la que pasará el drón: ");
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
            case 5:
                this.displayClean(new Random().nextInt(0,6));
                break;
        }
        
        arsenal.getArmas()[arma] = 0; arsenal.setArmas(SC.condenseIntArr(arsenal.getArmas(), 0)); 
        
        
        
    }
    
    private void displayClean(int orgcol) {
        for (int i = 0; i < this.tabl.length; i++) {
            for (int j = 0; j < this.tabl[0].length; j++) {
                if (i >= orgcol && i <= orgcol+3) {
                    this.disp[i][j] = ' ';
                }
            }
        }
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
    
    private boolean sonarArea(int i, int j, int[] pos, boolean inv, boolean notinv, int lng) {
        return 
        (i == pos[0] && j == pos[1] && inv) ||
        (i == pos[0]+lng-1 && j == pos[1]+lng-1 && inv) ||
        (i == pos[0]+lng-1 && j == pos[1] && notinv) ||
        (i == pos[0] && j == pos[1]+lng-1 && notinv);
    }
    
    private void miniSonar() {
        for (int z = 0; z < 2; z++) {
            int lng = 3;
            int[] mSonarPosXY;
            boolean inverted = new Random().nextBoolean(); 
            boolean alrsonar; do {
                mSonarPosXY = new int[]{
                    new Random().nextInt(0, this.tabl.length-lng), 
                    new Random().nextInt(0, this.tabl.length-lng)
                }; alrsonar = false;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (this.disp[i+mSonarPosXY[0]][j+mSonarPosXY[1]] >= 48 && 
                            this.disp[i+mSonarPosXY[0]][j+mSonarPosXY[1]] <= 57
                            ) {
                            alrsonar = true;
                        }
                    }
                }
            } while (alrsonar);

            int sonaramnt = 0;

            for (int i = mSonarPosXY[0]; i < mSonarPosXY[0]+lng; i++) {
                for (int j = mSonarPosXY[1]; j < mSonarPosXY[1]+lng; j++) {
                    if (this.sonarArea(i, j, mSonarPosXY, inverted, !inverted, lng)) {continue;}
                    if (this.tabl[i][j] == 'X') {
                        for (int s = 0; s < this.ships.length; s++) {
                            for (int sy = 0; sy < this.ships[s].getYspaces().length; sy++) {
                                for (int sx = 0; sx < this.ships[s].getXspaces().length; sx++) {
                                    if (this.ships[s].getYspaces()[sy] == i && this.ships[s].getXspaces()[sx] == j) {
                                        if (!this.ships[s].isSonared()) {
                                            this.ships[s].setSonared(true);
                                            sonaramnt++;
                                        }
                                    }
                                }
                                if (this.ships[s].isSonared()) {break;}
                            }
                            if (this.ships[s].isSonared()) {break;}
                        }
                    }
                }
            }

            for (int i = mSonarPosXY[0]; i < mSonarPosXY[0]+lng; i++) {
                for (int j = mSonarPosXY[1]; j < mSonarPosXY[1]+lng; j++) {
                    if (this.sonarArea(i, j, mSonarPosXY, inverted, !inverted, lng)) {continue;}
                    if (this.tabl[i][j] == 'X') {
                        for (int s = 0; s < this.ships.length; s++) {
                            for (int sy = 0; sy < this.ships[s].getYspaces().length; sy++) {
                                for (int sx = 0; sx < this.ships[s].getXspaces().length; sx++) {
                                    if (this.ships[s].getYspaces()[sy] == i && this.ships[s].getXspaces()[sx] == j) {
                                        if (this.ships[s].isSonared()) {
                                            this.ships[s].setSonared(false);
                                        }
                                    }
                                    if (!(this.ships[s].isSonared())) {break;}
                                }
                                if (!(this.ships[s].isSonared())) {break;}
                            }
                        }
                    }
                }
            }

            for (int i = mSonarPosXY[0]; i < mSonarPosXY[0]+lng; i++) {
                for (int j = mSonarPosXY[1]; j < mSonarPosXY[1]+lng; j++) {
                    if (this.sonarArea(i, j, mSonarPosXY, inverted, !inverted, lng)) {continue;}
                    this.disp[i][j] = (Integer.toString(sonaramnt)).charAt(0);
                }
            }

        }
    }
    
    private void normalSonar() {
        int lng = 5;
        int[] mSonarPosXY = new int[2];
        do {
            String cmd; do {
                cmd = SC.scanString("@Ingrese locación para apuntar el sonar (C2-H7): %n");
            } while (cmd.length() != 2);
            mSonarPosXY = new int[]{
                cmd.toUpperCase().charAt(0)-67,
                cmd.charAt(1)-50
            };
            if ((mSonarPosXY[0] >= 0 && mSonarPosXY[0] <= this.tabl.length-lng) &&
                (mSonarPosXY[1] >= 0 && mSonarPosXY[1] <= this.tabl.length-lng))
            {break;}
            
        } while (true);
        
        System.out.println(mSonarPosXY[0]);
            System.out.println(mSonarPosXY[1]);

        int sonaramnt = 0;

        for (int i = mSonarPosXY[0]; i < mSonarPosXY[0]+lng; i++) {
            for (int j = mSonarPosXY[1]; j < mSonarPosXY[1]+lng; j++) {
                if (this.sonarArea(i, j, mSonarPosXY, true, true, lng)) {continue;}
                if (this.tabl[i][j] == 'X') {
                    for (int shp = 0; shp < this.ships.length; shp++) {
                        //this.ships[shp].printShip();
                        for (int sy = 0; sy < this.ships[shp].getYspaces().length; sy++) {
                            for (int sx = 0; sx < this.ships[shp].getXspaces().length; sx++) {
                                if (this.ships[shp].getYspaces()[sy] == i && this.ships[shp].getXspaces()[sx] == j) {
                                    if (!this.ships[shp].isSonared()) {
                                        this.ships[shp].setSonared(true);
                                        sonaramnt++;
                                    }
                                }
                                if (this.ships[shp].isSonared()) {break;}
                            }
                            if (this.ships[shp].isSonared()) {break;}
                        }
                    }
                }
            }
        }

        for (int i = mSonarPosXY[0]; i < mSonarPosXY[0]+lng; i++) {
            for (int j = mSonarPosXY[1]; j < mSonarPosXY[1]+lng; j++) {
                if (this.sonarArea(i, j, mSonarPosXY, true, true, lng)) {continue;}
                if (this.tabl[i][j] == 'X') {
                    for (int s = 0; s < this.ships.length; s++) {
                        for (int sy = 0; sy < this.ships[s].getYspaces().length; sy++) {
                            for (int sx = 0; sx < this.ships[s].getXspaces().length; sx++) {
                                if (this.ships[s].getYspaces()[sy] == i && this.ships[s].getXspaces()[sx] == j) {
                                    if (this.ships[s].isSonared()) {
                                        this.ships[s].setSonared(false);
                                    }
                                }
                                if (!(this.ships[s].isSonared())) {break;}
                            }
                            if (!(this.ships[s].isSonared())) {break;}
                        }
                    }
                }
            }
        }

        for (int i = mSonarPosXY[0]; i < mSonarPosXY[0]+lng; i++) {
            for (int j = mSonarPosXY[1]; j < mSonarPosXY[1]+lng; j++) {
                if (this.sonarArea(i, j, mSonarPosXY, true, true, lng)) {continue;}
                this.disp[i][j] = (Integer.toString(sonaramnt)).charAt(0);
            }
        }
    }
    
    //Imprimir el atributo disp (tablero en pantalla)
    
    public void printTablero(String id) {
        char[] rows = new char[]{'A','B','C','D','E','F','G','H','I','J'};
        char[] cols = new char[]{'0','1','2','3','4','5','6','7','8','9'};
        switch (id.charAt(0)) {
            case '#':
                System.out.println("Contenido de la matriz "+id.substring(1,id.length())+": ");
                break;
            case '@':
                System.out.println(id.substring(1,id.length()));
                break;
            case '-':
                System.out.println("Contenido de la matriz: ");
                break;
            case '_':
                break;
        }
        System.out.print("   "); SC.printCharArr(cols,"_"," ");
        for (int i = 0; i < 10; i++) {
            System.out.print(" "+rows[i]+" ");
            SC.printCharArr(this.disp[i],"_","]");
        }
    }
    
    //Cañonazos
    
    public void shootingPhase() {
        for (int z = 0; z < this.ships.length; z++) {
            int[] shotXY = new int[2]; do {
                String cmd; do {
                    cmd = SC.scanString("@Ingrese locación para disparar (A1-J9): %n");
                } while (cmd.length() != 2);
                shotXY = new int[]{
                    cmd.toUpperCase().charAt(0)-65,
                    cmd.charAt(1)-48
                };
                if ((shotXY[0] >= 0 && shotXY[0] < this.tabl.length) &&
                    (shotXY[1] >= 0 && shotXY[1] < this.tabl.length))
                {break;}

            } while (true);
            
            if (this.disp[shotXY[0]][shotXY[1]] == 'X') {z--; continue;}
            
            if (this.tabl[shotXY[0]][shotXY[1]] == 'X') {
                for (int shp = 0; shp < this.ships.length; shp++) {
                    //this.ships[shp].printShip();
                    for (int sy = 0; sy < this.ships[shp].getYspaces().length; sy++) {
                        for (int sx = 0; sx < this.ships[shp].getXspaces().length; sx++) {
                            if (this.ships[shp].getYspaces()[sy] == shotXY[0] && this.ships[shp].getXspaces()[sx] == shotXY[1]) {
                                if (!this.ships[shp].isDefeat()) {
                                    this.ships[shp].setDefeat(true);
                                    this.sunkenShips++;
                                    for (sy = 0; sy < this.ships[shp].getYspaces().length; sy++) {
                                        for (sx = 0; sx < this.ships[shp].getXspaces().length; sx++) {
                                            this.disp[this.ships[shp].getYspaces()[sy]][this.ships[shp].getXspaces()[sx]] = 'X';
                                        }
                                    }
                                }
                            }
                            if (this.ships[shp].isDefeat()) {break;}
                        }
                        if (this.ships[shp].isDefeat()) {break;}
                    }
                }
            } else {
                this.disp[shotXY[0]][shotXY[1]] = '-';
            }
            
            this.printTablero("_");
            
            if (this.sunkenShips == this.ships.length) {this.win = true; break;}
            
        }
    }
    
}
