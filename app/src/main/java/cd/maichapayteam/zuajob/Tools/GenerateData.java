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

    //private static list<user> listjobeurs() {
    //    userdao userdao = userdao.getinstance(generalclass.applicationcontext);
    //    list<user> list = new arraylist<>();
    //    long max = userdao.max();
    //    for (int i = 0; i < 100; i++) {
    //        user user = generalclass.getrandomuser();
    //        max++;
    //        user.setid(max+1);
    //        user.settype(1);
    //        user = userdao.ajouter(user);
    //        if(user!=null) list.add(user);
    //    }
    //    for (int i = 0; i < 100; i++) {
    //        user user = generalclass.getrandomuser();
    //        max++;
    //        user.setid(max+1);
    //        user.settype(0);
    //        user = userdao.ajouter(user);
    //        if(user!=null) list.add(user);
    //    }
    //    log.e("generatedata", "all users:" + userdao.count());
    //    return list;
    //}
//
    //public static list<categorie> listuserpreferencecategorie() {
    //    categoriedao categoriedao = categoriedao.getinstance(generalclass.applicationcontext);
    //    list<categorie> list = categoriedao.getalluserpreference();
    //    return list;
    //}
//
    //private static list<service> listservice() {
    //    list<service> list = new arraylist<>();
    //    list<categorie> categorielist = categoriedao.getinstance(generalclass.applicationcontext).getall();
    //    list<user> userlist = userdao.getinstance(generalclass.applicationcontext).listalljobeurs();
    //    servicedao servicedao = servicedao.getinstance(generalclass.applicationcontext);
    //    long max = servicedao.max();
    //    for (int i = 0; i < 100; i++) {
    //        categorie categorie = categorielist.get(generalclass.randbetween(0, categorielist.size()-1));
    //        list<souscategorie> souscategorielist = souscategoriedao.getinstance(generalclass.applicationcontext).getall(categorie);
    //        souscategorie souscategorie = souscategorielist.get(generalclass.randbetween(0, souscategorielist.size()-1));
    //        user prestateur = userlist.get(generalclass.randbetween(0, userlist.size()-1));
    //        service service = new service();
    //        if(new random().nextint(200)==0) {
    //            prestateur = userdao.getinstance(generalclass.applicationcontext).myprofil();
    //            service.setmy(true);
    //        }
    //        service.setmy(false);
    //        service.setdatepublication(generalclass.randomdate(2018, 2019).tostring());
    //        service.setsouscategorie(souscategorie.getdesignation());
    //        service.setphonejobeur(prestateur.getcodepays() + prestateur.getphone());
    //        service.setnomsjobeur(prestateur.getprenom() + prestateur.getnom());
    //        max = max + 1;
    //        service.setid(max);
    //        service.setnombrerealisation(generalclass.randbetween(0, 100));
    //        service.setmontant(new random().nextint());
    //        string dev = "cdf";
    //        if(new random().nextint(2)==0) {
    //            dev = "usd";
    //            service.setmontant(new random().nextint(100) + 1);
    //        } else {
    //            service.setmontant(new random().nextint(10000) + 1);
    //        }
    //        service.setdevise(dev);
    //        service.setidsouscategorie(souscategorie.getid());
    //        service.setidjobeur(prestateur.getid());
    //        service.setcategorie(categorie.getdesignation());
    //        service.setidcategorie(categorie.getid());
    //        service.setdescription(generalclass.getrandomphrase(new random().nextint(4) + 1));
    //        service.setcote(new random().nextint(6));
    //        service = servicedao.ajouter(service);
    //        if(service!=null) list.add(service);
    //    }
    //    return list;
    //}
