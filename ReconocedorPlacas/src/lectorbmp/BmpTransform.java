/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorbmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author jmanuel
 */

public class BmpTransform {
    
    private FileInputStream fis;
    private long ancho;
    private long alto;
    private long resto;
    private int deadline = 60 ;
    private long matriz[][];
    
    public void readBMP(File archivo){
        try {
            fis = new FileInputStream(archivo);
            metadataBMP();
        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error");
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
        
        System.out.println(ancho + " px Ancho");

        bite = 0;
        for (int i = 22; i <= 25; i++) {
            alto |= ((long) (datos[i] & 0xff)) << bite;
            bite += 8;
        }
        System.out.println(alto + " px Alto");
        
        resto = ancho % 4;
        createM();
    }
    
    public void createM() throws IOException{
        matriz = new long[(int)alto][(int)ancho];
        byte pixeles[] = new byte[3];
        int alt = (int) alto;
        for (int fil = alt; fil > 0; fil--) {
            for (int col = 0; col < ancho; col++) {
                fis.read(pixeles, 0, 3);
                long blue = pixeles[0] & 0xff;
                long green = pixeles[1] & 0xff;
                long red = pixeles[2] & 0xff;
                long promedio = (blue + green + red)/3;
                if(promedio < deadline)
                    matriz[fil-1][col] = 1;
            }
            for (int k = 0; k < resto; k++)
                fis.read();
        }
    }
    
    public long[][] getMatriz(){
        return this.matriz;
    }
    
}
