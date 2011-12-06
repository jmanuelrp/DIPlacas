/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorbmp;

/**
 *
 * @author jmanuel
 */
public class test_BmpTransform {
    
    public static void main(String[] args) {
        BmpTransform bmp = new BmpTransform();
        bmp.readBMP("tux.bmp");
        long m[][] = bmp.getMatriz();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print( m[i][j] );
            }
            System.out.println("");
        }
    }
    
}
