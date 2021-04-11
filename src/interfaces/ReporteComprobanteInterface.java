package interfaces;

import java.util.ArrayList;

import modelos.ReporteComprobante;

public interface ReporteComprobanteInterface {

	public ArrayList<ReporteComprobante> listado(String ty, String fec_ini, String fec_fin);

}