//
    //private static list<annonce> listannonce() {
    //    list<annonce> list = new arraylist<>();
    //    list<categorie> categorielist = categoriedao.getinstance(generalclass.applicationcontext).getall();
    //    list<user> userlist = userdao.getinstance(generalclass.applicationcontext).listallusers();
    //    annoncedao annoncedao = annoncedao.getinstance(generalclass.applicationcontext);
    //    for (int i = 0; i < 100; i++) {
    //        categorie categorie = categorielist.get(generalclass.randbetween(0, categorielist.size()-1));
    //        list<souscategorie> souscategorielist = souscategoriedao.getinstance(generalclass.applicationcontext).getall(categorie);
    //        souscategorie souscategorie = souscategorielist.get(generalclass.randbetween(0, souscategorielist.size()-1));
    //        user user = userlist.get(generalclass.randbetween(0, userlist.size()-1));
    //        annonce annonce = new annonce();
    //        if(new random().nextint(200)==0) {
    //            user = userdao.getinstance(generalclass.applicationcontext).myprofil();
    //            annonce.setmy(true);
    //        }
    //        annonce.setdatepublication(generalclass.randomdate(2018, 2019).tostring());
    //        annonce.setsouscategorie(souscategorie.getdesignation());
    //        annonce.setphoneuser(user.getcodepays() + user.getphone());
    //        annonce.setnomsuser(user.getprenom() + user.getnom());
    //        annonce.setid(annoncedao.max()+1);
    //        if(new random().nextint(90)==0) annonce.setconfied(true);
    //        annonce.setmontant(new random().nextint());
    //        string dev = "cdf";
    //        if(new random().nextint(2)==0) {
    //            dev = "usd";
    //            annonce.setmontant(new random().nextint(100) + 1);
    //        } else {
    //            annonce.setmontant(new random().nextint(10000) + 1);
    //        }
    //        annonce.setdevise(dev);
    //        annonce.setidsouscategorie(souscategorie.getid());
    //        annonce.setiduser(user.getid());
    //        annonce.setcategorie(categorie.getdesignation());
    //        annonce.setidcategorie(categorie.getid());
    //        annonce.setdescription(generalclass.getrandomphrase(new random().nextint(4) + 1));
    //        annonce = annoncedao.ajouter(annonce);
    //        if(new random().nextint(200)==0) annonce.setmy(true);
    //        if(annonce !=null) list.add(annonce);
    //    }
    //    return list;
    //}
//
    //private static list<postuler> listpostuler() {
    //    list<postuler> list = new arraylist<>();
    //    postulerdao postulerdao = postulerdao.getinstance(generalclass.applicationcontext);
    //    userdao userdao = userdao.getinstance(generalclass.applicationcontext);
    //    list<user> prestateurlist = userdao.listalljobeurs();
//
    //    list<annonce> listannonce = annoncedao.getinstance(generalclass.applicationcontext).getall();
//
    //    for (int i = 0; i < 100; i++) {
    //        annonce annonce = listannonce.get(new random().nextint(listannonce.size()));
    //        user prestateur = prestateurlist.get(generalclass.randbetween(0, prestateurlist.size()-1));
    //        postuler postuler = new postuler();
    //        if(annonce.ismy()) {
    //            postuler.setmy(true);
    //            postuler.setphoneuser(prestateur.getcodepays() + prestateur.getphone());
    //            postuler.setnomsuser(prestateur.getprenom() + " " + prestateur.getnom());
    //            postuler.setiduser(prestateur.getid());
    //        } else {
    //            if(generalclass.currentuser != null && generalclass.currentuser.gettype()==1) {
    //                if(new random().nextint(20)==0) {
    //                    postuler.sethavepostuled(true);
    //                    postuler.setphoneuser(annonce.getphoneuser());
    //                    postuler.setnomsuser(annonce.getnomsuser());
    //                    postuler.setiduser(annonce.getiduser());
    //                }
    //            }
    //        }
    //        if(new random().nextint(4)==0) {
    //            postuler.setrdv(true);
    //            string dev = "cdf";
    //            if(new random().nextint(2)==0) {
    //                dev = "usd";
    //                postuler.setmontantconclu(new random().nextint(100) + 1);
    //            } else {
    //                postuler.setmontantconclu(new random().nextint(10000) + 1);
    //            }
    //            postuler.setdeviseconclu(dev);
    //            postuler.setheurerdv("08:30");
    //            postuler.setdaterdv(generalclass.randomdate().tostring());
    //            if(new random().nextint(3)==0) {
    //                postuler.setconclu(true);
    //            }
    //        }
    //        postuler.setid(postulerdao.max()+1);
    //        postuler.setidannonce(annonce.getid());
    //        postuler.setdescriptionannonce(annonce.getdescription());
    //        postuler.setmontantannonce(annonce.getmontant());
    //        postuler.setdeviseannonce(annonce.getdevise());
    //        postuler.setdate(generalclass.randomdate().tostring());
    //        postuler.setcomment(generalclass.getrandomphrase(1));
    //        postuler.setcote(new random().nextint(6));
    //        postuler = postulerdao.ajouter(postuler);
    //        if(postuler !=null) list.add(postuler);
    //    }
    //    return list;
    //}
