package model;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Alexis Arnould
 */
public class ConvertHexaAscii {

    /**
     * Retounr le code ascii correspondant à une valeur hexadecimale
     * @param hexa
     * @return 
     */
    public static String getAsciiFromHexa(String hexa) {
        try {
            return new String(HexBin.decode(hexa), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return "";
        }
    }
    
    /**
     * Retounr la valeur hexadecimale correspondant à un code ascii
     * @param ascii
     * @return 
     */
    public static String getHexaFromAscii(String ascii) {
        try {
            StringBuilder builder = new StringBuilder();
            for (char c : ascii.toCharArray()) {
                int i = (int) c;
                builder.append(Integer.toHexString(i).toUpperCase());
             }
            return builder.toString();
        }
        catch (Exception e) {
            return "";
        }
    }
}
