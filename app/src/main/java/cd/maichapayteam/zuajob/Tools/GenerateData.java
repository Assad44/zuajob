package cd.maichapayteam.zuajob.Tools;

import java.util.List;

import cd.maichapayteam.zuajob.Models.DAOClass.UserDAO;
import cd.maichapayteam.zuajob.Models.Object.User;

public class GenerateData {

    public static User createUser(User user) {
        user.authCode = generate(32);
        user.myProfil = true;
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        userDAO.ajouter(user);
        RemoteDataSync.getRandomUser(100);
        return userDAO.ajouter(user);
    }

    public static boolean checkNumero(String numero) {
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        User user = userDAO.findByPhoneNumer(numero);
        if(user!=null) return true;
        return false;
    }

    public static User login(String phone, String mdp) {
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        User user = userDAO.findByPhoneNumer(phone);
        if(user!=null) {
            if(user.password.equals(mdp)) {
                RemoteDataSync.getRandomUser(100);
                return user;
            }
        }
        user = new User();
        user.error = true;
        user.errorCode = 36212;
        user.errorMessage = "Le numéro de téléphone et le mot de passe saisis ne correspondent pas. Veuillez réessayer SVP.";
        return user;
    }

    public static List<User> listJobeurs(int min) {
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        return userDAO.listJobeurs(min);
    }

    public static List<User> listJobeurs(int min, String keyword) {
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        return userDAO.listJobeurs(min, keyword);
    }



    public static String generate(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres dont tu ne veux pas
        String pass = "";
        for(int x=0;x<length;x++) {
            int i = (int)Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
            pass += chars.charAt(i);
        }
        return pass;
    }

}
