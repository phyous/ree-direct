package models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.common.util.StringHelper;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Redirect extends Model {

    @Expose
    public String url;

    @Expose
    public String alias;

    public String password;

    @Expose
    public Date createAt;

    public Redirect(String url, String alias, String password) {
        this.url = url;
        this.alias = alias;
        this.password = password;
        this.createAt = new Date();
    }

    public boolean hasPassword() {
        return StringHelper.isNotEmpty(password);
    }

    public static Redirect findByAlias(String alias) {
        return find("byAlias", alias).first();
    }

    public static Redirect create(String url, String alias, String password) {
        Redirect newComment = new Redirect(url, alias, password).save();
        return newComment;
    }
}
