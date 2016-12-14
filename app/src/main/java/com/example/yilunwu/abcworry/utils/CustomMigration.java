/*
package com.example.yilunwu.abcworry.utils;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

*/
/**
 * Created by yilunwu on 16/12/12.
 *//*


*/
/**
 * 升级数据库
 *//*

public class CustomMigration implements RealmMigration {
    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema= realm.getSchema();
        if (oldVersion==1&&newVersion==2){
            //创建Dog表
            RealmObjectSchema dogSchema=schema.create("Dog");
            dogSchema.addField("name",String.class);
            dogSchema.addField("age",int.class);

            //User中添加dogs属性
            schema.get("USer")
                    .addRealmListField("dogs",dogSchema)
                    .transform(new RealmObjectSchema.Function() {

                        @Override
                        public void apply(DynamicRealmObject dynamicRealmObject) {
                            //为已经存在的数据设置dogs数据
                            DynamicRealmObject dog = realm.createObject("Dog");
                            dog.set("name", "二哈");
                            dog.set("age", 2);
                            dog.getList("dogs").add(dog);
                        }
                    });


            RealmObjectSchema personSchema=schema.get("User");
            //新增@Required的id
            personSchema
                    .addField("id",String.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function(){
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("id","1");//为id设置值
                            String id=obj.getString("id");
                            obj.setString("id","1");
                            obj.setInt("id",1);
                            obj.setLong("id",1);
                        }
                    })
                    .removeField("age");//移除age属性

            personSchema.setNullable("id", true);//取消id必填
            personSchema.removeField("id");//移除id字段
            personSchema.renameField("id", "userId");//重命名

                oldVersion++;

        }
    }
}
*/
