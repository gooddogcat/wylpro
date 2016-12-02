package com.example.yilunwu.abcworry.model;

/**
 * Created by yilunwu on 16/12/2.
 */

/**
 * 变种Builder模式自动化生成

 */
public class PersonTest {
    private final String mName ;
    private int mAge;
    private String mLocation;

    private PersonTest(Builder builder) {
        mName = builder.mName;
        mAge = builder.mAge;
        mLocation = builder.mLocation;
    }


    public static final class Builder {
        private String mName;
        private int mAge;
        private String mLocation;

        public Builder() {
        }

        public Builder mName(String mName) {
            this.mName = mName;
            return this;
        }

        public Builder mAge(int mAge) {
            this.mAge = mAge;
            return this;
        }

        public Builder mLocation(String mLocation) {
            this.mLocation = mLocation;
            return this;
        }

        public PersonTest build() {
            return new PersonTest(this);
        }
    }
}
