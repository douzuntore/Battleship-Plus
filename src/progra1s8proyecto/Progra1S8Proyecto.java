package progra1s8proyecto;

/**
 *
 * @author samuelzun
 */
import java.util.Random;

public class Progra1S8Proyecto {
    
    public static void main(String[] args) {
        
        Scut SC = new Scut();
        
        
        //Tablero JuegoNormal = new Tablero();
        
        //JuegoNormal.añadirNaves(new Random().nextInt(5,8));
        
        //SC.printCharBiArr(JuegoNormal.getTabl(), "-", "]");
        
        
        
        //SC.printIntArr(arsn.getArmas(), "_", " ");
        
        //JuegoNormal.tableroCambio(SC.scanInt("_"), arsn);
        
        //JuegoNormal.printTablero("_");
        
        //JuegoNormal.tableroCambio(SC.scanInt("_"), arsn);
        
        //JuegoNormal.printTablero("_");
        
        //JuegoNormal.shootingPhase();
        boolean continuar = true; do {
            System.out.printf("|.. .  .   .    BATTLESHIP PLUS+    .   .  . ..|%n");
            System.out.printf("|.. .  .   .                        .   .  . ..|%n");
            switch (SC.menu(
                    new String[]{"","Jugar partida","Como jugar","Salida"}, 
                    "_", 
                    "@%n|.. .  .   .                        .   .  . ..|%n|.. .  .   .                        .   .  . ..|%n%n>> ", 
                    "_")
                    ) {
                case 1:
                    jugarPartida();
                    break;
                case 2:
                    comoJugar();
                    break;
                case 3:
                    continuar = false;
                    break;
            }
        } while (continuar);
        
    }
    
    private static void jugarPartida() {
        
        Arsenal arsn = new Arsenal(11);
        Tablero juego = new Tablero();
        juego.añadirNaves(new Random().nextInt(5,8));
        int ronda = 1;
        for (int i = arsn.getArmas().length; i > 1; i--) {
            juego.printTablero("@%n|.. .  .   .        RONDA "+ronda+"        .   .  . ..|%n%n");
            int lng = 5;
            String[] selecArmas = new String[lng];
            int fix = 0; if (arsn.getArmas().length < lng-1) {
                selecArmas = new String[lng-Math.abs(lng-arsn.getArmas().length)+1];
            }
            for (int j = 0; j < selecArmas.length-fix; j++) {
                if (j == 0) {
                    selecArmas[j] = "";
                } else if (j == selecArmas.length-1) {
                    selecArmas[j] = "Saltar fase";
                } else {
                    selecArmas[j] = arsn.getWpnNames()[arsn.getArmas()[j-1]];
                }
            }
            byte opcion = new Scut().menu(selecArmas, "@Seleccione: ", "@>> ", "_");
            if (opcion == selecArmas.length-1) {break;}
            juego.tableroCambio(opcion-1, arsn);
            ronda++;
        }
        System.out.printf("%n|.. .  .   .    FASE TERMINADA    .   .  . ..|%n%n");
        String no = new Scut().scanString("@>> (enter)");
        juego.shootingPhase(ronda);
        System.out.printf("%n|.. .  .   .    RESULTADOS    .   .  . ..|%n%n");
        if (juego.isWin()) {
            no = new Scut().scanString(""
                + "@[GANASTE][WOW][INCREIBLE]%n%nBarcos derribados: "+juego.getSunkenShips()+"%n>> (enter)");
        } else {
            no = new Scut().scanString(""
                + "@[PERDISTE][AWW][QUEMAL:(]%n%nBarcos derribados: "+juego.getSunkenShips()+"%n>> (enter)");
        }
        
        no = new Scut().scanString(""
                + "@Barcos en pie: "+(juego.getShips().length-juego.getSunkenShips())+"%n>> (enter)");
        System.out.printf("Campo final: %n");
        juego.printTablero("_");
        no = new Scut().scanString("@%n>> (enter)");
        System.out.printf("Ubicacion de naves: %n");
        juego.printNaves("_");
        no = new Scut().scanString("@%n>> (enter)");
        no = new Scut().scanString(""
                + "@%n[GRACIAS][POR][JUGAR:)]%n%n>> (enter)");
        System.out.println("");
        
        
    }
    
    private static void comoJugar() {
        do {
            System.out.printf(
                    "%nEl objetivo dentro de este juego es derribar todas%n"
                  + "las naves dentro del terreno del juego. Antes de%n"
                  + "realizar tus disparos, dispones de distintas%n"
                  + "herramientas para realizar tus disparos. Puedes elegir%n"
                  + "una opción para saber la función de tus herramientas.%n");
            String a; switch (new Scut().menu(new Arsenal().getWpnNames(), "_", "@%n>> ", "_")) {
                case 0:
                    System.out.printf(""
                            + "%nEl sonar escanea un area para determinar cuantas naves se%n"
                            + "encuentran dentro de ella. Este sonar puede ser puesto en una%n"
                            + "casilla en específico para que realize el escaneo.%n"
                            + "%n"
                            + ">> (enter) ");
                    a = new Scut().scanString("_");
                    break;
                case 1:
                    System.out.printf(""
                            + "%nEl sonar escanea un area para determinar cuantas naves se%n"
                            + "encuentran dentro de ella. Este sonar es apuntado aleatoriamente%n"
                            + "en dos areas pequeñas para realizar su escaneo.%n"
                            + "%n"
                            + ">> (enter) ");
                    a = new Scut().scanString("_");
                    break;
                case 2:
                    System.out.printf(""
                            + "%nEl drón reccore una linea recta para indicarte si hay una%n"
                            + "nave del lado derecho o izquierdo de su recorrido. Este dron%n"
                            + "recorre una columna del campo de tu elección.%n"
                            + "%n"
                            + ">> (enter) ");
                    a = new Scut().scanString("_");
                    break;
                case 3:
                    System.out.printf(""
                            + "%nEl drón reccore una linea recta para indicarte si hay una%n"
                            + "nave del lado derecho o izquierdo de su recorrido. Este dron%n"
                            + "recorre una fila del campo de tu elección.%n"
                            + "%n"
                            + ">> (enter) ");
                    a = new Scut().scanString("_");
                    break;
                case 4:
                    System.out.printf(""
                            + "%nEl recalibre sirve para limpiar una gran franja de tu pantalla.%n"
                            + "Usalo si te ves inconforme con la información de han adquirido tus%n"
                            + "herramientas.%n"
                            + "%n"
                            + ">> (enter) ");
                    a = new Scut().scanString("_");
                    break;
            }
            
            if (new Scut().menu(new String[]{"Sí", "No"},"@%nDeseas salir?","@>> ","_") == 0) {break;}
            
        } while (true);
        System.out.println("");
    }
    
}
