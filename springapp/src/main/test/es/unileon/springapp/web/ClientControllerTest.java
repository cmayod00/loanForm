package es.unileon.springapp.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.handler.DNIHandler;



public class ClientControllerTest {

    @Test
    public void testHandleRequestView() throws Exception{		
        ClientController controller = new ClientController();
        Client client = new Client(new DNIHandler("71560136Y"));
        client.setName("Carlos");
        client.setSurname("Mayo");
        controller.setClient(client);
        ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("client", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
	@SuppressWarnings("unchecked")
        Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel().get("model");
        Client clientReceived = (Client) modelMap.get("client");
        assertEquals("Carlos", clientReceived.getName());
        assertEquals("Mayo", clientReceived.getSurname());
    }
}
