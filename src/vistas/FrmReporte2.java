package vistas;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimientos.GestionReporte2;
import modelos.Reporte2;

public class FrmReporte2 extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporte2 frame = new FrmReporte2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JComponent contentPane;
	private JTable tblResultado;
	private JComboBox cboTipo;

	/**
	 * Create the frame.
	 */
	public FrmReporte2() {
		setTitle("Reporte por Documento");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setFrameIcon(new ImageIcon(FrmReporte2.class.getResource("/iconos/clientedestinatario.png")));
		try {
			setIcon(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBounds(100, 100, 623, 387);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTipoDeCliente = new JLabel("Tipo de documento");
		lblTipoDeCliente.setIcon(new ImageIcon(FrmReporte2.class.getResource("/iconos/clientedestinatario.png")));
		lblTipoDeCliente.setBounds(10, 6, 162, 54);
		contentPane.add(lblTipoDeCliente);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 587, 256);
		contentPane.add(scrollPane);

		tblResultado = new JTable();
		tblResultado.setModel(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Tipo documento");
		modelo.addColumn("Numero documento");
		modelo.addColumn("RUC");
		modelo.addColumn("Direccion");
		modelo.addColumn("Telefono");
		modelo.addColumn("Email");

		scrollPane.setViewportView(tblResultado);

		cboTipo = new JComboBox();
		cboTipo.setModel(new DefaultComboBoxModel(new String[] { "Seleccione\t", "DNI", "CARNET DE EXTRANJERIA" }));
		cboTipo.setBounds(182, 23, 187, 20);
		contentPane.add(cboTipo);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (cboTipo.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Por favor seleccione Tipo");
				}

				consultar(cboTipo.getSelectedItem().toString());

			}
		});
		btnConsultar.setBounds(182, 54, 102, 25);
		contentPane.add(btnConsultar);

		JButton btnPdf = new JButton("Imprimir PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generarPDF(cboTipo.getSelectedItem().toString());
			}
		});
		btnPdf.setIcon(new ImageIcon(FrmReporte2.class.getResource("/iconos/imprimir.png")));
		btnPdf.setBounds(400, 23, 161, 46);
		contentPane.add(btnPdf);

		/* llenacombo(); */

	}

	/*
	 * public void llenacombo(){ GestionReporte2 gr=new GestionReporte2();
	 * ArrayList<Reporte2>listado=gr.listado(); cboTipo.addItem("Seleccione");
	 * for (Reporte2 r : listado) { cboTipo.addItem(r.getTip_doc()); } }
	 */

	String leerTipo() {

		int tipo = 0;
		tipo = cboTipo.getSelectedIndex();
		switch (tipo) {
		case 1:
			return "dni";
		case 2:
			return "cex";
		default:
			return null;
		}

	}

	public void generarPDF(String tipo) {

		GestionReporte2 gr = new GestionReporte2();
		ArrayList<Reporte2> lista = gr.listado(tipo = leerTipo());
		if (lista.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay datos para imprimir");

		} else {
			try {
				Document doc = new Document();
				FileOutputStream fos = new FileOutputStream("ReporteCliente.pdf");
				PdfWriter escritor = PdfWriter.getInstance(doc, fos);
				escritor.setInitialLeading(50);
				doc.open();
				/* contenido del documento */
				doc.add(new Paragraph(" "));
				doc.add(new Paragraph(" "));
				doc.add(new Paragraph(" "));
				Image logo = Image.getInstance("src/iconos/FloreiLogo.png");
				logo.scaleToFit(40, 40);
				logo.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(logo);
				doc.add(new Paragraph("Florei Import S.A.C.",
						FontFactory.getFont("arial", 30, Font.ITALIC, BaseColor.GREEN)));
				doc.add(new Paragraph(" "));
				doc.add(new Paragraph("Reporte de Clientes"));
				doc.add(new Paragraph(" "));
				PdfPTable tabla = new PdfPTable(9);
				tabla.addCell("Codigo de cliente");
				tabla.addCell("Nombre");
				tabla.addCell("Apellido");
				tabla.addCell("Tipo documento");
				tabla.addCell("Número documento");
				tabla.addCell("RUC");
				tabla.addCell("Dirección");
				tabla.addCell("Teléfono");
				tabla.addCell("Email");
				for (Reporte2 r : lista) {
					tabla.addCell(r.getCod_cli() + "");
					tabla.addCell(r.getNom_cli());
					tabla.addCell(r.getApe_cli());
					tabla.addCell(r.getTip_doc());
					tabla.addCell(r.getNum_doc());
					tabla.addCell(r.getRuc_cli());
					tabla.addCell(r.getDirec_cli());
					tabla.addCell(r.getTelef_cli());
					tabla.addCell(r.getEmail_cli());
				}
				doc.add(tabla);
				Date fecha = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("'Lima,' dd 'de' MMMM 'del' y, HH:mm:ss");
				doc.add(new Paragraph(sdf.format(fecha)));

				/* fin del docuemento */
				doc.close();
				Desktop.getDesktop().open(new File("ReporteCliente.pdf"));

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al crear pdf: " + e.getMessage());
			}

		}

	}

	DefaultTableModel modelo = new DefaultTableModel();

	public void consultar(String tipo) {

		GestionReporte2 gr = new GestionReporte2();
		ArrayList<Reporte2> listado = gr.listado(tipo = leerTipo());
		modelo.setRowCount(0);
		for (Reporte2 r : listado) {
			Object[] datos = { r.getCod_cli(), r.getNom_cli(), r.getApe_cli(), r.getTip_doc(), r.getNum_doc(),
					r.getRuc_cli(), r.getDirec_cli(), r.getDirec_cli(), r.getTelef_cli(), r.getEmail_cli()

			};
			modelo.addRow(datos);
		}

	}

}
