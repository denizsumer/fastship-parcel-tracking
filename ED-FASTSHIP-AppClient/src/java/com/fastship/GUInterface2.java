package com.fastship;

import com.fastship.entity.*;
import com.fastship.session.ManagementFacadeRemote;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * GUI class represents a graphical user interface to handle user input. It
 * calls appropriate logical methods from EJB.
 *
 * @author Deniz Sumer
 * @version v1.0.1816
 */
public class GUInterface2
{

    private final ManagementFacadeRemote facade;

    private JFrame frame;
    private Container container;
    private Insets insets;

    private JFrame addFrame;
    private JFrame removeFrame;
    private JFrame updateFrame;

    private JPanel topPanel;
    private JPanel subPanel;
    private JPanel logoPanel;
    private JPanel optionPanel;
    private JPanel displayPanel;

    private JPanel actionPanel;

    private TableModel tableModel;
    private JTable table;
    private TableRowSorter sorter;
    private RowFilter filter1;
    private RowFilter filter2;
    private JTextField searchTextField;
    private int searchColumn;
    private String searchRegex;
    private String comboData[];
    private JComboBox combo;
    private JComboBox typeComboBox;

    private Color topColor;

    private Dimension screenSize;
    private final int minFrameWidth = 1200;
    private final int minFrameHeight = 700;
    private final SimpleDateFormat sdf;
    private GUInterface2 gui;
    /**
     * Constructor for objects of class gui
     *
     * @param lib the LibSys object to be initialised
     */
    public GUInterface2(ManagementFacadeRemote facade)
    {
        this.gui = this;
        this.facade = facade;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        comboData = null;
        combo = null;
        this.sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        createFrame();
    }

