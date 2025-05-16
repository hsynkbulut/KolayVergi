package com.kolayvergi.constant.swagger;

public class SwaggerConstants {

    // Kullanıcı
    public static final String CREATE_KULLANICI_SUMMARY = "Kullanıcı oluştur";
    public static final String CREATE_KULLANICI_DESC = "Yeni bir kullanıcı oluşturur";
    public static final String GET_KULLANICI_SUMMARY = "Kullanıcı getir";
    public static final String GET_KULLANICI_DESC = "ID'ye göre kullanıcı bilgilerini getirir";
    public static final String UPDATE_KULLANICI_SUMMARY = "Kullanıcı güncelle";
    public static final String UPDATE_KULLANICI_DESC = "Kullanıcı bilgilerini günceller";
    public static final String DELETE_KULLANICI_SUMMARY = "Kullanıcı sil";
    public static final String DELETE_KULLANICI_DESC = "Kullanıcıyı sistemden siler";
    public static final String GET_ALL_KULLANICILAR_SUMMARY = "Tüm kullanıcıları listele";
    public static final String GET_ALL_KULLANICILAR_DESC = "Sistemdeki tüm kullanıcıları listeler";

    // Alışveriş
    public static final String CREATE_ALISVERIS_SUMMARY = "Yeni alışveriş oluştur";
    public static final String CREATE_ALISVERIS_DESC = "Sisteme yeni bir alışveriş kaydı ekler ve bağlı vergi, ödeme planı, taksitleri otomatik oluşturur.";
    public static final String GET_ALISVERIS_SUMMARY = "Alışverişi ID'ye göre getir";
    public static final String GET_ALISVERIS_DESC = "Verilen ID'ye sahip alışveriş kaydını getirir.";
    public static final String UPDATE_ALISVERIS_SUMMARY = "Alışveriş kaydını güncelle";
    public static final String UPDATE_ALISVERIS_DESC = "Alışveriş verisini ve ilişkili vergi, ödeme, taksit verilerini günceller.";
    public static final String DELETE_ALISVERIS_SUMMARY = "Alışveriş kaydını sil";
    public static final String DELETE_ALISVERIS_DESC = "Verilen ID'ye sahip alışverişi ve ilişkili kayıtları siler.";

    // Borç
    public static final String GET_BORC_SUMMARY = "Borç bilgisini ID ile getir";
    public static final String GET_BORC_DESC = "Verilen ID'ye sahip borç kaydını döner.";

    // Ödeme
    public static final String TAKSIT_ODE_SUMMARY = "Taksit öde";
    public static final String TAKSIT_ODE_DESC = "Verilen bilgilere göre bir taksit ödemesi gerçekleştirir.";
    public static final String TAKSIT_ODEME_BILGISI_SUMMARY = "Taksit ödeme bilgisi getir";
    public static final String TAKSIT_ODEME_BILGISI_DESC = "Taksit numarası ve ödeme türüne göre ödeme bilgisini döner.";

    // Auth işlemleri
    public static final String LOGIN_SUMMARY = "Kullanıcı girişi";
    public static final String LOGIN_DESC = "Email ve şifre ile giriş yaparak JWT token alır";
    public static final String REFRESH_TOKEN_SUMMARY = "Token yenileme";
    public static final String REFRESH_TOKEN_DESC = "Refresh token kullanarak yeni bir access token alır";
    public static final String REGISTER_SUMMARY = "Kullanıcı kaydı";
    public static final String REGISTER_DESC = "Yeni kullanıcı kaydı oluşturur";
    public static final String UPDATE_PROFILE_SUMMARY = "Profil güncelleme";
    public static final String UPDATE_PROFILE_DESC = "Giriş yapmış kullanıcının kendi bilgilerini günceller";

    private SwaggerConstants() {
        throw new UnsupportedOperationException("Bu bir yardımcı sınıftır ve nesnesi oluşturulamaz!");
    }
}
