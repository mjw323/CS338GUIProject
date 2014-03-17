/*
     * [Michael Wolfe]
     * [mjw323@drexel.edu]
     * CS338:GUI, Final Project
     */
	
//Imported Libraries	 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat.Field;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;


public class Main extends JFrame implements ListSelectionListener{

	private static final long serialVersionUID = 1L;
	
	//Definitions and Instantiations
	Device newDevice = new Device();
	LinkedList<Device> DevicesInList = new LinkedList<Device>();
	
	private JList ConnectedTolist;
	private JList ConnectedFromlist;
    private DefaultListModel ConnectedFromlistModel;
    private DefaultListModel ConnectedTolistModel;
    
    private JTextField ComputerDeviceName = new JTextField("N/A", 10);
    private JTextField ComputerDeviceType = new JTextField("N/A", 10);
    private JTextField ComputerDeviceIP = new JTextField("0.0.0.0", 10);
    private JTextField ComputerDeviceNetwork = new JTextField("N/A", 10);
    private JButton ComputerDevicePicture = new JButton();
    
    private JTextField ServerDeviceName = new JTextField("N/A", 10);
    private JTextField ServerDeviceType = new JTextField("N/A", 10);
    private JTextField ServerDeviceIP = new JTextField("0.0.0.0", 10);
    private JTextField ServerDeviceNetwork = new JTextField("N/A", 10);
    private JButton ServerDevicePicture = new JButton();
    
    private JTextField RouterDeviceName = new JTextField("N/A", 10);
    private JTextField RouterDeviceType = new JTextField("N/A", 10);
    private JTextField RouterDeviceIP = new JTextField("0.0.0.0", 10);
    private JTextField RouterDeviceNetwork = new JTextField("N/A", 10);
    private JButton RouterDevicePicture = new JButton();
    
    private JTextField BridgeDeviceName = new JTextField("N/A", 10);
    private JTextField BridgeDeviceType = new JTextField("N/A", 10);
    private JTextField BridgeDeviceIP = new JTextField("0.0.0.0", 10);
    private JTextField BridgeDeviceNetwork = new JTextField("N/A", 10);
    private JButton BridgeDevicePicture = new JButton();
    
    private JButton MakeDeviceDiagram = new JButton();
    
    public JDialog PopupPanel = new JDialog();
    public JDialog DiagramPanel = new JDialog();
    public JDialog HelpPanel = new JDialog();
    public JTabbedPane TabbedPanel = new JTabbedPane();
    public PicturesPanel DrawPictures = new PicturesPanel();
    
    public JButton Add = new JButton("Add");
    public JButton Change = new JButton("Change");
    public JButton Remove = new JButton("Remove");
    
    public JButton ComputerAdd = new JButton("OK");
    public JButton ServerAdd = new JButton("OK");
    public JButton RouterAdd = new JButton("OK");
    public JButton BridgeAdd = new JButton("OK");
    public JButton ComputerCancel = new JButton("Cancel");
    public JButton BridgeCancel = new JButton("Cancel");
    public JButton ServerCancel = new JButton("Cancel");
    public JButton RouterCancel = new JButton("Cancel");
    
    ActionListener ComputerListen = new ComputerAddListener();
    ActionListener ServerListen = new ServerAddListener();
    ActionListener BridgeListen = new BridgeAddListener();
    ActionListener RouterListen = new RouterAddListener();
    ActionListener ComputerChangeListen = new ComputerChangeListener();
    ActionListener ServerChangeListen = new ServerChangeListener();
    ActionListener BridgeChangeListen = new BridgeChangeListener();
    ActionListener RouterChangeListen = new RouterChangeListener();
   
    
		//Initialize the UI
		public Main() {
	        
	        initUI();
		}
		
