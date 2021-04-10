package modelos;

public class ComprobantePago {
	private int num_comp, cod_cli;
	private String fec_comp, hor_comp, tip_comp, lug_comp;

	public int getNum_comp() {
		return num_comp;
	}

	public void setNum_comp(int num_comp) {
		this.num_comp = num_comp;
	}

	public String getFec_comp() {
		return fec_comp;
	}

	public void setFec_comp(String fec_comp) {
		this.fec_comp = fec_comp;
	}

	public String getHor_comp() {
		return hor_comp;
	}

	public void setHor_comp(String hor_comp) {
		this.hor_comp = hor_comp;
	}

	public String getTip_comp() {
		return tip_comp;
	}

	public void setTip_comp(String tip_comp) {
		this.tip_comp = tip_comp;
	}

	public String getLug_comp() {
		return lug_comp;
	}

	public void setLug_comp(String lug_comp) {
		this.lug_comp = lug_comp;
	}

	public int getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(int cod_cli) {
		this.cod_cli = cod_cli;
	}

	public String codigochar() {
		int codigo = num_comp;
		if (codigo < 10)
			return "COM000" + codigo;
		else if (codigo < 100)
			return "COM00" + codigo;
		else if (codigo < 1000)
			return "COM0" + codigo;
		else
			return "COM" + codigo;
	}
}
