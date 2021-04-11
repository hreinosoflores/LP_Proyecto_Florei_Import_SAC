package modelos;

import java.sql.Date;

public class ComprobantePago {
	private int num_comp, cod_cli, usu_creador_comp;
	private String tip_comp, lug_comp;
	private Date fec_comp;

	public int getNum_comp() {
		return num_comp;
	}

	public void setNum_comp(int num_comp) {
		this.num_comp = num_comp;
	}

	public int getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(int cod_cli) {
		this.cod_cli = cod_cli;
	}

	public int getUsu_creador_comp() {
		return usu_creador_comp;
	}

	public void setUsu_creador_comp(int usu_creador_comp) {
		this.usu_creador_comp = usu_creador_comp;
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

	public Date getFec_comp() {
		return fec_comp;
	}

	public void setFec_comp(Date fec_comp) {
		this.fec_comp = fec_comp;
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

	public ComprobantePago(int num_comp, int cod_cli, int usu_creador_comp, String tip_comp, String lug_comp,
			Date fec_comp) {
		this.num_comp = num_comp;
		this.cod_cli = cod_cli;
		this.usu_creador_comp = usu_creador_comp;
		this.tip_comp = tip_comp;
		this.lug_comp = lug_comp;
		this.fec_comp = fec_comp;
	}

	public ComprobantePago() {
	}

}
