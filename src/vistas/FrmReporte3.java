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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimientos.GestionReporte3;
import modelos.Reporte3;

public class FrmReporte3 extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCantidad;
	private JTextField txtStk1;
	private JTextField txtStk2;
	private JTable tblResultado;
	private DefaultTableModel modelo;
	private JComboBox<Object> cboTipoUni;
	private JButton btnImprimirPdf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporte3 frame = new FrmReporte3();
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
	public FrmReporte3() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setFrameIcon(new ImageIcon(FrmReporte3.class.getResource("/iconos/producto.png")));
		setTitle("Reporte de Producto en Stock");
		setBounds(100, 100, 729, 467);
		getContentPane().setLayout(null);

		JLabel lblTipUni = new JLabel("Tipo de Uni Med");
		lblTipUni.setBounds(10, 35, 107, 14);
		getContentPane().add(lblTipUni);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 75, 65, 14);
		getContentPane().add(lblCantidad);

		JLabel lblEntre = new JLabel("Entre");
		lblEntre.setBounds(201, 75, 46, 14);
		getContentPane().add(lblEntre);

		txtStk1 = new JTextField();
		txtStk1.setBounds(134, 72, 46, 20);
		getContentPane().add(txtStk1);
		txtStk1.setColumns(10);

		txtStk2 = new JTextField();
		txtStk2.setBounds(246, 72, 46, 20);
		getContentPane().add(txtStk2);
		txtStk2.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 133, 668, 278);
		getContentPane().add(scrollPane);

		tblResultado = new JTable();
		scrollPane.setViewportView(tblResultado);
		modelo = new DefaultTableModel();
		tblResultado.setModel(modelo);

		modelo.addColumn("Cod Prod");
		modelo.addColumn("Marca");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Uni Med Prod");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Precio Uni");

		cboTipoUni = new JComboBox<Object>();
		cboTipoUni.setModel(new DefaultComboBoxModel<Object>(new String[] { "Seleccione", "Unidad", "Caja" }));
		cboTipoUni.setBounds(134, 32, 93, 20);
		getContentPane().add(cboTipoUni);

		JButton btnMostrar = new JButton("MOSTRAR ");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Consultar(leerCombo(), stk1(), stk2());

			}
		});
		btnMostrar.setIcon(new ImageIcon(FrmReporte3.class.getResource("/iconos/reporte.png")));
		btnMostrar.setBounds(331, 65, 158, 35);
		getContentPane().add(btnMostrar);

		btnImprimirPdf = new JButton("Imprimir PDF");
		btnImprimirPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ImprimirPDF(leerCombo(), stk1(), stk2());
			}
		});
		btnImprimirPdf.setIcon(new ImageIcon(FrmReporte3.class.getResource("/iconos/imprimir.png")));
		btnImprimirPdf.setBounds(511, 65, 165, 35);
		getContentPane().add(btnImprimirPdf);

	}

	public void Consultar(String tip, int stk1, int stk2) {

		GestionReporte3 gr = new GestionReporte3();
		ArrayList<Reporte3> lista = gr.listaCbo(tip, stk1, stk2);

		modelo.setRowCount(0);
		if (lista.size() > 0) {
			for (Reporte3 r3 : lista) {
				Object[] datos = { r3.getCod_prod(), r3.getDesc_prod(), r3.getMarca(), r3.getUni_med_prod(),
						r3.getStk_prod(), "S/." + r3.getPre_unit()

				};
				modelo.addRow(datos);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Registro Vacio");
		}

	}

	String leerCombo() {
		if (cboTipoUni.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione Tipo");
		}
		int tp = cboTipoUni.getSelectedIndex();
		switch (tp) {
		case 1:
			return "U";
		case 2:
			return "C";
		default:
			return null;
		}

	}

	int stk1() {
		int s=-1;
		try {
			s= Integer.parseInt(txtStk1.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese un valor entero en el rango de inicio");
		}
		return s;	

	}

	int stk2() {

		int s=-1;
		try {
			s= Integer.parseInt(txtStk2.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese un valor entero en el rango final");
		}
		return s;	

	}

	void ImprimirPDF(String tip, int stk1, int stk2) {

		GestionReporte3 gr = new GestionReporte3();
		ArrayList<Reporte3> lista = gr.listaCbo(tip, stk1, stk2);
		if (lista.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay datos para imprimir");

		} else {
			try {
				Document doc = new Document();
				FileOutputStream fos = new FileOutputStream("ReporteProducto.pdf");
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
				doc.add(new Paragraph("Reporte de Productos"));
				doc.add(new Paragraph(" "));
				PdfPTable tabla = new PdfPTable(6);
				tabla.addCell("Cod Prod");
				tabla.addCell("Marca");
				tabla.addCell("Descripcion");
				tabla.addCell("Uni Med Prod");
				tabla.addCell("Cantidad");
				tabla.addCell("Precio Uni");

				for (Reporte3 r3 : lista) {
					tabla.addCell(r3.getCod_prod() + "");
					tabla.addCell(r3.getMarca());
					tabla.addCell(r3.getDesc_prod());
					tabla.addCell(r3.getUni_med_prod());
					tabla.addCell(r3.getStk_prod() + "");
					tabla.addCell(r3.getPre_unit() + "");
				}
				doc.add(tabla);
				Date fecha = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("'Lima,' dd 'de' MMMM 'del' y, HH:mm:ss");
				doc.add(new Paragraph(sdf.format(fecha)));

				/* fin del docuemento */
				doc.close();
				Desktop.getDesktop().open(new File("ReporteProducto.pdf"));

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al crear pdf: " + e.getMessage());
			}

		}

	}
}
