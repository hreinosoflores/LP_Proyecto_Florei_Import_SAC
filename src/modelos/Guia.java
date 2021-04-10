package modelos;

public class Guia {
	private int num_gui, cod_cli, cod_trans, cod_emp;
	private String fec_guia, fec_trasl, direc_part, direc_lleg, motiv_trasl;
	private double pes_tot;

	public int getNum_gui() {
		return num_gui;
	}

	public void setNum_gui(int num_gui) {
		this.num_gui = num_gui;
	}

	public String getFec_guia() {
		return fec_guia;
	}

	public void setFec_guia(String fec_guia) {
		this.fec_guia = fec_guia;
	}

	public String getFec_trasl() {
		return fec_trasl;
	}

	public void setFec_trasl(String fec_trasl) {
		this.fec_trasl = fec_trasl;
	}

	public String getDirec_part() {
		return direc_part;
	}

	public void setDirec_part(String direc_part) {
		this.direc_part = direc_part;
	}

	public String getDirec_lleg() {
		return direc_lleg;
	}

	public void setDirec_lleg(String direc_lleg) {
		this.direc_lleg = direc_lleg;
	}

	public String getMotiv_trasl() {
		return motiv_trasl;
	}

	public void setMotiv_trasl(String motiv_trasl) {
		this.motiv_trasl = motiv_trasl;
	}

	public int getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(int cod_cli) {
		this.cod_cli = cod_cli;
	}

	public int getCod_trans() {
		return cod_trans;
	}

	public void setCod_trans(int cod_trans) {
		this.cod_trans = cod_trans;
	}

	public int getCod_emp() {
		return cod_emp;
	}

	public void setCod_emp(int cod_emp) {
		this.cod_emp = cod_emp;
	}

	public double getPes_tot() {
		return pes_tot;
	}

	public void setPes_tot(double pes_tot) {
		this.pes_tot = pes_tot;
	}

	public String codigochar() {
		int codigo = num_gui;
		if (codigo < 10)
			return "N000" + codigo;
		else if (codigo < 100)
			return "N00" + codigo;
		else if (codigo < 1000)
			return "NP0" + codigo;
		else
			return "N" + codigo;
	}

}
