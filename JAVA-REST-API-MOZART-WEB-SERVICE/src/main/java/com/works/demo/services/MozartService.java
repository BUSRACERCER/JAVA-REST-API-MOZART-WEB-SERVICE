package com.works.demo.services;

import com.works.demo.models.Item;
import com.works.demo.models.MusicCategory;
import com.works.demo.models.MusicCategoryList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class MozartService {
    public MusicCategoryList result() {
        Map<Integer, String> hmTitle = new HashMap<>();
        List<Item> items = new ArrayList<>();

        String baseUrl = "https://yiboyoung.com/music/mozart/mozart_complete/1EARLIERSYMPHONIES/";
        try {
            String url = baseUrl + "EARLYSYMPHONIES.htm";
            Document doc = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get();
            Elements fonts = doc.getElementsByAttributeValue("size", "+2");
            int countTitle = 0;
            for (Element item : fonts) {

                String objTitle = item.toString();
                if (!objTitle.contains("a href=")) {
                    String title = item.text().toString();
                    if (!title.isEmpty()) {
                        hmTitle.put(countTitle, title);
                        countTitle++;

                    }
                } else {
                    String subTitle = item.text().toString();
                    if (!subTitle.isEmpty()) {

                        String href = baseUrl + item.getAllElements().attr("href");
                        System.out.println("--subTitle : " + subTitle + " url : " + href);

                        Item item1 = new Item();
                        item1.setBaseCat(countTitle);
                        item1.setTitle(subTitle);
                        item1.setUrl(href);
                        items.add(item1);
                    }
                    System.out.println();
                }
            }

        } catch (Exception ex) {
            System.err.println("Mozart Error : " + ex);
        }
        MusicCategoryList musicCategoryList = new MusicCategoryList();
        musicCategoryList.setMusicCategories(parseData(hmTitle, items));
        return musicCategoryList;
    }

    private List<MusicCategory> parseData(Map<Integer, String> hmTitle, List<Item> items) {
        Set<Integer> keys = hmTitle.keySet();
        List<MusicCategory> musicCategories = new ArrayList<>();
        for (Integer key : keys) {
            MusicCategory musicCategory = new MusicCategory();
            musicCategory.setBaseTitle(hmTitle.get(key));
            List<Item> itemList = new ArrayList<>();

            for (Item item : items) {
                if (item.getBaseCat() - 1 == key) {
                    itemList.add(item);
                }
            }
            musicCategory.setItems(itemList);
            musicCategories.add(musicCategory);
        }
        return musicCategories;
    }
}
