package modelos;

public class Producto {
	private int cod_prod, stk_prod;
	private String marca, desc_prod, uni_med_prod;
	private double pre_unit;

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

	public int getCod_prod() {
		return cod_prod;
	}

	public void setCod_prod(int cod_prod) {
		this.cod_prod = cod_prod;
	}

	public double getPre_unit() {
		return pre_unit;
	}

	public void setPre_unit(double pre_unit) {
		this.pre_unit = pre_unit;
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}
