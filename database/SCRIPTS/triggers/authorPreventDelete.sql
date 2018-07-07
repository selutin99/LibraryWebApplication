DELIMITER $$

CREATE TRIGGER authorPreventDelete
BEFORE DELETE
	ON author FOR EACH ROW
BEGIN
	
    IF idAuthor IN(
		SELECT idAuthor FROM author 
        WHERE EXISTS(
         SELECT bookAuthor 
		 FROM book 
		 WHERE author.idAuthor = book.bookAuthor
		)
    )
    THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Запись используется. Удаление невозможно.';
    END IF;
END
$$

DELIMITER ;