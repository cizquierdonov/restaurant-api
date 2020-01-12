package com.restaurant.api.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CSVFileReaderWriter.class})
public class TestCSVFileReaderWriter {
	
	private CSVFileReaderWriter instance;
	
	/*@Value("${DATABASE_FILE_PATH}")
	private String databaseFilePath;*/
	
	@Test
	public void testInsertLinesSuccessful() {
		instance = CSVFileReaderWriter.getInstance();
		List<String> lines = new ArrayList<>();
		lines.add("Hola");
		lines.add("Ciao");
		try {
			instance.insertLines(lines, "Users2.txt");
		} catch (IOException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testGetAllFileLines() {
		instance = CSVFileReaderWriter.getInstance();
		List<String> lines;
		try {
			lines = instance.getAllFileLines("Users2.txt");
			assertTrue(lines.size() > 0);
		} catch (IOException e) {
			assertTrue(false);
		} finally {
			File file = new File("Users2.txt");
			if (file.exists() && !file.isDirectory() ) {
				file.delete();
			}
		}
		
	}

}
