package cn.deepai.evillage.manager;


/**
 * @author GaoYixuan
 */
public class LoginManager {

    private static LoginManager instance;
    private static boolean isProcssing;
    static {
        instance = new LoginManager();
    }

    private LoginManager() {
        isProcssing = false;
    }

    public static LoginManager getInstance() {
        return instance;
    }

    public boolean login() {
        if (isProcssing) return false;
        isProcssing = true;
        try {

            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        isProcssing = false;
        return true;
    }
}
