package dk.sidereal.finalexambogdanbele.utils;

import java.util.Date;

/**
 * Created by Bogdan on 1/6/2018.
 */

public class UniqueIdentifiers {

    public static int generateUniqueTimestamp(){
        long time = new Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        return Integer.valueOf(last4Str);
    }
}
