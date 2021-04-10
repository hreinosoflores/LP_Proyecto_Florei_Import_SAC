package interfaces;

import java.util.ArrayList;

import modelos.Empresa;

public interface EmpresaInterface {

	public int registrar(Empresa e);

	public ArrayList<Empresa> listado();

	public int eliminar(Empresa e);

	public int actualizar(Empresa e);

}
