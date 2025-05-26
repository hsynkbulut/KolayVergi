export const validateLoginForm = (form) => {
  const errors = {};
  if (!form.email.trim()) errors.email = 'E-posta boş olamaz.';
  else if (!/^\S+@\S+\.\S+$/.test(form.email)) errors.email = 'Geçerli bir e-posta adresi giriniz.';
  if (!form.password) errors.password = 'Şifre boş olamaz.';
  return errors;
}; 