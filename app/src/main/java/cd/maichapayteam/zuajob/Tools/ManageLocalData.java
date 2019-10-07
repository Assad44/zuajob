package cd.maichapayteam.zuajob.Tools;

import java.util.ArrayList;
import java.util.List;

import cd.maichapayteam.zuajob.Models.DAOClass.AnnonceDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.CategorieDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.NotificationDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.PostulerDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.ServiceDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.SollicitationDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.SousCategorieDAO;
import cd.maichapayteam.zuajob.Models.DAOClass.UserDAO;
import cd.maichapayteam.zuajob.Models.Object.Annonce;
import cd.maichapayteam.zuajob.Models.Object.Categorie;
import cd.maichapayteam.zuajob.Models.Object.Notification;
import cd.maichapayteam.zuajob.Models.Object.Postuler;
import cd.maichapayteam.zuajob.Models.Object.Service;
import cd.maichapayteam.zuajob.Models.Object.Sollicitation;
import cd.maichapayteam.zuajob.Models.Object.SousCategorie;
import cd.maichapayteam.zuajob.Models.Object.User;

public class ManageLocalData {

    public static User createUser(User user) {
        return RemoteDataSync.createUser(user);
    }

    public static boolean checkNumero(String numero) {
        return RemoteDataSync.checkNumero(numero);
    }

    public static String confirmCode(long id, String code) {
        return RemoteDataSync.confirmCode(id, code);
    }

    public static User updateUser(User user) {
        return RemoteDataSync.updateUser(user);
    }

    public static User login(String phone, String mdp) {
        return RemoteDataSync.login(phone, mdp);
    }

    public static boolean changePassword(String acienMDP, String nouveauMDP) {
        return RemoteDataSync.updatePassword(acienMDP, nouveauMDP);
    }

    public static List<Categorie> listUserPreferenceCategorie() {
        CategorieDAO categorieDAO = CategorieDAO.getInstance(GeneralClass.applicationContext);
        List<Categorie> list = categorieDAO.getAllUserPreference();
        if(list.size()==0) RemoteDataSync.getUserPreference();
        return list;
    }

    public static List<User> listJobeurs(int min) {
        List<User> list = RemoteDataSync.getListJobeur((int)(min/20));
        if(list.size()==0) {
            UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
            list = userDAO.listJobeurs(min);
        }
        return list;
    }

    public static List<User> listJobeurs(int min, String keyword) {
        List<User> list = RemoteDataSync.getListJobeur(keyword, (int)(min/20));
        if(list.size()==0) {
            UserDAO userDAO = UserDAO.getInstance(GeneralClass.applicationContext);
            list = userDAO.listJobeurs(min, keyword);
        }
        return list;
    }

    public static List<Service> listRandomService(int min) {
        List<Service> list = RemoteDataSync.getRandomServices((int)(min/20));
        if(list.size()==0) {
            ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
            list = serviceDAO.randomService(min);
        }
        return list;
    }

    public static List<Service> listNewService(int min, long idSC) {
        SousCategorie sousCategorie = new SousCategorie();
        sousCategorie.setId(idSC);
        List<Service> list = RemoteDataSync.getNewServices((int)(min/20), sousCategorie.getId());
        if(list.size()==0) {
            ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
            list = serviceDAO.getNewService(min, sousCategorie);
        }
        return list;
    }

    public static List<Service> listServiceByCote(int min, SousCategorie sousCategorie) {
        List<Service> list = RemoteDataSync.getServicesByCote((int)(min/20), sousCategorie.getId());
        if(list.size()==0) {
            ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
            list = serviceDAO.getServiceByCote(min, sousCategorie);
        }
        return list;
    }

    public static List<Service> listServiceByRealisation(int min, SousCategorie sousCategorie) {
        List<Service> list = RemoteDataSync.getServicesByRealisationCount((int)(min/20), sousCategorie.getId());
        if(list.size()==0) {
            ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
            list = serviceDAO.getServiceByCote(min, sousCategorie);
        }
        return list;
    }

    public static List<Annonce> listNewAnnonce(int min, long idSC) {
        SousCategorie sousCategorie = new SousCategorie();
        sousCategorie.setId(idSC);
        List<Annonce> list = RemoteDataSync.getNewAnnonces((int)(min/20), sousCategorie.getId());
        if(list.size()==0) {
            AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
            list = annonceDAO.getNewAnnonce(min, sousCategorie);
        }
        return list;
    }

