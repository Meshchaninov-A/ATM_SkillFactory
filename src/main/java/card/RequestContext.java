package card;

public class RequestContext {

    private boolean isAuthorized;
    private UserCard card;

    public RequestContext(boolean isAuthorized, UserCard card) {
        this.isAuthorized = isAuthorized;
        this.card = card;
    }


    public boolean isAuthorized() {
        return isAuthorized;
    }

    public UserCard getCard() {
        return card;
    }

}