//
    //private static list<sollicitation> listsolliciter() {
    //    list<sollicitation> list = new arraylist<>();
    //    sollicitationdao sollicitationdao = sollicitationdao.getinstance(generalclass.applicationcontext);
    //    userdao userdao = userdao.getinstance(generalclass.applicationcontext);
    //    list<user> userlist = userdao.getall();
//
    //    list<service> listservice = servicedao.getinstance(generalclass.applicationcontext).getall();
//
    //    for (int i = 0; i < 1000; i++) {
    //        service service = listservice.get(new random().nextint(listservice.size()));
    //        user user = userlist.get(generalclass.randbetween(0, userlist.size()-1));
    //        sollicitation sollicitation = new sollicitation();
    //        if(service.ismy()) {
    //            sollicitation.setmy(true);
    //            sollicitation.setphoneuser(user.getcodepays() + user.getphone());
    //            sollicitation.setnomsuser(user.getprenom() + " " + user.getnom());
    //            sollicitation.setiduser(user.getid());
    //        } else {
    //            if(generalclass.currentuser != null && generalclass.currentuser.gettype()==1) {
    //                if(new random().nextint(20)==0) {
    //                    sollicitation.sethavesollicited(true);
    //                    sollicitation.setphoneuser(service.getphonejobeur());
    //                    sollicitation.setnomsuser(service.getnomsjobeur());
    //                    sollicitation.setiduser(service.getidjobeur());
    //                }
    //            }
    //        }
    //        if(new random().nextint(4)==0) {
    //            sollicitation.setrdv(true);
    //            string dev = "cdf";
    //            if(new random().nextint(2)==0) {
    //                dev = "usd";
    //                sollicitation.setmontantconclu(new random().nextint(100) + 1);
    //            } else {
    //                sollicitation.setmontantconclu(new random().nextint(10000) + 1);
    //            }
    //            sollicitation.setdeviseconclu(dev);
    //            sollicitation.setheurerdv("08:30");
    //            sollicitation.setdaterdv(generalclass.randomdate().tostring());
    //            if(new random().nextint(3)==0) {
    //                sollicitation.setconclu(true);
    //            }
    //        }
    //        sollicitation.setid(sollicitationdao.max()+1);
    //        sollicitation.setidservice(service.getid());
    //        sollicitation.setdescriptionservice(service.getdescription());
    //        sollicitation.setmontant(service.getmontant());
    //        sollicitation.setdevise(service.getdevise());
    //        sollicitation.setdate(generalclass.randomdate().tostring());
    //        sollicitation.setcomment(generalclass.getrandomphrase(1));
    //        sollicitation.setcote(new random().nextint(6));
    //        sollicitation = sollicitationdao.ajouter(sollicitation);
    //        if(sollicitation !=null) list.add(sollicitation);
    //    }
    //    return list;
    //}
//
    //public static void generateall() {
    //    categoriedao.createcategories();
    //    souscategorie.createsouscategories();
    //    list<user> userlist = listjobeurs();
    //    log.e("generatedata", "users created : " + userlist.size());
    //    list<service> servicelist = listservice();
    //    log.e("generatedata", "service created : " + servicelist.size());
    //    list<annonce> annoncelist = listannonce();
    //    log.e("generatedata", "annonce created : " + annoncelist.size());
    //    list<postuler> postulerlist = listpostuler();
    //    log.e("generatedata", "postuler created : " + postulerlist.size());
    //    list<sollicitation> sollicitationlist = listsolliciter();
    //    log.e("generatedata", "sollicitation created : " + sollicitationlist.size());
    //}
//
    //private static string generate(int length) {
    //    string chars = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz1234567890"; // tu supprimes les lettres dont tu ne veux pas
    //    string pass = "";
    //    for(int x=0;x<length;x++) {
    //        int i = (int)math.floor(math.random() * 62); // si tu supprimes des lettres tu diminues ce nb
    //        pass += chars.charat(i);
    //    }
    //    return pass;
    //}
