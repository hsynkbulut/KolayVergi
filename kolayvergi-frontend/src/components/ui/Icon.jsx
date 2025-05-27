import * as FiIcons from 'react-icons/fi';

// Gerçek projede bir ikon kütüphanesi ile entegre edilebilir
const Icon = ({ name, className = "w-5 h-5 text-current" }) => {
  const IconComponent = FiIcons[name] || FiIcons.FiCircle;
  return <IconComponent className={className} />;
};

export default Icon; 