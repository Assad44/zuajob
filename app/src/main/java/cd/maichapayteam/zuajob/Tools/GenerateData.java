package cd.maichapayteam.zuajob.Tools;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.maichapayteam.zuajob.Models.DAOClass.AnnonceDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.CategorieDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.PostulerDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.ServiceDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.SollicitationDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.SousCategorieDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.UserDAO;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.Models.Object.User;

public class GenerateData {

    private static List<User> listJobeurs() {
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        List<User> list = new ArrayList<>();
        long max = userDAO.max();
        for (int i = 0; i < 1000; i++) {
            User user = GeneralClass.getRandomUser();
            max++;
            user.setId(max+1);
            user.setType(1);
            user = userDAO.ajouter(user);
            if(user!=null) list.add(user);
        }
        for (int i = 0; i < 1000; i++) {
            User user = GeneralClass.getRandomUser();
            max++;
            user.setId(max+1);
            user.setType(0);
            user = userDAO.ajouter(user);
            if(user!=null) list.add(user);
        }
        Log.e("GenerateData", "all users:" + userDAO.count());
        return list;
    }

    public static List<Categorie> listUserPreferenceCategorie() {
        CategorieDAO categorieDAO = CategorieDAO.getInstance(GeneralClass.applicationContext);
        List<Categorie> list = categorieDAO.getAllUserPreference();
        return list;
    }

    private static List<Service> listService() {
        List<Service> list = new ArrayList<>();
        List<Categorie> categorieList = CategorieDAO.getInstance(GeneralClass.applicationContext).getAll();
        Log.e("GenerateData", "all users" + UserDAO.getInstance(GeneralClass.applicationContext).count());
        List<User> userList = UserDAO.getInstance(GeneralClass.applicationContext).listAllJobeurs();
        Log.e("GenerateData", String.valueOf(userList.size()));
        ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
        long max = serviceDAO.max();
        for (int i = 0; i < 1000; i++) {
            Categorie categorie = categorieList.get(GeneralClass.randBetween(0, categorieList.size()-1));
            List<SousCategorie> sousCategorieList = SousCategorieDAO.getInstance(GeneralClass.applicationContext).getAll(categorie);
            SousCategorie sousCategorie = sousCategorieList.get(GeneralClass.randBetween(0, sousCategorieList.size()-1));
            User prestateur = userList.get(GeneralClass.randBetween(0, userList.size()-1));
            Service service = new Service();
            if(new Random().nextInt(200)==0) {
                prestateur = UserDAO.getInstance(GeneralClass.applicationContext).myProfil();
                service.setMy(true);
            }
            service.setMy(false);
            service.setDatePublication(GeneralClass.randomDate(2018, 2019).toString());
            service.setSousCategorie(sousCategorie.designation);
            service.setPhoneJobeur(prestateur.getCodePays() + prestateur.getPhone());
            service.setNomsJobeur(prestateur.getPrenom() + prestateur.getNom());
            max = max + 1;
            service.setId(max);
            service.setNombreRealisation(GeneralClass.randBetween(0, 100));
            service.setMontant(new Random().nextInt());
            String dev = "CDF";
            if(new Random().nextInt(2)==0) {
                dev = "USD";
                service.setMontant(new Random().nextInt(100) + 1);
            } else {
                service.setMontant(new Random().nextInt(10000) + 1);
            }
            service.setDevise(dev);
            service.setIdSousCategorie(sousCategorie.id);
            service.setIdJobeur(prestateur.id);
            service.setCategorie(categorie.getDesignation());
            service.setIdCategorie(categorie.getId());
            service.setDescription(GeneralClass.getRandomPhrase(new Random().nextInt(4) + 1));
            service.setCote(new Random().nextInt(6));
            service = serviceDAO.ajouter(service);
            if(service!=null) list.add(service);
        }
        return list;
    }

