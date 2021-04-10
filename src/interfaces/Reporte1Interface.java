package interfaces;

import java.util.ArrayList;

import modelos.ReporteProducto;

public interface Reporte1Interface {
	ArrayList<ReporteProducto> listado(String tipo, String fec_ini, String fec_fin);

}
