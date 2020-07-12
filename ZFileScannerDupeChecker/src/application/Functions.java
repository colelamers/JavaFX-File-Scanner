package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
//Created by Cole Lamers
public class Functions
{
	public ArrayList<String> listOfFiles = new ArrayList<>(); //made public to access in the controller
	public ArrayList<String> listOfParentDir = new ArrayList<>();
	public ArrayList<String> fileType = new ArrayList<>();

	FileObjects wordy = new FileObjects();
	List<FileObjects> fol = new ArrayList<>();

	public void listFilesInDirectory(final File folder)
	{
		for (final File fileEntry : folder.listFiles()) 
		{
			if (fileEntry.isDirectory()) 
			{
				listFilesInDirectory(fileEntry);
			} //recurisively checks if a directory, then makes it go through again if it is a folder and checks within
			else 
			{
				fol.add(new FileObjects(fileEntry.getParent(), fileEntry.getName()));

				//fileObjectList.add(fileObjects.toString());//the toString provides a get function and this stores it as a string and not an object

				/*
	        	listOfFiles.add(fileEntry.getName());
	        	listOfParentDir.add(fileEntry.getParent());


	        	if (!fileType.contains(getFileType(i)))
	        	{
	        		fileType.add(getFileType(i));
	        	}//gets the file type only if it does not exist in the list keeping distinct instances only
	        	i++;*/
			}//adds the contents to the object
		}

	}

	/*	public void alphabeticalSort(ArrayList<String> list)
	{
		String temp;


		for (int i = 0; i < list.size(); i++) 
		{
			for (int j = i + 1; j < list.size(); j++) 
			{ 
				if (list.get(i).compareTo(list.get(j)) > 0)
				{
					temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
	}

	 */

	public void sortAscFileNames(List<FileObjects> listOfFileObjects)
	{
		Collections.sort(listOfFileObjects, new Comparator<FileObjects>()
		{
			@Override
			public int compare(FileObjects arg1, FileObjects arg2)
			{
				return arg1.getFileName().compareTo(arg2.getFileName());
			}
		}
				);

	}

	public void sortAscFileTypes(List<FileObjects> listOfFileObjects)
	{
		Collections.sort(listOfFileObjects, new Comparator<FileObjects>()
		{
			@Override
			public int compare(FileObjects arg1, FileObjects arg2)
			{
				return arg1.getFileType().compareTo(arg2.getFileType());
			}
		}
				);

	}

	public void sortAscFilePaths(List<FileObjects> listOfFileObjects)
	{
		Collections.sort(listOfFileObjects, new Comparator<FileObjects>()
		{
			@Override
			public int compare(FileObjects arg1, FileObjects arg2)
			{
				return arg1.getFilePath().compareTo(arg2.getFilePath());
			}
		}
				);

	}
}
