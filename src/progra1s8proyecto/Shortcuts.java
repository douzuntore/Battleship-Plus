package progra1s8proyecto;
/**
 *
 * @author samuelzun
 */

/* 
bienvenido
esta clase tiene un conjunto de atajos utiles para distintas tareas, aqui va una breve descripción de ellas:

- menu(String[] incisos): crea un menu en base a los incisos, retorna la opción elegida por el usuario (en forma de byte)

- scan *tipo de dato* (String id): atajo para el Scanner
    (String id): es para que la salida pregunte por el dato, agrega estos caractéres como primer caractér para distintas funciones:
    ["#..."] = solo agrega un numero incluirlo en la misma cadena
    ["_"] = no hay salida
    ["-"] = no agrega nada
    ["@..."] = texto personalizado, incluirlo en la misma cadena

- halt(): espera un 0 del usuario para continuar el programa

- charToInt(char chr): convierte un numero en char a un int 

- printIntArr: imprime el contenido de un array (int[])
  separ " ": 0 1 2 3 4
  separ "]": [0][1][2][3][4]
  separ "@*texto*": 
    *texto*0
    *texto*1
    *texto*2
    *texto*3
    *texto*4

- printCharArr: imprime el contenido de un array (char[])

- fillIntArr: llena un array (int[]) con un numero en específico;
- fillIntArr: llena un array (int[]) con datos int aleatorios dentro de un rango especificado

- printIntBiArr: imprime el contenido de un array bidimensional (int[][])
- fillIntBiArr: llena un array (int[][]) con datos int aleatorios dentro de un rango especificado

- condenseIntArr: elimina todos los indices en el array con el valor equivalente al segundo parametro (int)
- interpolateIntArr: elimina todos los indices en el array con el valor equivalente al segundo parametro (int)

*/

import java.util.Scanner;
import java.util.Random;

public class Shortcuts {
    
    Scanner entr = new Scanner(System.in);
    Random rng = new Random();
    
    public byte menu(String[] incisos, String id1, String id2) {
        int numdeinv = 0;
        printCmd(id1,"---------| Menu |---------","");
        System.out.println("");
        for (int i = 0; i < incisos.length; i++) {
            if (incisos[i].length() != 0) { //dejar inciso con una cadena vacia para saltar término
                System.out.println(i+".- "+incisos[i]);
            } else {
                numdeinv++;
            }
        }
        
        int[] invalidos = new int[numdeinv];
        for (int iinc = 0; iinc < incisos.length; iinc++) { //guardar cada opción invalida
            if (incisos[iinc].length() == 0) {
                for (int iinv = 0; iinv < invalidos.length; iinv++) {
                    if (invalidos[iinv] == 0) {
                        invalidos[iinv] = iinc;
                        break;
                    }
                }
            }
        }
        printCmd(id2,"--------------------------%n","");
        
        byte opcion;
        boolean continuar = false; do {
            opcion = scanByte("@Ingresa una opción: ");
            if (opcion >= 0 && opcion <= incisos.length-1) {
                if (invalidos.length != 0) {
                    for (int i = 0; i < invalidos.length; i++) {
                        if (opcion != invalidos[i]) {
                            continuar = false;
                        } else {
                            continuar = true;
                            System.out.println("Opción invalida");
                            break;
                        }
                    }
                } else {
                    continuar = false;
                }
            } else {
                continuar = true;
                System.out.println("Opción inválida");
            }
            
        } while (continuar);
        return opcion;
    }
    
    public void halt() {
        byte confirmacion;
        while (true) {
            System.out.println("Presione 0 para continuar.");
            confirmacion = entr.nextByte();
            entr.nextLine();
            if (confirmacion == 0) {break;}
        } 
    }
    
    public int scanInt(String id) {
        printCmd(id,"Ingrese el valor",": ");
        int var = entr.nextInt();
        entr.nextLine(); //arreglo de bug
        return var;
    }
    
    public byte scanByte(String id) {
        printCmd(id,"Ingrese el valor",": ");
        byte var = entr.nextByte();
        entr.nextLine(); //arreglo de bug
        return var;
    }
    
