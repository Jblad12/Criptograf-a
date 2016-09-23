
package turing;

import java.util.Scanner;
import java.util.*;

public class Turing {
    public static void main(String[] args) {
        double rootcast;
        System.out.println("frase a encriptar: ");
        String entradaTeclado="";
        String cadenaSinBlancos= "";
        Scanner entradaEscaner = new Scanner(System.in);
        entradaTeclado = entradaEscaner.nextLine();
       
        for (int x=0; x < entradaTeclado.length(); x++) {// para contar la cadena sin espacios
            if (entradaTeclado.charAt(x) != ' ')
              cadenaSinBlancos += entradaTeclado.charAt(x);
        }
        int tam= cadenaSinBlancos.length();
        System.out.println("la cadena sin espacios es: "+ tam);
        rootcast = (float) Math.sqrt(tam);// evalúo la raiz cuadrada para obtener el tamaño de la matriz
        //evaluarTam(rootcast); // inicializo e imprimo la matriz correspondiente 2x2, 4x4, 6x6,8x8...
                
        if (rootcast>=1 && rootcast<2){//strings de 2 a 3 caracteres
            int mat[][] = inicializarMatriz(2);
            System.out.println("original");
            imprimirMatriz(mat);
            
//            int van[][] = rotarDerecha(mat);
//            imprimirMatriz(van);
            System.out.println("rot1");
            int rot[][]=rotarIzquierda(mat);
            imprimirMatriz(rot);
            

        }else if(rootcast>=2 && rootcast<=4){//strings de 4 a 16 caracteres
            int mat[][] = inicializarMatriz(4);
            //imprimirMatriz(mat);
            Algor(mat);
            
            
	}
          
   }
    
    public static int[][]   inicializarMatriz(int n) {
        int  [][]matriz = new int[n][n];
        int conut = 4;
        for (int i=0; i< n; i++ ){
            for(int j=0; j< n;j++){
                matriz[i][j]= conut;
                conut++;
            }
        }
     return matriz;
    }
    public static void      imprimirMatriz(int [][]m) {
    for (int i=0; i< m.length; i++ ){
        for(int j=0; j< m.length;j++){
            System.out.print(m[i][j] + "\t");    
        }
        System.out.println();
    }
    }
    public static int[][]   rotarDerecha(int [][]m){
    int [][]aux=new int[m.length][m.length];
    for(int i=0; i<m.length;i++ ){
       for (int j=0; j<m.length; j++){
           aux[i][j] = m[(m.length-j-1)][i];
       }
    }
    return aux;

    }
    public static int[][]   rotarIzquierda(int [][]m){
    int [][]aux=new int[m.length][m.length];
    for(int i=0; i<m.length;i++ ){
       for (int j=0; j<m.length; j++){
           aux[i][j] = m[j][m.length-i-1];
       }
    }
    return aux;
    }
    public static int[][]   dividir(int [][]m){
        int count =1;
        int  [][]nueva = new int [m.length/2][m.length/2];
        for (int i=0; i< m.length/2; i++ ){
            for(int j=0; j< m.length/2;j++){
                nueva[i][j]=count;
                count++;
            }
        }
    return nueva;
    }
    public static int[][]   shuffle(int[][] a) {
    Random random = new Random();

    for (int i = a.length - 1; i > 0; i--) {
        for (int j = a[i].length - 1; j > 0; j--) {
            int m = random.nextInt(i + 1);
            int n = random.nextInt(j + 1);
            int temp = a[i][j];
            a[i][j] = a[m][n];
            a[m][n] = temp;
        }
    }
    return a;
}
    public static int[]  selecciona_pos(int[][]m){
        int []pos = new int [2];
        Random r = new Random();
        int i1 = 0,i2=0;
        for(int i = 0; i < m.length*2; i++) {
            i1 = r.nextInt( m.length);
            i2 = r.nextInt( m.length);
        }
        pos[0] = i1;
        pos[1] = i2;
        return pos;
    }
    public static int traer_ele(int[][] aux1, int []pos) {
       
        int q=pos[0];
        int r=pos[1];
        return aux1[q][r];
    }
    public static void Algor(int [][]mat){
        int aux[][] = dividir(mat);
        //imprimirMatriz(aux);
        //devuelve la matriz con elementos ubucados aleatoriamente
        System.out.println("suffle");
        int aux1[][]=shuffle(aux);
        imprimirMatriz(aux1);

        // tomar una posicion de la matriz al azar
        int pos[] =  selecciona_pos(aux1);
        System.out.println("posicion "+ pos[0]+","+pos[1]);
        //traer el elemeto anterior de la matriz
        int elem = traer_ele(aux1,pos) ;
        System.out.println("********valor del elemento*********** "+ elem); // imprime elemento de la matriz auxiliar

        //Girar la matriz
        System.out.println("Primier giro: ");
        int primerGiro[][]= rotarDerecha(aux1);
        imprimirMatriz(primerGiro);
        int comp[]=tre_pos_elm_otras_M(primerGiro, elem);
        System.out.println("valor donde estan: "+ comp[0]+","+comp[1]);
        
        System.out.println("segundo giro: ");
        int segundoGiro[][]= rotarDerecha(primerGiro);
        imprimirMatriz(segundoGiro);
        int comp1[]=tre_pos_elm_otras_M(segundoGiro, elem);
        System.out.println("valor donde estan: "+ comp1[0]+","+comp1[1]);

        System.out.println("Tercer giro: ");
        int tercerGiro[][]= rotarDerecha(segundoGiro);
        int comp2[]=tre_pos_elm_otras_M(tercerGiro, elem);
        System.out.println("valor donde estan: "+ comp2[0]+","+comp2[1]);
        imprimirMatriz(tercerGiro);
        
        //comparar el elemento con las demas matrices, y esocger los huecos de la matriz 
        
        
        
        
    }
    
    public static int [] tre_pos_elm_otras_M(int[][]l, int f){
        int []posi= new int[2];
        for(int i=0; i<l.length; i++){
            for(int j=0; j<l.length; j++){
                if (f ==l[i][j]){
                    posi[0]=i;
                    posi[1]=j;
                }
            }
        }
        return posi;
    }
    
    
    


}




//*******************ROTAR A LA IZQUIERDA*****************************       
//            System.out.println("rot2");
//            int rot1[][]=rotarIzquierda(rot);
//            imprimirMatriz(rot1);

//            System.out.println("rot3");
//            int rot2[][]=rotarIzquierda(rot1);
//            imprimirMatriz(rot2);

//            System.out.println("rot4");
//            int rot3[][]=rotarIzquierda(rot2);
//            imprimirMatriz(rot3);
//            System.out.println();
//*******************ROTAR A LA DERECHA*****************************       
//            System.out.println();
//            int van1[][] = rotarDerecha(van);
//            imprimirMatriz(van1);

//            System.out.println();
//            int van2[][] = rotarDerecha(van1);
//            imprimirMatriz(van2);

//            System.out.println();
//            int van3[][] = rotarDerecha(van2);
//            imprimirMatriz(van3);

    




    
    

