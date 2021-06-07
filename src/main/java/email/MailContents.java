package email;

import DatabaseObjects.Tree;
import beans.CardDetailsBean;
import beans.CartBean;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.text.DecimalFormat;

@Dependent
public class MailContents implements Serializable {

    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    @Inject
    CartBean cartBean;

    @Inject
    CardDetailsBean cardDetailsBean;

    private String totalSpent() {
        return "You spent a total of: £" +
                decimalFormat.format(cartBean.getTotalAfterDiscount());
    }

    private String promotion(){
        return 	"You saved £"+
                decimalFormat.format(cartBean.getDiscount())+
                " today with our buy one get one half price offer!";
    }

    private String shippedTo(){
        String message = "Your order will be shipped to:\n"+
                cardDetailsBean.getFirstName()+" "+cardDetailsBean.getLastName()+"\n"+
                cardDetailsBean.getAddressLine1()+"\n"+
                cardDetailsBean.getCity()+"\n"+
                cardDetailsBean.getPostcode()+"\n"+
                cardDetailsBean.getCountry().getText();
        return message;
    }

    private String cartContents(){
        String contents = "Your order: \n";
        for(Tree tree : cartBean.getContents()){
            contents += tree.formattedMailToString()+"\n";
        }
        return contents;
    }

    public String buildMessage() {
        return  shippedTo()+
                "\n\n" + cartContents() +
                "\n\n" + totalSpent() +
                "\n\n" + promotion() +
                "\n\n Thank you for shopping with RentATree";
    }
}