    public short scanShort(String id) {
        printCmd(id,"Ingrese el valor",": ");
        short var = entr.nextShort();
        entr.nextLine(); //arreglo de bug
        return var;
    }
    
    public long scanLong(String id) {
        printCmd(id,"Ingrese el valor",": ");
        long var = entr.nextLong();
        entr.nextLine(); //arreglo de bug
        return var;
    }
    
    public float scanFloat(String id) {
        printCmd(id,"Ingrese el valor",": ");
        float var = entr.nextFloat();
        entr.nextLine(); //arreglo de bug
        return var;
    }
    
    public double scanDouble(String id) {
        printCmd(id,"Ingrese el valor",": ");
        double var = entr.nextDouble();
        entr.nextLine(); //arreglo de bug
        return var;
    }
    
    public char scanChar(String id) {
        printCmd(id,"Ingrese el caracter",": ");
        char var = entr.next().charAt(0);
        entr.nextLine(); //arreglo de bug
        return var;
    }
    
    public String scanString(String id) {
        printCmd(id,"Ingrese el valor",": ");
        String var = entr.nextLine();
        //entr.nextLine(); //arreglo de bug
        return var;
    }
    
    public int charToInt(char chr) {
        return Integer.parseInt(Character.toString(chr));
    }
    
    public void printIntArr(int[] arr, String id, String separ) {
        printCmd(id+"%n","Contenido del arreglo",": %n");
        String[] separs = new String[2];
        switch (separ.charAt(0)) {
            case ' ':
                separs[0] = "%d";
                separs[1] = " ";
                break;
            case ']':
                separs[0] = "[%d";
                separs[1] = "]";
                break;
            case '@':
                separs[0] = separ.substring(1, separ.length())+"%d";
                separs[1] = "%n";
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(separs[0]+separs[1],arr[i]);
        }
        if (separ.charAt(0) != '@') {System.out.printf("%n");}
    }
    
