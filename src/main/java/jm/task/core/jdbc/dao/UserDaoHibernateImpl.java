package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String stringSql = "CREATE TABLE IF NOT EXISTS USERS "
                + "(ID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,"
                + "NAME VARCHAR(45),"
                + "LASTNAME VARCHAR(45) NOT NULL,"
                + "AGE TINYINT NOT NULL)";
        session.createSQLQuery(stringSql).addEntity(User.class);
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String stringSql = "DROP TABLE IF EXISTS USERS";
        session.createSQLQuery(stringSql).addEntity(User.class);
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        System.out.printf("User с именем - %s  добавлен в базу данных\n", name);
        session.getTransaction().commit();


        //        Transaction transaction = null;
//        try (Session session = getSessionFactory().openSession()) {
////            User user = new User(name, lastName, age);
//            transaction = session.beginTransaction();
//            session.save(name);
//            transaction.commit();
//            System.out.format("User с именем - %s  добавлен в базу данных\n", name);
//        } catch (Exception e) {
//            transaction.rollback();
//            e.printStackTrace();
//        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
//        User user = session.get(User.class, id);
        session.delete(session.get(User.class, id));
        session.getTransaction().commit();

//        Transaction transaction = null;
//        Session session = getSessionFactory().openSession();
//        transaction = session.beginTransaction();
//        User userRemove = new User();
//        session.delete(userRemove);
//        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            String stringSql = "FROM User";
            userList = session.createQuery(stringSql, User.class).getResultList();
            session.getTransaction().commit();
            return userList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String stringSql = "DELETE FROM users";
        session.createSQLQuery(stringSql).executeUpdate();
        session.getTransaction().commit();

//        Transaction transaction = null;
//        try (Session session = getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//        }
    }
}
