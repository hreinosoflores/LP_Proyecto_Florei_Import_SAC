package modelos;

public class Producto {
	private int cod_prod;
	private String marca;
	private String desc_prod;
	private String uni_med_prod;
	private int stk_prod;
	private double pre_unit;
	private double pes_unit;
	private int usu_creador_prod;

	public int getCod_prod() {
		return cod_prod;
	}

	public void setCod_prod(int cod_prod) {
		this.cod_prod = cod_prod;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDesc_prod() {
		return desc_prod;
	}

	public void setDesc_prod(String desc_prod) {
		this.desc_prod = desc_prod;
	}

	public String getUni_med_prod() {
		return uni_med_prod;
	}

	public void setUni_med_prod(String uni_med_prod) {
		this.uni_med_prod = uni_med_prod;
	}

	public int getStk_prod() {
		return stk_prod;
	}

	public void setStk_prod(int stk_prod) {
		this.stk_prod = stk_prod;
	}

	public double getPre_unit() {
		return pre_unit;
	}

	public void setPre_unit(double pre_unit) {
		this.pre_unit = pre_unit;
	}

	public double getPes_unit() {
		return pes_unit;
	}

	public void setPes_unit(double pes_unit) {
		this.pes_unit = pes_unit;
	}

	public int getUsu_creador_prod() {
		return usu_creador_prod;
	}

	public void setUsu_creador_prod(int usu_creador_prod) {
		this.usu_creador_prod = usu_creador_prod;
	}

	public String codigochar() {
		int codigo = cod_prod;
		if (codigo < 10)
			return "PR000" + codigo;
		else if (codigo < 100)
			return "PR00" + codigo;
		else if (codigo < 1000)
			return "PR0" + codigo;
		else
			return "PR" + codigo;
	}

	public Producto(int cod_prod, String marca, String desc_prod, String uni_med_prod, int stk_prod, double pre_unit,
			double pes_unit, int usu_creador_prod) {
		this.cod_prod = cod_prod;
		this.marca = marca;
		this.desc_prod = desc_prod;
		this.uni_med_prod = uni_med_prod;
		this.stk_prod = stk_prod;
		this.pre_unit = pre_unit;
		this.pes_unit = pes_unit;
		this.usu_creador_prod = usu_creador_prod;
	}

	public Producto() {
	}
	
	

}
