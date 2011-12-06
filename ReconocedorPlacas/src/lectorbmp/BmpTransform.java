/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorbmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmanuel
 */

public class BmpTransform {
    
    private FileInputStream fis;
    private long ancho;
    private long alto;
    private long resto;
    private int matriz[][][];
    
    public void readBMP(File archivo){
        try {
            fis = new FileInputStream(archivo);
            metadataBMP();
            fis.close();
        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error\n"+ex);
        }
    }
    
    public void readBMP(String archivo){
        readBMP(new File(archivo));
    }
    
    private void metadataBMP() throws IOException{
        byte datos[] = new byte[54];
        fis.read(datos, 0, 54);
        int bite = 0;
        bite = 0;
        for (int i = 18; i <= 21; i++) {
            ancho |= ((long) (datos[i] & 0xff)) << bite;
            bite += 8;
        }
        bite = 0;
        for (int i = 22; i <= 25; i++) {
            alto |= ((long) (datos[i] & 0xff)) << bite;
            bite += 8;
        }
        resto = ancho % 4;
        createM();
    }
    
    public void createM() throws IOException{
        matriz = new int[(int)alto][(int)ancho][3];
        byte pixeles[] = new byte[3];
        int alt = (int) alto;
        for (int fil = alt; fil > 0; fil--) {
            for (int col = 0; col < ancho; col++) {
                fis.read(pixeles, 0, 3);
                for (int i = 0; i < pixeles.length; i++)
                    matriz[fil-1][col][2-i] = pixeles[i] & 0xff;
            }
            for (int k = 0; k < resto; k++)
                fis.read();
        }
    }
    
    public int[][][] getMatriz(){
        return this.matriz;
    }
    
}
