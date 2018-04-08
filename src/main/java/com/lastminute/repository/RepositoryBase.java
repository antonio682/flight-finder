package com.lastminute.repository;

public class RepositoryBase {

    protected String fullPathTo(String fileName) { return getClass().getClassLoader().getResource(fileName).getPath(); }
}
