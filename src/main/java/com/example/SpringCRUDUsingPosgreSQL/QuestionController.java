package com.example.SpringCRUDUsingPosgreSQL;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
@RestController
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;
	@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
	@GetMapping("/questions")
	public List<Quesiton> getQuestions(){
		return questionRepository.findAll();				
	}
	@GetMapping("/questions/{questionId}")
	public Quesiton getQuestion(@PathVariable Long id){
		return questionRepository.getOne(id);			
	}
	@PostMapping("/questions")
	public Quesiton createQuestion(@Valid @RequestBody Quesiton question) {
		return questionRepository.save(question);
	}
	
	@PutMapping("/questions/{questionId}")
	public Quesiton updateQuestion (@PathVariable Long questionId,
			@Valid @RequestBody Quesiton questionRequest) {
		return questionRepository.findById(questionId)
			 .map(question -> {
				 Quesiton aQuestion =questionRequest;
                 question.setTitle(questionRequest.getTitle());
                 question.setDescription(questionRequest.getDescription());
              
                 return questionRepository.save(question);
             }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
		
	}
	@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
	@DeleteMapping("/questions/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId){
		return questionRepository.findById(questionId)
				.map(question->{
					questionRepository.delete(question);
					return ResponseEntity.ok().build();
				}).orElseThrow(()-> new ResourceNotFoundException("Question not found with id"+questionId));
	}
	
	
}
