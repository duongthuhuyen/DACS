/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytracnghiem;

import Respository.UserRepository;
import Respository.impl.UserRepositoryImpl;
import com.laptrinhjavaweb.dao.IUserDAO;
import dao.IAnswer;
import dao.IHistory;
import dao.IInfortest;
import dao.IQuestion;
import dao.ItestDetail;
import dao.impl.IAnswerImpl;
import dao.impl.IHistoryImpl;
import dao.impl.IQuestionImpl;
import dao.impl.IinfortestImpl;
import dao.impl.ItestDetailImpl;
import dao.impl.UserDao;
import pojo.Users;
import responsity.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import layout.user.login;
import org.eclipse.persistence.jpa.jpql.tools.model.IPropertyChangeEvent;
import pojo.Answer;
import pojo.History;
import pojo.InforTest;
import pojo.Question;
import pojo.TestDetail;


/**
 *
 * @author PC
 */
public class MyTracNghiem {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Integer> swap(int n){
        ArrayList<Integer> list = new ArrayList<>();
        int upper = n;
        for(int i=0; i<n; ++i){
            list.add(i);
        }

        ArrayList<Integer> result = new ArrayList();
        Random random = new Random();
        for (int i=0; i<n; ++i){
            int ind = random.nextInt(upper);
            result.add(list.get(ind));
            list.remove(ind);
            --upper;
        }

        return result;
    }
    public static void main(String[] args) {
       // IQuestion iq = new IQuestionImpl();
       // Optional<Question> p = iq.getQuestionById(1);
      //  System.out.println(p.get());
       /* IHistory i = new IHistoryImpl();
        List<History> l = i.getHistoryByUserId(1);
        for(History a:l){
            System.out.println(a.getId());
        }*/
       //IInfortest i = new IinfortestImpl();
     /*  InforTest p =new InforTest();
       p = i.getInforTestByTestID(1);
        System.out.println(p.getName()+" "+p.getId()+" "+p.getTopic()+" "+p.getUserID()+" "+p.getDateCreate());
*/
    // int k = i.saveInforTest(4, "Tiếng Nhật kt", "Tiếng Nhật", true);
      //  System.out.println(k);
    /*  List<Question> listQ = iq.getListByUserIDAndTopic(1, "Toan");
      for(Question Q:listQ){
          System.out.println(Q.getContent());
      }*/
   // int k =iq.saveQuestion(3,"Toán", "3+4");
     //   System.out.println(k);
    // IQuestion q = new IQuestionImpl();
     //IAnswer a= new IAnswerImpl();
  //   boolean k = a.deleteByQID(3);
  //   boolean t = q.deleteQuestionByID(3);
    // System.out.println(k+" "+t);
       // System.out.println("k".equals("k"));
      ItestDetail i = new ItestDetailImpl();
      //  boolean dg = i.saveQuestionInTest(1, 2);
      //  System.out.println(dg);
       // Optional<Question> y = iq.getQuestionById(2);
       // Question u = y.orElseThrow();
        //System.out.println(u.getContent());
       // Random random = new Random();
      
    /*    for(int i=3;i>=0;i--){
            int k = random.nextInt(i);
            System.out.println(k);
        }*/
   // 1-10 random
    IHistory ih = new IHistoryImpl();
     boolean check = ih.saveHistory(5,7,3);
    
    }
}

    

