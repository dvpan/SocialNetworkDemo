package repository.remote;

import model.Message;
import model.Word;
import repository.MessageRepository;
import repository.remote.dao.MessageDaoSQL;
import tool.AsyncCallback;

import java.util.*;

public class MessageRepositoryImpl implements MessageRepository {
    private MessageDaoSQL messageDaoSQL;

    public MessageRepositoryImpl(MessageDaoSQL messageDaoSQL) {
        this.messageDaoSQL = messageDaoSQL;
    }

    @Override
    public void sendMessage(AsyncCallback<Void> callback, String token, String login, String text) {
        Boolean response = messageDaoSQL.sendMessage(token, login, text, new Date().getTime());
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }

    @Override
    public void getMessageList(AsyncCallback<ArrayList<Message>> callback, String token, String login) {
        ArrayList<Message> messages =
                messageDaoSQL.getMessageList(token, login);
        if(messages == null || messages.isEmpty()) callback.onFailure(new Exception());
        else callback.onSuccess(messages);
    }

    @Override
    public void getRankedMessageList(AsyncCallback<List<Word>> callback, String token) {
        List<String> messages = messageDaoSQL.getMessagesText(token);
        List<Word> words = getRankedMessageList(messages);
        if(words == null || words.isEmpty()) callback.onFailure(new Exception());
        else callback.onSuccess(words);
    }

    public List<Word> getRankedMessageList(List<String> messages) {
        Map<String, Integer> map = new HashMap<>();
        Set<Word> set = new TreeSet<>(Comparator.comparing(Word::getCount).reversed().
                thenComparing(Word::getValue));

        for (String message : messages) {
            String[] text = message
                    .trim()
                    .replace(",", "")
                    .replace(".", "")
                    .split(" ");

            for (String word : text) {
                Integer count = map.get(word);
                count = (count == null) ? 1 : ++count;
                map.put(word, count);
            }
        }

        map.forEach((text, count) -> set.add(new Word(text, count)));

        return new ArrayList<>(set).subList(0, set.size() < 10 ? set.size() : 10);
    }
}
