package utils;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Named
@Dependent
public class ConvertDate implements Serializable {

    private static final long serialVersionUID = 1L;

    public static java.sql.Date utilDateToSqlDate(Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return java.sql.Date.valueOf(localDate);
    }
}
