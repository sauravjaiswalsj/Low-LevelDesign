# Postgress

1. Connect to DB:

```
public static Connection connect()throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Library";
        String user = "username";
        String password = "password";
        return DriverManager.getConnection(url, user, password);
```

2. Create Table:

```
    try{
            Connection connection = DatabaseConnection.connect();
            if(!DatabaseConnection.tableExists(connection,"books")) {
                String book = "CREATE TABLE IF NOT EXISTS books (" +
                        "book_id SERIAL PRIMARY KEY, title VARCHAR(255)," +
                        "genre VARCHAR(255), author VARCHAR(255), available_copies INTEGER, " +
                        "total_copies INTEGER" +
                        ")";
                connection.prepareStatement(book).execute();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
```

3. Insert into Table:

```
    public static void insertBook(Book book){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "INSERT INTO books (title, genre, author, available_copies, total_copies) VALUES(?, ?, ?, ?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getGenre());
                preparedStatement.setString(3, book.getAuthor());
                preparedStatement.setInt(4, book.getAvailableCopies());
                preparedStatement.setInt(5, book.getTotalCopies());

                preparedStatement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
```

4. Delete From Table

```

    public static void removeBook(String title, String author){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "DELETE FROM books where title = ? AND author = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, author);
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
```

5. Update Table:

```
        public static Book updateBook(Book book){
        try{
            Connection connection = DatabaseConnection.connect();
            Book temp = getBook(book.getTitle(),book.getAuthor());
            if(temp == null && temp.getTitle() == null){
                System.out.println("Book not found");
            }
            String query = "UPDATE books SET title = ?, genre = ?, author =  ?, available_copies = ?, total_copies = ? where book_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getGenre());
                preparedStatement.setString(3, book.getAuthor());
                preparedStatement.setInt(4, book.getAvailableCopies());
                preparedStatement.setInt(5, book.getTotalCopies());
                preparedStatement.setLong(6,temp.getBookId());
                preparedStatement.executeUpdate();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
```
