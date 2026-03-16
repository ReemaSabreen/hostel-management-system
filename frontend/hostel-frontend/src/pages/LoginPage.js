import { useState } from "react";
import { login } from "../services/authService";
import { useNavigate } from "react-router-dom";

function LoginPage(){

const navigate = useNavigate();

const [username,setUsername] = useState("");
const [password,setPassword] = useState("");

const handleLogin = async () => {
   


  try{

    const res = await login(username,password);
   const token = res.data.token;

localStorage.setItem("token", token);
localStorage.setItem("role", res.data.role);
localStorage.setItem("userId", res.data.userId);

    
    if(res.data.studentId){
    localStorage.setItem("studentId",res.data.studentId);
}
   

    navigate("/dashboard");

  }
  catch(err){
    alert("Login failed");
  }

};

return(

<div className="auth-container">
  <div className="auth-card">
    

<h3 className="auth-title">Login</h3>

<input
className="input-field"
placeholder="Username"
onChange={(e)=>setUsername(e.target.value)}
/>

<input
className="input-field"
type="password"
placeholder="Password"
onChange={(e)=>setPassword(e.target.value)}
/>

<button
 className="primary-btn"
onClick={handleLogin}
>
Login
</button>
<div className="auth-links">

<p>
  <a href="/register">Register</a>
</p>

<p>
<a href="/forgot-password">Forgot Password?</a>
</p>
</div>
</div>
</div>

);

}

export default LoginPage;