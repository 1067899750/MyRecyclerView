package com.json.itemdecoration.wx;

/**
 * @author puyantao
 * @describe
 * @create 2020/10/9 14:59
 */
public class User implements Comparable<User> {
    private String id;
    //名子
    private String name;
    // 名子对应的拼音
    private String pinyin;
    // 拼音的首字母
    private String firstLetter;

    public User() {
    }

    public User(String name) {
        this.name = name;
        // 根据姓名获取拼音
        pinyin = Cn2Spell.getPinYin(name);
        // 获取拼音首字母并转成大写
        firstLetter = pinyin.substring(0, 1).toUpperCase();
        if (!firstLetter.matches("[A-Z]")) {
            // 如果不在A-Z中则默认为“#”
            firstLetter = "#";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }


    @Override
    public int compareTo(User another) {
        if (firstLetter.equals("#") && !another.getFirstLetter().equals("#")) {
            return 1;
        } else if (!firstLetter.equals("#") && another.getFirstLetter().equals("#")) {
            return -1;
        } else {
            return pinyin.compareToIgnoreCase(another.getPinyin());
        }
    }


}









