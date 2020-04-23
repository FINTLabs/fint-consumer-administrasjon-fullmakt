
package no.fint.consumer.config;

public enum Constants {
;

    public static final String COMPONENT = "administrasjon-fullmakt";
    public static final String COMPONENT_CONSUMER = COMPONENT + " consumer";
    public static final String CACHE_SERVICE = "CACHE_SERVICE";

    
    public static final String CACHE_INITIALDELAY_FULLMAKT = "${fint.consumer.cache.initialDelay.fullmakt:900000}";
    public static final String CACHE_FIXEDRATE_FULLMAKT = "${fint.consumer.cache.fixedRate.fullmakt:900000}";
    
    public static final String CACHE_INITIALDELAY_ROLLE = "${fint.consumer.cache.initialDelay.rolle:960000}";
    public static final String CACHE_FIXEDRATE_ROLLE = "${fint.consumer.cache.fixedRate.rolle:900000}";
    

}
