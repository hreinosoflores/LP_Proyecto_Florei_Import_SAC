package hilos;

import java.text.SimpleDateFormat;
import java.util.Date;

import vistas.FrmPrincipal;

public class HiloFunciona extends Thread {

	public void run() {
		while (true) {
			Date hora = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			FrmPrincipal.mnHoraDelDia.setText(sdf.format(hora));
		}
	}
}
