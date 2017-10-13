package db;

import model.Message;
import model.User;
import model.Word;

import java.util.*;


public class DatabaseProcess {

    public List<Word> getRankedMessageList(List<Message> messages, User user) {
        Map<String, Integer> map = new HashMap<>();
        Set<Word> set = new TreeSet<>(Comparator.comparing(Word::getCount).reversed().thenComparing(Word::getValue));

        for(Message message : messages) {
            if (!message.getUserFromId().equals(user.getId())) continue;
            String[] text = message.getText()
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
