package com.ze1sure99.pojo;

public class User {
    private Integer id;
    private String  username;
    private Integer tel;

    public User(Integer id, String username, Integer tel) {
        this.id = id;
        this.username = username;
        this.tel = tel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", tel=").append(tel);
        sb.append('}');
        return sb.toString();
    }
}
