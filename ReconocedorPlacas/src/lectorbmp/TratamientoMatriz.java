/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorbmp;

/**
 *
 * @author jmanuel
 */
public class TratamientoMatriz {
    
    private int matriz[][][];
    private int nmatriz[][];
    int p_negro; //porcentaje de negro para que sea 1
    int cuadros_x; //numero de divisiones a lo largo
    int cuadros_y; //numero de divisiones a lo alto
    
    public void TratamientoMatriz(int[][][] matriz, int cuadros_x, int cuadros_y){
        this.matriz = matriz;
        this.cuadros_x = cuadros_x;
        this.cuadros_y = cuadros_y;
        this.nmatriz = new int[cuadros_y][cuadros_x];
    }
    
    private void crearMatriz(){
        
    }
    
    
}
