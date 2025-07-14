package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

public class ImageWriterTest {
    @Test
    void testWriteToImage() {
        ImageWriter imageWriter=new ImageWriter( 800, 500);
        for(int i=0; i<800;i++) {
            for(int j=0; j<500;j++) {
                if(i%50==0||j%50==0){
                    imageWriter.writePixel(i, j, new Color(java.awt.Color.YELLOW));
                }
                else{
                    imageWriter.writePixel(i, j, new Color(java.awt.Color.BLUE));
                }
            }
        }
        imageWriter.writeToImage("grid");
    }
}
