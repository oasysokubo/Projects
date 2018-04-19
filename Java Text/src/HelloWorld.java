import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

@SuppressWarnings("serial")
public class HelloWorld extends JFrame implements ActionListener {

	// =========================================================
	// ======================= Fields ==========================
	// =========================================================

	// Menus
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenuItem newFile, openFile, saveFile, saveAsFile, pageSetup, printFile, exit;
	private JMenuItem undoEdit, redoEdit, selectAll, copy, paste, cut;

	// Window
	private JFrame editorWindow;

	// Text Area
	private Border textBorder;
	private JScrollPane scroll;
	private JTextArea textArea;
	private Font textFont;

	// Window
	private JFrame window;

	// Printing variables
	private PrinterJob job;
	public PageFormat format;

	// Quick Save File
	private File openedFile;

	// Check File Status Saved/Opened
	private boolean opened = false;
	private boolean saved = false;

	// Undo Manager to manage storage of past requests of undo
	private UndoManager undo;

	// =========================================================
	// ==================== Constructor ========================
	// =========================================================

	public HelloWorld() {

		super("Java Text");

		// Create Menus function call
		fileMenu();
		editMenu();

		// Create Text Area
		createTextArea();

		// Create Undo Manager
		undoMan();

		// Create Window
		createEditorWindow();

	}

	// =========================================================
	// =================== Private Methods =====================
	// =========================================================

	private JFrame createEditorWindow() {

		editorWindow = new JFrame("Java Text");
		editorWindow.setVisible(true);
		editorWindow.setSize(500, 600);
		editorWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Create Menu Bar
		editorWindow.setJMenuBar(createMenuBar());
		editorWindow.add(scroll, BorderLayout.CENTER);
		editorWindow.pack();

		// Center window on screen
		editorWindow.setLocationRelativeTo(null);

		return editorWindow;
	}

	private JMenuBar createMenuBar() {

		// Create menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		return menuBar;

	}

	// createTextArea()
	// This method creates text area in the window
	private JTextArea createTextArea() {

		textBorder = BorderFactory.createBevelBorder(0, Color.BLACK, Color.BLACK);
		textArea = new JTextArea(40, 60);
		textArea.setEditable(true);
		textArea.setBorder(BorderFactory.createCompoundBorder(textBorder, BorderFactory.createEmptyBorder(2, 5, 0, 0)));

		textFont = new Font("Courier", 0, 14);
		textArea.setFont(textFont);

		scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		return textArea;

	}

	// undoMan()
	// This method is a listener for undo and redo functions to document
	private UndoManager undoMan() {

		undo = new UndoManager();
		textArea.getDocument().addUndoableEditListener((UndoableEditListener) new UndoableEditListener() {

			public void undoableEditHappened(UndoableEditEvent e) {
				undo.addEdit(e.getEdit());
			}
		});

		return undo;

	}

	// fileMenu()
	// This method declares and adds File menu and its items
	private void fileMenu() {

		// Create new File menu
		fileMenu = new JMenu("File");
		fileMenu.setPreferredSize(new Dimension(40, 20));

		// Declaration of File menu items
		newFile = new JMenuItem("New");
		newFile.addActionListener(this);
		newFile.setPreferredSize(new Dimension(90, 20));
		newFile.setEnabled(true);

		openFile = new JMenuItem("Open");
		openFile.addActionListener(this);
		openFile.setPreferredSize(new Dimension(90, 20));
		openFile.setEnabled(true);

		saveFile = new JMenuItem("Save");
		saveFile.addActionListener(this);
		saveFile.setPreferredSize(new Dimension(90, 20));
		saveFile.setEnabled(true);

		saveAsFile = new JMenuItem("Save As");
		saveAsFile.addActionListener(this);
		saveAsFile.setPreferredSize(new Dimension(90, 20));
		saveAsFile.setEnabled(true);

		pageSetup = new JMenuItem("Setup");
		pageSetup.addActionListener(this);
		pageSetup.setPreferredSize(new Dimension(90, 20));
		pageSetup.setEnabled(true);

		printFile = new JMenuItem("Print");
		printFile.addActionListener(this);
		printFile.setPreferredSize(new Dimension(90, 20));
		printFile.setEnabled(true);

		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		exit.setPreferredSize(new Dimension(90, 20));
		exit.setEnabled(true);

		// Addition of File menu items
		fileMenu.add(newFile);
		fileMenu.add(openFile);
		fileMenu.add(saveFile);
		fileMenu.add(saveAsFile);
		fileMenu.add(pageSetup);
		fileMenu.add(printFile);
		fileMenu.add(exit);

	}

