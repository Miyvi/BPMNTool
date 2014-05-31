package view;

import jade.gui.GuiEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToolTip;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import model.End;
import model.JoinGateway;
import model.Panneau;
import model.SplitGateway;
import model.Start;
import model.Task;
import model.WorkFlow;
import agents.ViewAgent;

public class View extends JFrame
{
	private ViewAgent viewagent;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private final JFileChooser choose = new JFileChooser();
	private final JMenuBar bar = new JMenuBar();
	private final JMenu fileMenu = new JMenu("Fichier");
	private final JMenuItem oppenFile = new JMenuItem("Ouvrir");
	private final JMenuItem save = new JMenuItem("Sauvegarder");
	private final JMenuItem saveAs = new JMenuItem("Sauvegarder Sous");
	private final JMenuItem quit = new JMenuItem("Quitter");
	private final StyleContext context = new StyleContext();
	private final StyledDocument document = new DefaultStyledDocument(context);
	private final JTextPane historic = new JTextPane(document);
	private final JScrollPane conversationContent = new JScrollPane(historic);
	private final JPanel graph = new JPanel();
	private final JPanel pane = new JPanel(new GridBagLayout());
	private final JPanel conversation = new JPanel(new GridBagLayout());
	private final JButton micro = new JButton(new ImageIcon((((new ImageIcon("doc/micro.png")).getImage()).getScaledInstance(45,45, java.awt.Image.SCALE_SMOOTH))));
	private final JTextArea text = new JTextArea(3, 2);
	private final JScrollPane actions = new JScrollPane(text);
	boolean isMicroOn = false;

	public View(ViewAgent a) 
	{
		viewagent=a;
		this.setVisible(true);
		this.setSize(1200, 650);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
		final Style center = document.addStyle("center", style);
		center.addAttribute(StyleConstants.Alignment, StyleConstants.ALIGN_CENTER);
		center.addAttribute(StyleConstants.Foreground, Color.black);
		final Style right = document.addStyle("right", style);
		right.addAttribute(StyleConstants.Alignment, StyleConstants.ALIGN_RIGHT);
		right.addAttribute(StyleConstants.Foreground, new Color(0x2F, 0x8F, 0x00));

		final Style left = document.addStyle("right", style);
		left.addAttribute(StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);
		left.addAttribute(StyleConstants.Foreground, new Color(0x08, 0x10, 0x73));

		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder title;
		title = BorderFactory.createTitledBorder(blackline, "Assitant");
		title.setTitleJustification(TitledBorder.CENTER);

		pane.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		actions.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		micro.setBackground(Color.gray);
		micro.setFocusable(false);
		final JToolTip microTip = new JToolTip();
		microTip.add(micro);
		micro.setToolTipText("Micro off");
		text.setMargin(new Insets(5, 5, 0, 5));
		// text.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		fileMenu.add(oppenFile);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.add(quit);
		bar.add(fileMenu);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipady = 20;
		constraints.gridwidth = 2;
		pane.add(bar, constraints);

		conversation.setLayout(new GridBagLayout());
		GridBagConstraints conversationConstraints = new GridBagConstraints();
		conversationConstraints.fill = GridBagConstraints.HORIZONTAL;
		conversationConstraints.weightx = 0;
		conversationConstraints.gridx = 0;
		conversationConstraints.gridy = 0;
		conversationConstraints.gridwidth = 3;
		conversationConstraints.ipady = 437;
		historic.setEditable(false);
		conversationContent.setSize(32, 30);
		conversation.add(conversationContent, conversationConstraints);

		conversationConstraints.fill = GridBagConstraints.HORIZONTAL;
		conversationConstraints.weightx = 0.9;
		conversationConstraints.gridx = 1;
		conversationConstraints.gridy = 1;
		conversationConstraints.gridwidth = 1;
		conversationConstraints.gridheight = 1;
		conversationConstraints.ipady = 9;
		conversation.add(actions, conversationConstraints);

		conversationConstraints.fill = GridBagConstraints.HORIZONTAL;
		conversationConstraints.weightx = 0.01;
		conversationConstraints.gridx = 0;
		conversationConstraints.gridy = 1;
		conversationConstraints.gridwidth = 1;
		conversationConstraints.ipady = 9;
		conversation.add(micro, conversationConstraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.25;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		// constraints.ipady = 500;
		constraints.insets = new Insets(20, 0, 0, 0);
		conversation.setBorder(title);
		pane.add(conversation, constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.75;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.ipady = 528;
		constraints.insets = new Insets(20, 0, 0, 0);
		title = BorderFactory.createTitledBorder(blackline, "BPMN Graphic");
		title.setTitleJustification(TitledBorder.CENTER);
		graph.setBorder(title);
		pane.add(graph, constraints);

		this.add(pane);

		// listener sur le bouton du menu pour lancer le choix du fichier
		oppenFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				choose.setAcceptAllFileFilterUsed(false);
				choose.addChoosableFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "XML contacts";
					}

					@Override
					public boolean accept(File f) {
						return f.getName().endsWith(".xml");
					}
				});

				int returnVal = choose.showOpenDialog(choose);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = choose.getSelectedFile();
					// Model.getModel().setFichier(file);
					// refresh();
				}
			}
		});

		// listener sur le bouton de sauvegarde
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Model.getModel().saveFile();
			}
		});

		// listener sur le bouton du menu pour lancer le choix du fichier
		saveAs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				choose.setAcceptAllFileFilterUsed(false);
				choose.addChoosableFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "XML contacts";
					}

					@Override
					public boolean accept(File f) {
						return f.getName().endsWith(".xml");
					}
				});

				int returnVal = choose.showOpenDialog(choose);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = choose.getSelectedFile();
					// Model.getModel().saveFileAs(file);
				}
			}
		});

		// listener sur le bouton pour quitter
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					String actionsText = text.getText();
					actionsText=actionsText.replace("\n","");
					GuiEvent ev = new GuiEvent(this,ViewAgent.TEXT_SEND);
					ev.addParameter(actionsText);
					viewagent.postGuiEvent(ev);
					
					text.setText("");
					/* historic.getSize().height */
					final int pos = document.getLength();
					try {
						document.insertString(
								pos,
								actionsText
										+ System.getProperty("line.separator"),
								right);
						document.setParagraphAttributes(pos,
								actionsText.length(), right, true);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		micro.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (isMicroOn) {
					micro.setFocusable(true);
					isMicroOn = false;
					System.out.println("micro off");
					micro.setBackground(Color.gray);
					micro.setFocusable(false);
					microTip.setVisible(false);
					micro.setToolTipText("Micro off");
				} else {
					// JToolTip microOn = new JToolTip();
					// microOn.add(micro);
					micro.setFocusable(false);
					isMicroOn = true;
					System.out.println("micro on");
					micro.setBackground(new Color(159, 82, 85));
					micro.setFocusable(false);
					micro.setToolTipText("Micro on");
				}

			}

		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public PropertyChangeSupport getPcs() {
		return pcs;
	}

	public void setPcs(PropertyChangeSupport pcs) {
		this.pcs = pcs;
	}

}