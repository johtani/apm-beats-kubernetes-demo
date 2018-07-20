package info.johtani.apmdemo.controller;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Span;
import co.elastic.apm.api.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

@Controller
public class IndexController {

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        return "about";
    }

    @RequestMapping(value = "/soslow", method = RequestMethod.GET)
    public String soSlow(Model model) throws Exception{

        doSomething("Set something");
        sleeeeeeep();
        doSomething("Call external API");

        return "soslow";
    }

    private Random random = new Random();

    private void sleeeeeeep() throws InterruptedException{
        Span customSpan = ElasticApm.startSpan();
        customSpan.setName("sleeeeeeep");
        customSpan.setType("app");
        int millis = this.random.nextInt(1000);
        Thread.sleep(millis);
        customSpan.end();
    }

    private void doSomething(String tag) throws Exception {
        Span customSpan = ElasticApm.startSpan();
        customSpan.setName(tag);
        customSpan.setType("ext");
        int millis = this.random.nextInt(100);
        Thread.sleep(millis);
        customSpan.end();
    }


}
