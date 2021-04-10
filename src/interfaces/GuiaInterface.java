package interfaces;

import java.util.ArrayList;

import modelos.Guia;

public interface GuiaInterface {
	public ArrayList<Guia> listado();

	public int registrar(Guia g);

	public int eliminar(Guia g);

	public int actualizar(Guia g);
}
