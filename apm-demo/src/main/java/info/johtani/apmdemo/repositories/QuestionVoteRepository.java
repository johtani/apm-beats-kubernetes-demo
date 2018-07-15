package info.johtani.apmdemo.repositories;

import info.johtani.apmdemo.entities.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {

    @Query("select qv from QuestionVote qv where qv.sessionKey = :session_key")
    public List<QuestionVote> findIdsBySessionKey(@Param("session_key") String sessionKey);
}
