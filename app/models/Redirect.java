package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Redirect extends Model {

    public String url;
    public String alias;
    public String password;
    public Date createAt;

    public Redirect(String url, String alias, String password) {
        this.url = url;
        this.alias = alias;
        this.password = password;
        this.createAt = new Date();
    }

    public static Redirect findByAlias(String alias) {
        return find("byAlias", alias).first();
    }

    public static Redirect create(String url, String alias, String password) {
        Redirect newComment = new Redirect(url, alias, password).save();
        return newComment;
    }
}