		//Creates the design of the UI
	    public final void initUI() {
	    	
	    	//Set Picture Icons
	    	Image Computerimg = null;
			try {
				Computerimg = ImageIO.read(getClass().getResource("icons/Computer.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image Serverimg = null;
			try {
				Serverimg = ImageIO.read(getClass().getResource("icons/Server.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image Routerimg = null;
			try {
				Routerimg = ImageIO.read(getClass().getResource("icons/Router.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image Bridgeimg = null;
			try {
				Bridgeimg = ImageIO.read(getClass().getResource("icons/Switch.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        ComputerDevicePicture.setIcon(new ImageIcon(Computerimg));
	        ServerDevicePicture.setIcon(new ImageIcon(Serverimg));
	        RouterDevicePicture.setIcon(new ImageIcon(Routerimg));
	        BridgeDevicePicture.setIcon(new ImageIcon(Bridgeimg));
	        
	        Image diagram = null;
			try {
				diagram = ImageIO.read(getClass().getResource("icons/Diagram.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        MakeDeviceDiagram.setIcon(new ImageIcon(diagram));
	        
	        //Set example picture for the diagram
	        Image Example = null;
			try {
				Example = ImageIO.read(getClass().getResource("icons/ExampleDiagram.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JLabel label = new JLabel();
			label.setIcon(new ImageIcon(Example));
	        DiagramPanel.add(label);

			//One Main Panel which will contain the other panels
	        JPanel MainWindow = new JPanel();
	        MainWindow.setLayout(new BoxLayout(MainWindow, BoxLayout.Y_AXIS));
	        add(MainWindow);
	        
	        //The Menu Bar
	        JMenuBar menubar = new JMenuBar();

	        JMenu filem = new JMenu("File");
	        JMenu viewm = new JMenu("View");

	        
	        
	        JMenuItem fileNew = new JMenuItem("New");
	        fileNew.setMnemonic(KeyEvent.VK_N);
	        fileNew.setToolTipText("Clear database");
	        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
	            ActionEvent.CTRL_MASK));

	        JMenuItem fileOpen = new JMenuItem("Open");
	        fileOpen.setMnemonic(KeyEvent.VK_O);
	        fileOpen.setToolTipText("Open saved database");
	        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
	            ActionEvent.CTRL_MASK));

	        JMenuItem fileSave = new JMenuItem("Save");
	        fileSave.setMnemonic(KeyEvent.VK_S);
	        fileSave.setToolTipText("Save current database");
	        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
	            ActionEvent.CTRL_MASK));

	        JMenuItem fileExit = new JMenuItem("Exit");
	        fileExit.setMnemonic(KeyEvent.VK_Q);
	        fileExit.setToolTipText("Exit application");
	        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
	            ActionEvent.CTRL_MASK));
	        
	        JMenuItem DiagramView = new JMenuItem("Diagram");
	        DiagramView.setMnemonic(KeyEvent.VK_V);
	        DiagramView.setToolTipText("View current database diagram");
	        DiagramView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
	            ActionEvent.CTRL_MASK));
	        
	        JMenuItem HelpView = new JMenuItem("Help");
	        HelpView.setToolTipText("See Help for Program");
	        
	        HelpView.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	HelpPanel.setVisible(true);
	            }
	        });
	        
	        DiagramView.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	DiagramPanel.setVisible(true);
	  			   	repaint();
	            }
	        });
	        
	        fileNew.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                ConnectedFromlist.removeAll();
	                ConnectedFromlistModel.removeAllElements();
	            }
	        });

