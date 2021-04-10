package interfaces;

import java.util.ArrayList;

import modelos.Transportista;

public interface TransportistaInterface {

	public int registrar(Transportista trans);

	public ArrayList<Transportista> listado();

	public int eliminar(Transportista trans);

	public int actualizar(Transportista trans);

}
