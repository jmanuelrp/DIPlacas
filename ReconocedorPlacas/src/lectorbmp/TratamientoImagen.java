/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorbmp;

import java.awt.Color;

/**
 *
 * @author jmanuel
 */
public class TratamientoImagen {
    
    private int imgbytes[][][];
    private int angulocontraste = 80;
    
    public TratamientoImagen(int[][][] imgbytes){
        this.imgbytes = imgbytes;
    }
    
    public void brillo(){
        for (int i = 0; i < imgbytes.length; i++)
            for (int j = 0; j < imgbytes[i].length; j++) {
                Color c = new Color(imgbytes[i][j][0],imgbytes[i][j][1],imgbytes[i][j][2]);
                c = c.brighter();
                imgbytes[i][j][0] = c.getRed();
                imgbytes[i][j][1] = c.getGreen();
                imgbytes[i][j][2] = c.getBlue();
            }
    }
    
    public void contraste(){
        for (int i = 0; i < imgbytes.length; i++)
            for (int j = 0; j < imgbytes[i].length; j++)
                for (int k = 0; k < imgbytes[i][j].length; k++) {
                    double contraste = Math.tan( angulocontraste * Math.PI / 180.0 );
                    imgbytes[i][j][k] = (int) (128 + (imgbytes[i][j][k] - 128) * contraste);
                    if( imgbytes[i][j][k] < 0 ) imgbytes[i][j][k] = 0;
                    if( imgbytes[i][j][k] > 255 ) imgbytes[i][j][k] = 255;
                }
    }

    /**
     * @return the imgbytes
     */
    public int[][][] getImgbytes() {
        return imgbytes;
    }

    /**
     * @return the angulocontraste
     */
    public int getAngulocontraste() {
        return angulocontraste;
    }

    /**
     * @param angulocontraste the angulocontraste to set
     */
    public void setAngulocontraste(int angulocontraste) {
        this.angulocontraste = angulocontraste;
    }
    
    
}