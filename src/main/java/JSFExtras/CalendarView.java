package JSFExtras;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;

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
