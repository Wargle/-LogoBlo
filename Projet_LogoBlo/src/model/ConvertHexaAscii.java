package model;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Alexis Arnould
 */
public class ConvertHexaAscii {

    public static String getAsciiFromHexa(String hexa) {
        try {
            return new String(HexBin.decode(hexa), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
