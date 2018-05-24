package co.edu.javeriana.aes.modval.resources;

import co.servicios.pagos.schemas.ReferenciaFactura;
import co.servicios.pagos.schemas.ResultadoConsulta;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PaymentResource {

	
    @RequestMapping(value="/serviciosoap/{param}", method=RequestMethod.GET)
    @ResponseBody
    String consultarServicio(@PathVariable String param) {
              
        String[] temp;
        String divisor = ",";
        temp = param.split(divisor);
        String metodo = temp[4]; 
        String id = temp[5];   
        String valor = temp[6];

        
        String respuesta = "";
        
        if(("consultar".equalsIgnoreCase(metodo))){
            respuesta = consultar(id);
        }else if(("pagar".equalsIgnoreCase(metodo))){
            respuesta = pagar(  valor, id);
        }else{
            respuesta = compensar(valor, id);
        }

        return respuesta;

    }
    
    public String consultar(String id){
        
        ReferenciaFactura ref = new ReferenciaFactura();
        ref.setReferenciaFactura(id);
        co.edu.javeriana.aes.modval.pagos.boundary.PagosServiceService service = new co.edu.javeriana.aes.modval.pagos.boundary.PagosServiceService();
        co.edu.javeriana.aes.modval.pagos.boundary.PagosInerface port = service.getPagosServicePort();
        
        ResultadoConsulta resultado = new ResultadoConsulta();     
        resultado = port.cosultar(ref);
        String respuesta = String.valueOf(resultado.getTotalPagar());
       
        return "Total a pagar:" + respuesta ;
        
    }
    
    public String pagar( String valor,String id){
        
        String respuesta = "";
          
        try { // Call Web Service Operation
            co.edu.javeriana.aes.modval.pagos.boundary.PagosServiceService service = new co.edu.javeriana.aes.modval.pagos.boundary.PagosServiceService();
            co.edu.javeriana.aes.modval.pagos.boundary.PagosInerface port = service.getPagosServicePort();
            // TODO initialize WS operation arguments here
            co.servicios.pagos.schemas.Pago input = new co.servicios.pagos.schemas.Pago();
            ReferenciaFactura referencia = new ReferenciaFactura();
            referencia.setReferenciaFactura(id);
            input.setReferenciaFactura(referencia);
            double totalPagar = Double.parseDouble(valor);
            input.setTotalPagar(totalPagar);
            co.servicios.pagos.schemas.Resultado result = port.pagar(input);
            
            respuesta = "Mensaje: " + result.getMensaje() + " Referencia factura: " + result.getReferenciaFactura().getReferenciaFactura();
            
        } catch (Exception ex) {
            respuesta = "Error al pagar";
        }

      return respuesta;
    

    }
    
    
    public String compensar( String valor,String id){
        
       String respuesta = "";
       
       
       try { // Call Web Service Operation
           co.edu.javeriana.aes.modval.pagos.boundary.PagosServiceService service = new co.edu.javeriana.aes.modval.pagos.boundary.PagosServiceService();
           co.edu.javeriana.aes.modval.pagos.boundary.PagosInerface port = service.getPagosServicePort();
           // TODO initialize WS operation arguments here
           co.servicios.pagos.schemas.Pago input = new co.servicios.pagos.schemas.Pago();
           
           ReferenciaFactura referencia = new ReferenciaFactura();
           referencia.setReferenciaFactura(id);
           input.setReferenciaFactura(referencia);
           double totalPagar = Double.parseDouble(valor);
           input.setTotalPagar(totalPagar);
      
           co.servicios.pagos.schemas.Resultado result = port.compensar(input);
        
           respuesta = "Mensaje: " + result.getMensaje() + " Referencia factura: " + result.getReferenciaFactura().getReferenciaFactura();
       } catch (Exception ex) {
           respuesta = "Error al compensar";
       }

     
        return respuesta; 

    }


	

   
}