//
//
//
//
//
    //public static user createuser(user user) {
    //    userdao userdao = userdao.getinstance(generalclass.applicationcontext);
    //    user.setid(userdao.max() + 1);
    //    user.setauthcode(generate(32));
    //    user.setmyprofil(true);
    //    userdao.ajouter(user);
    //    generateall();
    //    return userdao.ajouter(user);
    //}
//
    //public static boolean checknumero(string numero) {
    //    userdao userdao = userdao.getinstance(generalclass.applicationcontext);
    //    user user = userdao.findbyphonenumer(numero);
    //    if(user!=null) return true;
    //    return false;
    //}
//
    //public static user login(string phone, string mdp) {
    //    userdao userdao = userdao.getinstance(generalclass.applicationcontext);
    //    user user = userdao.findbyphonenumer(phone);
    //    if(user!=null) {
    //        if(user.getpassword().equals(mdp)) {
    //            generateall();
    //            //remotedatasync.getrandomuser(100);
    //            return user;
    //        }
    //    }
    //    user = new user();
    //    user.seterror(true);
    //    user.seterrorcode(36212);
    //    user.seterrormessage("le numéro de téléphone et le mot de passe saisis ne correspondent pas. veuillez réessayer svp.");
    //    return user;
    //}
//
    //public static list<user> listjobeurs(int min) {
    //    userdao userdao = userdao.getinstance(generalclass.applicationcontext);
    //    list<user> list = userdao.listjobeurs(min);
    //    return list;
    //}
//
    //public static list<user> listjobeurs(int min, string keyword) {
    //    userdao userdao = userdao.getinstance(generalclass.applicationcontext);
    //    return userdao.listjobeurs(min, keyword);
    //}
//
    //public static list<categorie> listcategorie() {
    //    categoriedao categoriedao = categoriedao.getinstance(generalclass.applicationcontext);
    //    list<categorie> list = categoriedao.getall();
    //    return list;
    //}
//
    //public static list<souscategorie> listsouscategorie(categorie categorie) {
    //    souscategoriedao souscategoriedao = souscategoriedao.getinstance(generalclass.applicationcontext);
    //    list<souscategorie> list = souscategoriedao.getall(categorie);
    //    return list;
    //}
//
    //public boolean deconnection() {
    //    if(userdao.getinstance(generalclass.applicationcontext).deconnection()>0) return true;
    //    return false;
    //}
//
    //public static list<service> listrandomservice(int min) {
    //    servicedao servicedao = servicedao.getinstance(generalclass.applicationcontext);
    //    list<service> list = servicedao.randomservice(min);
    //    return list;
    //}
//
    //public static list<service> listnewservice(int min, int idsouscategorie) {
    //    servicedao servicedao = servicedao.getinstance(generalclass.applicationcontext);
    //    souscategorie souscategorie = new souscategorie();
    //    souscategorie.setid(idsouscategorie);
    //    list<service> list = servicedao.getnewservice(min, souscategorie);
    //    return list;
    //}
//
    //public static list<service> listservicebycote(int min, int idsouscategorie) {
    //    servicedao servicedao = servicedao.getinstance(generalclass.applicationcontext);
    //    souscategorie souscategorie = new souscategorie();
    //    souscategorie.setid(idsouscategorie);
    //    list<service> list = servicedao.getservicebycote(min, souscategorie);
    //    return list;
    //}
//
    //public static list<service> listmesservices() {
    //    servicedao servicedao = servicedao.getinstance(generalclass.applicationcontext);
    //    list<service> list = servicedao.getmesservices();
    //    return list;
    //}
//
    //public static list<annonce> listrandomannonce(int min) {
    //    annoncedao annoncedao = annoncedao.getinstance(generalclass.applicationcontext);
    //    list<annonce> list = annoncedao.randomannonce(min);
    //    return list;
    //}
//
    //public static list<annonce> listnewannonce(int min, int idsouscategorie) {
    //    annoncedao annoncedao = annoncedao.getinstance(generalclass.applicationcontext);
    //    souscategorie souscategorie = new souscategorie();
    //    souscategorie.setid(idsouscategorie);
    //    list<annonce> list = annoncedao.getnewannonce(min, souscategorie);
    //    return list;
    //}
//
    //public static list<annonce> listmesannonces() {
    //    annoncedao annoncedao = annoncedao.getinstance(generalclass.applicationcontext);
    //    list<annonce> list = annoncedao.getmesannonces();
    //    return list;
    //}

}
