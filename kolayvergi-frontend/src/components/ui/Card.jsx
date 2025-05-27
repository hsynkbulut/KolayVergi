const Card = ({ children, className = "" }) => {
  return (
    <div className={`bg-white shadow-lg rounded-2xl p-6 transition-transform duration-200 hover:scale-[1.02] ${className}`}>
      {children}
    </div>
  );
};

export default Card; 