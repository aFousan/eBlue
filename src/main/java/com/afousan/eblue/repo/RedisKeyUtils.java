/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.repo;

/**
 *
 * @author aFousan
 */
abstract class RedisKeyUtils {

    static final String UID = ":uid";

    static String uid(String uid) {
        return UID + uid;
    }

    static String globalUid() {
        return "global:uid";
    }

    public static String user(String name) {
        return "user:" + name + ":uid";
    }
}
