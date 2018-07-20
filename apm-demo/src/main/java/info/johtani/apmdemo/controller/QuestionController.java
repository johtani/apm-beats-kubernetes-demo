package info.johtani.apmdemo.controller;

import info.johtani.apmdemo.entities.Question;
import info.johtani.apmdemo.entities.QuestionVote;
import info.johtani.apmdemo.forms.QuestionForm;
import info.johtani.apmdemo.repositories.QuestionRepository;
import info.johtani.apmdemo.repositories.QuestionVoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(value = "author")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionVoteRepository questionVoteRepository;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(HttpSession session, Model model) {

        //TODO get all questions
        //List<Question> questions = questionRepository.findAll();
        List<Question> questions = questionRepository.findQuestionsSortByVotes();
        //TODO set questions to model
        List<QuestionVote> questionVotes = questionVoteRepository.findIdsBySessionKey(session.getId());
        List<Integer> voteQuestionIds = new ArrayList<>(questionVotes.size());
        for (QuestionVote q : questionVotes) {
            voteQuestionIds.add(q.getQuestion().getId());
        }

        model.addAttribute("questions", questions);
        model.addAttribute("votes", voteQuestionIds);
        return "index";
    }

    @ModelAttribute("author")
    private String setAuthor(String author) {
        return author;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showQuestionForm(QuestionForm questionForm, @ModelAttribute("author") String author) {
        Question question = new Question();
        if ( isNotEmpty(author)) {
            question.setAuthor(author);
        }
        return "question_form";
    }

    private boolean isNotEmpty(String str) {
        return str != null && str.isEmpty() == false;
    }

    private Question copyQuestion(QuestionForm form) {
        Question question = new Question();
        question.setAuthor(form.getAuthor());
        question.setTitle(form.getTitle());
        return question;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitQuestion(UriComponentsBuilder builder, @Valid QuestionForm questionForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {

            return "question_form";
        }
        setAuthor(questionForm.getAuthor());

        //TODO check multi post
        questionRepository.save(copyQuestion(questionForm));

        return "redirect:/index";
    }

    @RequestMapping(value = "/vote")
    public String vote(UriComponentsBuilder builder, HttpSession session, @RequestParam("question_id") String questionId) {

        Integer questionIdInt = Integer.parseInt(questionId);
        QuestionVote questionVote = new QuestionVote();
        Question question = new Question();
        question.setId(questionIdInt);
        questionVote.setQuestion(question);
        questionVote.setSessionKey(session.getId());

        questionVoteRepository.save(questionVote);

        return "redirect:/index";
    }


}
