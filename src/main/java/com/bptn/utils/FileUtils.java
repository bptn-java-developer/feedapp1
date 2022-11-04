package com.bptn.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	public static void main(String[] args) throws IOException {

		
		String home = System.getProperty("user.home");
		
		System.out.println(home);
		
		String folder = "D:\\Users\\hello";
		
		Path p = Paths.get(home);
		
		System.out.println("Check if a folder exists: " + home + ", " + Files.exists(p));
		
		Path p1 = Paths.get(home + "/hello.txt");
		System.out.println("Check if a file exists in " + home + " folder: hello.txt, " + Files.exists(p1));
		
		//Checks if a path is a folder or a file
		System.out.println( home + " is a regular file: " + Files.isDirectory(p));
		
		System.out.println( home + " is a readable: " + Files.isReadable(p));
		System.out.println( home + " is a writable: " + Files.isWritable(p));

		System.out.println( home + " is a executable: " + Files.isExecutable(p));
		
		Path p2 = Paths.get(home);
		
		System.out.println(Files.isSameFile(p, p2));
		
		
		// Create a text file
		
		String fileName = "my_file_" + UUID.randomUUID().toString() + ".txt";
		
		p = Paths.get(home + "/" + fileName);
		
		if (!Files.exists(p)) {
			Files.createFile(p);
		}
		
		System.out.println("Check if a file exists in " + home + " folder: " + fileName + " ," + Files.exists(p));
		
		String dirName = "my_dir_" + UUID.randomUUID().toString();
		
		p = Paths.get(home + "/" + dirName);
		
		if (!Files.exists(p)) {
			Files.createDirectory(p);
		}

		System.out.println("Check if a directory exists: " + dirName + " ," + Files.exists(p));
		
		//Delete a File
		p = Paths.get(home + "/" + "hello.txt");
		
		if (!Files.exists(p)) {
			Files.createFile(p);
		}
		
		System.out.println("Check if a file exists in " + home + " folder: hello.txt, " + Files.exists(p));

		Files.deleteIfExists(p);

		System.out.println("Check if a file exists in " + home + " folder: hello.txt, " + Files.exists(p));

		// Delete a Directory
		
		p = Paths.get(home + "/my_dir");
		
		if (!Files.exists(p)) {
			Files.createDirectory(p);
		}
		
		System.out.println("Check if a dir exists: " + home + "/my_dir , " + Files.exists(p));
		
		Files.delete(p);
		
		System.out.println("Check if a dir exists: " + home + "/my_dir, " + Files.exists(p));
		
		/*
		Files.list(Paths.get(home))
		   .filter(px -> Files.isRegularFile(px))
		   .forEach(System.out::println);*/
		
		// Write a file
		
		p = Paths.get(home + "/hello.txt");
		
		if (!Files.exists(p)) {
			Files.createFile(p);
		}
		
		try(FileWriter writer = new FileWriter(new File(p.toUri()))){
			
			for(int i=0; i<10; i++) {				
				writer.write("I love Java\n");
			}
			
			System.out.println("File Written Successfully");
		}
		
		// Update (Append data) a file
		
		try(FileWriter writer = new FileWriter(new File(p.toUri()), true)){
			writer.write("\r\n");
			writer.write("I'm updating the file");
		};

		//writer.close();
		
		//Read the file
		
		Scanner scanner = new Scanner(new File(p.toUri()));
		
		while ( scanner.hasNextLine() ) {
			String str = scanner.nextLine();
			//System.out.println(str);
			logger.debug(str);
		}
		
	}

}
