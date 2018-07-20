package info.johtani.apmdemo.repositories;

import info.johtani.apmdemo.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT q.*, COUNT(v.session_key) AS vote_num FROM question q LEFT JOIN question_vote v ON q.id = v.question_id GROUP BY q.id ORDER BY vote_num DESC", nativeQuery = true)
    public List<Question> findQuestionsSortByVotes();
}
