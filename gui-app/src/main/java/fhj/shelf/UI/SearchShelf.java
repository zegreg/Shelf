package fhj.shelf.UI;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import fhj.shelf.commandsDomain.EraseShelf;
import fhj.shelf.commandsDomain.GetOneShelf;
import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.repos.ShelfRepository;





@SuppressWarnings("serial")
public class SearchShelf extends JFrame {
	

		//Declara e cria os componentes   
		JLabel jlShelfId = new JLabel ("SheflId");
		JTextField jtfNome = new JTextField(20);
		JLabel jlCapacity = new JLabel ("Capacity");
		JTextField jtfPassword = new JTextField(20);
		JLabel jlFreeSpace = new JLabel ("FreeSpace");
		JTextField jtfFullname = new JTextField(20);
		JButton jbSearch = new JButton("Search");
		JLabel jlVazia = new JLabel("");
		private ShelfRepository repository;
		private JButton btnDelete = new JButton("Delete");

		//Construtor
		public  SearchShelf(ShelfRepository shelfrepository) {    
			this.repository = shelfrepository;


			//Define as propriedades da janela
			setTitle("Procura por nome");
			setSize(350,234);
			setLocation(100,100);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
			jlShelfId.setBounds(14, 23, 65, 20);

			//Define o tamanho dos rótulos
			jlShelfId.setPreferredSize(new Dimension(65,20));
			jlCapacity.setBounds(14, 54, 65, 20);
			jlCapacity.setPreferredSize(new Dimension(65,20));
			jlFreeSpace.setBounds(14, 85, 65, 20);
			jlFreeSpace.setPreferredSize(new Dimension(65,20));
			jlVazia.setBounds(5, 105, 325, 10);
			jlVazia.setPreferredSize(new Dimension(325,10));

			/*Define as caixas de texto como não editáveis,
	        uma vez que não será necessário a introdução de dados nestes campos*/
			jtfPassword.setBounds(84, 54, 166, 20);
			jtfPassword.setEditable(false);
			jtfFullname.setBounds(84, 85, 166, 20);
			jtfFullname.setEditable(false);
			getContentPane().setLayout(null);

			//Adiciona os componentes à janela
			getContentPane().add(jlShelfId);
			jtfNome.setBounds(84, 23, 166, 20);
			getContentPane().add(jtfNome);
			getContentPane().add(jlCapacity);
			getContentPane().add(jtfPassword);
			getContentPane().add(jlFreeSpace);
			getContentPane().add(jtfFullname);
			getContentPane().add(jlVazia);
			jbSearch.setBounds(84, 142, 73, 23);
			getContentPane().add(jbSearch);
			btnDelete.setBounds(178, 142, 73, 23);
			
			getContentPane().add(btnDelete);

			/*Registo do listener ActionListener junto do botão.
	        Quando for gerado um evento por este componente, é
	        criada uma instância da classe EventoJBProcurarNome,
	        onde está o código que deve ser executado quando tal acontece*/


			jbSearch.addActionListener(new EventModelExecuteAdd());
			btnDelete.addActionListener(new EventModelExecuteDelete());

		}

		public ShelfRepository getRepository() {
			return repository;
		}
		

		private class EventModelExecuteAdd implements ActionListener{


			@Override
			public void actionPerformed(ActionEvent e) {



				if (jtfNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o campo nome!");
					cleanFields();
				} else{						

					new EventHandling().execute();
				}

			}

		}


		
		private class EventHandling extends SwingWorker<Shelf, Void>{
			boolean nameFound = false;

			@Override
			protected  Shelf  doInBackground() throws Exception {

				Shelf shelf = (Shelf)new GetOneShelf(getRepository(), Long.valueOf(jtfNome.getText())).call();

				return shelf;
			}

			@Override
			protected void done() {

				try {

					jtfPassword.setText(String.valueOf(((Shelf) get()).getCapacity()));
					jtfFullname.setText(String.valueOf(((Shelf) get()).getFreeSpace()));
					


				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Shelf doesn´t exist" + e);
					cleanFields();
					e.printStackTrace();
				}

			}
		}

		private class EventModelExecuteDelete implements ActionListener{


			@Override
			public void actionPerformed(ActionEvent e) {



				if (jtfNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You must searchFirst");
					cleanFields();
				} else{						

					new EventHandlingDelete().execute();
				}

			}

		}


		
		private class EventHandlingDelete extends SwingWorker<String, Void>{
			boolean encontrouNome = false;

			@Override
			protected  String doInBackground() throws Exception {

				String eraseShelf = new EraseShelf(repository, Long.valueOf(jtfNome.getText())).call();
				return eraseShelf;
			}

			@Override
			protected void done() {

				
					try {
						JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum shelf com esse id!" + get());
					} catch (HeadlessException | InterruptedException
							| ExecutionException e) {
						
						e.printStackTrace();
					}
					cleanFields();
					
			

			}
		}
		
		
		
		
		
		
		private void cleanFields() {
			jtfNome.setText("");
			jtfPassword.setText("");
			jtfFullname.setText("");
		
		}
	}

