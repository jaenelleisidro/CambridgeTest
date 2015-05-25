package com.herobo.securitywizards.cambridgetest.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jaenelle Isidro (jaenelleisidro@yahoo.com) on 1/4/2015.
 */
public class Book implements Serializable {

    public String title_suggest;
    public List<String> edition_key;
    public String cover_i;
    public List<String> isbn;
    boolean has_fulltext;
    public List<String> text;
    public List<String> author_name;
    public List<String> contributor;
    public List<String> seed;
    public List<String> oclc;
    public List<String> ia;
    public List<String> author_key;
    public List<String> subject;
    public String title;
    public String ia_collection_s;
    public List<String> publish_date;
    public int ebook_count_i;
    public List<String> publish_place;
    public int edition_count;
    public String key;
    public boolean public_scan_b;

    public List<String> publisher;
    public List<String> language;
    public int last_modified_i;
    public String cover_edition_key;
    public List<String> person;
    public List<Integer> publish_year;
    public int  first_publish_year;
    public List<String> place;
    public List<String> time;
}
