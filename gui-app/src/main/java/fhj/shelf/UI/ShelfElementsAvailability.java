package fhj.shelf.UI;

/**
 * Write a description of class PesquisaDisponibilidades here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ShelfElementsAvailability extends JFrame {

    //Declara e cria os componentes
    JLabel jlElementType = new JLabel ("Element Type");
    JLabel jlTitle = new JLabel ("Title");
    JTextField jtfShelfData = new JTextField(6);
    JButton jbSearch = new JButton("Search");
    DefaultTableModel tmElementsInformation = new DefaultTableModel (null, new String[]{"Title", "Author", "Tracks", "Duration","Availability"});
    JTable jtElements = new JTable(tmElementsInformation);
    JScrollPane jspElements = new JScrollPane(jtElements);
    JComboBox comboBox = new JComboBox();
    JLabel lblShelfid = new JLabel("ShelfId");
    JTextField jtfShelfid = new JTextField(6);
    
    //Construtor
    public ShelfElementsAvailability() {    
        
        //Define as porpriedades da janela
        setTitle("ElementSearch");
        setSize(500,330);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        jspElements.setBounds(10, 99, 470, 146);
        jspElements.setPreferredSize(new Dimension(470,200));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"CD", "DVD", "Book", "Collection Book", "Collection DVD", "Collection CD"}));
        comboBox.setBounds(100, 15, 109, 24);
        comboBox.addItem("DC");
        comboBox.addItem("DVD");
        comboBox.addItem("Book");
        comboBox.addItem("Collection Book");
        comboBox.addItem("Collection DVD");
        comboBox.addItem("Collection CD");
        getContentPane().setLayout(null);
        getContentPane().setLayout(null);
        jlElementType.setBounds(10, 8, 96, 31);
        
        //Adiciona os componentes à janela
        getContentPane().add(jlElementType);
        
        getContentPane().add(comboBox);
        jlTitle.setBounds(20, 50, 42, 18);
        getContentPane().add(jlTitle);
        jtfShelfData.setBounds(100, 49, 230, 20);
        getContentPane().add(jtfShelfData);
        jbSearch.setBounds(379, 47, 80, 24);
        getContentPane().add(jbSearch);
        getContentPane().add(jspElements);
        lblShelfid.setBounds(234, 8, 96, 31);
        
        getContentPane().add(lblShelfid);
        jtfShelfid.setText("");
        jtfShelfid.setBounds(274, 13, 74, 20);
        
        getContentPane().add(jtfShelfid);
        
        /*Registo do listener ActionListener junto do botão.
        Quando for gerado um evento por este componente, é
        criada uma instância da classe EventoJBPesqusiar,
        onde está o código que deve ser executado quando tal acontece*/
        jbSearch.addActionListener(new EventoJBPesquisar());
    
    //Limpa os dados que possam ter ficado desde a última utilização da janela
    //Os métodos invocados estão implementados em baixo
    limpaCampos();
    removeLinhasTabela();
    }
    
    public static void main(String[] args) {
        new ShelfElementsAvailability();
    }
    
    //Classe interna que contém o código que é executado quando se pressiona o botão jbPesquisar
    private class EventoJBPesquisar implements ActionListener {
        
        public void actionPerformed(ActionEvent ev) {
            
            //Invoca o método implementado em baixo
            removeLinhasTabela();
            boolean availabilityFound = false;
            if (jtfShelfData.getText().equals("")|| jtfShelfid.getText().equals("") )
                JOptionPane.showMessageDialog(null, "Fill out all fields!");
            else {
                try {
                   
                    int i=0;
                    String[] campos = new String[] {null, null, null, null, null};
//                    while (rs.next()) {
//                        encontrouDisponibilidades = true;
//                        tmCarros.addRow(campos);
//                        tmCarros.setValueAt(rs.getInt("num_carro"), i, 0);
//                        tmCarros.setValueAt(rs.getString("marca"), i, 1);
//                        tmCarros.setValueAt(rs.getString("modelo"), i, 2);
//                        tmCarros.setValueAt(rs.getBigDecimal("preco_diario"), i, 3);
//                        i++;
//                    }
                    if (availabilityFound == false) {
                        JOptionPane.showMessageDialog(null, "There is no element to order in selected Shelf!");
                        //Invoca o método implementado em baixo
                        limpaCampos();
                    }
                   // ligacaoBD.fecharLigacao(con);
                }
                catch(Exception e) {
                    System.out.println("Não foi possível efetuar a operação sobre a BD!");
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void removeLinhasTabela() {
        while (tmElementsInformation.getRowCount() > 0)
            tmElementsInformation.removeRow(0);
    }
    
    private void limpaCampos() {
        jtfShelfData.setText("");
        jtfShelfid.setText("");
    }
}