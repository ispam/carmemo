package tech.destinum.carmemo.pojo;

public class User {
    private String name, email, soat, rtm, str, src, to;
    private int cell, fijo;
    private long id;

    public User(String name, int cell, int fijo, String email, String soat, String rtm, String str, String src, String to, long id) {
        this.name = name;
        this.cell = cell;
        this.fijo = fijo;
        this.email = email;
        this.soat = soat;
        this.rtm = rtm;
        this.str = str;
        this.src = src;
        this.to = to;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoat() {
        return soat;
    }

    public void setSoat(String soat) {
        this.soat = soat;
    }

    public String getRtm() {
        return rtm;
    }

    public void setRtm(String rtm) {
        this.rtm = rtm;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public int getFijo() {
        return fijo;
    }

    public void setFijo(int fijo) {
        this.fijo = fijo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
