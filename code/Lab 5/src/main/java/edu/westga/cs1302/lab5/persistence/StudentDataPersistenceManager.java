package edu.westga.cs1302.lab5.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.westga.cs1302.lab5.model.Student;

/**
 * Supports saving and loading student data,
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class StudentDataPersistenceManager {

	public static final String FILE_LOCATION = "data.txt";

	/**
	 * Save the students!
	 * 
	 * @precondition students != null
	 * @postcondition none
	 * 
	 * @param students the set of students to save
	 * @throws IllegalArgumentException if precondition is violated
	 * @throws IOException              Unable to write to FILE_LOCATION
	 */
	public static void saveStudentData(Student[] students) throws IOException, IllegalArgumentException {
		StudentDataPersistenceManager.saveStudentData(students, StudentDataPersistenceManager.FILE_LOCATION);
	}

	/**
	 * Saves the provided array of students to the specified file location
	 * <p>
	 * Each students data will be written to the file in a suitable format
	 * <p>
	 * 
	 * @param students     the array of link students objects to save must not be
	 *                     code null
	 * @param fileLocation the path to the file where the data should be saved
	 * @throws IOException error occurs while writing to the file throws
	 *                     IllegalArgunebtException if code students is code null
	 */
	public static void saveStudentData(Student[] students, String fileLocation) throws IOException {
		if (students == null) {
			throw new IllegalArgumentException("Must provide an array of students");
		}

		try (FileWriter writer = new FileWriter(fileLocation)) {
			for (Student currStudent : students) {
				writer.write(currStudent.getName() + "," + currStudent.getGrade() + "\n");
			}
		}
	}

	/**
	 * Load the students!
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the set of students loaded
	 * @throws FileNotFoundException no file exists at FILE_LOCATION
	 * @throws IOException           unable to read file due to formatting issue
	 */

	public static Student[] loadStudentData() throws FileNotFoundException, IOException {
		ArrayList<Student> students = new ArrayList<>();
		File inputFile = new File(StudentDataPersistenceManager.FILE_LOCATION);

		try (Scanner reader = new Scanner(inputFile)) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] parts = line.split(",");
				if (parts.length != 2) {
					throw new IOException("Invalid line format: " + line);
				}

				String name = parts[0].trim();
				int grade = Integer.parseInt(parts[1].trim());
				students.add(new Student(name, grade));
			}
		} catch (NumberFormatException error) {
			throw new IOException("Grade value was not formatted as an integer (" + error.getMessage() + ")");
		} catch (IllegalArgumentException error) {
			throw new IOException(error.getMessage());
		}

		return students.toArray(new Student[0]);
	}
}
