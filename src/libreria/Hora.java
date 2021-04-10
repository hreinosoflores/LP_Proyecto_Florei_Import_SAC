package libreria;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;

public class Hora {

	public static void setHoras(JComboBox<String> cbo) {
		for (int i = 0; i < 24; i++)
			cbo.addItem(String.format("%02d", i));
	}

	public static void setMinutos(JComboBox<String> cbo) {
		for (int i = 0; i < 60; i++)
			cbo.addItem(String.format("%02d", i));
	}

	public static void setSegundos(JComboBox<String> cbo) {
		for (int i = 0; i < 60; i++)
			cbo.addItem(String.format("%02d", i));
	}

	public static void setHora(JComboBox<String> hor, JComboBox<String> min, JComboBox<String> seg) {
		Calendar c = new GregorianCalendar();
		hor.setSelectedIndex(c.get(Calendar.HOUR_OF_DAY));
		min.setSelectedIndex(c.get(Calendar.MINUTE));
		seg.setSelectedIndex(c.get(Calendar.SECOND));
	}

	public static void setHora(JComboBox<String> hor, JComboBox<String> min, JComboBox<String> seg, String s) {
		hor.setSelectedItem(s.substring(0, 2));
		min.setSelectedItem(s.substring(3));
		seg.setSelectedItem(s.substring(3));
	}

	public static String getHora(JComboBox<String> hor, JComboBox<String> min, JComboBox<String> seg) {
		return String.format("%02d:", hor.getSelectedIndex()) + String.format("%02d:", min.getSelectedIndex())
				+ String.format("%02d", seg.getSelectedIndex());
	}

}
