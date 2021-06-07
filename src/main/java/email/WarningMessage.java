package email;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class WarningMessage {
	@Inject
	MailHandler mailHandler;

	public void sendWarning(String email, String forename) {
		String warningMessage = String.format(
				"Hi %s, \n\nYou have failed to successfully return / pickup tree(s) 2 times. \n\nIf this is repeated once more, your account will be banned.",
				forename);
		mailHandler.sendMail(email, "Warning!", warningMessage);
	}

}
