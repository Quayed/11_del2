package game;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Language {
	private String chosenLanguage;
	
	public Language(){
		
	}
	
	public String chooseLanguage(){
		JFrame frame = new JFrame();
		Object[] languageOptions = {"Dansk", "Engelsk"};
		
		int question = JOptionPane.showOptionDialog(frame,
			    "Skal spillet startes på dansk eller engelsk??",
			    "Sprog indstillinger",
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    languageOptions,
			    languageOptions[1]);
		
		
		if(question == 0){
			chosenLanguage = (String) languageOptions[0];
		} else if(question == 1){
			chosenLanguage = (String) languageOptions[1];
		}
		
		return(chosenLanguage);
	}
}


