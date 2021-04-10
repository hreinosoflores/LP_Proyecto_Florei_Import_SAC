package interfaces;

import java.util.ArrayList;

import modelos.Producto;


public interface ReporteProductoInterface {

	public ArrayList<Producto> listado(String tip_uni, int stock1, int stock2,String desc_cont);
}
