package info.johtani.apmdemo.repositories;

import info.johtani.apmdemo.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
//    public List<QuestionForm> findQuestions();
}
