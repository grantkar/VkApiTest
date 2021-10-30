package api.utils;

import lombok.experimental.UtilityClass;
import java.text.SimpleDateFormat;
import java.util.*;


@UtilityClass
public class UtilsHelper {

    public String getDateTomorrow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dt;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        dt = sdf.format(c.getTime());
        return dt.toString();
    }
}
