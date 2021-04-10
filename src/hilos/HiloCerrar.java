package hilos;

import javax.swing.JFrame;

import vistas.FrmLogueo;

public class HiloCerrar extends Thread {
	private JFrame ventana;

	public HiloCerrar(JFrame ventana) {
		this.ventana = ventana;
	}

	public void run() {
		for (int i = 30; i >= 0; i--) {
			FrmLogueo.lblTiempo.setText(i + "s");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Problema en el sleep");
			}
		}
		ventana.dispose();
	}

}
