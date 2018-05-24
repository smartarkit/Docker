package co.edu.javeriana.aes.modval.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
@RequestMapping("orquestador/")
public class PaymentResource {
    @RequestMapping(path = "seleccion/{param}", method = RequestMethod.GET)
    public String getInfoFactura(@PathVariable("param") String param) {
    	
    	String resultado = "";
        
        String[] temp;
        String divisor = ",";
        temp = param.split(divisor);
        String tipoServicio = temp[0];
        
        if(("REST").equalsIgnoreCase(tipoServicio)) {   	
        	resultado = constructorServicioRest(param);       
    	}else if(("SOAP").equalsIgnoreCase(tipoServicio)){
    		resultado = constructorServicioSoap(param);	
    	}

         return resultado;
    
    }
    
    public String constructorServicioRest(String param) {
    	
    	String constructorServicioRest;
		
    	constructorServicioRest = "http://192.168.99.100:7078/conversor/rest/";
    	constructorServicioRest += param;
		
		String salida = null;
		try {

            URL url = new URL(constructorServicioRest);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error HTTP : código "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            salida = br.readLine();        
            conn.disconnect();

        } catch (Exception e) {
        	salida = "Error en el orquestador";

        }
		return salida;
    }
    
    public String constructorServicioSoap(String param) {
    	
    	String constructorServicioRest;
		
    	constructorServicioRest = "http://192.168.99.100:7173/serviciosoap/";
    	constructorServicioRest += param;
		
		String salida = null;
		try {

            URL url = new URL(constructorServicioRest);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error HTTP : código "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            salida = br.readLine();        
            conn.disconnect();

        } catch (Exception e) {
        	salida = "Error en el orquestador";

        }
		return salida;
    }

}