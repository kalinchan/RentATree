package validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("searchValidation")
public class SearchValidation implements Validator<Object> {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Object searchItem = component.getAttributes().get("searchItem");
		String validateValue = value.toString();
		System.out.println("Validate called");
		if (searchItem == null) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Search item is null", "Empty Value"));
		}
		if (validateValue.isEmpty())
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value cannot be empty", "Empty Value"));

		if (searchItem.equals("material") | searchItem.equals("type")) {
			if (!validateValue.matches("[a-zA-Z]+")) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Search term can only contain letters", "Letters only"));
			}
		}

		if (searchItem.equals("Height")) {
			if (!validateValue.matches("[0-9]+")) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Search term can only contain numbers", "Numbers only"));
			}
		}
		
		if (searchItem.equals("Available")) {
			if (isStringBoolean(validateValue) == false) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Boolean value only", "Boolean only"));
			}
		}
	}
	
	
	private boolean isStringBoolean(String bool) {
		if(bool.equalsIgnoreCase("true") || bool.equalsIgnoreCase("false")) {
			return true;
		}
		return false;
	}
}