    /**
     * CreateFrame method creates the GUI frame and initializes required
     * variables to run the GUI. This method called by the constructor.
     *
     */
    private void createFrame()
    {
        // CREATE FRAME
        frame = new JFrame("FastShip - ED Custom Software - Deniz Sumer 101527131");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // SET MIN FRAME SIZE AND SET FRAME TO CENTER OF THE SCREEN
        frame.setMinimumSize(new Dimension(minFrameWidth, minFrameHeight));
        insets = frame.getInsets();
        frame.setLocationRelativeTo(null);
        // MAKE MENU BAR
        makeMenuBar();
        // INITIALIZE MAIN PANEL AND SET LAYOUT
        container = frame.getContentPane();
        container.setBackground(Color.WHITE);
        frame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        // INITIALIZE COLORS FOR PANELS
        topColor = new Color(240, 240, 255);
        // MAKE TOP PANEL SUB PANEL AS HOME PAGE(LOGO)
        initTopPanel();
        initSubPanel(0);
        // PACK FRAME AND SET VISIBLE
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method initializes the top panel of GUI.
     *
     */
    private void initTopPanel()
    {
        // INITIALIZE TOP PANEL, SET LAYOUT AND COLOR
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(topColor);
        // ADD LOGO TO TOP PANEL
        try
        {
            topPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("./img/logo_top.png")))));
        }
        catch (IOException e)
        {
            // NO NEED TO DISTURB USER FOR THIS THING
        }
        // ADD PARCELS BUTTON TO TOP PANEL
        JButton parcelsButton = new JButton("Parcels");
        parcelsButton.addActionListener(new TopPanelListener());
        topPanel.add(parcelsButton);
        // ADD EVENTS BUTTON TO TOP PANEL
        JButton eventsButton = new JButton("Events");
        eventsButton.addActionListener(new TopPanelListener());
        topPanel.add(eventsButton);
        // ADD PAYMENTS BUTTON TO TOP PANEL
        JButton paymentsButton = new JButton("Payments");
        paymentsButton.addActionListener(new TopPanelListener());
        topPanel.add(paymentsButton);
        // ADD CLIENTS BUTTON TO TOP PANEL
        JButton clientsButton = new JButton("Clients");
        clientsButton.addActionListener(new TopPanelListener());
        topPanel.add(clientsButton);
        // SET MAXIMUM HEIGHT FOR TOP PANEL
        topPanel.setMaximumSize(new Dimension(frame.getWidth() + 100, 50));
        // ADD TOP PANEL TO FRAME
        frame.add(topPanel);
    }

    /**
     * TopPanelListener class is an implementation of ActionListener for the top
     * panel buttons.
     *
     */
    class TopPanelListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // CLEAR ALL SUBPANEL CONTENT
            subPanel.removeAll();
            // GET COMMAND OF ACTION (BUTTON TEXT)
            String command = e.getActionCommand();
            if (command.equals("Parcels"))
            {
                // ADD Parcels CONTENT TO SUBPANEL AND PACK FRAME
                initSubPanel(1);
                frame.pack();
            }
            if (command.equals("Events"))
            {
                // ADD Events CONTENT TO SUBPANEL AND PACK FRAME
                initSubPanel(2);
                frame.pack();
            }
            if (command.equals("Payments"))
            {
                // ADD Payments CONTENT TO SUBPANEL AND PACK FRAME
                initSubPanel(3);
                frame.pack();
            }
            if (command.equals("Clients"))
            {
                // ADD Events CONTENT TO SUBPANEL AND PACK FRAME
                initSubPanel(4);
                frame.pack();
            }
        }
    }

    /**
     * This method initializes the sub panel of GUI. Receives an integer as
     * selection parameter.
     *
     * @param selection the displayed page
     *
     */
    private void initSubPanel(int selection)
    {
        // INITIALIZE SUB PANEL AND SET LAYOUT
        subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
        // MAKE HOME (LOGO) SUB PANEL
        if (selection == 0)
        {
            initSubLogoPanel();
        }
        // MAKE PARCELS SUB PANELS
        if (selection == 1)
        {
            initSubOptionPanel(1);
        }
        // MAKE EVENTS SUB PANELS
        if (selection == 2)
        {
            initSubOptionPanel(2);
        }
        // MAKE PAYMENTS SUB PANELS
        if (selection == 3)
        {
            initSubOptionPanel(3);
        }
        // MAKE CLIENTS SUB PANELS
        if (selection == 4)
        {
            initSubOptionPanel(4);
        }
        // SET MINIMUM SIZE FOR SUBPANEL
        subPanel.setMinimumSize(new Dimension(insets.left, 550));
        // ADD SUBPANEL TO FRAME
        frame.add(subPanel);
    }

    /**
     * This method initializes the logo display on sub panel of GUI It displays
     * the logo on the screen as a home screen
     *
     */
    private void initSubLogoPanel()
    {
        // INITIALIZE LOGO PANEL
        logoPanel = new JPanel();
        // CREATE NEW JLABEL AND ASSIGN LOGO TO IT
        try
        {
            logoPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("./img/logo.png")))));
        }
        catch (IOException e)
        {
            // IMAGE NOT LOADED
        }
        // ADD LOGO PANEL TO SUBPANEL
        subPanel.add(logoPanel);

    }

    /**
     * This method initializes the sub option panel. Sub option panel used for
     * filtering purposes on the table. Receives an integer as selection
     * parameter.
     *
     * @param selection the displayed page
     *
     */
    private void initSubOptionPanel(int selection)
    {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));

        JPanel optionComboPanel = new JPanel();
        optionComboPanel.setLayout(new FlowLayout());
        optionComboPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        List<RowFilter<Object, Object>> filterList = new ArrayList<RowFilter<Object, Object>>(2);

        if (selection == 1)
        {
            // INITILIZE LABEL-1
            JLabel searchLabel1 = new JLabel("Search : ");
            // INITALIZE SEARCH TEXT FIELD
            searchTextField = new JTextField(30);
            // INITILIZE LABEL-2
            JLabel searchLabel2 = new JLabel(" in : ");
            // INITALIZE SEARCH COMBOBOX
            comboData = new String[]
            {
                "Tracking No", "From Client (Id)", "To Client (Id)", "Volume", "Weight", "Payment Id", "Service"
            };
            combo = new JComboBox(comboData);

            // ADD ITEM LISTENER FOR JComboBox typeComboBox
            combo.addItemListener(new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    if (e.getStateChange() == ItemEvent.SELECTED)
                    {
                        searchColumn = combo.getSelectedIndex();
                        searchRegex = searchTextField.getText();
                        filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                        RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                        sorter.setRowFilter(andFilter);
                        filterList.clear();
                    }
                }
            });
            // ADD DOCUMENT LISTENER FOR JTextField searchTextField
            searchTextField.getDocument().addDocumentListener(new DocumentListener()
            {
                @Override
                public void changedUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }
            });

            // ADD JOBJECTS TO SUBO-OPTION PANEL
            optionComboPanel.add(searchLabel1);
            optionComboPanel.add(searchTextField);
            optionComboPanel.add(searchLabel2);
            optionComboPanel.add(combo);
        }

        if (selection == 2)
        {
            // INITILIZE LABEL-1
            JLabel searchLabel1 = new JLabel("Search : ");
            // INITALIZE SEARCH TEXT FIELD
            searchTextField = new JTextField(30);
            // INITILIZE LABEL-2
            JLabel searchLabel2 = new JLabel(" in : ");
            // INITALIZE SEARCH COMBOBOX
            comboData = new String[]
            {
                "Event Id", "Event Time", "Tracking No", "Location", "Status", "Activity", "Additional Info"
            };
            combo = new JComboBox(comboData);

            // ADD ITEM LISTENER FOR JComboBox typeComboBox
            combo.addItemListener(new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    if (e.getStateChange() == ItemEvent.SELECTED)
                    {
                        searchColumn = combo.getSelectedIndex();
                        searchRegex = searchTextField.getText();
                        filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                        RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                        sorter.setRowFilter(andFilter);
                        filterList.clear();
                    }
                }
            });
            // ADD DOCUMENT LISTENER FOR JTextField searchTextField
            searchTextField.getDocument().addDocumentListener(new DocumentListener()
            {
                @Override
                public void changedUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }
            });

            // ADD JOBJECTS TO SUBO-OPTION PANEL
            optionComboPanel.add(searchLabel1);
            optionComboPanel.add(searchTextField);
            optionComboPanel.add(searchLabel2);
            optionComboPanel.add(combo);
        }

        if (selection == 3)
        {
            // INITILIZE LABEL-1
            JLabel searchLabel1 = new JLabel("Search : ");
            // INITALIZE SEARCH TEXT FIELD
            searchTextField = new JTextField(30);
            // INITILIZE LABEL-2
            JLabel searchLabel2 = new JLabel(" in : ");
            // INITALIZE SEARCH COMBOBOX
            comboData = new String[]
            {
                "Payment Id", "Debtor (Client) Id", "Amount", "Is Paid", "Reference"
            };
            combo = new JComboBox(comboData);

            // ADD ITEM LISTENER FOR JComboBox typeComboBox
            combo.addItemListener(new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    if (e.getStateChange() == ItemEvent.SELECTED)
                    {
                        searchColumn = combo.getSelectedIndex();
                        searchRegex = searchTextField.getText();
                        filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                        RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                        sorter.setRowFilter(andFilter);
                        filterList.clear();
                    }
                }
            });
            // ADD DOCUMENT LISTENER FOR JTextField searchTextField
            searchTextField.getDocument().addDocumentListener(new DocumentListener()
            {
                @Override
                public void changedUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }
            });

            // ADD JOBJECTS TO SUBO-OPTION PANEL
            optionComboPanel.add(searchLabel1);
            optionComboPanel.add(searchTextField);
            optionComboPanel.add(searchLabel2);
            optionComboPanel.add(combo);
        }

        if (selection == 4)
        {
            // INITILIZE LABEL-1
            JLabel searchLabel1 = new JLabel("Search : ");
            // INITALIZE SEARCH TEXT FIELD
            searchTextField = new JTextField(30);
            // INITILIZE LABEL-2
            JLabel searchLabel2 = new JLabel(" in : ");
            // INITALIZE SEARCH COMBOBOX
            comboData = new String[]
            {
                "Client Id", "Name", "PIC", "Phone", "Address", "Email", "Username"
            };
            combo = new JComboBox(comboData);

            // ADD ITEM LISTENER FOR JComboBox typeComboBox
            combo.addItemListener(new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    if (e.getStateChange() == ItemEvent.SELECTED)
                    {
                        searchColumn = combo.getSelectedIndex();
                        searchRegex = searchTextField.getText();
                        filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                        RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                        sorter.setRowFilter(andFilter);
                        filterList.clear();
                    }
                }
            });
            // ADD DOCUMENT LISTENER FOR JTextField searchTextField
            searchTextField.getDocument().addDocumentListener(new DocumentListener()
            {
                @Override
                public void changedUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    searchColumn = combo.getSelectedIndex();
                    searchRegex = searchTextField.getText();
                    filterList.add(RowFilter.regexFilter(searchRegex, searchColumn));
                    RowFilter<Object, Object> andFilter = RowFilter.andFilter(filterList);
                    sorter.setRowFilter(andFilter);
                    filterList.clear();
                }
            });

            // ADD JOBJECTS TO SUBO-OPTION PANEL
            optionComboPanel.add(searchLabel1);
            optionComboPanel.add(searchTextField);
            optionComboPanel.add(searchLabel2);
            optionComboPanel.add(combo);
        }

        optionPanel.add(optionComboPanel);

        optionPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 100));

        subPanel.add(optionPanel);
        initSubDisplayPanel(selection);
        initSubActionPanel(selection);
    }

    /**
     * This method initializes the sub display panel. Sub display panel is the
     * screen that displays data as table. Receives an integer as selection
     * parameter. selection 0 is logo display selection 1 is Books display
     * selection 2 is Transactions display
     *
     * @param selection the displayed page
     *
     */
    private void initSubDisplayPanel(int selection)
    {
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        String[] header = null;
        String[][] data = null;
        int[] columnSize = null;
        String actualReturn = null;

        if (selection == 1)
        {
            try
            {
                ArrayList<ParcelDTO> parcelList = facade.displayAllParcel();
                header = new String[]
                {
                    "Tracking No", "From Client (Id)", "To Client (Id)", "Volume", "Weight", "Payment Id", "Service"
                };
                data = new String[parcelList.size()][header.length];
                for (int i = 0; i < parcelList.size(); i++)
                {
                    ParcelDTO p = parcelList.get(i);
                    data[i][0] = p.getTrackingNo();
                    data[i][1] = p.getFromClientId();
                    data[i][2] = p.getToClientId();
                    data[i][3] = Integer.toString(p.getVolume());
                    data[i][4] = Integer.toString(p.getWeight());
                    data[i][5] = p.getPaymentId();
                    data[i][6] = p.getService();
                }
                columnSize = new int[]
                {
                    100, 100, 100, 100, 100, 100
                };
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (selection == 2)
        {
            try
            {
                ArrayList<EventDTO> eventList = facade.displayAllEvent();
                header = new String[]
                {
                    "Event Id", "Event Time", "Tracking No", "Location", "Status", "Activity", "Additional Info"
                };
                data = new String[eventList.size()][header.length];
                for (int i = 0; i < eventList.size(); i++)
                {
                    EventDTO e = eventList.get(i);
                    data[i][0] = e.getEventId();
                    data[i][1] = sdf.format(e.getEventTime().getTime());
                    data[i][2] = e.getTrackingNo();
                    data[i][3] = e.getLocation();
                    data[i][4] = e.getStatus();
                    data[i][5] = e.getActivity();
                    data[i][6] = e.getAdditionalInfo();
                }
                columnSize = new int[]
                {
                    100, 100, 100, 100, 100, 100
                };
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        if (selection == 3)
        {
            try
            {
                ArrayList<PaymentDTO> paymentList = facade.displayAllPayment();
                header = new String[]
                {
                    "Payment Id", "Debtor (Client) Id", "Amount", "Is Paid", "Reference"
                };
                data = new String[paymentList.size()][header.length];
                for (int i = 0; i < paymentList.size(); i++)
                {
                    PaymentDTO p = paymentList.get(i);

                    data[i][0] = p.getPaymentId();
                    data[i][1] = p.getDebtor();
                    data[i][2] = p.getAmount().toString();
                    Boolean paid = p.getIsPaid();
                    data[i][3] = paid.toString();
                    data[i][4] = p.getReference();
                }
                columnSize = new int[]
                {
                    100, 100, 100, 100
                };
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (selection == 4)
        {
            try
            {
                ArrayList<ClientDTO> clientList = facade.displayAllClient();

                header = new String[]
                {
                    "Client Id", "Name", "PIC", "Phone", "Address", "Email", "Username"
                };
                data = new String[clientList.size()][header.length];
                for (int i = 0; i < clientList.size(); i++)
                {
                    ClientDTO c = clientList.get(i);

                    data[i][0] = c.getClientId();
                    data[i][1] = c.getName();
                    data[i][2] = c.getPic();
                    data[i][3] = c.getPhone();
                    data[i][4] = c.getAddress();
                    data[i][5] = c.getEmail();
                    data[i][6] = c.getUsername();
                }
                columnSize = new int[]
                {
                    100, 100, 100, 100, 100, 100
                };
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        tableModel = new DefaultTableModel(data, header)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;// This causes all cells to be not editable
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter(tableModel);
        table.setRowSorter(sorter);
        table.getTableHeader().setReorderingAllowed(false);

        int[] selectedRow = table.getSelectedRows();
        for (int i = 0; i < selectedRow.length; i++)
        {
            selectedRow[i] = table.convertRowIndexToModel(selectedRow[i]);
        }

        for (int c = 0; c < columnSize.length; c++)
        {
            table.getColumnModel().getColumn(c).setPreferredWidth(columnSize[c]);
        }

        // ADD TABLE TO DISPLAY PANEL AND SET SIZES
        displayPanel.add(new JScrollPane(table));

        displayPanel.setMinimumSize(new Dimension(frame.getWidth() - 100, 550));

        // ADD DISPLAY PANEL TO SUBPANEL
        subPanel.add(displayPanel);

    }

    /**
     * This method initializes the sub action panel. Sub action panel is used
     * for changing data purposes on the table such as adding / removing and
     * setting. Receives an integer as selection parameter. selection 0 is logo
     * display selection 1 is Books display selection 2 is Transactions display
     *
     * @param selection the displayed page
     *
     */
    private void initSubActionPanel(int selection)
    {
        // INITIALIZE SUB-ACTION PANEL
        actionPanel = new JPanel();
        actionPanel.setBackground(Color.WHITE);
        actionPanel.setLayout(new FlowLayout());
        // INITIALIZE NULL SUB-ACTION PANEL BUTTONS
        JButton addButton = null;
        JButton removeButton = null;
        JButton updateButton = null;
        // ASSIGN BUTTONS AS PER SELECTION
        if (selection == 1) // SELECTION 1: PARCEL ACTION PANEL
        {
            // ADD BUTTONS TO SUB-ACTION PANEL
            addButton = new JButton("Add Parcel");
            addButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(addButton);
            removeButton = new JButton("Remove Parcel");
            removeButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(removeButton);
            updateButton = new JButton("Update Parcel");
            updateButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(updateButton);
        }
        if (selection == 2) // SELECTION 2: EVENT ACTION PANEL
        {
            addButton = new JButton("Add Event");
            addButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(addButton);
            removeButton = new JButton("Remove Event");
            removeButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(removeButton);
            updateButton = new JButton("Update Event");
            updateButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(updateButton);
        }
        if (selection == 3) // SELECTION 3: PAYMENT ACTION PANEL
        {
            addButton = new JButton("Add Payment");
            addButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(addButton);
            removeButton = new JButton("Remove Payment");
            removeButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(removeButton);
            updateButton = new JButton("Update Payment");
            updateButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(updateButton);
        }
        if (selection == 4) // SELECTION 3: CLIENT ACTION PANEL
        {
            addButton = new JButton("Add Client");
            addButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(addButton);
            removeButton = new JButton("Remove Client");
            removeButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(removeButton);
            updateButton = new JButton("Update Client");
            updateButton.addActionListener(new SubActionPanelListener());
            actionPanel.add(updateButton);
        }

        // SET MAXIMUM HEIGHT
        actionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        // ADD SUB-ACTION PANEL TO SUBPANEL
        subPanel.add(actionPanel);
    }

    /**
     * SubActionPanelListener class is an implementation of ActionListener for
     * the sub action panel buttons.
     *
     */
    class SubActionPanelListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // GET COMMAND OF ACTION (BUTTON TEXT)
            String command = e.getActionCommand();
            //parcel
            if (command.equals("Add Parcel"))
            {
                initAddParcelFrame();
                subPanel.removeAll();
                initSubPanel(1);
                frame.pack();
            }
            if (command.equals("Remove Parcel"))
            {
                initRemoveParcelFrame();
                subPanel.removeAll();
                initSubPanel(1);
                frame.pack();
            }
            if (command.equals("Update Parcel"))
            {
                initUpdateParcelFrame();
                subPanel.removeAll();
                initSubPanel(1);
                frame.pack();
            }
            //event
            if (command.equals("Add Event"))
            {
                initAddEventFrame();
                subPanel.removeAll();
                initSubPanel(2);
                frame.pack();
            }
            if (command.equals("Remove Event"))
            {
                initRemoveEventFrame();
                subPanel.removeAll();
                initSubPanel(2);
                frame.pack();
            }
            if (command.equals("Update Event"))
            {
                initUpdateEventFrame();
                subPanel.removeAll();
                initSubPanel(2);
                frame.pack();
            }
            //payment
            if (command.equals("Add Payment"))
            {
                initAddPaymentFrame();
                subPanel.removeAll();
                initSubPanel(3);
                frame.pack();
            }
            if (command.equals("Remove Payment"))
            {
                initRemovePaymentFrame();
                subPanel.removeAll();
                initSubPanel(3);
                frame.pack();
            }
            if (command.equals("Update Payment"))
            {
                initUpdatePaymentFrame();
                subPanel.removeAll();
                initSubPanel(3);
                frame.pack();
            }
            //client
            if (command.equals("Add Client"))
            {
                initAddClientFrame();
                subPanel.removeAll();
                initSubPanel(4);
                frame.pack();
            }
            if (command.equals("Remove Client"))
            {
                initRemoveClientFrame();
                subPanel.removeAll();
                initSubPanel(4);
                frame.pack();
            }
            if (command.equals("Update Client"))
            {
                initUpdateClientFrame();
                subPanel.removeAll();
                initSubPanel(4);
                frame.pack();
            }

        }
    }

    private void initAddParcelFrame()
    {
        addFrame = new JFrame("Add Parcel");
        addFrame.setLocationRelativeTo(null);
        addFrame.setMinimumSize(new Dimension(350, 350));
        Container addFrameContainer = addFrame.getContentPane();
        addFrame.setLayout(new BoxLayout(addFrameContainer, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("From Client : ");
        ArrayList<ClientDTO> clientList = facade.displayAllClient();
        String[] comboData = new String[clientList.size()];
        for (int i = 0; i < clientList.size(); i++)
        {
            comboData[i] = clientList.get(i).getClientId();
        }
        JComboBox combo1 = new JComboBox(comboData);
        addFrame.add(label1);
        addFrame.add(combo1);

        JLabel label2 = new JLabel("To Client : ");
        JComboBox combo2 = new JComboBox(comboData);
        addFrame.add(label2);
        addFrame.add(combo2);

        JLabel label3 = new JLabel("Volume : ");
        JTextField text3 = new JTextField(20);
        addFrame.add(label3);
        addFrame.add(text3);

        JLabel label4 = new JLabel("Weight : ");
        JTextField text4 = new JTextField(20);
        addFrame.add(label4);
        addFrame.add(text4);

        JLabel label5 = new JLabel("Payment Id : ");
        ArrayList<PaymentDTO> paymentList = facade.displayAllPayment();
        String[] comboData5 = new String[paymentList.size()];
        for (int i = 0; i < paymentList.size(); i++)
        {
            comboData5[i] = paymentList.get(i).getPaymentId();
        }
        JComboBox combo5 = new JComboBox(comboData5);
        addFrame.add(label5);
        addFrame.add(combo5);

        JLabel label6 = new JLabel("Service Type : ");
        String[] comboData6 = new String[]
        {
            "Standard", "Express"
        };
        JComboBox combo6 = new JComboBox(comboData6);
        addFrame.add(label6);
        addFrame.add(combo6);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int volume = 0, weight = 0;
                try
                {
                    volume = Integer.parseInt(text3.getText());
                    weight = Integer.parseInt(text4.getText());
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Use only numerical values for volume and weight.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String fromClient = combo1.getSelectedItem().toString();
                String toClient = combo2.getSelectedItem().toString();
                String paymentId = combo5.getSelectedItem().toString();
                String service = combo6.getSelectedItem().toString();

                ParcelDTO parcel = new ParcelDTO("XXXXXXXX", fromClient, toClient, volume, weight, paymentId, service);
                try
                {
                    String trackingNo = facade.createParcel(parcel);
                    EventDTO event = new EventDTO("XXXXXXXX", Calendar.getInstance(), trackingNo, "Online", "Information Received", "Parcel Added on System", "Parcel Added on System");
                    facade.createEvent(event);
                    JOptionPane.showMessageDialog(null, "Parcel added with tracking number : " + trackingNo);
                    addFrame.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...",
                            "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addFrame.dispose();
            }
        });
        addFrame.add(buttonPanel);
        addFrame.pack();
        addFrame.setVisible(true);
    }

    private void initRemoveParcelFrame()
    {
        // CHECK IF ROW IS SELECTED
        if (table.getSelectedRowCount() == 0)
        {
            JOptionPane.showMessageDialog(frame, "Select a parcel from table to remove.", "Select Parcel", JOptionPane.ERROR_MESSAGE);
            return; // RETURN TO MAIN FRAME
        }
        // GET THE SELECTED ROW
        String parcelId = table.getValueAt(table.getSelectedRow(), 0).toString();
        boolean result = false;
        try
        {
            result = facade.removeParcel(parcelId);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...", "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (result)
        {
            JOptionPane.showMessageDialog(null, "Parcel deleted");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Unable to remove parcel. \nTry again later...", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initUpdateParcelFrame()
    {
        // CHECK IF ROW IS SELECTED
        if (table.getSelectedRowCount() == 0)
        {
            JOptionPane.showMessageDialog(frame, "Select a parcel from table to update.", "Select Parcel", JOptionPane.ERROR_MESSAGE);
            return; // RETURN TO MAIN FRAME
        }
        // GET THE SELECTED ROW
        String trackingNo = table.getValueAt(table.getSelectedRow(), 0).toString();
        ParcelDTO parcel = facade.displayParcel(trackingNo);

        updateFrame = new JFrame("Update Parcel");
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setMinimumSize(new Dimension(350, 350));
        Container updateFrameContainer = updateFrame.getContentPane();
        updateFrame.setLayout(new BoxLayout(updateFrameContainer, BoxLayout.Y_AXIS));

        JLabel label0 = new JLabel("Tracking Number : " + trackingNo);
        updateFrame.add(label0);

        JLabel label1 = new JLabel("From Client : ");
        ArrayList<ClientDTO> clientList = facade.displayAllClient();
        String[] comboData = new String[clientList.size() + 2];
        comboData[0] = parcel.getFromClientId();
        comboData[1] = parcel.getToClientId();
        for (int i = 0; i < clientList.size(); i++)
        {
            comboData[i + 2] = clientList.get(i).getClientId();
        }
        JComboBox combo1 = new JComboBox(comboData);
        combo1.setSelectedIndex(0);
        updateFrame.add(label1);
        updateFrame.add(combo1);

        JLabel label2 = new JLabel("To Client : ");
        JComboBox combo2 = new JComboBox(comboData);
        combo2.setSelectedIndex(1);
        updateFrame.add(label2);
        updateFrame.add(combo2);

        JLabel label3 = new JLabel("Volume : ");
        JTextField text3 = new JTextField(20);
        text3.setText(Integer.toString(parcel.getVolume()));
        updateFrame.add(label3);
        updateFrame.add(text3);

        JLabel label4 = new JLabel("Weight : ");
        JTextField text4 = new JTextField(20);
        text4.setText(Integer.toString(parcel.getWeight()));
        updateFrame.add(label4);
        updateFrame.add(text4);

        JLabel label5 = new JLabel("Payment Id : ");
        ArrayList<PaymentDTO> paymentList = facade.displayAllPayment();
        String[] comboData5 = new String[paymentList.size() + 1];
        comboData5[0] = parcel.getPaymentId();
        for (int i = 0; i < paymentList.size(); i++)
        {
            comboData5[i + 1] = paymentList.get(i).getPaymentId();
        }
        JComboBox combo5 = new JComboBox(comboData5);
        combo5.setSelectedIndex(0);
        updateFrame.add(label5);
        updateFrame.add(combo5);

        JLabel label6 = new JLabel("Service Type : ");
        String[] comboData6 = new String[]
        {
            parcel.getService(), "Standard", "Express"
        };
        JComboBox combo6 = new JComboBox(comboData6);
        combo6.setSelectedIndex(0);
        updateFrame.add(label6);
        updateFrame.add(combo6);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Update");
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int volume = 0, weight = 0;
                try
                {
                    volume = Integer.parseInt(text3.getText());
                    weight = Integer.parseInt(text4.getText());
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Use only numerical values for volume and weight.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String fromClient = combo1.getSelectedItem().toString();
                String toClient = combo2.getSelectedItem().toString();
                String paymentId = combo5.getSelectedItem().toString();
                String service = combo6.getSelectedItem().toString();

                ParcelDTO newParcel = new ParcelDTO(trackingNo, fromClient, toClient, volume, weight, paymentId, service);
                boolean result = false;
                try
                {
                    result = facade.updateParcel(newParcel);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...",
                            "System Error", JOptionPane.ERROR_MESSAGE);
                }
                if (result)
                {
                    JOptionPane.showMessageDialog(null, "Parcel updated with tracking number : " + trackingNo);
                    updateFrame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Unable to update parcel. \nTry again later...", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateFrame.dispose();
            }
        });
        updateFrame.add(buttonPanel);
        updateFrame.pack();
        updateFrame.setVisible(true);
    }

    private void initAddEventFrame()
    {
        addFrame = new JFrame("Add Event");
        addFrame.setLocationRelativeTo(null);
        addFrame.setMinimumSize(new Dimension(350, 350));
        Container addFrameContainer = addFrame.getContentPane();
        addFrame.setLayout(new BoxLayout(addFrameContainer, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Date : ");
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());
        JTextField day = new JTextField(6);
        String[] comboData1 = new String[]
        {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };
        JComboBox combo1 = new JComboBox(comboData1);
        JTextField year = new JTextField(6);
        datePanel.add(day);
        datePanel.add(combo1);
        datePanel.add(year);
        addFrame.add(label1);
        addFrame.add(datePanel);

        JLabel label2 = new JLabel("Time : ");
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());
        JTextField hrs = new JTextField(6);
        JLabel hrsSep = new JLabel(" : ");
        JTextField minutes = new JTextField(6);
        timePanel.add(hrs);
        timePanel.add(hrsSep);
        timePanel.add(minutes);
        addFrame.add(label2);
        addFrame.add(timePanel);

        JLabel label3 = new JLabel("Tracking No : ");
        ArrayList<ParcelDTO> parcelList = facade.displayAllParcel();
        String[] comboData3 = new String[parcelList.size()];
        for (int i = 0; i < parcelList.size(); i++)
        {
            comboData3[i] = parcelList.get(i).getTrackingNo();
        }
        JComboBox combo3 = new JComboBox(comboData3);
        addFrame.add(label3);
        addFrame.add(combo3);

        JLabel label4 = new JLabel("Location : ");
        JTextField text4 = new JTextField(20);
        addFrame.add(label4);
        addFrame.add(text4);

        JLabel label5 = new JLabel("Activity : ");
        JTextField text5 = new JTextField(20);
        addFrame.add(label5);
        addFrame.add(text5);

        JLabel label6 = new JLabel("Additional Info : ");
        JTextField text6 = new JTextField(20);
        addFrame.add(label6);
        addFrame.add(text6);

        JLabel label7 = new JLabel("Activity : ");
        JTextField text7 = new JTextField(20);
        addFrame.add(label7);
        addFrame.add(text7);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Calendar eventTime = null;
                try
                {
                    //convert date time
                    String dateStr = day.getText() + "-" + combo1.getSelectedItem().toString() + "-" + year.getText();
                    String timeStr = hrs.getText() + ":" + minutes.getText();
                    String calStr = dateStr + " " + timeStr;
                    eventTime = Calendar.getInstance();
                    eventTime.setTime(sdf.parse(calStr));
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Date or time is invalid.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String trackingNo = combo3.getSelectedItem().toString();
                String location = text4.getText();
                String status = text5.getText();
                String activity = text6.getText();
                String additionalInfo = text7.getText();

                EventDTO event = new EventDTO("XXXXXXXX", eventTime, trackingNo, location, status, activity, additionalInfo);
                try
                {
                    String eventId = facade.createEvent(event);
                    JOptionPane.showMessageDialog(null, "Event added with id number : " + eventId);
                    addFrame.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...",
                            "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(
                new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addFrame.dispose();
            }
        }
        );
        addFrame.add(buttonPanel);
        addFrame.pack();
        addFrame.setVisible(true);
    }

    private void initRemoveEventFrame()
    {
        // CHECK IF ROW IS SELECTED
        if (table.getSelectedRowCount() == 0)
        {
            JOptionPane.showMessageDialog(frame, "Select an event from table to remove.", "Select Event", JOptionPane.ERROR_MESSAGE);
            return; // RETURN TO MAIN FRAME
        }
        // GET THE SELECTED ROW
        String eventId = table.getValueAt(table.getSelectedRow(), 0).toString();
        boolean result = false;
        try
        {
            result = facade.removeEvent(eventId);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...", "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (result)
        {
            JOptionPane.showMessageDialog(null, "Event deleted");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Unable to delete event. \nTry again later...", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initUpdateEventFrame()
    {
        // CHECK IF ROW IS SELECTED
        if (table.getSelectedRowCount() == 0)
        {
            JOptionPane.showMessageDialog(frame, "Select a parcel from table to update.", "Select Parcel", JOptionPane.ERROR_MESSAGE);
            return; // RETURN TO MAIN FRAME
        }
        // GET THE SELECTED ROW
        String eventId = table.getValueAt(table.getSelectedRow(), 0).toString();
        EventDTO event = facade.displayEvent(eventId);

        updateFrame = new JFrame("Update Event");
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setMinimumSize(new Dimension(350, 350));
        Container updateFrameContainer = updateFrame.getContentPane();
        updateFrame.setLayout(new BoxLayout(updateFrameContainer, BoxLayout.Y_AXIS));

        JLabel label0 = new JLabel("Event Id : " + eventId);
        updateFrame.add(label0);

        JLabel label1 = new JLabel("Date : ");
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());
        JTextField day = new JTextField(6);
        String[] comboData1 = new String[]
        {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };
        JComboBox combo1 = new JComboBox(comboData1);
        JTextField year = new JTextField(6);
        day.setText(new SimpleDateFormat("dd").format(event.getEventTime().getTime()));
        combo1.setSelectedItem(new SimpleDateFormat("MMM").format(event.getEventTime().getTime()));
        year.setText(new SimpleDateFormat("yyyy").format(event.getEventTime().getTime()));
        datePanel.add(day);
        datePanel.add(combo1);
        datePanel.add(year);
        updateFrame.add(label1);
        updateFrame.add(datePanel);

        JLabel label2 = new JLabel("Time : ");
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());
        JTextField hrs = new JTextField(6);
        JLabel hrsSep = new JLabel(" : ");
        JTextField minutes = new JTextField(6);
        hrs.setText(new SimpleDateFormat("HH").format(event.getEventTime().getTime()));
        minutes.setText(new SimpleDateFormat("mm").format(event.getEventTime().getTime()));
        timePanel.add(hrs);
        timePanel.add(hrsSep);
        timePanel.add(minutes);
        updateFrame.add(label2);
        updateFrame.add(timePanel);

        JLabel label3 = new JLabel("Tracking No : ");
        ArrayList<ParcelDTO> parcelList = facade.displayAllParcel();
        String[] comboData3 = new String[parcelList.size() + 1];
        comboData3[0] = event.getTrackingNo();
        for (int i = 0; i < parcelList.size(); i++)
        {
            comboData3[i + 1] = parcelList.get(i).getTrackingNo();
        }
        JComboBox combo3 = new JComboBox(comboData3);
        combo3.setSelectedIndex(0);
        updateFrame.add(label3);
        updateFrame.add(combo3);

        JLabel label4 = new JLabel("Location : ");
        JTextField text4 = new JTextField(20);
        text4.setText(event.getLocation());
        updateFrame.add(label4);
        updateFrame.add(text4);

        JLabel label5 = new JLabel("Status : ");
        JTextField text5 = new JTextField(20);
        text5.setText(event.getStatus());
        updateFrame.add(label5);
        updateFrame.add(text5);

        JLabel label6 = new JLabel("Activity : ");
        JTextField text6 = new JTextField(20);
        text6.setText(event.getActivity());
        updateFrame.add(label6);
        updateFrame.add(text6);

        JLabel label7 = new JLabel("Additional Info : ");
        JTextField text7 = new JTextField(20);
        text7.setText(event.getAdditionalInfo());
        updateFrame.add(label7);
        updateFrame.add(text7);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Update");
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Calendar eventTime = null;
                try
                {
                    //convert date time
                    String dateStr = day.getText() + "-" + combo1.getSelectedItem().toString() + "-" + year.getText();
                    String timeStr = hrs.getText() + ":" + minutes.getText();
                    String calStr = dateStr + " " + timeStr;
                    eventTime = Calendar.getInstance();
                    eventTime.setTime(sdf.parse(calStr));
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Date or time is invalid.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String trackingNo = combo3.getSelectedItem().toString();
                String location = text4.getText();
                String status = text5.getText();
                String activity = text6.getText();
                String additionalInfo = text7.getText();

                EventDTO newEvent = new EventDTO(eventId, eventTime, trackingNo, location, status, activity, additionalInfo);
                boolean result = false;
                try
                {
                    result = facade.updateEvent(newEvent);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...",
                            "System Error", JOptionPane.ERROR_MESSAGE);
                }
                if (result)
                {
                    JOptionPane.showMessageDialog(null, "Event updated with id number : " + eventId);
                    updateFrame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Unable to update event. \nTry again later...", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(
                new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateFrame.dispose();
            }
        }
        );
        updateFrame.add(buttonPanel);
        updateFrame.pack();
        updateFrame.setVisible(true);
    }

    private void initAddPaymentFrame()
    {
        addFrame = new JFrame("Add Payment");
        addFrame.setLocationRelativeTo(null);
        addFrame.setMinimumSize(new Dimension(350, 350));
        Container addFrameContainer = addFrame.getContentPane();
        addFrame.setLayout(new BoxLayout(addFrameContainer, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Debtor : ");
        ArrayList<ClientDTO> clientList = facade.displayAllClient();
        String[] comboData = new String[clientList.size()];
        for (int i = 0; i < clientList.size(); i++)
        {
            comboData[i] = clientList.get(i).getClientId();
        }
        JComboBox combo1 = new JComboBox(comboData);
        addFrame.add(label1);
        addFrame.add(combo1);

        JLabel label2 = new JLabel("Amount : ");
        JTextField text2 = new JTextField(20);
        addFrame.add(label2);
        addFrame.add(text2);

        JLabel label3 = new JLabel("Is Paid? : ");
        JCheckBox box3 = new JCheckBox();
        addFrame.add(label3);
        addFrame.add(box3);

        JLabel label4 = new JLabel("Reference : ");
        JTextField text4 = new JTextField(20);
        addFrame.add(label4);
        addFrame.add(text4);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //amount to bigdecimal
                BigDecimal amount = new BigDecimal(0);
                try
                {
                    amount = new BigDecimal(text2.getText());
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Use only numerical values for amount.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String debtor = combo1.getSelectedItem().toString();
                Boolean isPaid = box3.isSelected();
                String reference = text4.getText();

                PaymentDTO payment = new PaymentDTO("XXXXXXXX", debtor, amount, isPaid, reference);
                try
                {
                    String paymentId = facade.createPayment(payment);
                    JOptionPane.showMessageDialog(null, "Payment added with id number : " + paymentId);
                    addFrame.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...",
                            "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addFrame.dispose();
            }
        });
        addFrame.add(buttonPanel);
        addFrame.pack();
        addFrame.setVisible(true);
    }

    private void initRemovePaymentFrame()
    {
        // CHECK IF ROW IS SELECTED
        if (table.getSelectedRowCount() == 0)
        {
            JOptionPane.showMessageDialog(frame, "Select a payment from table to remove.", "Select Payment", JOptionPane.ERROR_MESSAGE);
            return; // RETURN TO MAIN FRAME
        }
        // GET THE SELECTED ROW
        String paymentId = table.getValueAt(table.getSelectedRow(), 0).toString();
        boolean result = false;
        try
        {
            result = facade.removePayment(paymentId);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...", "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (result)
        {
            JOptionPane.showMessageDialog(null, "Payment deleted");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Unable to delete payment. \nTry again later...", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initUpdatePaymentFrame()
    {
        // CHECK IF ROW IS SELECTED
        if (table.getSelectedRowCount() == 0)
        {
            JOptionPane.showMessageDialog(frame, "Select a payment from table to update.", "Select Payment", JOptionPane.ERROR_MESSAGE);
            return; // RETURN TO MAIN FRAME
        }
        // GET THE SELECTED ROW
        String paymentId = table.getValueAt(table.getSelectedRow(), 0).toString();
        PaymentDTO payment = facade.displayPayment(paymentId);

        updateFrame = new JFrame("Update Payment");
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setMinimumSize(new Dimension(350, 350));
        Container updateFrameContainer = updateFrame.getContentPane();
        updateFrame.setLayout(new BoxLayout(updateFrameContainer, BoxLayout.Y_AXIS));

        JLabel label0 = new JLabel("Payment Id : " + paymentId);
        updateFrame.add(label0);

        JLabel label1 = new JLabel("Debtor : ");
        ArrayList<ClientDTO> clientList = facade.displayAllClient();
        String[] comboData = new String[clientList.size() + 1];
        comboData[0] = payment.getDebtor();
        for (int i = 0; i < clientList.size(); i++)
        {
            comboData[i + 1] = clientList.get(i).getClientId();
        }
        JComboBox combo1 = new JComboBox(comboData);
        updateFrame.add(label1);
        updateFrame.add(combo1);

        JLabel label2 = new JLabel("Amount : ");
        JTextField text2 = new JTextField(20);
        text2.setText(payment.getAmount().toString());
        updateFrame.add(label2);
        updateFrame.add(text2);

        JLabel label3 = new JLabel("Is Paid? : ");
        JCheckBox box3 = new JCheckBox();
        box3.setSelected(payment.getIsPaid());
        updateFrame.add(label3);
        updateFrame.add(box3);

        JLabel label4 = new JLabel("Reference : ");
        JTextField text4 = new JTextField(20);
        text4.setText(payment.getReference());
        updateFrame.add(label4);
        updateFrame.add(text4);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Update");
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //amount to bigdecimal
                BigDecimal amount = new BigDecimal(0);
                try
                {
                    amount = new BigDecimal(text2.getText());
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Use only numerical values for amount.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String debtor = combo1.getSelectedItem().toString();
                Boolean isPaid = box3.isSelected();
                String reference = text4.getText();

                PaymentDTO newPayment = new PaymentDTO(paymentId, debtor, amount, isPaid, reference);
                boolean result = false;
                try
                {
                    result = facade.updatePayment(newPayment);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...",
                            "System Error", JOptionPane.ERROR_MESSAGE);
                }
                if (result)
                {
                    JOptionPane.showMessageDialog(null, "Payment updated with id number : " + paymentId);
                    updateFrame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Unable to update payment. \nTry again later...", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateFrame.dispose();
            }
        });
        updateFrame.add(buttonPanel);
        updateFrame.pack();
        updateFrame.setVisible(true);
    }

    private void initAddClientFrame()
    {
        addFrame = new JFrame("Add Client");
        addFrame.setLocationRelativeTo(null);
        addFrame.setMinimumSize(new Dimension(350, 350));
        Container addFrameContainer = addFrame.getContentPane();
        addFrame.setLayout(new BoxLayout(addFrameContainer, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Name : ");
        JTextField text1 = new JTextField(20);
        addFrame.add(label1);
        addFrame.add(text1);

        JLabel label2 = new JLabel("Person in Contact : ");
        JTextField text2 = new JTextField(20);
        addFrame.add(label2);
        addFrame.add(text2);

        JLabel label3 = new JLabel("Phone : ");
        JTextField text3 = new JTextField(20);
        addFrame.add(label3);
        addFrame.add(text3);

        JLabel label4 = new JLabel("Address : ");
        JTextField text4 = new JTextField(20);
        addFrame.add(label4);
        addFrame.add(text4);

        JLabel label5 = new JLabel("Email : ");
        JTextField text5 = new JTextField(20);
        addFrame.add(label5);
        addFrame.add(text5);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ClientDTO client = new ClientDTO();
                client.setName(text1.getText());
                client.setPic(text2.getText());
                client.setPhone(text3.getText());
                client.setAddress(text4.getText());
                client.setEmail(text5.getText());
                client.setUsername(null);
                client.setPassword(null);
                client.setUsergroup("Client");

                try
                {
                    String clientId = facade.createClient(client);
                    JOptionPane.showMessageDialog(null, "Client added with id number : " + clientId);
                    addFrame.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...",
                            "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addFrame.dispose();
            }
        });
        addFrame.add(buttonPanel);
        addFrame.pack();
        addFrame.setVisible(true);
    }

    private void initRemoveClientFrame()
    {
        // CHECK IF ROW IS SELECTED
        if (table.getSelectedRowCount() == 0)
        {
            JOptionPane.showMessageDialog(frame, "Select a client from table to remove.", "Select Client", JOptionPane.ERROR_MESSAGE);
            return; // RETURN TO MAIN FRAME
        }
        // GET THE SELECTED ROW
        String clientId = table.getValueAt(table.getSelectedRow(), 0).toString();
        boolean result = false;
        try
        {
            result = facade.removeClient(clientId);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...", "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (result)
        {
            JOptionPane.showMessageDialog(null, "Client deleted");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Unable to remove client. \nTry again later...", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initUpdateClientFrame()
    {
        // CHECK IF ROW IS SELECTED
        if (table.getSelectedRowCount() == 0)
        {
            JOptionPane.showMessageDialog(frame, "Select a client from table to update.", "Select Client", JOptionPane.ERROR_MESSAGE);
            return; // RETURN TO MAIN FRAME
        }
        // GET THE SELECTED ROW
        String clientId = table.getValueAt(table.getSelectedRow(), 0).toString();
        ClientDTO client = facade.displayClient(clientId);

        updateFrame = new JFrame("Update Client");
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setMinimumSize(new Dimension(350, 350));
        Container updateFrameContainer = updateFrame.getContentPane();
        updateFrame.setLayout(new BoxLayout(updateFrameContainer, BoxLayout.Y_AXIS));

        JLabel label0 = new JLabel("Client Id : " + clientId);
        updateFrame.add(label0);

        JLabel label1 = new JLabel("Name : ");
        JTextField text1 = new JTextField(20);
        text1.setText(client.getName());
        updateFrame.add(label1);
        updateFrame.add(text1);

        JLabel label2 = new JLabel("Person in Contact : ");
        JTextField text2 = new JTextField(20);
        text2.setText(client.getPic());
        updateFrame.add(label2);
        updateFrame.add(text2);

        JLabel label3 = new JLabel("Phone : ");
        JTextField text3 = new JTextField(20);
        text3.setText(client.getPhone());
        updateFrame.add(label3);
        updateFrame.add(text3);

        JLabel label4 = new JLabel("Address : ");
        JTextField text4 = new JTextField(20);
        text4.setText(client.getAddress());
        updateFrame.add(label4);
        updateFrame.add(text4);

        JLabel label5 = new JLabel("Email : ");
        JTextField text5 = new JTextField(20);
        text5.setText(client.getEmail());
        updateFrame.add(label5);
        updateFrame.add(text5);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Update");
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                client.setName(text1.getText());
                client.setPic(text2.getText());
                client.setPhone(text3.getText());
                client.setAddress(text4.getText());
                client.setEmail(text5.getText());
                boolean result = false;
                try
                {
                    result = facade.updateClient(client);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "An error occured between server and client. \nTry again later...",
                            "System Error", JOptionPane.ERROR_MESSAGE);
                }
                if (result)
                {
                    JOptionPane.showMessageDialog(null, "Client updated with id number : " + clientId);
                    updateFrame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Unable to update client. \nTry again later...", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateFrame.dispose();
            }
        });
        updateFrame.add(buttonPanel);
        updateFrame.pack();
        updateFrame.setVisible(true);
    }

    /**
     * This method makes the menu bar for the main frame
     *
     */
    private void makeMenuBar()
    {
        // INITIALIZE MENUBAR
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        // MAKE FILE MENU
        JMenu fileMenu = new JMenu("File");
        // MAKE FILE MENU ITEMS
        JMenuItem newParcelItem = new JMenuItem("New Parcel");
        newParcelItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                initAddParcelFrame();
            }
        });

        JMenuItem quitItem = new JMenuItem("Exit");
        quitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });

        // ADD MENUITEMS TO FILE MENU
        fileMenu.add(newParcelItem);
        fileMenu.add(quitItem);
        // MAKE ABOUT MENU
        JMenu aboutMenu = new JMenu("About");
        // MAKE ABOUT MENU ITEMS
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(frame, "Enterprise Development Custom Project" + "\nVersion   : v1.0.1823" + "\nDeveloper : Deniz Sumer"
                        + "\nContact   : 101527131@student.swin.edu.au", "About Program", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // ADD MENUITEMS TO ABOUT MENU
        aboutMenu.add(aboutItem);
        // ADD ALL MENUS TO MENUBAR
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
    }
}
