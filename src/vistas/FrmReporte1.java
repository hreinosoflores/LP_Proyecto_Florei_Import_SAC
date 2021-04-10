package vistas;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import mantenimientos.GestionReporte1;
import modelos.ReporteProducto;

public class FrmReporte1 extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox cboTipo;
	private JDateChooser txtInicio;
	private JDateChooser txtFin;
	private JTable tblReporte;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporte1 frame = new FrmReporte1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmReporte1() {
		setFrameIcon(new ImageIcon(FrmReporte1.class.getResource("/iconos/reporte.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de comprobantes por fecha");
		setBounds(100, 100, 640, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipo.setBounds(10, 24, 64, 14);
		contentPane.add(lblTipo);

		cboTipo = new JComboBox();
		cboTipo.setModel(new DefaultComboBoxModel(new String[] { "Seleccione", "Boleta", "Factura" }));
		cboTipo.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboTipo.setBounds(84, 18, 144, 20);
		contentPane.add(cboTipo);

		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio");
		lblFechaDeInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaDeInicio.setBounds(10, 70, 118, 14);
		contentPane.add(lblFechaDeInicio);

		JLabel lblFechaFin = new JLabel("Fecha de Fin");
		lblFechaFin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaFin.setBounds(265, 70, 101, 14);
		contentPane.add(lblFechaFin);

		txtInicio = new JDateChooser();
		txtInicio.setBounds(127, 64, 101, 20);
		contentPane.add(txtInicio);

		txtFin = new JDateChooser();
		txtFin.setBounds(376, 64, 101, 20);
		contentPane.add(txtFin);

		JButton btnPdf = new JButton("CREAR PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarPDF();
			}
		});
		btnPdf.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPdf.setIcon(new ImageIcon(FrmReporte1.class.getResource("/iconos/imprimir.png")));
		btnPdf.setBounds(237, 161, 164, 35);
		contentPane.add(btnPdf);

		JButton btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();

			}
		});
		btnLimpiar.setBounds(265, 15, 136, 37);
		contentPane.add(btnLimpiar);

		JButton btnMostrar = new JButton("MOSTRAR ");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listar();

			}
		});
		btnMostrar.setIcon(new ImageIcon(FrmReporte1.class.getResource("/iconos/reporte.png")));
		btnMostrar.setBounds(29, 162, 176, 35);
		contentPane.add(btnMostrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 220, 569, 239);
		contentPane.add(scrollPane);

		tblReporte = new JTable();
		scrollPane.setViewportView(tblReporte);

		modelo = new DefaultTableModel();
		modelo.addColumn("Nº Comprobante");
		modelo.addColumn("Registro en el sistema");
		modelo.addColumn("Nombre Cliente");
		modelo.addColumn("Producto");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Monto");
		tblReporte.setModel(modelo);
		MostrarFecha();
	}

	void listar() {
		String tipo = leertipo();
		String fec_ini = leerfec_ini();
		String fec_fin = leerfec_fin();

		GestionReporte1 gr = new GestionReporte1();
		ArrayList<ReporteProducto> lista = gr.listado(tipo, fec_ini, fec_fin);
		if (lista.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay datos para mostrar");
		} else {
			modelo.setRowCount(0);
			for (ReporteProducto r : lista) {
				Object[] fila = { r.getCodigo(), r.getRegistro(), r.getCliente(), r.getProducto(), r.getCant(),
						r.getPago() };
				modelo.addRow(fila);
			}
		}

	}

	void limpiar() {
		Date hoy = new Date();
		/* cboTipo.setSelectedIndex(0); */
		txtInicio.setDate(hoy);
		txtFin.setDate(hoy);
	}
	void MostrarFecha(){
		Date hoy = new Date();
		txtInicio.setDate(hoy);
		txtFin.setDate(hoy);}
	
	
	void GenerarPDF() {
		String tipo = leertipo();
		String fec_ini = leerfec_ini();
		String fec_fin = leerfec_fin();
		GestionReporte1 gr = new GestionReporte1();
		ArrayList<ReporteProducto> lista = gr.listado(tipo, fec_ini, fec_fin);
		if (lista.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay datos para imprimir");

		} else {
			try {
				Document doc = new Document();
				FileOutputStream fos = new FileOutputStream("ReporteComprobante.pdf");
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
				doc.add(new Paragraph("Reporte Comprobantes"));
				doc.add(new Paragraph("Tipo de comprobante: " + tipo + ", desde " + fec_ini + " hasta " + fec_fin));
				doc.add(new Paragraph(" "));
				PdfPTable tabla = new PdfPTable(6);
				tabla.addCell("Nº Comprobante");
				tabla.addCell("Registro");
				tabla.addCell("Cliente");
				tabla.addCell("Producto");
				tabla.addCell("Cantidad");
				tabla.addCell("Monto");
				for (ReporteProducto r : lista) {
					tabla.addCell(r.getCodigo() + "");
					tabla.addCell(r.getRegistro());
					tabla.addCell(r.getCliente());
					tabla.addCell(r.getProducto());
					tabla.addCell(r.getCant() + "");
					tabla.addCell(r.getPago() + "");
				}
				doc.add(tabla);
				Date fecha = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("'Lima,' dd 'de' MMMM 'del' y, HH:mm:ss");
				doc.add(new Paragraph(sdf.format(fecha)));

				/* fin del docuemento */
				doc.close();
				Desktop.getDesktop().open(new File("ReporteComprobante.pdf"));

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al crear pdf: " + e.getMessage());
			}

		}

	}

	void mostrarFecha() {

		Date fecha = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		txtInicio.setToolTipText(sdf.format(fecha));

	}

	String leerfec_ini() {
		try {
			String f = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			f = sdf.format(txtInicio.getDate());
			if (f != null)
				return f;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ingrese la fecha de inicio de consulta");
		}
		return null;
	}

	String leerfec_fin() {
		try {
			String f = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			f = sdf.format(txtFin.getDate());
			if (f != null)
				return f;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ingrese la fecha de fin de consulta");
		}
		return null;
	}

	String leertipo() {
		if (cboTipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Seleccione Tipo");
		}

		int tp = cboTipo.getSelectedIndex();
		switch (tp) {
		case 2:
			return "F";
		default:
			return "B";
		}
	}
}
