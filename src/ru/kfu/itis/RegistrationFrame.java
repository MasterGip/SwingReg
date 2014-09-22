package ru.kfu.itis;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class RegistrationFrame extends JFrame {
	
	private static JTextField tf_username, tf_e_mail;
	private static JPasswordField  tf_password;
	
	private static HashMap<String, LinkedList<String>> data = new HashMap<>();
	
	public static String getUsername(){
		return tf_username.getText();
	}
	
	public static String getPassword(){
		//JPasswordField.
		return tf_password.getText();
	}
	
	public static String getE_Mail(){
		return tf_e_mail.getText();
	}
	
	class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			switch(arg0.getActionCommand()){
			case "Enter":
				//System.out.println("!");
				Pattern username = Pattern.compile("[a-zA-Z0-9_]{6,14}");
				Matcher usernameMatcher = username.matcher(getUsername());
				LinkedList<Pattern> password = new LinkedList<Pattern>();
				password.add(Pattern.compile(".{8,32}"));
				password.add(Pattern.compile(".*[A-Z].*"));
				password.add(Pattern.compile(".*[a-z].*"));
				password.add(Pattern.compile(".*[0-9].*"));
				password.add(Pattern.compile(".*[#@%].*"));
//				Matcher passwordMatcher = pattern.matcher(getUsername());
				Pattern e_mail = Pattern.compile("([\\w]|[.-]){3,}[@](.?[a-z0-9]){3,}[.]([a-z]){2,}");
				Matcher e_mailMatcher = e_mail.matcher(getE_Mail());
				boolean b = true;
				String pass = getPassword();
				//System.out.println(pass);
				for(Pattern p : password){
					if(!p.matcher(pass).matches()){
						b = false;
						break;
					}
				}
				boolean all = true;
				if(!usernameMatcher.matches()){
					tf_username.setBackground(Color.RED);
					all = false;
				}else{
					tf_username.setBackground(Color.WHITE);
				}
				if(!b){
					tf_password.setBackground(Color.RED);
					all = false;
				}else{
					tf_password.setBackground(Color.WHITE);
				}
				if(!e_mailMatcher.matches() || data.get(getE_Mail())!=null){
					tf_e_mail.setBackground(Color.RED);
					
					all = false;
				}else{
					tf_e_mail.setBackground(Color.WHITE);
				}
				
				if(all){
					
					JOptionPane.showMessageDialog(
							Main.regfrm.getContentPane(),
							"ÓÐÀ!");
					LinkedList<String> list = new LinkedList<>();
					list.add(getUsername());
					list.add(getPassword());
					data.put(getE_Mail(), list);
				}
				
				break;
			case "ShowData":
				String str = "";
				for(String s : data.keySet()){
					str += s + " " + data.get(s).toString() + "\n";
				}
				
				JOptionPane.showMessageDialog(
						Main.regfrm.getContentPane(),
						str);
				break;
			}
			
		}
		
	}
	
	Listener listener = new Listener();
	

	public RegistrationFrame() {
		// TODO Auto-generated constructor stub
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setLayout(new GridLayout(7, 1));
		this.setTitle("REgistrationDemo");
		tf_username = new JTextField();
		tf_password = new JPasswordField();
		tf_e_mail = new JTextField();
		JPanel btn_panel = new JPanel(new FlowLayout());
		
		JButton btn_registration = new JButton("Registration!");
		btn_registration.addActionListener(listener);
		btn_registration.setActionCommand("Enter");
		JButton btn_showData = new JButton("Show Data");
		btn_showData.addActionListener(listener);
		btn_showData.setActionCommand("ShowData");
		//System.out.println(tf_username.getForeground() + " " + tf_username.getBackground());
		btn_panel.add(btn_registration);
		btn_panel.add(btn_showData);
		
		
		this.add(new JLabel("Username"));
		this.add(tf_username);
		this.add(new JLabel("Password"));
		this.add(tf_password);
		this.add(new JLabel("E-Mail"));
		this.add(tf_e_mail);
		this.add(btn_panel);
		this.setVisible(true);
		
		
		
		
	}
	
	
}
