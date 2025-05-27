const Label = ({ children, htmlFor, className = "" }) => {
  return (
    <label htmlFor={htmlFor} className={`block mb-2 font-bold text-gray-600 tracking-wide text-sm focus-within:text-blue-600 ${className}`}>
      {children}
    </label>
  );
};

export default Label; 