package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;

import java.awt.Component;

import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Seminario2 {

	private JFrame frame;
	private final JPanel pnlFicha = new JPanel();
	private final JLabel lblExpediente = new JLabel("Num. expediente:");
	private final JTextField txtExpediente = new JTextField();
	private final JButton btnCargarFoto = new JButton("Cargar foto..");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblNombre = new JLabel("Nombre:");
	private final JLabel lblApellidos = new JLabel("Apellidos:");
	private final JLabel lblDni = new JLabel("DNI:");
	private final JLabel lblTelefono = new JLabel("Telefono:");
	private final JLabel lblFoto = new JLabel("");
	private final JTextField txtNombre = new JTextField();
	private final JTextField txtApellidos = new JTextField();
	private final JButton btnCargarComentarios = new JButton("Cargar Comentarios");
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JButton btnGuardar = new JButton("Guardar");
	private final JTextArea txtArComentarios = new JTextArea();
	private final JLabel lblCheck = new JLabel("");
	
	/* Quitamos los final porque vamos a modificar la estructura del atributo */
	private JFormattedTextField txtDNI = new JFormattedTextField();
	private JFormattedTextField txtTelefono = new JFormattedTextField();
	
	private Border bordeRojo = BorderFactory.createLineBorder(Color.RED);
	private Border bordeVerde = BorderFactory.createLineBorder(Color.GREEN);
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mArchivo = new JMenu("Archivo");
	private final JMenuItem miAbrir = new JMenuItem("Abrir...");
	private final JMenuItem miCargarFoto = new JMenuItem("Cargar foto...");
	private final JMenuItem miCargarComentarios = new JMenuItem("Cargar Comentarios...");
	private final JSeparator separator = new JSeparator();
	private final JMenuItem miGuardar = new JMenuItem("Guardar");
	private final JMenu mEdicion = new JMenu("Edicion");
	private final JMenu mAyuda = new JMenu("Ayuda");
	private final JMenu mTamanoLetra = new JMenu("Tamano Letra");
	private final JRadioButtonMenuItem radioPequena = new JRadioButtonMenuItem("Pequena");
	private final JRadioButtonMenuItem radioNormal = new JRadioButtonMenuItem("Normal");
	private final JRadioButtonMenuItem radioGrande = new JRadioButtonMenuItem("Grande");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JMenuItem miAcercaDe = new JMenuItem("Acerca de...");
	private final JSeparator separator_1 = new JSeparator();
	private final JMenuItem miSalir = new JMenuItem("Salir");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Seminario2 window = new Seminario2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Seminario2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/* Inicializamos las acciones de los distintos elementos */
		/* Ahora los campos */
		txtExpediente.addFocusListener(new MyFocusListener());
		txtNombre.addFocusListener(new MyFocusListener());
		txtApellidos.addFocusListener(new MyFocusListener());
		txtDNI.addFocusListener(new MyFocusListener());
		txtTelefono.addFocusListener(new MyFocusListener());
		
		/* Ahora el TextArea */
		txtArComentarios.addFocusListener(new MyFocusListener());
		
		/* Adding ActionListener to the menu actions (Cargar Foto y Cargar Comentarios) */
		miCargarFoto.addActionListener(new BtnCargarFotoActionListener());
		miCargarComentarios.addActionListener(new BtnCargarComentariosActionListener());
		
		/* Modificamos la actuacion del Jformat en el telefono */
		MaskFormatter formatoTlfno;
		try{
			formatoTlfno = new MaskFormatter("'(###')' ### '- ### '- ###");
			formatoTlfno.setPlaceholderCharacter('*');
			txtTelefono = new JFormattedTextField(formatoTlfno);
		}catch (ParseException e){
			e.printStackTrace();
		}
		
		/* Modificamos la actuacion del Jformat en el dni */
		MaskFormatter formatoDNI;
		try{
			formatoDNI = new MaskFormatter("########' -U");
			formatoDNI.setPlaceholderCharacter('_');
			txtDNI = new JFormattedTextField(formatoDNI);
		}catch (ParseException e){
			e.printStackTrace();
		}
		
		txtApellidos.setColumns(10);
		txtNombre.setColumns(10);
		frame = new JFrame();
		frame.addWindowListener(new FrameWindowListener());
		frame.setBounds(100, 100, 483, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		{
			pnlFicha.setBounds(-4, 0, 470, 450);
			pnlFicha.setBorder(new TitledBorder(null, "Ficha Alumno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			frame.getContentPane().add(pnlFicha);
		}
		GridBagLayout gbl_pnlFicha = new GridBagLayout();
		gbl_pnlFicha.columnWidths = new int[]{74, 36, 49, 106, 108, 0};
		gbl_pnlFicha.rowHeights = new int[]{42, 35, 31, 35, 34, 33, 34, 0, 0, 0};
		gbl_pnlFicha.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlFicha.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		pnlFicha.setLayout(gbl_pnlFicha);
		{
			GridBagConstraints gbc_lblExpediente = new GridBagConstraints();
			gbc_lblExpediente.anchor = GridBagConstraints.WEST;
			gbc_lblExpediente.insets = new Insets(0, 0, 5, 5);
			gbc_lblExpediente.gridx = 0;
			gbc_lblExpediente.gridy = 0;
			pnlFicha.add(lblExpediente, gbc_lblExpediente);
		}
		txtExpediente.addKeyListener(new TxtExpedienteKeyListener());
		txtExpediente.setColumns(10);
		{
			GridBagConstraints gbc_txtExpediente = new GridBagConstraints();
			gbc_txtExpediente.gridwidth = 2;
			gbc_txtExpediente.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtExpediente.insets = new Insets(0, 0, 5, 5);
			gbc_txtExpediente.gridx = 1;
			gbc_txtExpediente.gridy = 0;
			pnlFicha.add(txtExpediente, gbc_txtExpediente);
		}
		{
			GridBagConstraints gbc_lblCheck = new GridBagConstraints();
			gbc_lblCheck.insets = new Insets(0, 0, 5, 5);
			gbc_lblCheck.gridx = 3;
			gbc_lblCheck.gridy = 0;
			pnlFicha.add(lblCheck, gbc_lblCheck);
		}
		{
			GridBagConstraints gbc_btnCargarFoto = new GridBagConstraints();
			gbc_btnCargarFoto.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnCargarFoto.gridwidth = 2;
			gbc_btnCargarFoto.insets = new Insets(0, 0, 5, 5);
			gbc_btnCargarFoto.gridx = 0;
			gbc_btnCargarFoto.gridy = 1;
			btnCargarFoto.addActionListener(new BtnCargarFotoActionListener());
			btnCargarFoto.setIcon(new ImageIcon(Seminario2.class.getResource("/presentacion/cargarFoto.png")));
			pnlFicha.add(btnCargarFoto, gbc_btnCargarFoto);
		}
		{
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 2;
			gbc_scrollPane.gridheight = 4;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 2;
			pnlFicha.add(scrollPane, gbc_scrollPane);
		}
		{
			lblFoto.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
			lblFoto.setIcon(new ImageIcon(Seminario2.class.getResource("/presentacion/111.png")));
			scrollPane.setViewportView(lblFoto);
		}
		{
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.WEST;
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 2;
			gbc_lblNombre.gridy = 2;
			pnlFicha.add(lblNombre, gbc_lblNombre);
		}
		{
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.gridwidth = 2;
			gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 3;
			gbc_txtNombre.gridy = 2;
			pnlFicha.add(txtNombre, gbc_txtNombre);
		}
		{
			GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
			gbc_lblApellidos.anchor = GridBagConstraints.WEST;
			gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
			gbc_lblApellidos.gridx = 2;
			gbc_lblApellidos.gridy = 3;
			pnlFicha.add(lblApellidos, gbc_lblApellidos);
		}
		{
			GridBagConstraints gbc_txtApellidos = new GridBagConstraints();
			gbc_txtApellidos.gridwidth = 2;
			gbc_txtApellidos.insets = new Insets(0, 0, 5, 0);
			gbc_txtApellidos.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtApellidos.gridx = 3;
			gbc_txtApellidos.gridy = 3;
			pnlFicha.add(txtApellidos, gbc_txtApellidos);
		}
		{
			GridBagConstraints gbc_lblDni = new GridBagConstraints();
			gbc_lblDni.anchor = GridBagConstraints.WEST;
			gbc_lblDni.fill = GridBagConstraints.VERTICAL;
			gbc_lblDni.insets = new Insets(0, 0, 5, 5);
			gbc_lblDni.gridx = 2;
			gbc_lblDni.gridy = 4;
			pnlFicha.add(lblDni, gbc_lblDni);
		}
		{
			GridBagConstraints gbc_txtDNI = new GridBagConstraints();
			gbc_txtDNI.insets = new Insets(0, 0, 5, 5);
			gbc_txtDNI.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDNI.gridx = 3;
			gbc_txtDNI.gridy = 4;
			pnlFicha.add(txtDNI, gbc_txtDNI);
		}
		{
			GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
			gbc_lblTelefono.anchor = GridBagConstraints.WEST;
			gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
			gbc_lblTelefono.gridx = 2;
			gbc_lblTelefono.gridy = 5;
			pnlFicha.add(lblTelefono, gbc_lblTelefono);
		}
		{
			GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
			gbc_txtTelefono.insets = new Insets(0, 0, 5, 5);
			gbc_txtTelefono.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTelefono.gridx = 3;
			gbc_txtTelefono.gridy = 5;
			pnlFicha.add(txtTelefono, gbc_txtTelefono);
		}
		{
			GridBagConstraints gbc_btnCargarComentarios = new GridBagConstraints();
			gbc_btnCargarComentarios.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnCargarComentarios.gridwidth = 3;
			gbc_btnCargarComentarios.insets = new Insets(0, 0, 5, 5);
			gbc_btnCargarComentarios.gridx = 0;
			gbc_btnCargarComentarios.gridy = 6;
			btnCargarComentarios.addActionListener(new BtnCargarComentariosActionListener());
			btnCargarComentarios.setIcon(new ImageIcon(Seminario2.class.getResource("/presentacion/cargarComentarios.png")));
			pnlFicha.add(btnCargarComentarios, gbc_btnCargarComentarios);
		}
		{
			GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
			gbc_scrollPane_1.gridwidth = 5;
			gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
			gbc_scrollPane_1.gridx = 0;
			gbc_scrollPane_1.gridy = 7;
			pnlFicha.add(scrollPane_1, gbc_scrollPane_1);
		}
		{
			txtArComentarios.setBorder(new TitledBorder(null, "Comentarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scrollPane_1.setViewportView(txtArComentarios);
		}
		{
			GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
			gbc_btnGuardar.anchor = GridBagConstraints.EAST;
			gbc_btnGuardar.gridx = 4;
			gbc_btnGuardar.gridy = 8;
			btnGuardar.addActionListener(new BtnGuardarActionListener());
			btnGuardar.setIcon(new ImageIcon(Seminario2.class.getResource("/presentacion/guardar.png")));
			pnlFicha.add(btnGuardar, gbc_btnGuardar);
		}
		{
			frame.setJMenuBar(menuBar);
		}
		{
			mArchivo.setMnemonic('A');
			menuBar.add(mArchivo);
		}
		{
			miAbrir.addActionListener(new MiAbrirActionListener());
			miAbrir.setIcon(new ImageIcon(Seminario2.class.getResource("/presentacion/abrir.png")));
			miAbrir.setMnemonic('b');
			mArchivo.add(miAbrir);
		}
		{
			miCargarFoto.setIcon(new ImageIcon(Seminario2.class.getResource("/presentacion/cargarFoto.png")));
			miCargarFoto.setMnemonic('C');
			mArchivo.add(miCargarFoto);
		}
		{
			miCargarComentarios.setIcon(new ImageIcon(Seminario2.class.getResource("/presentacion/cargarComentarios.png")));
			miCargarComentarios.setMnemonic('m');
			mArchivo.add(miCargarComentarios);
		}
		{
			mArchivo.add(separator);
		}
		{
			miGuardar.setIcon(new ImageIcon(Seminario2.class.getResource("/presentacion/guardar.png")));
			miGuardar.addActionListener(new BtnGuardarActionListener());
			miGuardar.setMnemonic('G');;
			mArchivo.add(miGuardar);
		}
		{
			mArchivo.add(separator_1);
		}
		{
			miSalir.addActionListener(new MiSalirActionListener());
			mArchivo.add(miSalir);
		}
		{
			mEdicion.setMnemonic('E');
			menuBar.add(mEdicion);
		}
		{
			mEdicion.add(mTamanoLetra);
		}
		{
			buttonGroup.add(radioPequena);
			radioPequena.addActionListener(new RadioPequenaActionListener());
			mTamanoLetra.add(radioPequena);
		}
		{
			buttonGroup.add(radioNormal);
			radioNormal.addActionListener(new RadioNormalActionListener());
			mTamanoLetra.add(radioNormal);
		}
		{
			buttonGroup.add(radioGrande);
			radioGrande.addActionListener(new RadioGrandeActionListener());
			mTamanoLetra.add(radioGrande);
		}
		{
			mAyuda.setMnemonic('y');
			menuBar.add(mAyuda);
		}
		{
			miAcercaDe.addActionListener(new MiAcercaDeActionListener());
			mAyuda.add(miAcercaDe);
		}
	}
	private class TxtExpedienteKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent arg0) {
			if(String.valueOf(txtExpediente.getText()).equals("Exp345")){
				txtExpediente.setBorder(bordeVerde);
				lblCheck.setIcon(new ImageIcon(Seminario2.class.getResource("/presentacion/tick.png")));
			}else{
				txtExpediente.setBorder(bordeRojo);
				lblCheck.setIcon(new ImageIcon(Seminario2.class.getResource("/presentacion/cross.png")));
			}
		}
	}
	private class MyFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			e.getComponent().setBackground(new Color(250, 250, 210));
		}
		@Override
		public void focusLost(FocusEvent e) {
			e.getComponent().setBackground(new Color(250, 250, 250));
		}
	}
	
	/* Configuramos el boton cargar foto */
	private class BtnCargarFotoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			/* Creamos la accion */
			JFileChooser fcAbrir = new JFileChooser();
			fcAbrir.setFileFilter(new ImageFilter());
			/* Abrimos el explorador de archivos */
			int valorDevuelto = fcAbrir.showOpenDialog(frame);
			
			/* Si pulsamos el boton de aceptar */
			if(valorDevuelto == JFileChooser.APPROVE_OPTION){
				File file = fcAbrir.getSelectedFile();
				lblFoto.setIcon(new ImageIcon(file.getAbsolutePath()));
				
				System.out.println("Fichero seleccionado: "+file.getName());
			}
		}
	}
	private class BtnCargarComentariosActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fcAbrir = new JFileChooser();
			fcAbrir.setFileFilter(new FileTypeFilter());
			int valorDevuelto = fcAbrir.showOpenDialog(frame);
			
			/* Recogemos el nombre del fichero seleccionado por el usuario */
			if(valorDevuelto == JFileChooser.APPROVE_OPTION){
				File file = fcAbrir.getSelectedFile();
				System.out.println("Fichero seleccionado: "+file.getName());
				
				try{
					txtArComentarios.setText("");
					
					/* Apertura del fichero y creacion del BufferedReader */
					FileReader fr = new FileReader (file.getAbsolutePath());
					BufferedReader br = new BufferedReader(fr);
					
					/* Lectura del fichero linea a linea */
					String linea;
					while((linea = br.readLine()) != null){
						System.out.println(linea);
						txtArComentarios.append(linea+"\n");
					}
					br.close();
					System.out.println("Fichero leído: "+ file.getName()+ ".");
				}catch (IOException ioe){
					System.out.println("Problemas mientras se leia el archivo");
				}
			}else{
				System.out.println("El usuario ha cancelado el proceso de lectura.");
			}
		}
	}
	private class RadioPequenaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			txtArComentarios.setFont(new Font(txtArComentarios.getFont().getFontName(), txtArComentarios.getFont().getStyle(), 8));
		}
	}
	private class RadioNormalActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			txtArComentarios.setFont(new Font(txtArComentarios.getFont().getFontName(), txtArComentarios.getFont().getStyle(), 12));
		}
	}
	private class RadioGrandeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			txtArComentarios.setFont(new Font(txtArComentarios.getFont().getFontName(), txtArComentarios.getFont().getStyle(), 16));
		}
	}
	private class FrameWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			JOptionPane.showMessageDialog(frame, "Gracias por utilizar nuestra aplicacion", "Cerrar la aplicacion", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private class BtnGuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser selecArchivo = new JFileChooser();
			selecArchivo.setFileFilter(new FileTypeFilter());
			selecArchivo.setDialogTitle("Guardar como:");
			 
			int userSelection = selecArchivo.showSaveDialog(frame);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    /* Name of the file */
				String filename = selecArchivo.getSelectedFile().getPath();
			    
				/* Creating the file with the name given before */
				File fileToSave = new File(filename);
			    if(!fileToSave.exists()){ /* Exists? */
			    	try {
						fileToSave.createNewFile(); /* If not, create it */
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			    
			    try {
					FileWriter fw = new FileWriter(fileToSave.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					
					/* Writting the file with the data in the fields */
					bw.write(txtExpediente.getText());
					bw.newLine();
					bw.write(txtNombre.getText());
					bw.newLine();
					bw.write(txtApellidos.getText());
					bw.newLine();
					bw.write(txtDNI.getText());
					bw.newLine();
					bw.write(txtTelefono.getText());
					bw.newLine();
					bw.write(txtArComentarios.getText());
					
					/* Close the file */
					bw.close();
					
					System.out.println("Done");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(userSelection == JFileChooser.CANCEL_OPTION){
				JOptionPane.showMessageDialog(selecArchivo, "Has cancelado la opción.");
			}else if(userSelection == JFileChooser.ERROR_OPTION){
				JOptionPane.showMessageDialog(selecArchivo, "Error al intentar guardar el fichero.");
			}
		}
	}
	private class MiAbrirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser selecArchivo = new JFileChooser();
			selecArchivo.setFileFilter(new FileTypeFilter());
			selecArchivo.setDialogTitle("Abrir:");
			
			int userSelection = selecArchivo.showOpenDialog(frame);
			
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				/* Name of the file selected */
				String filename = selecArchivo.getSelectedFile().getPath();
				String [] rellenar;
			    BufferedReader br;
				try {
					br = new BufferedReader(new FileReader(filename));
			        StringBuilder sb = new StringBuilder();
			        String line = br.readLine();

			        while (line != null) {
			            sb.append(line);
			            sb.append("\n");
			            line = br.readLine();
			        }
			        
			        String informacion = sb.toString();
			        rellenar = informacion.split("\n");
			        
			        txtExpediente.setText(rellenar[0]);
			        txtNombre.setText(rellenar[1]);
			        txtApellidos.setText(rellenar[2]);
			        txtDNI.setText(rellenar[3]);
			        txtTelefono.setText(rellenar[4]);
			        txtArComentarios.setText(rellenar[5]);
			        
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	private class MiAcercaDeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(frame, "Javier Garcia Ceca");
		}
	}
	private class MiSalirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		}
	}
}
