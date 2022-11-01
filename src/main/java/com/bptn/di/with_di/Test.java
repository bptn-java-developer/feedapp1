package com.bptn.di.with_di;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class Test {

	private static String HOME = System.getProperty("user.home");

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		System.out.println(HOME);
		
		
		// To check if a file exists, we use the exists API:
		Path p = Paths.get(HOME);
		System.out.println(Files.exists(p));
		

		// To check that a file does not exist, we use the notExists API:
		Path p1 = Paths.get(HOME + "/inexistent_file.txt");
		System.out.println(Files.notExists(p1));


		// We can also check if a file is a regular file like myfile.txt or is just a directory, we use the isRegularFile API:
		System.out.println(Files.isRegularFile(p));


		// There are also static methods to check for file permissions. To check if a file is readable, we use the isReadable API:
		System.out.println(Files.isReadable(p));

		// To check if it is writable, we use the isWritable API:
		System.out.println(Files.isWritable(p));

		// Similarly, to check if it is executable:
		System.out.println(Files.isExecutable(p));

		// When we have two paths, we can check if they both point to the same file on the underlying file system:
		Path p2 = Paths.get(HOME);
		System.out.println(Files.isSameFile(p, p2));
		
		// All the name elements in the path must exist, apart from the file name, otherwise, we will get an IOException:
		String fileName = "myfile_" + UUID.randomUUID().toString() + ".txt";
		
		p = Paths.get(HOME + "/" + fileName);
		
		if(!Files.exists(p)){
		    Files.createFile(p);    
		}
		System.out.println(Files.exists(p));
		System.exit(0);

		// In the above example, when we first check the path, it is inexistent, then after the createFile operation, it is found to be existent.

		// To create a directory, we use the createDirectory API:
		String dirName = "myDir_" + UUID.randomUUID().toString();
		p1 = Paths.get(HOME + "/" + dirName);
		if(!Files.exists(p1)){
		    Files.createDirectory(p1);
		}
		System.out.println(Files.exists(p1));
		System.out.println(Files.isRegularFile(p1));
		System.out.println(Files.isDirectory(p1));
	}

}
