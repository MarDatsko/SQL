import dao.DbUtil;
import model.Account;
import model.Model;
import model.Profile;

public class Executor {
    public void run() {
        DbUtil dbUtil = new DbUtil();
        Model account = new Account(1, "MarDatsko", "Marian", "Datsko", "Lviv", "Male");
        Model profile = new Profile(1, "MarDatsko", "student", "Student", "cursor", "SQL");

        System.out.println(dbUtil.selectAllItem(account));
        System.out.println(dbUtil.selectAllItem(profile));

        dbUtil.deleteItem(account, 2);
        dbUtil.deleteItem(profile, 2);

        dbUtil.insertItem(account);
        dbUtil.insertItem(profile);

        dbUtil.updateItem(account, 3);
        dbUtil.updateItem(profile, 3);
    }
}
