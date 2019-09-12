package cd.maichapayteam.zuajob.Tools;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.DAOClass.CategorieDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.SousCategorieDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.UserDAO;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.Models.Object.User;

public class ManageLocalData {

    public static User createUser(User user) {
        user.authCode = generate(32);
        user.myProfil = true;
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        userDAO.ajouter(user);
        RemoteDataSync.getRandomUser();

        return user;
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
                RemoteDataSync.getRandomUser();
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
        RemoteDataSync.getListJobeur((int)(min/20));
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        return userDAO.listJobeurs(min);
    }

    public static List<User> listJobeurs(int min, String keyword) {
        RemoteDataSync.getListJobeur(keyword, (int)(min/20));
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        return userDAO.listJobeurs(min, keyword);
    }

    public static List<Categorie> listCategorie() {
        CategorieDAO categorieDAO = CategorieDAO.getInstance(GeneralClass.applicationContext);
        List<Categorie> list = categorieDAO.getAll();
        if(list.size()==0) RemoteDataSync.getListCategorie();
        return list;
    }

    public static List<SousCategorie> listSousCategorie(Categorie categorie) {
        SousCategorieDAO sousCategorieDAO = SousCategorieDAO.getInstance(GeneralClass.applicationContext);
        List<SousCategorie> list = sousCategorieDAO.getAll(categorie);
        if(list.size()==0) RemoteDataSync.getListSousCategorie();
        return list;
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
