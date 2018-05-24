package co.edu.javeriana.aes.modval.resources;

import co.edu.javeriana.aes.modval.entities.ParametrosServicio;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class PaymentResource {

	/**
	 * Consultar servicio REST
	 */
	@RequestMapping(value="/servicio/{serviciopagar}/{metodo}/{idfactura}/{valor}", method=RequestMethod.GET)
    @ResponseBody
    String consultarServicio(@PathVariable 	String serviciopagar, @PathVariable String metodo, @PathVariable String idfactura, @PathVariable String valor) {
		
		ParametrosServicio servicio = new ParametrosServicio();
		servicio.setNombre(serviciopagar);
		servicio.setMetodo(metodo);
		servicio.setIdfactura(idfactura);
		servicio.setValor(valor);
		
		consultarBaseDatos(servicio);
		
		String resultado = llamarServicioOrquestacion(servicio);
		
		return resultado;
    }
	
	public void consultarBaseDatos(ParametrosServicio servicio) {
		try
	    {
	      
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://192.168.99.100/blogapp?useUnicode=true&characterEncoding=UTF8";
	      Class.forName(myDriver);
	      
	      Connection conn = DriverManager.getConnection(myUrl, "root", "password");
	      
	      String query = "SELECT * FROM servicio";
	     
	      Statement st = conn.createStatement();
	      
	      ResultSet rs = st.executeQuery(query);

	      
	      while (rs.next())
	      {
	    	  	  
	    	if(rs.getString("nombre").equalsIgnoreCase(servicio.getNombre())) {
	    		servicio.setNombre(rs.getString("nombre"));
	    		servicio.setIp(rs.getString("ip"));
	    		servicio.setPuerto(rs.getString("puerto"));
	    		servicio.setRuta(rs.getString("ruta"));
	    		servicio.setTipo(rs.getString("tipo"));
	    	}
	       
	      }
	      st.close();
	    }
		
	    catch (Exception e)
	    {
	      System.err.println(e.getMessage());
	    }
		
	}
	
	public String llamarServicioOrquestacion(ParametrosServicio servicio) {
		

		
        String llamarorquestador;
		
		llamarorquestador = "http://192.168.99.100:7077/orquestador/seleccion/";
		
		llamarorquestador += servicio.getTipo() +",";
		llamarorquestador += servicio.getNombre() +",";
		llamarorquestador += servicio.getPuerto() +",";
		llamarorquestador += servicio.getRuta() +",";
		llamarorquestador += servicio.getMetodo() +",";
		llamarorquestador += servicio.getIdfactura() +",";
		llamarorquestador += servicio.getValor() +",";
		llamarorquestador += servicio.getIp() ;

		
				
		String salida = null;
		try {

            URL url = new URL(llamarorquestador);
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
        	salida = "Excepción de servicio ";
        }
		 return salida;
		
	}
	
	

   
}