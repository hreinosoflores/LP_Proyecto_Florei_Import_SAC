package modelos;

public class DetalleComp {
	private int num_comp, cod_prod, cant;

	public int getNum_comp() {
		return num_comp;
	}

	public void setNum_comp(int num_comp) {
		this.num_comp = num_comp;
	}

	public int getCod_prod() {
		return cod_prod;
	}

	public void setCod_prod(int cod_prod) {
		this.cod_prod = cod_prod;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public DetalleComp(int num_comp, int cod_prod, int cant) {
		this.num_comp = num_comp;
		this.cod_prod = cod_prod;
		this.cant = cant;
	}

	public DetalleComp() {
	}

}
