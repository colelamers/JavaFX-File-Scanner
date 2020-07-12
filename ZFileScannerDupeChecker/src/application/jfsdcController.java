package application;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.util.Collections;
//Created by Cole Lamers

public class jfsdcController extends Functions
{
	@FXML
	private TextField tbDirectory;

	@FXML
	private ComboBox<String> cbFileType;

	@FXML
	private Pane paneTabMain;

	@FXML
	private Pane paneTabSecond;

	@FXML
	private Label labelResults;


	@FXML
	public void btnBrowseClick(ActionEvent event)
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("C:\\"));
		chooser.setDialogTitle("Select Directory Path");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(true);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
		{
			tbDirectory.setText(String.valueOf(chooser.getSelectedFile()));
		}
		/*
		FileChooser fc = new FileChooser();
		File file = new File("filename");
		file = fc.showOpenDialog(jfsdcMain.primaryStage);// 07-07 opens dialog in file explorer
		 */
	}

	@FXML
	public void btnGetDataClick(ActionEvent event)
	{
		fol.clear();//clears the list each time the button is pressed so that it doesn't accumulate together if a new search is conducted
		labelResults.setText("Results: ");//resets the text each time it's called
		try
		{

			File directoryPath = new File(tbDirectory.getText());//directory is set by whatever the user inputs in the directory field

			listFilesInDirectory(directoryPath);
			initialize();//need to add files to combobo

			labelResults.setText("Results: " + fol.size());

		}
		catch (NullPointerException e)
		{
			//CREATE A POPUP HERE THAT STATES RETUNRS NO VALUES OR SOMETHING
		}
	}

	public void btnExecuteClick(ActionEvent event) throws IOException
	{
		File desktopPath = new File (System.getProperty("user.home") +
				"\\Desktop\\ListOfFiles.txt");//saves file to desktop -- UPDATE THIS AFTER THE PERIOD TO CAPTURE WHICHEVER RADIO BUTTON IS CHECKED FOR CSV OR TXT
		PrintWriter pw = new PrintWriter(desktopPath);//this will create the file, passes through path set by desktopPath

		/*
		 * switch case goes here to choose type of sorting
		 */
		sortAscFileTypes(fol);//modify as a button or possibly abstract class that determines which sort method used?

		for (FileObjects obj : fol)
		{
			pw.println(obj);
		}//adds all the contents stored in the listOfFiles object to the text file
		pw.close();//flushes the printwriter and actually writes the text to the file

	}

	public void initialize()
	{
		cbFileType.getItems().clear();
		ArrayList<String> distinctFileTypes = new ArrayList<>();
		for(int i = 0; i < fol.size(); i++)
		{
			if (!distinctFileTypes.contains(fol.get(i).getFileType()))
			{
				distinctFileTypes.add(fol.get(i).getFileType());//added solely because I can't check off the combobox
			}//if the filetype at that spot does not exist, it adds it to the list
		}		

		Collections.sort(distinctFileTypes);//sorts the list now alphabetically

		for (int i = 0; i < distinctFileTypes.size(); i++)
		{
			cbFileType.getItems().add(
					distinctFileTypes.get(i)
					);
		}//adds the alphabetically sorted list into the combobox
	}
}

