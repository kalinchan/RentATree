package JSFExtras;

import DatabaseObjects.Tree;
import org.joda.time.Days;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class CalendarView implements Serializable {
    private LocalDate minDate = LocalDate.of(LocalDate.now().getYear(), 12, 01);
    private LocalDate maxDate = LocalDate.of(LocalDate.now().getYear()+1, 01, 14);

    public LocalDate getMinDate(){
        return minDate;
    }

    public LocalDate getMaxDate(){
        return maxDate;
    }
}