	        fileExit.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                System.exit(0);
	            }
	        });
	        
	        filem.add(fileNew);
	        filem.add(fileOpen);
	        filem.add(fileSave);
	        filem.add(fileExit);
	        viewm.add(DiagramView);
	        
	        menubar.add(filem);
	        menubar.add(viewm);   
	        menubar.add(Box.createHorizontalGlue());
	        menubar.add(Box.createHorizontalGlue());
	        menubar.add(Box.createHorizontalGlue());
	        menubar.add(Box.createHorizontalGlue());
	        menubar.add(Box.createHorizontalGlue());
	        menubar.add(Box.createHorizontalGlue());
	        menubar.add(Box.createHorizontalGlue());
	        menubar.add(HelpView);

	        setJMenuBar(menubar);
	        
	        //Help Window
	        JTextArea textArea = new JTextArea("The Network bible is used to help create a documentation" + "\n" +
	        	    "of fairly large and expansive network devices." + "\n" + "\n" +
	        		"Press Add - To add a new device" + "\n" +
	        		"Press Change - To change a selected device" + "\n" +
	        		"Press Remove - To remove a selected device" + "\n" +
	        		"Or use the menu bar to get started..." +  "\n" +   "\n" + 		
	        		"This is a program created by Michael Wolfe for CS338 - GUI" + "\n" +
	        	    "v0.7 - Still missing Picture & Save/Load & Diagram functionality.");
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setEditable(false);
	        HelpPanel.add(textArea);
	        HelpPanel.setTitle("Help!");
	       
			//The Tabbed - Information Panel
	        PopupPanel.setModal(true);
	        JPanel ComputerPanel = new JPanel();
	        JPanel ServerPanel = new JPanel();
	        JPanel RouterPanel = new JPanel();
	        JPanel BridgePanel = new JPanel();
	        PopupPanel.add(TabbedPanel);
	        
	        TabbedPanel.addTab("Computer", ComputerPanel);
	        TabbedPanel.setMnemonicAt(0, KeyEvent.VK_1);
	        TabbedPanel.addTab("Server", ServerPanel);
	        TabbedPanel.setMnemonicAt(1, KeyEvent.VK_2);
	        TabbedPanel.addTab("Bridge", BridgePanel);
	        TabbedPanel.setMnemonicAt(2, KeyEvent.VK_3);
	        TabbedPanel.addTab("Router", RouterPanel);
	        TabbedPanel.setMnemonicAt(2, KeyEvent.VK_4);
			
	        //Set up input fields for the different Device panels
	        
	        //Name
	        JLabel  ComputerNameLabel= new JLabel("Name: ");
	        ComputerNameLabel.setMaximumSize( ComputerNameLabel.getPreferredSize() );
	        ComputerNameLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  ServerNameLabel= new JLabel("Name: ");
	        ServerNameLabel.setMaximumSize( ServerNameLabel.getPreferredSize() );
	        ServerNameLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  RouterNameLabel= new JLabel("Name: ");
	        RouterNameLabel.setMaximumSize( RouterNameLabel.getPreferredSize() );
	        RouterNameLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  BridgeNameLabel= new JLabel("Name: ");
	        BridgeNameLabel.setMaximumSize( BridgeNameLabel.getPreferredSize() );
	        BridgeNameLabel.setAlignmentX(LEFT_ALIGNMENT);
	       
	        ComputerDeviceName.setMaximumSize( ComputerDeviceName.getPreferredSize() );
	        ComputerDeviceName.setAlignmentX(Component.LEFT_ALIGNMENT);
	        ServerDeviceName.setMaximumSize( ServerDeviceName.getPreferredSize() );
	        ServerDeviceName.setAlignmentX(Component.LEFT_ALIGNMENT);
	        RouterDeviceName.setMaximumSize( RouterDeviceName.getPreferredSize() );
	        RouterDeviceName.setAlignmentX(Component.LEFT_ALIGNMENT);
	        BridgeDeviceName.setMaximumSize( BridgeDeviceName.getPreferredSize() );
	        BridgeDeviceName.setAlignmentX(Component.LEFT_ALIGNMENT);
	            
	        //Type
	        JLabel  ComputerTypeLabel= new JLabel("Type: ");
	        ComputerTypeLabel.setMaximumSize( ComputerTypeLabel.getPreferredSize() );
	        ComputerTypeLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  ServerTypeLabel= new JLabel("Type: ");
	        ServerTypeLabel.setMaximumSize( ServerTypeLabel.getPreferredSize() );
	        ServerTypeLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  RouterTypeLabel= new JLabel("Type: ");
	        RouterTypeLabel.setMaximumSize( RouterTypeLabel.getPreferredSize() );
	        RouterTypeLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  BridgeTypeLabel= new JLabel("Type: ");
	        BridgeTypeLabel.setMaximumSize( BridgeTypeLabel.getPreferredSize() );
	        BridgeTypeLabel.setAlignmentX(LEFT_ALIGNMENT);
	        
	        ComputerDeviceType.setMaximumSize( ComputerDeviceType.getPreferredSize() );
	        ComputerDeviceType.setAlignmentX(Component.LEFT_ALIGNMENT);
	        ServerDeviceType.setMaximumSize( ServerDeviceType.getPreferredSize() );
	        ServerDeviceType.setAlignmentX(Component.LEFT_ALIGNMENT);
	        RouterDeviceType.setMaximumSize( RouterDeviceType.getPreferredSize() );
	        RouterDeviceType.setAlignmentX(Component.LEFT_ALIGNMENT);
	        BridgeDeviceType.setMaximumSize( BridgeDeviceType.getPreferredSize() );
	        BridgeDeviceType.setAlignmentX(Component.LEFT_ALIGNMENT);
	        
	        //IP
	        JLabel  ComputerIPLabel= new JLabel("IP: ");
	        ComputerIPLabel.setMaximumSize( ComputerIPLabel.getPreferredSize() );
	        ComputerIPLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  ServerIPLabel= new JLabel("IP: ");
	        ServerIPLabel.setMaximumSize( ServerIPLabel.getPreferredSize() );
	        ServerIPLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  RouterIPLabel= new JLabel("IP: ");
	        RouterIPLabel.setMaximumSize( RouterIPLabel.getPreferredSize() );
	        RouterIPLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  BridgeIPLabel= new JLabel("IP: ");
	        BridgeIPLabel.setMaximumSize( BridgeIPLabel.getPreferredSize() );
	        BridgeIPLabel.setAlignmentX(LEFT_ALIGNMENT);
	        
	        ComputerDeviceIP.setMaximumSize( ComputerDeviceIP.getPreferredSize() );
	        ComputerDeviceIP.setAlignmentX(Component.LEFT_ALIGNMENT);
	        ServerDeviceIP.setMaximumSize( ServerDeviceIP.getPreferredSize() );
	        ServerDeviceIP.setAlignmentX(Component.LEFT_ALIGNMENT);
	        RouterDeviceIP.setMaximumSize( RouterDeviceIP.getPreferredSize() );
	        RouterDeviceIP.setAlignmentX(Component.LEFT_ALIGNMENT);
	        BridgeDeviceIP.setMaximumSize( BridgeDeviceIP.getPreferredSize() );
	        BridgeDeviceIP.setAlignmentX(Component.LEFT_ALIGNMENT);
	        
	        //Network
	        JLabel  ComputerNetworkLabel= new JLabel("Network: ");
	        ComputerNetworkLabel.setMaximumSize( ComputerNetworkLabel.getPreferredSize() );
	        ComputerNetworkLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  ServerNetworkLabel= new JLabel("Network: ");
	        ServerNetworkLabel.setMaximumSize( ServerNetworkLabel.getPreferredSize() );
	        ServerNetworkLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  RouterNetworkLabel= new JLabel("Network: ");
	        RouterNetworkLabel.setMaximumSize( RouterNetworkLabel.getPreferredSize() );
	        RouterNetworkLabel.setAlignmentX(LEFT_ALIGNMENT);
	        JLabel  BridgeNetworkLabel= new JLabel("Network: ");
	        BridgeNetworkLabel.setMaximumSize( BridgeNetworkLabel.getPreferredSize() );
	        BridgeNetworkLabel.setAlignmentX(LEFT_ALIGNMENT);
	        
	        ComputerDeviceNetwork.setMaximumSize( ComputerDeviceNetwork.getPreferredSize() );
	        ComputerDeviceNetwork.setAlignmentX(Component.LEFT_ALIGNMENT);
	        ServerDeviceNetwork.setMaximumSize( ServerDeviceNetwork.getPreferredSize() );
	        ServerDeviceNetwork.setAlignmentX(Component.LEFT_ALIGNMENT);
	        RouterDeviceNetwork.setMaximumSize( RouterDeviceNetwork.getPreferredSize() );
	        RouterDeviceNetwork.setAlignmentX(Component.LEFT_ALIGNMENT);
	        BridgeDeviceNetwork.setMaximumSize( BridgeDeviceNetwork.getPreferredSize() );
	        BridgeDeviceNetwork.setAlignmentX(Component.LEFT_ALIGNMENT);   
	        
	        //Layout of info in Tabs on TabbedPane
	        ComputerPanel.add(ComputerNameLabel);
	        ComputerPanel.add(ComputerDeviceName);
	        ComputerPanel.add(ComputerIPLabel);
	        ComputerPanel.add(ComputerDeviceIP);
	        ComputerPanel.add(ComputerTypeLabel);
	        ComputerPanel.add(ComputerDeviceType);
	        ComputerPanel.add(ComputerNetworkLabel);
	        ComputerPanel.add(ComputerDeviceNetwork);

	        //Creates the buttons panel at the bottom of the computer tab
	        JPanel ComputerButtons = new JPanel();
	        ComputerButtons.setLayout(new BoxLayout(ComputerButtons, BoxLayout.X_AXIS));
	        ComputerAdd.addActionListener(ComputerListen);
	        ComputerCancel.addActionListener(new CancelListener());

			//Spacing and initialization
	        ComputerButtons.add(ComputerAdd);
	        ComputerButtons.add(Box.createRigidArea(new Dimension(5, 0)));
	        ComputerButtons.add(ComputerCancel);
	        ComputerButtons.add(Box.createRigidArea(new Dimension(5, 0)));
	        ComputerButtons.add(ComputerDevicePicture);
	        ComputerPanel.add(ComputerButtons);
	        
	        //Server
	        ServerPanel.add(ServerNameLabel);
	        ServerPanel.add(ServerDeviceName);
	        ServerPanel.add(ServerIPLabel);
	        ServerPanel.add(ServerDeviceIP);
	        ServerPanel.add(ServerTypeLabel);
	        ServerPanel.add(ServerDeviceType);
	        ServerPanel.add(ServerNetworkLabel);
	        ServerPanel.add(ServerDeviceNetwork);

	        //Creates the buttons panel at the bottom of the server tab
	        JPanel ServerButtons = new JPanel();
	        ServerButtons.setLayout(new BoxLayout(ServerButtons, BoxLayout.X_AXIS));
	        ServerAdd.addActionListener(ServerListen);
	        ServerCancel.addActionListener(new CancelListener());

			//Spacing and initialization
	        ServerButtons.add(ServerAdd);
	        ServerButtons.add(Box.createRigidArea(new Dimension(5, 0)));
	        ServerButtons.add(ServerCancel);
	        ServerButtons.add(Box.createRigidArea(new Dimension(5, 0)));
	        ServerButtons.add(ServerDevicePicture);
	        ServerPanel.add(ServerButtons);
	        
	        //Router
	        RouterPanel.add(RouterNameLabel);
	        RouterPanel.add(RouterDeviceName);
	        RouterPanel.add(RouterIPLabel);
	        RouterPanel.add(RouterDeviceIP);
	        RouterPanel.add(RouterTypeLabel);
	        RouterPanel.add(RouterDeviceType);
	        RouterPanel.add(RouterNetworkLabel);
	        RouterPanel.add(RouterDeviceNetwork);

	        
	        //Creates the buttons panel at the bottom of the server tab
	        JPanel RouterButtons = new JPanel();
	        RouterButtons.setLayout(new BoxLayout(RouterButtons, BoxLayout.X_AXIS));
	        RouterAdd.addActionListener(RouterListen);
	        RouterCancel.addActionListener(new CancelListener());

			//Spacing and initialization
	        RouterButtons.add(RouterAdd);
	        RouterButtons.add(Box.createRigidArea(new Dimension(5, 0)));
	        RouterButtons.add(RouterCancel);
	        RouterButtons.add(Box.createRigidArea(new Dimension(5, 0)));
	        RouterButtons.add(RouterDevicePicture);
	        RouterPanel.add(RouterButtons);
	        
	        //Bridge
	        BridgePanel.add(BridgeNameLabel);
	        BridgePanel.add(BridgeDeviceName);
	        BridgePanel.add(BridgeIPLabel);
	        BridgePanel.add(BridgeDeviceIP);
	        BridgePanel.add(BridgeTypeLabel);
	        BridgePanel.add(BridgeDeviceType);
	        BridgePanel.add(BridgeNetworkLabel);
	        BridgePanel.add(BridgeDeviceNetwork);

	        
	        //Creates the buttons panel at the bottom of the server tab
	        JPanel BridgeButtons = new JPanel();
	        BridgeButtons.setLayout(new BoxLayout(BridgeButtons, BoxLayout.X_AXIS));
	        BridgeAdd.addActionListener(BridgeListen);
	        BridgeCancel.addActionListener(new CancelListener());

			//Spacing and initialization
	        BridgeButtons.add(BridgeAdd);
	        BridgeButtons.add(Box.createRigidArea(new Dimension(5, 0)));
	        BridgeButtons.add(BridgeCancel);
	        BridgeButtons.add(Box.createRigidArea(new Dimension(5, 0)));
	        BridgeButtons.add(BridgeDevicePicture);
	        BridgePanel.add(BridgeButtons);

	        //Create Main Content Window
	        //Create JList Component
	        JPanel ContentPanel = new JPanel();
	        ContentPanel.setLayout(new BoxLayout(ContentPanel, BoxLayout.X_AXIS));
	        ConnectedFromlistModel = new DefaultListModel();
	        ConnectedTolistModel = new DefaultListModel();
			ConnectedFromlist = new JList(ConnectedFromlistModel);
			ConnectedFromlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ConnectedFromlist.addListSelectionListener(this);
			ConnectedFromlist.setLayoutOrientation(JList.VERTICAL);
			ConnectedFromlist.setVisibleRowCount(-1);
	        JScrollPane listScroller = new JScrollPane(ConnectedFromlist);
	        listScroller.setPreferredSize(new Dimension(250,80));
	        ContentPanel.add(listScroller);
	        
	        //Creates the PicturesPanel which extends JPanel and draws the devices in the list.
	        
	        DrawPictures.setSize(400,300);
	        ContentPanel.add(DrawPictures);
	        
	        //Creates the buttons panel on bottom of the List.
	        JPanel buttons = new JPanel();
	        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
	        Add.addActionListener(new AddListener());
	        Change.addActionListener(new ChangeListener());
	        Change.setEnabled(false);
	        Remove.addActionListener(new RemoveListener());
	        Remove.setEnabled(false);
	        MakeDeviceDiagram.addActionListener(new DiagramListener());

			//Spacing and initialization of buttons
	        buttons.add(Add);
	        buttons.add(Box.createRigidArea(new Dimension(5, 0)));
	        buttons.add(Change);
	        buttons.add(Box.createRigidArea(new Dimension(5, 0)));
	        buttons.add(Remove);
	        buttons.add(Box.createRigidArea(new Dimension(5, 0)));
	        buttons.add(MakeDeviceDiagram);

	        //Initialize Main Content Layout
	        MainWindow.add(ContentPanel);
	        MainWindow.add(Box.createRigidArea(new Dimension(0, 15)));
	        MainWindow.add(buttons);
	        MainWindow.add(Box.createRigidArea(new Dimension(0, 15)));
	        PopupPanel.setVisible(false);
	        
	        //Set PopUp Settings
	        PopupPanel.setSize(360,240);
	        DiagramPanel.setSize(410,240);
	        HelpPanel.setSize(410,240);
	        DiagramPanel.setTitle("Current Network Diagram");
	        
			//Set Window Settings
	        setTitle("CS338 - Project - Network Bible - Mike Wolfe");
	        setSize(500, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	    }
	    
	  //Listener for Diagram button which shows a diagram of the current information
	  		class DiagramListener implements ActionListener {
	  			public void actionPerformed(ActionEvent e) {
	  			   	DiagramPanel.setVisible(true);
	  			   	repaint();
	  			}
	  		}
	 
		//Listener for Add button which adds the info from the text fields to the JList
		class AddListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	ConnectedFromlist.clearSelection();
	        	PopupPanel.setTitle("Add New Device");
	        	PopupPanel.setVisible(true);
	        	
	        	repaint();
	        }
	    }
		//Listener for Add button which adds the info from the text fields to the JList
		class CancelListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
			   	PopupPanel.setVisible(false);
			   	repaint();
			}
		}
		
		//Listener for Add button which adds the info from the text fields to the JList
		class ComputerAddListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				newDevice = new Device("Computer", ComputerDeviceType.getText(), ComputerDeviceName.getText(), ComputerDeviceIP.getText(), ComputerDeviceNetwork.getText(), "N/A");
				DevicesInList.add(newDevice);
			    ConnectedFromlistModel.addElement("Computer - " + ComputerDeviceType.getText() + " - " + ComputerDeviceName.getText() + " - " + ComputerDeviceIP.getText() + " - " + ComputerDeviceNetwork.getText());
			    repaint();
			    
			    PopupPanel.setVisible(false);
			}
			
		}
		
		class ComputerChangeListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				int index = ConnectedFromlist.getSelectedIndex();
				newDevice = new Device("Computer", ComputerDeviceType.getText(), ComputerDeviceName.getText(), ComputerDeviceIP.getText(), ComputerDeviceNetwork.getText(), "N/A");
				DevicesInList.set(index,newDevice);
				ConnectedFromlistModel.setElementAt("Computer - " + ComputerDeviceType.getText() + " - " + ComputerDeviceName.getText() + " - " + ComputerDeviceIP.getText() + " - " + ComputerDeviceNetwork.getText(), index);
			    repaint();
			    
			    PopupPanel.setVisible(false);
			    ComputerAdd.removeActionListener(ComputerChangeListen);
	        	ServerAdd.removeActionListener(ServerChangeListen);
	        	RouterAdd.removeActionListener(RouterChangeListen);
	        	BridgeAdd.removeActionListener(BridgeChangeListen);
	        	
	        	ComputerAdd.addActionListener(ComputerListen);
	        	ServerAdd.addActionListener(ServerListen);
	        	RouterAdd.addActionListener(RouterListen);
	        	BridgeAdd.addActionListener(BridgeListen);
			    
			}
			
		}
		
		//Listener for Add button which adds the info from the text fields to the JList
		class ServerAddListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				newDevice = new Device("Server", ServerDeviceType.getText(), ServerDeviceName.getText(), ServerDeviceIP.getText(), ServerDeviceNetwork.getText(), "N/A");
				DevicesInList.add(newDevice);
			    ConnectedFromlistModel.addElement("Server - " + ServerDeviceType.getText() + " - " + ServerDeviceName.getText() + "- " + ServerDeviceIP.getText() + "-" + ServerDeviceNetwork.getText());
			    repaint();
			    
			    PopupPanel.setVisible(false);
			}
		}
		
		class ServerChangeListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				int index = ConnectedFromlist.getSelectedIndex();
				newDevice = new Device("Server", ServerDeviceType.getText(), ServerDeviceName.getText(), ServerDeviceIP.getText(), ServerDeviceNetwork.getText(), "N/A");
				DevicesInList.set(index,newDevice);
				ConnectedFromlistModel.setElementAt("Server - " + ServerDeviceType.getText() + " - " + ServerDeviceName.getText() + "- " + ServerDeviceIP.getText() + "-" + ServerDeviceNetwork.getText(), index);
			    repaint();
			    
			    PopupPanel.setVisible(false);
			    ComputerAdd.removeActionListener(ComputerChangeListen);
	        	ServerAdd.removeActionListener(ServerChangeListen);
	        	RouterAdd.removeActionListener(RouterChangeListen);
	        	BridgeAdd.removeActionListener(BridgeChangeListen);
	        	
	        	ComputerAdd.addActionListener(ComputerListen);
	        	ServerAdd.addActionListener(ServerListen);
	        	RouterAdd.addActionListener(RouterListen);
	        	BridgeAdd.addActionListener(BridgeListen);
			}
		}
		
		//Listener for Add button which adds the info from the text fields to the JList
		class RouterChangeListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				int index = ConnectedFromlist.getSelectedIndex();
				newDevice = new Device("Router", RouterDeviceType.getText(), RouterDeviceName.getText(), RouterDeviceIP.getText(), RouterDeviceNetwork.getText(), "N/A");
				DevicesInList.set(index,newDevice);
				ConnectedFromlistModel.setElementAt("Router - " + RouterDeviceType.getText() + " - " + RouterDeviceName.getText() + "- " + RouterDeviceIP.getText() + "-" + RouterDeviceNetwork.getText(), index);
			    repaint();
			    
			    PopupPanel.setVisible(false);
			    ComputerAdd.removeActionListener(ComputerChangeListen);
	        	ServerAdd.removeActionListener(ServerChangeListen);
	        	RouterAdd.removeActionListener(RouterChangeListen);
	        	BridgeAdd.removeActionListener(BridgeChangeListen);
	        	
	        	ComputerAdd.addActionListener(ComputerListen);
	        	ServerAdd.addActionListener(ServerListen);
	        	RouterAdd.addActionListener(RouterListen);
	        	BridgeAdd.addActionListener(BridgeListen);
			}
		}
		
		class RouterAddListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				newDevice = new Device("Router", RouterDeviceType.getText(), RouterDeviceName.getText(), RouterDeviceIP.getText(), RouterDeviceNetwork.getText(), "N/A");
				DevicesInList.add(newDevice);
			    ConnectedFromlistModel.addElement("Router - " + RouterDeviceType.getText() + " - " + RouterDeviceName.getText() + "- " + RouterDeviceIP.getText() + "-" + RouterDeviceNetwork.getText());
			    repaint();
			    
			    PopupPanel.setVisible(false);
			}
		}
		
		//Listener for Add button which adds the info from the text fields to the JList
			class BridgeAddListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					newDevice = new Device("Bridge", BridgeDeviceType.getText(), BridgeDeviceName.getText(), BridgeDeviceIP.getText(), BridgeDeviceNetwork.getText(), "N/A");
					DevicesInList.add(newDevice);
					   ConnectedFromlistModel.addElement("Bridge - " + BridgeDeviceType.getText() + " - " + BridgeDeviceName.getText() + "- " + BridgeDeviceIP.getText() + "-" + BridgeDeviceNetwork.getText());
					   repaint();
					    
					   PopupPanel.setVisible(false);
				}
					
			}
				
			class BridgeChangeListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					int index = ConnectedFromlist.getSelectedIndex();
					newDevice = new Device("Bridge", BridgeDeviceType.getText(), BridgeDeviceName.getText(), BridgeDeviceIP.getText(), BridgeDeviceNetwork.getText(), "N/A");
					DevicesInList.set(index,newDevice);
					ConnectedFromlistModel.setElementAt("Bridge - " + BridgeDeviceType.getText() + " - " + BridgeDeviceName.getText() + "- " + BridgeDeviceIP.getText() + "-" + BridgeDeviceNetwork.getText(), index);
					repaint();
					    
					PopupPanel.setVisible(false);
					ComputerAdd.removeActionListener(ComputerChangeListen);
					ServerAdd.removeActionListener(ServerChangeListen);
			        RouterAdd.removeActionListener(RouterChangeListen);
			        BridgeAdd.removeActionListener(BridgeChangeListen);
			        	
			        ComputerAdd.addActionListener(ComputerListen);
			        ServerAdd.addActionListener(ServerListen);
			        RouterAdd.addActionListener(RouterListen);
			        BridgeAdd.addActionListener(BridgeListen);
					    
				}
					
			}
		
				
		//Listener for the Remove button which removes info from the JList
		class RemoveListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	int index = ConnectedFromlist.getSelectedIndex();
		        DevicesInList.remove(index);
		        ConnectedFromlistModel.remove(index);
		        repaint();
		        return;
	        }
	    }
		
		//Listener for Change button, which opens a JDialog to change information for a shape that's already in the list
		class ChangeListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	PopupPanel.setTitle("Change Device Details");
	        	ComputerAdd.removeActionListener(ComputerListen);
	        	ServerAdd.removeActionListener(ServerListen);
	        	RouterAdd.removeActionListener(RouterListen);
	        	BridgeAdd.removeActionListener(BridgeListen);
	        	
	        	ComputerAdd.addActionListener(ComputerChangeListen);
	        	ServerAdd.addActionListener(ServerChangeListen);
	        	RouterAdd.addActionListener(RouterChangeListen);
	        	BridgeAdd.addActionListener(BridgeChangeListen);
	        	PopupPanel.setVisible(true);
	        	repaint();
	        }
	    }

		//Listener to tell when the user selects on the JList	
		@Override
		public void valueChanged(ListSelectionEvent e) {
			JList<Object> list = (JList<Object>)e.getSource();
			if (list.isSelectionEmpty()){
				Change.setEnabled(false);
				Remove.setEnabled(false);
			}
			else{
				Change.setEnabled(true);
				Remove.setEnabled(true);
				int index = list.getSelectedIndex();
				if(DevicesInList.get(index).getType() == "Computer"){
					TabbedPanel.setSelectedIndex(0);
					ComputerDeviceName.setText(DevicesInList.get(index).getName());
					ComputerDeviceType.setText(DevicesInList.get(index).getType());
					ComputerDeviceIP.setText(DevicesInList.get(index).getIP());
					ComputerDeviceNetwork.setText(DevicesInList.get(index).getNetwork());
					
					Image Computerimg = null;
					try {
						Computerimg = ImageIO.read(getClass().getResource(DevicesInList.get(index).getIcon()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JLabel label = new JLabel();
					label.setIcon(new ImageIcon(Computerimg));
			        DrawPictures.add(label);
			        DrawPictures.repaint();
				}
				if(DevicesInList.get(index).getType() == "Server"){
					TabbedPanel.setSelectedIndex(1);
					ServerDeviceName.setText(DevicesInList.get(index).getName());
					ServerDeviceType.setText(DevicesInList.get(index).getType());
					ServerDeviceIP.setText(DevicesInList.get(index).getIP());
					ServerDeviceNetwork.setText(DevicesInList.get(index).getNetwork());
					
					Image Serverimg = null;
					try {
						Serverimg = ImageIO.read(getClass().getResource("icons/Server.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JLabel label = new JLabel();
					label.setIcon(new ImageIcon(Serverimg));
					DrawPictures.add(label);
					
				}
				if(DevicesInList.get(index).getType() == "Router"){
					TabbedPanel.setSelectedIndex(2);
					RouterDeviceName.setText(DevicesInList.get(index).getName());
					RouterDeviceType.setText(DevicesInList.get(index).getType());
					RouterDeviceIP.setText(DevicesInList.get(index).getIP());
					RouterDeviceNetwork.setText(DevicesInList.get(index).getNetwork());
					
					Image Routerimg = null;
					try {
						Routerimg = ImageIO.read(getClass().getResource("icons/Router.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JLabel label = new JLabel();
					label.setIcon(new ImageIcon(Routerimg));
					DrawPictures.add(label);
					
				}
				if(DevicesInList.get(index).getType() == "Bridge"){
					TabbedPanel.setSelectedIndex(3);
					BridgeDeviceName.setText(DevicesInList.get(index).getName());
					BridgeDeviceType.setText(DevicesInList.get(index).getType());
					BridgeDeviceIP.setText(DevicesInList.get(index).getIP());
					BridgeDeviceNetwork.setText(DevicesInList.get(index).getNetwork());
					
					Image Bridgeimg = null;
					try {
						Bridgeimg = ImageIO.read(getClass().getResource("icons/Switch.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JLabel label = new JLabel();
					label.setIcon(new ImageIcon(Bridgeimg));
					DrawPictures.add(label);
					
				}
			}
		}

		class PicturesPanel extends JPanel
	    {
	        //Render physical pictures
	        	
	        
	    }
		
		//Main Function that invokes Swing and makes the Main UI Visible
		public static void main(String[] args) {
	        
	        SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
	                Main ex = new Main();
	                ex.setVisible(true);
	        	}
	        });
		}
}