    public static List<Annonce> listAnnonce(int min, SousCategorie sousCategorie) {
        List<Annonce> list = RemoteDataSync.getAnnonces((int)(min/20), sousCategorie.getId());
        if(list.size()==0) {
            AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
            list = annonceDAO.getAll(min, sousCategorie);
        }
        return list;
    }

    public static List<Annonce> listRandomAnnonce(int min) {
        List<Annonce> list = RemoteDataSync.getRandomAnnonces();
        if(list.size()==0) {
            AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
            list = annonceDAO.randomAnnonce(min);
        }
        return list;
    }

    public static List<Service> listMesServices() {
        List<Service> list = RemoteDataSync.getMesServices();
        if(list.size()==0) {
            ServiceDAO serviceDAO = ServiceDAO.getInstance(GeneralClass.applicationContext);
            list = serviceDAO.getMesServices();
        }
        return list;
    }

    public static List<Annonce> listMesAnnonces() {
        List<Annonce> list = RemoteDataSync.getMesAnnonces();
        if(list.size()==0) {
            AnnonceDAO annonceDAO = AnnonceDAO.getInstance(GeneralClass.applicationContext);
            list = annonceDAO.getMesAnnonces();
        }
        return list;
    }

    public static List<Sollicitation> listMesSollicitations() {
        List<Sollicitation> list = RemoteDataSync.getMesSollicitations();
        if(list.size()==0) {
            SollicitationDAO sollicitationDAO = SollicitationDAO.getInstance(GeneralClass.applicationContext);
            list = sollicitationDAO.getMesSollucitations();
        }
        return list;
    }

    public static List<Postuler> listMesPostulances() {
        List<Postuler> list = RemoteDataSync.getMesPostulations();
        if(list.size()==0) {
            PostulerDAO postulerDAO = PostulerDAO.getInstance(GeneralClass.applicationContext);
            list = postulerDAO.getMesPostulances();
        }
        return list;
    }

    public static List<Postuler> listPostulants(Annonce annonce) {
        List<Postuler> list = RemoteDataSync.getPostulants(annonce.getId());
        if(list.size()==0) {
            PostulerDAO postulerDAO = PostulerDAO.getInstance(GeneralClass.applicationContext);
            list = postulerDAO.getPostulants(annonce);
        }
        return list;
    }

    public static List<Sollicitation> listSollicitant(Service service) {
        List<Sollicitation> list = RemoteDataSync.getSollicitations(service.getId());
        if(list.size()==0) {
            SollicitationDAO sollicitationDAO = SollicitationDAO.getInstance(GeneralClass.applicationContext);
            list = sollicitationDAO.getSollicitants(service);
        }
        return list;
    }

    public static List<Categorie> listCategorie() {
        CategorieDAO categorieDAO = CategorieDAO.getInstance(GeneralClass.applicationContext);
        List<Categorie> list = categorieDAO.getAll();
        if(list.size()==0) list = RemoteDataSync.getListCategorie();
        return list;
    }

    public static List<SousCategorie> listSousCategorie(Categorie categorie) {
        SousCategorieDAO sousCategorieDAO = SousCategorieDAO.getInstance(GeneralClass.applicationContext);
        List<SousCategorie> list = sousCategorieDAO.getAll(categorie);
        if(list.size()==0) list = RemoteDataSync.getListSousCategorie();
        return list;
    }

    public static Sollicitation solliciter(long idService) {
        return RemoteDataSync.solliciter(idService);
    }

    public static Service creerService(Service service) {
        return RemoteDataSync.publierService(service);
    }

    public static Annonce creerAnnonce(Annonce annonce) {
        return RemoteDataSync.publierAnnonce(annonce);
    }

    public static Postuler postuler(long idAnnonece) {
        return RemoteDataSync.postuler(idAnnonece);
    }

    public static List<Object> historiqueBySollicitation() {
        List<Object> objectList = new ArrayList<>();
        objectList.add(RemoteDataSync.getHistoriqueRealisationBySollicitation());
        objectList.add(RemoteDataSync.getHistoriqueRealisationByPostulance());
        return objectList;
    }

    public static List<Object> mesRDVenAttente() {
        List<Postuler> post = RemoteDataSync.getMesRDVenAttentePost();
        List<Sollicitation> soll = RemoteDataSync.getMesRDVenAttenteSoll();
        List<Object> objectList = new ArrayList<>();
        SollicitationDAO sollicitationDAO = SollicitationDAO.getInstance(GeneralClass.applicationContext);
        if(post.size()==0) post = new ArrayList<>();
        if(soll.size()==0) soll = new ArrayList<>();
        objectList.add(post);
        objectList.add(soll);
        return objectList;
    }