    private static List<Annonce> listAnnonce() {
        List<Annonce> list = new ArrayList<>();
        List<Categorie> categorieList = CategorieDAO.getInstance(GeneralClass.applicationContext).getAll();
        List<User> userList = UserDAO.getInstance(GeneralClass.applicationContext).listAllUsers();
        AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
        for (int i = 0; i < 1000; i++) {
            Categorie categorie = categorieList.get(GeneralClass.randBetween(0, categorieList.size()-1));
            List<SousCategorie> sousCategorieList = SousCategorieDAO.getInstance(GeneralClass.applicationContext).getAll(categorie);
            SousCategorie sousCategorie = sousCategorieList.get(GeneralClass.randBetween(0, sousCategorieList.size()-1));
            User user = userList.get(GeneralClass.randBetween(0, userList.size()-1));
            Annonce annonce = new Annonce();
            if(new Random().nextInt(200)==0) {
                user = UserDAO.getInstance(GeneralClass.applicationContext).myProfil();
                annonce.setMy(true);
            }
            annonce.setDatePublication(GeneralClass.randomDate(2018, 2019).toString());
            annonce.setSousCategorie(sousCategorie.designation);
            annonce.setPhoneUser(user.getCodePays() + user.getPhone());
            annonce.setNomsUser(user.getPrenom() + user.getNom());
            annonce.setId(annonceDAO.max()+1);
            if(new Random().nextInt(90)==0) annonce.setConfied(true);
            annonce.setMontant(new Random().nextInt());
            String dev = "CDF";
            if(new Random().nextInt(2)==0) {
                dev = "USD";
                annonce.setMontant(new Random().nextInt(100) + 1);
            } else {
                annonce.setMontant(new Random().nextInt(10000) + 1);
            }
            annonce.setDevise(dev);
            annonce.setIdSousCategorie(sousCategorie.id);
            annonce.setIdUser(user.id);
            annonce.setCategorie(categorie.getDesignation());
            annonce.setIdCategorie(categorie.getId());
            annonce.setDescription(GeneralClass.getRandomPhrase(new Random().nextInt(4) + 1));
            annonce = annonceDAO.ajouter(annonce);
            if(new Random().nextInt(200)==0) annonce.setMy(true);
            if(annonce !=null) list.add(annonce);
        }
        return list;
    }

    private static List<Postuler> listPostuler() {
        List<Postuler> list = new ArrayList<>();
        PostulerDAO postulerDAO = PostulerDAO.getInstance(GeneralClass.applicationContext);
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        List<User> prestateurList = userDAO.listAllJobeurs();

        List<Annonce> listAnnonce = AnnonceDAO.getInstance(GeneralClass.applicationContext).getAll();

        for (int i = 0; i < 1000; i++) {
            Annonce annonce = listAnnonce.get(new Random().nextInt(listAnnonce.size()));
            User prestateur = prestateurList.get(GeneralClass.randBetween(0, prestateurList.size()-1));
            Postuler postuler = new Postuler();
            if(annonce.isMy()) {
                postuler.setMy(true);
                postuler.setPhoneUser(prestateur.getCodePays() + prestateur.getPhone());
                postuler.setNomsUser(prestateur.getPrenom() + " " + prestateur.getNom());
                postuler.setIdUser(prestateur.getId());
            } else {
                if(GeneralClass.Currentuser != null && GeneralClass.Currentuser.type==1) {
                    if(new Random().nextInt(20)==0) {
                        postuler.setHavePostuled(true);
                        postuler.setPhoneUser(annonce.getPhoneUser());
                        postuler.setNomsUser(annonce.getNomsUser());
                        postuler.setIdUser(annonce.getIdUser());
                    }
                }
            }
            if(new Random().nextInt(4)==0) {
                postuler.setRDV(true);
                String dev = "CDF";
                if(new Random().nextInt(2)==0) {
                    dev = "USD";
                    postuler.setMontantConclu(new Random().nextInt(100) + 1);
                } else {
                    postuler.setMontantConclu(new Random().nextInt(10000) + 1);
                }
                postuler.setDeviseConclu(dev);
                postuler.setHeureRDV("08:30");
                postuler.setDateRDV(GeneralClass.randomDate().toString());
                if(new Random().nextInt(3)==0) {
                    postuler.setConclu(true);
                }
            }
            postuler.setId(postulerDAO.max()+1);
            postuler.idAnnonce = annonce.id;
            postuler.descriptionAnnonce = annonce.getDescription();
            postuler.setMontant(annonce.getMontant());
            postuler.setDevise(annonce.getDevise());
            postuler.setDate(GeneralClass.randomDate().toString());
            postuler.setComment(GeneralClass.getRandomPhrase(1));
            postuler.setCote(new Random().nextInt(6));
            postuler = postulerDAO.ajouter(postuler);
            if(postuler !=null) list.add(postuler);
        }
        return list;
    }

