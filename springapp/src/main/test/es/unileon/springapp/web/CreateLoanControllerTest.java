package es.unileon.springapp.web;

import static org.junit.Assert.*;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.handler.DNIHandler;

public class CreateLoanControllerTest {

	@Test
    public void testHandleRequestView() throws Exception{		
        CreateLoanController controller = new CreateLoanController();
			

    }

}
