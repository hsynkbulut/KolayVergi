const Input = ({ error, className = '', ...props }) => {
  return (
    <input
      {...props}
      className={`border rounded-lg px-4 py-2 w-full text-base bg-white focus:outline-none focus:ring-2 focus:ring-blue-400 transition-all duration-150 placeholder-gray-400 disabled:bg-gray-100 disabled:cursor-not-allowed ${error ? 'border-red-500 focus:ring-red-400' : 'border-gray-300'} ${className}`}
    />
  );
};

export default Input; 