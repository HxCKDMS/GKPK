package HxCKDMS.gkpk.util;

public class LSDHelper {
    public static final String[] shaderList = new String[] {"bits","bumpy","deconverge","green","ntsc","phosphor","sobel","notch","wobble"};
    public String GenerateShader(){
        return shaderList[(int) ((float)shaderList.length * Math.random())];
    }
}