    public static List<Object> mesRDV() {
        List<Postuler> post = RemoteDataSync.getMesRDVPostulance();
        List<Sollicitation> soll = RemoteDataSync.getMesRDVSollicitance();
        List<Object> objectList = new ArrayList<>();
        SollicitationDAO sollicitationDAO = SollicitationDAO.getInstance(GeneralClass.applicationContext);
        if(post.size()==0) post = new ArrayList<>();
        if(soll.size()==0) soll = new ArrayList<>();
        objectList.add(post);
        objectList.add(soll);
        return objectList;
    }

    public static List<Sollicitation> mesSollicitationsRefusees() {
        List<Sollicitation> sollicitationList = RemoteDataSync.getMesSollicitationsRefuses();
        if(sollicitationList.size()==0) {
            //TODO recupérer dans la bdd
        }
        return sollicitationList;
    }

    public static List<Postuler> mesPostulancesRefusees() {
        List<Postuler> postulerList = RemoteDataSync.getMesPostulancesRefuses();
        if(postulerList.size()==0) {
            //TODO recupérer dans la bdd
        }
        return postulerList;
    }

    public static List<Postuler> mesAnnoncesConclus() {
        List<Postuler> postulerList = RemoteDataSync.getMesAnnoncesConclu();
        if(postulerList.size()==0) {
            //TODO recupérer dans la bdd
        }
        return postulerList;
    }

    public static List<Sollicitation> mesServicesConclus() {
        List<Sollicitation> sollicitationList = RemoteDataSync.getMesServicesConclu();
        if(sollicitationList.size()==0) {
            //TODO recupérer dans la bdd
        }
        return sollicitationList;
    }

    public static Sollicitation creerRDVbyJobeur(long idSollicitation, String date, String heure, String detail, float montant, String devise) {
        return RemoteDataSync.creerRDVbyJobeur(idSollicitation, date, heure, detail, montant, devise);
    }

    public static Sollicitation refuserSollicitation(long idSollicitation) {
        return RemoteDataSync.refuserSollicitation(idSollicitation);
    }

    public static Sollicitation confirmerRDVbyUser(long idSollicitation, String codePayement) {
        return RemoteDataSync.confirmerRDVbyUser(idSollicitation, codePayement);
    }

    public static Sollicitation serviceRenduBySollicitance(long idSollicitation, int cote, String comment) {
        return RemoteDataSync.serviceRenduBySollicitance(idSollicitation, cote, comment);
    }

    public static Postuler creerRDVbyUser(long idPostulance, String date, String heure, String detail, float montant, String devise, String codePayement) {
        return RemoteDataSync.creerRDVbyUser(idPostulance, date, heure, detail, codePayement, montant, devise);
    }

    public static Postuler refuserPostulance(long idPostulance) {
        return RemoteDataSync.refuserPostulance(idPostulance);
    }

    public static Postuler confirmerRDVbyJobeur(long idPostulance) {
        return RemoteDataSync.confirmerRDVbyJobeur(idPostulance);
    }

    public static Postuler serviceRenduByPostulance(long idPostulance, int cote, String comment) {
        return RemoteDataSync.serviceRenduByPostulance(idPostulance, cote, comment);
    }

    public static boolean uploadImage(String image, String format) {
        return RemoteDataSync.uploadImage(image, format);
    }

    public static List<Notification> getListNotifications() {
        return NotificationDAO.getInstance(GeneralClass.applicationContext).getAll();
    }

    public static void setNotificationReaded(long idNotification) {
        NotificationDAO.getInstance(GeneralClass.applicationContext).setReaded(idNotification);
    }

    public static boolean deconnection() {
        if(UserDAO.getInstance(GeneralClass.applicationContext).deconnection()>0) {
            CategorieDAO.getInstance(GeneralClass.applicationContext).deletePersonnelData();
            AnnonceDAO.getInstance(GeneralClass.applicationContext).deletePersonnelData();
            ServiceDAO.getInstance(GeneralClass.applicationContext).deletePersonnelData();
            PostulerDAO.getInstance(GeneralClass.applicationContext).deletePersonnelData();
            SollicitationDAO.getInstance(GeneralClass.applicationContext).deletePersonnelData();
            NotificationDAO.getInstance(GeneralClass.applicationContext).deletePersonnelData();
            return true;
        }
        return false;
    }

}
