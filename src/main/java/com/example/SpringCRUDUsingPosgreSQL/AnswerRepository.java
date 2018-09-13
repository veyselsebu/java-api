package com.example.SpringCRUDUsingPosgreSQL;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.SpringCRUDUsingPosgreSQL.Answer;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
	List<Answer> findByQuestionId(Long questionId);

}
