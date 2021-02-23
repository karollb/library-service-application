package pl.edu.wszib.library.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class SessionObject {
    private User loggedUser = null;
    private String info = null;
    private final List<Book> loanList = new ArrayList<>();

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isLogged() {
        return this.loggedUser != null;
    }

    public String getInfo() {
        String tmp = this.info;
        this.info = null;
        return tmp;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Book> getLoanList() {
        return loanList;
    }

    public void clearLoanList() {
        this.loanList.clear();
    }
}
