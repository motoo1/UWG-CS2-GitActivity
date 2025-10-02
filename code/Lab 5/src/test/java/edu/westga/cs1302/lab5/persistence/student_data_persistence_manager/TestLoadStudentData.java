package edu.westga.cs1302.lab5.persistence.student_data_persistence_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab5.model.Student;
import edu.westga.cs1302.lab5.persistence.StudentDataPersistenceManager;

public class TestLoadStudentData {

    private static final String FILE_LOCATION = StudentDataPersistenceManager.FILE_LOCATION;

    @BeforeEach
    public void setup() throws IOException {
        // Clear the file before each test
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_LOCATION))) {
            writer.print("");
        }
    }

    @Test
    public void testLoadValidData() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_LOCATION))) {
            writer.println("alice,90");
            writer.println("bob,85");
        }

        Student[] students = StudentDataPersistenceManager.loadStudentData();

        assertEquals(2, students.length);
        assertEquals("alice", students[0].getName());
        assertEquals(90, students[0].getGrade());
        assertEquals("bob", students[1].getName());
        assertEquals(85, students[1].getGrade());
    }

    @Test
    public void testLoadEmptyFile() throws IOException {
        Student[] students = StudentDataPersistenceManager.loadStudentData();
        assertEquals(0, students.length);
    }

    @Test
    public void testLoadMalformedLine() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_LOCATION))) {
            writer.println("alice,90");
            writer.println("invalid_line");
        }

        IOException exception = assertThrows(IOException.class, () -> {
            StudentDataPersistenceManager.loadStudentData();
        });

        assertTrue(exception.getMessage().contains("Invalid line format"));
    }

    @Test
    public void testLoadNonIntegerGrade() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_LOCATION))) {
            writer.println("alice,ninety");
        }

        IOException exception = assertThrows(IOException.class, () -> {
            StudentDataPersistenceManager.loadStudentData();
        });

        assertTrue(exception.getMessage().contains("Grade value was not formatted as an integer"));
    }
}
