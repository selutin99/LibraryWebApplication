DELIMITER $$

CREATE TRIGGER genrePreventDelete
BEFORE DELETE
	ON genre FOR EACH ROW
BEGIN
	
    IF idGenre IN(
		SELECT idGenre FROM genre 
        WHERE EXISTS(
         SELECT bookGenre 
		 FROM book 
		 WHERE genre.idGenre = book.bookGenre
		)
    )
    THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Запись используется. Удаление невозможно.';
    END IF;
END
$$

DELIMITER ;