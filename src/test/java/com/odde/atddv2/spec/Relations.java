package com.odde.atddv2.spec;

import com.github.leeonky.jfactory.Global;
import com.github.leeonky.jfactory.Spec;

public class Relations {
    @Global
    public static class School extends Spec<com.odde.atddv2.entity.relations.School> {
        @Override
        public void main() {
            property("id").ignore();
        }
    }

    @Global
    public static class Teacher extends Spec<com.odde.atddv2.entity.relations.Teacher> {
        @Override
        public void main() {
            property("id").ignore();
        }
    }

    @Global
    public static class Clazz extends Spec<com.odde.atddv2.entity.relations.Clazz> {
        @Override
        public void main() {
            property("id").ignore();
            property("school").is(School.class);
            property("teacher").is(Teacher.class);
        }
    }

    @Global
    public static class Student extends Spec<com.odde.atddv2.entity.relations.Student> {
        @Override
        public void main() {
            property("id").ignore();
            property("clazz").is(Clazz.class);
            property("school").dependsOn("clazz", clazz -> ((com.odde.atddv2.entity.relations.Clazz) clazz).getSchool());
        }
    }
}
