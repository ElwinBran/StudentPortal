package com.github.elwinbran.studentportal;

/**
 *
 * @author Elwin Slokker
 */
public interface Portal
{
    public final static String TITLE_IDENTIFIER = "title";
    public final static String URL_IDENTIFIER = "url";

    String getTitle();

    String getUrl();
}
