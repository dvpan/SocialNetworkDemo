package db;

import model.Message;
import model.User;
import model.Word;

import java.util.*;


/** Служит для вычислений на стороне сервера
 * @author Daniil Panichev
 */

public class DatabaseProcess {

    /**
     * Возвращает список наиболее популярных слов пользователя, основываясь
     * на списке всех сообщений. Полученный список является отсортированным по
     * уменьшению популярности слов.
     *
     * @param  messages список всех сообщений социальной сети
     * @param  user пользователь, для которого составляется ТОП слов
     * @return список наиболее популярных слов пользователя
     */

    public List<Word> getRankedMessageList(List<Message> messages, User user) {
        Map<String, Integer> map = new HashMap<>();
        Set<Word> set = new TreeSet<>(Comparator.comparing(Word::getCount).reversed().
                thenComparing(Word::getValue));

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
