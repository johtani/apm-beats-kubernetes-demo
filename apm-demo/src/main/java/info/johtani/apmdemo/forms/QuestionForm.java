package info.johtani.apmdemo.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuestionForm {

    @NotNull
    @Size(min=3, max=400)
    private String title;

    @NotNull
    @Size(min=1, max=100)
    private String author;

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
}
