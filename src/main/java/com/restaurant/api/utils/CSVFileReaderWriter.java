package com.restaurant.api.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVFileReaderWriter {
	
	private static CSVFileReaderWriter instance;
	
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private PrintWriter printWriter;
	
	private CSVFileReaderWriter() {}
	
	public static CSVFileReaderWriter getInstance() { 
		if (instance == null) {
			synchronized (CSVFileReaderWriter.class) {
				if (instance == null) {
					instance = new CSVFileReaderWriter();
				}
			}
		} 
  
		return instance; 
    }
	
	public synchronized List<String> getAllFileLines(String filepath) throws IOException {
		List<String> lines = new ArrayList<>();
		
		try {
			fileReader = new FileReader(filepath);
			bufferedReader = new BufferedReader(fileReader);
			
			String line = null;
			
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
			
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {			
			closeReader();
		}
		
		return lines;
	}
	
	public synchronized void insertLinesIfFileNotExists(List<String> lines, String filepath) throws IOException {
		File file = new File(filepath);
		if (!file.exists() || file.isDirectory()) {
			insertLines(lines, filepath, false);
		} 
	}
	
	public synchronized void insertLines(List<String> lines, String filepath) throws IOException {
		insertLines(lines, filepath, true);
	}
	
	public synchronized void insertLines(List<String> lines, String filepath, boolean append) throws IOException {
		
		try {
			fileWriter = new FileWriter(filepath, append);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);
			
			for (String line : lines) { 
				printWriter.println(line);
			}
			
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {			
			closeWriter();
		}
		
	}

	private void closeReader() {
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		
		if (fileReader != null) {
			try {
				fileReader.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}		
	}
	
	private void closeWriter() {
		
		if (printWriter != null) {			
			printWriter.close();			
		}
		
		if (bufferedWriter != null) {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		
		if (fileWriter != null) {
			try {
				fileWriter.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}		
	}

}
