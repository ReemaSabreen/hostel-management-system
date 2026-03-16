import { useState } from "react";
import { register } from "../services/authService";
import { useNavigate } from "react-router-dom";

function RegisterPage() {

  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [passwordHash, setPassword] = useState("");
  const [role, setRole] = useState("STUDENT");

  const handleRegister = async () => {

    try {

      await register({
        username,
        email,
        passwordHash,
        role
      });

      alert("User registered successfully");

      navigate("/");

    } catch (error) {
      alert("Registration failed");
    }
  };

  return (

    <div className="auth-container">
      <div className="auth-card">

      <h3 className="auth-title">Register</h3>

      <input
        className="input-field"
        placeholder="Username"
        onChange={(e) => setUsername(e.target.value)}
      />

      <input
      className="input-field"
        placeholder="Email"
        onChange={(e) => setEmail(e.target.value)}
      />

      <input
      className="input-field"
        type="password"
        placeholder="Password"
        onChange={(e) => setPassword(e.target.value)}
      />

      <select
        className="input-field"
        onChange={(e) => setRole(e.target.value)}
      >

        <option value="ADMIN">ADMIN</option>
        <option value="WARDEN">WARDEN</option>
        <option value="STUDENT">STUDENT</option>
        <option value="MESS_MANAGER">MESS_MANAGER</option>

      </select>

      <button
        className="primary-btn"
        onClick={handleRegister}
      >
        Register
      </button>
      <div className="auth-links">
          <p>
            <a href="/">Back to Login</a>
          </p>
        </div>
       </div>
    </div>
  );
}

export default RegisterPage;