	private void editMenu() {

		// create new Edit menu
		editMenu = new JMenu("Edit");
		editMenu.setPreferredSize(new Dimension(40, 20));

		// Declaration of Edit menu items
		undoEdit = new JMenuItem("Undo");
		undoEdit.addActionListener(this);
		undoEdit.setPreferredSize(new Dimension(90, 20));
		undoEdit.setEnabled(true);

		redoEdit = new JMenuItem("Redo");
		redoEdit.addActionListener(this);
		redoEdit.setPreferredSize(new Dimension(90, 20));
		redoEdit.setEnabled(true);

		selectAll = new JMenuItem("Select All");
		selectAll.addActionListener(this);
		selectAll.setPreferredSize(new Dimension(90, 20));
		selectAll.setEnabled(true);

		copy = new JMenuItem("Copy");
		copy.addActionListener(this);
		copy.setPreferredSize(new Dimension(90, 20));
		copy.setEnabled(true);

		paste = new JMenuItem("Paste");
		paste.addActionListener(this);
		paste.setPreferredSize(new Dimension(90, 20));
		paste.setEnabled(true);

		cut = new JMenuItem("Cut");
		cut.addActionListener(this);
		cut.setPreferredSize(new Dimension(90, 20));
		cut.setEnabled(true);

		// Add menu items to Edit menu
		editMenu.add(undoEdit);
		editMenu.add(redoEdit);
		editMenu.add(selectAll);
		editMenu.add(copy);
		editMenu.add(paste);
		editMenu.add(cut);

	}

	// saveFile()
	// This method saves files
	private void saveFile(File file) {

		try {
			BufferedWriter write = new BufferedWriter(new FileWriter(file));
			write.write(textArea.getText());
			write.close();
			saved = true;
			window.setTitle("Java Text | " + file.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// quickSave()
	// This method quick saves files
	private void quickSave(File file) {

		try {
			BufferedWriter write = new BufferedWriter(new FileWriter(file));
			write.write(textArea.getText());
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// openingFiles()
	// This method opens files
	private void openingFiles(File file) {

		try {
			openedFile = file;
			FileReader read = new FileReader(file);
			textArea.read(read, null);
			opened = true;
			window.setTitle("Java Text | " + file.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// =========================================================
	// =============== Getter & Setter Methods =================
	// =========================================================

	public JTextArea getTextArea() {
		return this.textArea;
	}

	public void setTextArea(JTextArea ta) {
		this.textArea = ta;
	}

	// =========================================================
	// ==================== Public Methods =====================
	// =========================================================

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newFile) {
			new HelloWorld();
		} else if (e.getSource() == openFile) {
			JFileChooser open = new JFileChooser();
			open.showOpenDialog(null);
			File file = open.getSelectedFile();
			openingFiles(file);
		} else if (e.getSource() == saveFile) {
			JFileChooser save = new JFileChooser();
			File filename = save.getSelectedFile();
			if (opened == false && saved == false) {
				save.showSaveDialog(null);
				int confirmationResult;
				if (filename.exists()) {
					confirmationResult = JOptionPane.showConfirmDialog(saveFile, "Replace existing file?");
					if (confirmationResult == JOptionPane.YES_OPTION) {
						saveFile(filename);
					}
				} else {
					saveFile(filename);
				}
			} else {
				quickSave(openedFile);
			}
		} else if (e.getSource() == saveAsFile) {
			JFileChooser saveAs = new JFileChooser();
			saveAs.showSaveDialog(null);
			File filename = saveAs.getSelectedFile();
			int confirmationResult;
			if (filename.exists()) {
				confirmationResult = JOptionPane.showConfirmDialog(saveAsFile, "Replace existing file?");
				if (confirmationResult == JOptionPane.YES_OPTION) {
					saveFile(filename);
				}
			} else {
				saveFile(filename);
			}
		} else if (e.getSource() == pageSetup) {
			job = PrinterJob.getPrinterJob();
			format = job.pageDialog(job.defaultPage());
		} else if (e.getSource() == printFile) {
			job = PrinterJob.getPrinterJob();
			if (job.printDialog()) {
				try {
					job.print();
				} catch (PrinterException err) {
					err.printStackTrace();
				}
			}
		} else if (e.getSource() == exit) {
			System.exit(0);
		} else if (e.getSource() == undoEdit) {
			try {
				undo.undo();
			} catch (CannotUndoException cu) {
				cu.printStackTrace();
			}
		} else if (e.getSource() == redoEdit) {
			try {
				undo.redo();
			} catch (CannotUndoException cu) {
				cu.printStackTrace();
			}
		} else if (e.getSource() == selectAll) {
			textArea.selectAll();
		} else if (e.getSource() == copy) {
			textArea.copy();
		} else if (e.getSource() == paste) {
			textArea.paste();
		} else if (e.getSource() == cut) {
			textArea.cut();
		}
	}

	public static void main(String[] args) {
		new HelloWorld();
	}

}