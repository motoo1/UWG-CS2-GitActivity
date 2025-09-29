package edu.westga.cs1302.lab5.persistence.student_data_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab5.model.Student;
import edu.westga.cs1302.lab5.persistence.StudentDataPersistenceManager;

class TestSaveStudentData {

	private static final String TEST_FILE = "test-data.txt";

	private void clearTestFile() {
		File file = new File(TEST_FILE);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	void testSaveSingleStudent() throws IOException {
		clearTestFile();

		Student[] students = { new Student("maame", 5) };
		StudentDataPersistenceManager.saveStudentData(students, TEST_FILE);

		File file = new File(TEST_FILE);
		try (Scanner reader = new Scanner(file)) {
			assertTrue(reader.hasNextLine(), "File should contain one line");
			String line = reader.nextLine();
			assertEquals("maame,5", line, "Saved line should match expected CSV format");
		}
	}

	@Test
	void testSaveEmptyArray() throws IOException {
		clearTestFile();

		Student[] students = new Student[0];
		StudentDataPersistenceManager.saveStudentData(students, TEST_FILE);

		File file = new File(TEST_FILE);
		try (Scanner reader = new Scanner(file)) {
			assertFalse(reader.hasNextLine(), "File should be empty");
		}
	}

	@Test
	void testSaveMultipleStudents() throws IOException {
		clearTestFile();

		Student[] students = { new Student("bob", 87), new Student("alice", 92) };
		StudentDataPersistenceManager.saveStudentData(students, TEST_FILE);

		File file = new File(TEST_FILE);
		try (Scanner reader = new Scanner(file)) {
			assertEquals("bob,87", reader.nextLine());
			assertEquals("alice,92", reader.nextLine());
			assertFalse(reader.hasNextLine(), "File should contain exactly two lines");
		}
	}
}
