package progra1s8proyecto;

/**
 *
 * @author samuelzun
 */
import java.util.Random;

public class Ship {
    
    private int lngY;
    private int lngX;
    private int posY;
    private int posX;
    private String name;
    private boolean defeat = false;
    private int[] Yspaces;
    private int[] Xspaces;
    private boolean sonared = false;

    public int getLngY() {
        return lngY;
    }
    public void setLngY(byte lngY) {
        this.lngY = lngY;
    }

    public int getLngX() {
        return lngX;
    }
    public void setLngX(int lngX) {
        this.lngX = lngX;
    }

    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefeat() {
        return defeat;
    }
    public void setDefeat(boolean defeat) {
        this.defeat = defeat;
    }

    public int[] getYspaces() {
        return Yspaces;
    }
    public void setYspaces(int[] Yspaces) {
        this.Yspaces = Yspaces;
    }

    public int[] getXspaces() {
        return Xspaces;
    }
    public void setXspaces(int[] Xspaces) {
        this.Xspaces = Xspaces;
    }

    public boolean isSonared() {
        return sonared;
    }
    public void setSonared(boolean sonared) {
        this.sonared = sonared;
    }
    
    
    
    public Ship() {
    }
    
    private static Random rng = new Random();
    private static Shortcuts SC = new Shortcuts();
    
    // Constructor

    public Ship(int minSize, int maxSize, String name, char[][] tabl) {
        
        this.lngY = rng.nextInt(minSize, maxSize);
        this.lngX = rng.nextInt(minSize, maxSize);
        
        if (this.lngY == this.lngX) {
            if (this.lngX == maxSize-1) {
                if (rng.nextBoolean()) {this.lngY--;}
                else {this.lngX--;}
            } else if (this.lngX == 1) {
                if (rng.nextBoolean()) {this.lngY++;}
                else {this.lngX++;}
            }
        }
        
        this.posY = rng.nextInt(tabl.length-(this.lngY-1));
        this.posX = rng.nextInt(tabl.length-(this.lngX-1));
        
        this.name = name;
        
        this.Yspaces = new int[this.lngY];
        for (int i = 0; i < this.Yspaces.length; i++) {
            this.Yspaces[i] = this.posY+i;
        }
        this.Xspaces = new int[this.lngX];
        for (int i = 0; i < this.Xspaces.length; i++) {
            this.Xspaces[i] = this.posX+i;
        }
    }
    
    // Nave vista en matriz
    
    public char[][] matrixImage() {
        return SC.fillCharBiArr(new char[this.lngY][this.lngX], 'X');
    }
    
    // toString
    
    public void printShip() {
        SC.printCharBiArr(this.matrixImage(),"@Nave: "," ");
        System.out.println("PosY: "+this.posY);
        System.out.println("PosX: "+this.posX);
        System.out.println("Name: "+this.name);
        System.out.println("Defeat? "+this.defeat);
        SC.printIntArr(Yspaces, "_", "]");
        SC.printIntArr(Xspaces, "_", "]");
    }
    
    
    
}
