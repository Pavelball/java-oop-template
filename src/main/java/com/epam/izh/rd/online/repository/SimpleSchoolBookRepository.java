package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        try {
            schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length+1);
            schoolBooks[schoolBooks.length-1] = book;
            return true;
        } catch (Exception  e) {
            return false;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books = new SchoolBook[0];
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                books = Arrays.copyOf(books, books.length+1);
                books[books.length-1] = book;
            }
        }

        return books;
    }

    @Override
    public boolean removeByName(String name) {
        int countIdenticalName = findByName(name).length, id = 0;
        SchoolBook[] copySchoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length);
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length-countIdenticalName);
        if (countIdenticalName > 0) {
        for (SchoolBook book : copySchoolBooks) {
            if (!book.getName().equals(name)) {
                schoolBooks[id] = book;
                id++;
            }
        }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
