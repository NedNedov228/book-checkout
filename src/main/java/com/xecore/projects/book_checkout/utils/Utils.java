package com.xecore.projects.book_checkout.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static Map<Integer, String> searchBooksByTitlePrefix(HashMap<Integer, String> books, String prefix) {
        Map<Integer, String> foundBooks = new HashMap<>();
        for (Map.Entry<Integer, String> entry : books.entrySet()) {
            if (entry.getValue().toLowerCase().startsWith(prefix.toLowerCase())) {
                foundBooks.put(entry.getKey(), entry.getValue());
            }
        }
        return foundBooks;
    }
}
