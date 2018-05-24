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
@RequestMapping("conversor/")
public class PaymentResource {
    @RequestMapping(path = "rest/{param}", method = RequestMethod.GET)
    public String getInfoFactura(@PathVariable("param") String param) {
    	
    
    	String[] temp;
        String divisor = ",";
        temp = param.split(divisor);
        String puerto = temp[2]; 
        String ruta = temp[3];
        String rutaCambiar = ruta.replace("'", "/");
        String valor = temp[6];
        String ip = temp[7];
        String ipCambiar = ip.replace("_", ".");
        
    	
    	String constructorServicioRest = "";
    	constructorServicioRest += "http://";
    	constructorServicioRest += ipCambiar + ":";
    	constructorServicioRest += puerto;
    	constructorServicioRest += rutaCambiar;
    	constructorServicioRest += valor;
		
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
        	salida = "Error en transformador rest";
        	e.printStackTrace();

        }

        
		return salida;
  
    }
 

}