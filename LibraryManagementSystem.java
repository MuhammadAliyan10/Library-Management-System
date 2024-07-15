import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem extends JFrame {
    private CustomLinkedList<LibraryItem> library;
    private CustomStack<LibraryItem> stack;
    private CustomQueue<LibraryItem> queue;

    private JTextField titleField, authorField, isbnField;
    private JTextArea displayArea;

    public LibraryManagementSystem() {
        library = new CustomLinkedList<>();
        stack = new CustomStack<>();
        queue = new CustomQueue<>();

        setTitle("Library Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Title:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Author:"));
        authorField = new JTextField();
        panel.add(authorField);

        panel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        panel.add(isbnField);

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        panel.add(addButton);

        JButton searchButton = new JButton("Search Book");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        panel.add(searchButton);

        JButton sortButton = new JButton("Sort Books");
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortBooks();
            }
        });
        panel.add(sortButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();

        LibraryItem book = new LibraryItem(title, author, isbn);
        library.add(book);
        stack.push(book);
        queue.enqueue(book);

        displayArea.append("Book added: " + book.toString() + "\n");

        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
    }

    private void searchBook() {
        String title = titleField.getText();
        LibraryItem book = library.search(title);

        if (book != null) {
            displayArea.append("Book found: " + book.toString() + "\n");
        } else {
            displayArea.append("Book not found.\n");
        }
    }

    private void sortBooks() {
        library.sort();
        displayArea.append("Books sorted.\n");
        library.display();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryManagementSystem().setVisible(true);
            }
        });
    }
}
