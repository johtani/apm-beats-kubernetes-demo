package info.johtani.apmdemo.entities;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String title;

    private String author;

    @Column(nullable = false, updatable = false)
    private Timestamp pubDate;

    @PrePersist
    public void prePersist() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        this.pubDate = ts;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "question")
    private List<QuestionVote> votes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumVotes() {
        return votes.size();
    }

    public boolean hasVotes() {
        return !votes.isEmpty();
    }

    public Timestamp getPubDate() {
        return pubDate;
    }
}