    public void printBoolArr(boolean[] arr, String id, String separ, char y, char n) {
        printCmd(id+"%n","Contenido del arreglo",": %n");
        String[] separs = new String[2];
        switch (separ.charAt(0)) {
            case ' ':
                separs[0] = "%-2c";
                separs[1] = "";
                break;
            case ']':
                separs[0] = "[%c";
                separs[1] = "]";
                break;
            case '@':
                separs[0] = separ.substring(1, separ.length())+"%c";
                separs[1] = "%n";
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                System.out.printf(separs[0]+separs[1],y);
            } else {
                System.out.printf(separs[0]+separs[1],n);
            }
        }
        if (separ.charAt(0) != '@') {System.out.printf("%n");}
    }
    
    
    
    public void printCharArr(char[] arr, String id, String separ) {
        printCmd(id+"%n","Contenido del arreglo",": %n");
        String[] separs = new String[2];
        switch (separ.charAt(0)) {
            case ' ':
                separs[0] = "%-2c";
                separs[1] = "";
                break;
            case ']':
                separs[0] = "[%c";
                separs[1] = "]";
                break;
            case '@':
                separs[0] = separ.substring(1, separ.length())+"%c";
                separs[1] = "%n";
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(separs[0]+separs[1],arr[i]);
        }
        if (separ.charAt(0) != '@') {System.out.printf("%n");}
    }
    
    public int[] fillIntArr(int[] arr, int min, int max) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rng.nextInt(min, max);
        }
        return arr;
    }
    
    public int[] fillIntArr(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = num;
        }
        return arr;
    }
    
    public int[][] fillIntBiArr(int[][] arr, int min, int max) {
        for (int i = 0; i < arr.length; i++) {
            fillIntArr(arr[i], min, max);
        }
        return arr;
    }
    
    public int[][] fillIntBiArr(int[][] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            fillIntArr(arr[i], num);
        }
        return arr;
    }
    
    public void printIntBiArr(int[][] arr, String id, String separ) {
        printCmd(id+"%n","Contenido de la matriz",": %n");
        for (int i = 0; i < arr.length; i++) {
            printIntArr(arr[i],"_", separ);
        }
    }
    public void printIntBiArr(int[][] arr, String id, String separ, String[] cols) {
        printCmd(id+"%n","Contenido de la matriz",": %n");
        for (int i = 0; i < cols.length; i++) {
            System.out.print(" "+cols[i]+" ");
        }
        
        for (int i = 0; i < arr.length; i++) {
            printIntArr(arr[i],"_", separ);
        }
    }
    public void printBoolBiArr(boolean[][] arr, String id, String separ, String[] cols, char y, char n) {
        printCmd(id+"%n","Contenido de la matriz",": %n");
        for (int i = 0; i < cols.length; i++) {
            System.out.print(" "+cols[i]+" ");
        }
        System.out.println("");
        
        for (int i = 0; i < arr.length; i++) {
            printBoolArr(arr[i],"_", separ, y, n);
        }
    }
    
    
    public int[] condenseIntArr(int[] arr, int del) {
        int arrCondElem = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != del) {
                arrCondElem++;
            }
        }
        int[] arrCond = new int[arrCondElem];
        arrCondElem = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != del) {
                arrCond[arrCondElem] = arr[i];
                arrCondElem++;
            }
        }
        return arrCond;
    }
    
    public int[] interpolateIntArr(int[] arr, int[] arr2,  int repl) {
        int arr2elem = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == repl) {
                arr2elem++;
            }
        }
        if (arr2.length > arr2elem) {
            System.out.println("[2do array muy grande, no hubieron cambios]");
            return arr;
        }
        arr2elem = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == repl) {
                arr[i] = arr2[arr2elem];
                arr2elem++;
            }
        }
        return arr;
    }
    
    public int[][] rotateIntBiArr(int[][] arr) {
        int[][] newArr = new int[arr.length][arr[0].length];
        for (int iy = 0; iy < newArr.length; iy++) {
            for (int ix = 0; ix < newArr[iy].length; ix++) {
                newArr[iy][ix] = arr[ix][iy];
            }
        }
        return newArr;
    }
    
    public int[][] flipIntBiArr(int[][] arr) {
        int[][] newArr = new int[arr.length][arr[0].length];
        for (int iy = 0; iy < newArr.length; iy++) {
            for (int ix = 0; ix < newArr[iy].length; ix++) {
                newArr[iy][ix] = arr[arr.length-1-iy][arr[iy].length-1-ix];
            }
        }
        return newArr;
    }
    
    public char[][] fillCharBiArr(char[][] biArr, char chr) {
        for (int iy = 0; iy < biArr.length; iy++) {
            for (int ix = 0; ix < biArr[iy].length; ix++) {
                biArr[iy][ix] = chr;
            }
        }
        return biArr;
    }
    
    public void printCharBiArr(char[][] arr, String id, String separ) {
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
        for (int i = 0; i < arr.length; i++) {
            printCharArr(arr[i],"_",separ);
        }
    }
    
    private void printCmd(String id, String defp1, String defp2) {
        switch (id.charAt(0)) {
            case '#':
                System.out.printf(defp1+" "+id.substring(1,id.length())+defp2);
                break;
            case '@':
                System.out.printf(id.substring(1,id.length()));
                break;
            case '-':
                System.out.printf(defp1+defp2);
                break;
            case '_':
                break;
        }
    }
    
    public int[] bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (arr[j-1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        return arr;
    }
    
    public double[] bubbleSort(double[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (arr[j-1] > arr[j]) {
                    double temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        return arr;
    }
    
    public int binarySearch(int[] arr, int target) {
        int st = 0;
        int end = arr.length-1;
        int ind = 0;
        while (st <= end) {
            ind = (st+end)/2;
            if (arr[ind] == target) {return ind;}
            else if (arr[ind] < target) {
                st = ind+1;
            } else if (arr[ind] > target) {
                end = ind-1;
            }
        }
        return -1;
    }
    
    public int binarySearch(double[] arr, double target) {
        int st = 0;
        int end = arr.length-1;
        int ind = 0;
        while (st <= end) {
            ind = (st+end)/2;
            if (arr[ind] == target) {return ind;}
            else if (arr[ind] < target) {
                st = ind+1;
            } else if (arr[ind] > target) {
                end = ind-1;
            }
        }
        return -1;
    }
    
    public int linearSearch(int arr[], int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    public int linearSearch(double arr[], double target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
}

