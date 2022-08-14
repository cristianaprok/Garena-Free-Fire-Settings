package com.jvmfrog.ffsettings.model;

public class ParamsModel {

    public ParamsModel() {};

    public String device_name, settings_source_url;
    public int dpi, fire_button, review, collimator, x2_scope, x4_scope, sniper_scope, free_review;

    public ParamsModel(String device_name, String settings_source_url, int dpi, int fire_button, int review, int collimator, int x2_scope, int x4_scope, int sniper_scope, int free_review) {
        this.device_name = device_name;
        this.settings_source_url = settings_source_url;
        this.dpi = dpi;
        this.fire_button = fire_button;
        this.review = review;
        this.collimator = collimator;
        this.x2_scope = x2_scope;
        this.x4_scope = x4_scope;
        this.sniper_scope = sniper_scope;
        this.free_review = free_review;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getSettings_source_url() {
        return settings_source_url;
    }

    public void setSettings_source_url(String settings_source_url) {
        this.settings_source_url = settings_source_url;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public int getFire_button() {
        return fire_button;
    }

    public void setFire_button(int fire_button) {
        this.fire_button = fire_button;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getCollimator() {
        return collimator;
    }

    public void setCollimator(int collimator) {
        this.collimator = collimator;
    }

    public int getX2_scope() {
        return x2_scope;
    }

    public void setX2_scope(int x2_scope) {
        this.x2_scope = x2_scope;
    }

    public int getX4_scope() {
        return x4_scope;
    }

    public void setX4_scope(int x4_scope) {
        this.x4_scope = x4_scope;
    }

    public int getSniper_scope() {
        return sniper_scope;
    }

    public void setSniper_scope(int sniper_scope) {
        this.sniper_scope = sniper_scope;
    }

    public int getFree_review() {
        return free_review;
    }

    public void setFree_review(int free_review) {
        this.free_review = free_review;
    }
}
