package com.foodordermicroservice.common.share.constant;

public class ShareConstant {
public static final String SYSTEM_USER = "SYSTEM";
  
  public static final String USER_CONTEXT_KEY = "USER_CONTEXT";
  
  public static final int DEFAULT_PAGE_SIZE = 20;
  
  public static final int MAX_PAGE_SIZE = 500;
  
  public static final String DEFAULT_SORT_DIRECTION = "desc";
  
  public static final String DEFAULT_SORT_FIELD = "created_at";
  
  public static final String DEFAULT_TENANT_ID = "default";
  
  public static final String DEFAULT_ORGANIZATION_ID = "default";
  
  public static final String UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
  
  public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
  
  public static final int MIN_PASSWORD_LENGTH = 8;
  
  public static final int MAX_EMAIL_LENGTH = 255;
  
  public static final int MAX_NAME_LENGTH = 100;
  
  public static final int MAX_DESCRIPTION_LENGTH = 1000;
  
  public static final long SHORT_CACHE_TTL = 300L;
  
  public static final long MEDIUM_CACHE_TTL = 900L;
  
  public static final long LONG_CACHE_TTL = 3600L;
  
  public static final String DATE_FORMAT = "yyyy-MM-dd";
  
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  
  public static final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
}