    private static List<Sollicitation> listSolliciter() {
        List<Sollicitation> list = new ArrayList<>();
        SollicitationDAO sollicitationDAO = SollicitationDAO.getInstance(GeneralClass.applicationContext);
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        List<User> userList = userDAO.getAll();

        List<Service> listService = ServiceDAO.getInstance(GeneralClass.applicationContext).getAll();

        for (int i = 0; i < 1000; i++) {
            Service service = listService.get(new Random().nextInt(listService.size()));
            User user = userList.get(GeneralClass.randBetween(0, userList.size()-1));
            Sollicitation sollicitation = new Sollicitation();
            if(service.isMy()) {
                sollicitation.setMy(true);
                sollicitation.setPhoneUser(user.getCodePays() + user.getPhone());
                sollicitation.setNomsUser(user.getPrenom() + " " + user.getNom());
                sollicitation.setIdUser(user.getId());
            } else {
                if(GeneralClass.Currentuser != null && GeneralClass.Currentuser.type==1) {
                    if(new Random().nextInt(20)==0) {
                        sollicitation.setHaveSollicited(true);
                        sollicitation.setPhoneUser(service.getPhoneJobeur());
                        sollicitation.setNomsUser(service.getNomsJobeur());
                        sollicitation.setIdUser(service.getIdJobeur());
                    }
                }
            }
            if(new Random().nextInt(4)==0) {
                sollicitation.setRDV(true);
                String dev = "CDF";
                if(new Random().nextInt(2)==0) {
                    dev = "USD";
                    sollicitation.setMontantConclu(new Random().nextInt(100) + 1);
                } else {
                    sollicitation.setMontantConclu(new Random().nextInt(10000) + 1);
                }
                sollicitation.setDeviseConclu(dev);
                sollicitation.setHeureRDV("08:30");
                sollicitation.setDateRDV(GeneralClass.randomDate().toString());
                if(new Random().nextInt(3)==0) {
                    sollicitation.setConclu(true);
                }
            }
            sollicitation.setId(sollicitationDAO.max()+1);
            sollicitation.setIdService(service.id);
            sollicitation.setDescriptionService(service.getDescription());
            sollicitation.setMontant(service.getMontant());
            sollicitation.setDevise(service.getDevise());
            sollicitation.setDate(GeneralClass.randomDate().toString());
            sollicitation.setComment(GeneralClass.getRandomPhrase(1));
            sollicitation.setCote(new Random().nextInt(6));
            sollicitation = sollicitationDAO.ajouter(sollicitation);
            if(sollicitation !=null) list.add(sollicitation);
        }
        return list;
    }

    public static void generateAll() {
        CategorieDAO.createCategories();
        SousCategorie.createSousCategories();
        List<User> userList = listJobeurs();
        Log.e("GenerateData", "Users created : " + userList.size());
        List<Service> serviceList = listService();
        Log.e("GenerateData", "Service created : " + serviceList.size());
        List<Annonce> annonceList = listAnnonce();
        Log.e("GenerateData", "Annonce created : " + annonceList.size());
        List<Postuler> postulerList = listPostuler();
        Log.e("GenerateData", "Postuler created : " + postulerList.size());
        List<Sollicitation> sollicitationList = listSolliciter();
        Log.e("GenerateData", "Sollicitation created : " + sollicitationList.size());
    }

    private static String generate(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres dont tu ne veux pas
        String pass = "";
        for(int x=0;x<length;x++) {
            int i = (int)Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
            pass += chars.charAt(i);
        }
        return pass;
    }





    public static User createUser(User user) {
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        user.id = userDAO.max() + 1;
        user.authCode = generate(32);
        user.myProfil = true;
        userDAO.ajouter(user);
        //RemoteDataSync.getRandomUser(100);
        generateAll();
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
        List<User> list = userDAO.listJobeurs(min);
        return list;
    }

    public static List<User> listJobeurs(int min, String keyword) {
        UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
        return userDAO.listJobeurs(min, keyword);
    }

    public static List<Categorie> listCategorie() {
        CategorieDAO categorieDAO = CategorieDAO.getInstance(GeneralClass.applicationContext);
        List<Categorie> list = categorieDAO.getAll();
        return list;
    }

    public static List<SousCategorie> listSousCategorie(Categorie categorie) {
        SousCategorieDAO sousCategorieDAO = SousCategorieDAO.getInstance(GeneralClass.applicationContext);
        List<SousCategorie> list = sousCategorieDAO.getAll(categorie);
        return list;
    }

    public boolean deconnection() {
        if(UserDAO.getInstance(GeneralClass.applicationContext).deconnection()>0) return true;
        return false;
    }

    public static List<Service> listRandomService(int min) {
        ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
        List<Service> list = serviceDAO.randomService(min);
        return list;
    }

    public static List<Service> listNewService(int min, int idSousCategorie) {
        ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
        SousCategorie sousCategorie = new SousCategorie();
        sousCategorie.setId(idSousCategorie);
        List<Service> list = serviceDAO.getNewService(min, sousCategorie);
        return list;
    }

    public static List<Service> listServiceByCote(int min, int idSousCategorie) {
        ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
        SousCategorie sousCategorie = new SousCategorie();
        sousCategorie.setId(idSousCategorie);
        List<Service> list = serviceDAO.getServiceByCote(min, sousCategorie);
        return list;
    }

    public static List<Service> listMesServices() {
        ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
        List<Service> list = serviceDAO.getMesServices();
        return list;
    }

    public static List<Annonce> listRandomAnnonce(int min) {
        AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
        List<Annonce> list = annonceDAO.randomAnnonce(min);
        return list;
    }

    public static List<Annonce> listNewAnnonce(int min, int idSousCategorie) {
        AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
        SousCategorie sousCategorie = new SousCategorie();
        sousCategorie.setId(idSousCategorie);
        List<Annonce> list = annonceDAO.getNewAnnonce(min, sousCategorie);
        return list;
    }

    public static List<Annonce> listMesAnnonces() {
        AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
        List<Annonce> list = annonceDAO.getMesAnnonces();
        return list;
    }

}
