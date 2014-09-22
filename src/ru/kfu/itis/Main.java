package ru.kfu.itis;

import javax.swing.SwingUtilities;

public class Main {

	public static RegistrationFrame regfrm;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				regfrm = new RegistrationFrame();
			}
		});
	}

}
