export const validateRegisterForm = (form) => {
  const errors = {};
  // TCKN: 11 haneli, sadece rakam
  if (!/^[0-9]{11}$/.test(form.tckn)) {
    errors.tckn = 'TCKN 11 haneli ve sadece rakamlardan oluşmalıdır.';
  }
  if (!form.ad.trim()) errors.ad = 'Ad boş olamaz.';
  if (!form.soyad.trim()) errors.soyad = 'Soyad boş olamaz.';
  if (!form.email.trim()) errors.email = 'E-posta boş olamaz.';
  else if (!/^\S+@\S+\.\S+$/.test(form.email)) errors.email = 'Geçerli bir e-posta adresi giriniz.';
  if (!form.sifre) errors.sifre = 'Şifre boş olamaz.';
  else if (form.sifre.length < 4 || form.sifre.length > 16) errors.sifre = 'Şifre 4-16 karakter arası olmalıdır.';
  if (!form.yas) errors.yas = 'Yaş boş olamaz.';
  else if (Number(form.yas) < 18) errors.yas = '18 yaşından küçük kullanıcı kayıt olamaz.';
  else if (Number(form.yas) > 150) errors.yas = 'Yaş 150\'den büyük olamaz.';
  if (!form.cinsiyet) errors.cinsiyet = 'Cinsiyet seçilmelidir.';
  if (!form.meslek) errors.meslek = 'Meslek seçilmelidir.';
  if (!form.maas) errors.maas = 'Maaş boş olamaz.';
  else if (Number(form.maas) <= 0) errors.maas = 'Maaş sıfırdan büyük olmalıdır.';
  return errors;
}; 