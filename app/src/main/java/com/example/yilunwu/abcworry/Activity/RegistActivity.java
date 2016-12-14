//package com.example.yilunwu.abcworry.Activity;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.example.yilunwu.abcworry.R;
//import com.example.yilunwu.abcworry.model.City;
//import com.example.yilunwu.abcworry.model.Dog;
//import com.example.yilunwu.abcworry.model.Person;
//import com.example.yilunwu.abcworry.model.User;
//import com.example.yilunwu.abcworry.model.User1;
//import com.example.yilunwu.abcworry.model.User2;
//import com.example.yilunwu.abcworry.utils.CustomMigration;
//import com.example.yilunwu.abcworry.utils.HttpThread1;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.security.SecureRandom;
//
//import io.realm.Realm;
//import io.realm.RealmAsyncTask;
//import io.realm.RealmConfiguration;
//import io.realm.RealmQuery;
//import io.realm.RealmResults;
//import io.realm.Sort;
//import io.realm.internal.IOException;
//
//public class RegistActivity extends Activity {
//    private EditText name;
//    private EditText age;
//    private Button regist;
//    Realm mRealm = Realm.getDefaultInstance();
//    private Context context;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_regist);
//        name = (EditText) findViewById(R.id.name);
//        age = (EditText) findViewById(R.id.age);
//        regist = (Button) findViewById(R.id.regist);
//        Realm.init(this);
//
//        regist.setOnClickListener(new View.OnClickListener() {
//            String url = "http://ipconfig/web/MyServlet";
//
//            @Override
//            public void onClick(View v) {
//                new HttpThread1(url, name.getText().toString(), age.getText()
//                        .toString()).start();
//
//            }
//        });
//
//        mRealm.executeTransaction(new Realm.Transaction() {
//
//            @Override
//            public void execute(Realm realm) {
//                User user = realm.createObject(User.class);
//                user.setName("Gavin");
//                user.setAge(23);
//
//                Dog dog1 = realm.createObject(Dog.class);
//                dog1.setAge(1);
//                dog1.setName("二哈");
//                user.getDogs().add(dog1);
//
//                Dog dog2 = realm.createObject(Dog.class);
//                dog2.setAge(2);
//                dog2.setName("阿拉斯加");
//                user.getDogs().add(dog2);
//            }
//        });
//
//        final User1 user1 = new User1();
//        user1.setName("Jack");
//        user1.setAge("2");
//        mRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealmOrUpdate(user1);
//
//            }
//        });
//
//
//        mRealm.executeTransaction(new Realm.Transaction() {
//
//            @Override
//            public void execute(Realm realm) {
//                User2 user2 = realm.createObject(User2.class);
//                user2.name = "Micheal";
//                user2.age = 30;
//            }
//        });
//
//        mRealm.beginTransaction();//开启事务
//        User user = mRealm.createObject(User.class);
//        user.setName("Gavin");
//        user.setId("3");
//        mRealm.commitTransaction();//提交事务
//
//
//        //使用json字符串插入数据
//        realm.executeTransaction(new Realm.Transaction() {
//
//            @Override
//            public void execute(Realm realm) {
//                realm.createObjectFromJson(City.class, "{city:\"Copenhagen\",id:1}");
//            }
//        });
//
//        //使用InputSream插入数据
//        realm.executeTransaction(new Realm.Transaction() {
//
//            @Override
//            public void execute(Realm realm) {
//                try {
//                    InputStream is = new FileInputStream(new File("path_to_file"));
//                    realm.createAllFromJson(City.class, is);
//                } catch (IOException e) {
//                    throw new RuntimeException();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (java.io.IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//
//        //查询
//        RealmResults<User> userList = mRealm.where(User.class).findAll();
//        RealmResults<User> userList1 = mRealm.where(User.class)
//                .equalTo("name", "Gavin")
//                .findAllAsync();
//
//        if (userList.isLoaded()) {
//            //完成查询
//
//        }
//
//        User user2 = mRealm.where(User.class).findFirst();//查询第一条数据
//        RealmResults<User> userList2 = mRealm.where(User.class)
//                .equalTo("name", "Gavin")
//                .equalTo("dogs.name", "二哈")
//                .findAll();
//
//        RealmQuery<User> query = mRealm.where(User.class);
//        query.equalTo("name", "Gavin");
//        query.or().equalTo("name", "Eric");
//
//        RealmResults<User> userList3 = query
//                .equalTo("name", "Gvain")
//                .or().equalTo("name", "Eric")
//                .findAll();
//
//
//        //排序
//        RealmResults<User> userList4 = mRealm.where(User.class).findAll();
//        userList4 = userList4.sort("age");//根据age,正序排列
//        userList4 = userList4.sort("age", Sort.DESCENDING);//逆序排列
//
//
//        //聚合
//        RealmResults<User> results = realm.where(User.class).findAll();
//        long sum = results.sum("age").longValue();
//        long min = results.min("age").longValue();
//        long max = results.max("age").longValue();
//        double average = results.average("age");
//        long matches = results.size();
//
//
//        //改
//        mRealm.executeTransaction(new Realm.Transaction() {
//
//            @Override
//            public void execute(Realm realm) {
//                //先查找后得到User对象
//                User user = mRealm.where(User.class).findFirst();
//                user.setAge(26);
//            }
//        });
//
//
//        //删
//        //先查找到数据
//        final RealmResults<User> userList5 = mRealm.where(User.class).findAll();
//        mRealm.executeTransaction(new Realm.Transaction() {
//
//            @Override
//            public void execute(Realm realm) {
//                userList5.get(0).deleteFromRealm();
//            }
//        });
//
//        mRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                userList5.deleteFromRealm(0);
//                userList5.deleteFirstFromRealm();//删除user表中的第一条数据
//                userList5.deleteLastFromRealm();//删除user表的最后一条数据
//                userList5.deleteAllFromRealm();//删除user表中的全部数据
//            }
//        });
//
//
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .name("myrealm.realm")//文件名
//                .schemaVersion(1)
//                .migration(new CustomMigration())//升级数据库
//                .build();
//
//        //加密
//        byte[] key = new byte[64];
//        new SecureRandom().nextBytes(key);
//        RealmConfiguration config2=new RealmConfiguration.Builder()
//                .encryptionKey(key)
//                .build();
//        Realm realm=Realm.getInstance(config2);
//
//
//
//
//    }
//
//
//
//    RealmConfiguration config = new RealmConfiguration.Builder()
//            .name("myrealm.realm")//文件名
//            .schemaVersion(0)
//            .build();
//    Realm realm = Realm.getInstance(config);
//
//    RealmConfiguration myConfig = new RealmConfiguration.Builder()
//            .name("myrealm.realm")
//            .inMemory().build();
//
//    RealmAsyncTask transaction = mRealm.executeTransactionAsync(new Realm.Transaction() {
//        @Override
//        public void execute(Realm realm) {
//            User user = realm.createObject(User.class);
//            user.setName("Eric");
//            user.setId("4");
//        }
//
//    }, new Realm.Transaction.OnSuccess() {
//
//        @Override
//        public void onSuccess() {
//            //成功回调
//        }
//    }, new Realm.Transaction.OnError() {
//
//        @Override
//        public void onError(Throwable error) {
//            //失败回调
//        }
//    });
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        realm.close();
//    }
//
//    @Override
//    protected void onStop() {
//        if (transaction != null && !transaction.isCancelled()) {
//            transaction.cancel();
//        }
//        super.onStop();
//    }
//}
