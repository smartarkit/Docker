package co.edu.javeriana.aes.modaval.resources.pagos.boundary;

import co.edu.javeriana.aes.modaval.resources.pagos.entity.Factura;
import co.edu.javeriana.aes.modaval.resources.pagos.entity.Resultado;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@Path("payments")
public class PaymentResource {

    @GET
    @Path("{idFactura}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getInfoFactura(@PathParam("idFactura") int idFactura) {
        return "mierda";
    }
}
