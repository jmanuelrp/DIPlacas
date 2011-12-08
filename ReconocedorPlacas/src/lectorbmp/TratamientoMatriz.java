/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorbmp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author jmanuel
 */
public class TratamientoMatriz {
    
    private int matriz[][][];
    private int nmatriz[][][];
    private int mbinarizada[][];
    int p_negro = 60; //porcentaje de negro para que sea 1
    int cuadros_x; //numero de divisiones a lo largo
    int cuadros_y; //numero de divisiones a lo alto
    
    public TratamientoMatriz(int[][][] matriz, int cuadros_x, int cuadros_y){
        this.matriz = matriz;
        this.cuadros_x = cuadros_x;
        this.cuadros_y = cuadros_y;
        this.nmatriz = new int[cuadros_y+1][cuadros_x+1][3];
        this.mbinarizada = new int[cuadros_y+1][cuadros_x+1];
    }
    
    public TratamientoMatriz(int[][][] matriz){
        this.matriz = matriz;
    }
    
    public void encuadrar(){
        int deltai = matriz.length / cuadros_y;
        int deltaj = matriz[0].length / cuadros_x;
        double restoi = (matriz.length / cuadros_y) - deltai;
        double restoj = (matriz[0].length / cuadros_x) - deltaj;
        double ipx = 0.0, jpx=0.0; //acumulado para generar un pixel
        int ipxs = 0, jpxs=0; //pixeles generados
        for (int i = 0; i < cuadros_y; i++) {
            if( (ipx += restoi) > (1+ipxs)) ipxs++;
            int topey = deltai*(i+1)+ipxs;
            for (int j = 0; j < cuadros_x; j++) {
                if( (jpx += restoj) > (1+jpxs)) jpxs++;
                int topex = deltaj*(j+1)+jpxs;
                for (int k = i*deltai+ipxs; k < topey; k++)
                    for (int l = j*deltaj+jpxs; l < topex; l++)
                        for (int m = 0; m < 3; m++)
                            nmatriz[i][j][m] += matriz[k][l][m];
                for (int k = 0; k < 3; k++)
                    nmatriz[i][j][k] /= (deltai*deltaj);
            }
        }
    }
    
    public void binarizar(){
        for (int i = 0; i < mbinarizada.length; i++)
            for (int j = 0; j < mbinarizada[i].length; j++){
                for (int k = 0; k < 3; k++)
                    mbinarizada[i][j] += nmatriz[i][j][k];
                mbinarizada[i][j] /= 3;
                mbinarizada[i][j] = (mbinarizada[i][j]<p_negro)? 1 : 0;
            }
    }
    
    public void archivoBinarizada(String nombre) throws IOException{
        PrintWriter pw = new PrintWriter(new FileWriter(nombre+".di"));
        for (int i = 0; i < mbinarizada.length; i++){
            for (int j = 0; j < mbinarizada[i].length; j++)
                pw.print(mbinarizada[i][j]);
            pw.println();
        }
        pw.close();
    }
    
    public int[][][] getEncuandrada(){
        return this.nmatriz;
    }
    
    public int[][] getBinarizada(){
        return this.mbinarizada;
    }
    
}